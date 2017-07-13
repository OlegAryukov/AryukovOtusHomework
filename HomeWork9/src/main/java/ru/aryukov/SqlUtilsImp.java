package ru.aryukov;

import ru.aryukov.functionalIntfaces.ParamsSetInterface;
import ru.aryukov.functionalIntfaces.ResultSetInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by oaryukov on 10.07.2017.
 */
public class SqlUtilsImp implements SqlUtils {
    private final Connection connection;


    private String tableName = null;
    private List<Field> idList = new ArrayList<>();
    private List<Field> columnsList = new ArrayList<>();
    private List<Field> columnsValueList = new ArrayList<>();



    public SqlUtilsImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }

    @Override
    public void save(Object object) throws Exception {
        getObjectStructureInfo(object);

        final String insert = insert();
        update(insert, ps -> {
            int columnIdx = 0;
            for (final Field field : columnsValueList) {
                final String type = field.getType().getTypeName();
                try {
                    Object value = getFieldValue(field, object);
                    if (value != null) {
                        switch (type) {
                            case "java.lang.Long":
                                ps.setLong(++columnIdx, (Long) value);
                                break;
                            case "java.lang.String":
                                ps.setString(++columnIdx, (String) value);
                                break;
                            case "java.lang.Integer":
                                ps.setInt(++columnIdx, (int) value);
                                break;
                            default:
                                throw new IllegalArgumentException("Unsupported data type:" + type);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        });
    }

    @Override
    public void load(Object object, Map<String, Object> idValues) throws Exception {
        getObjectStructureInfo(object);
        final String select = select();

        queryExecutor(select, paramsSet -> {
            for (int idx = 0; idx < idList.size(); idx++) {
                final Field fieldId = idList.get(idx);
                ObjectTypes type = ObjectTypes.byCode(fieldId.getName());
                try {
                    switch (type) {
                        case LONG:
                            paramsSet.setLong(idx + 1, (Long) idValues.get(fieldId.getName()));
                            break;
                        case "java.lang.String":
                            paramsSet.setString(idx + 1, (String) idValues.get(fieldId.getName()));
                            break;
                        case "java.lang.Integer":
                            paramsSet.setInt(idx + 1, (Integer) idValues.get(fieldId.getName()));
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported data type:" + type);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }

            }
        }, rs -> {
            while (rs.next()) {
                for (final Field field : columnsList) {
                    final Class<?> type = field.getType();
                    try {
                        switch (type.getTypeName()) {
                            case ObjectTypes.LONG.getFullName():
                                setFieldValue(field, object, rs.getLong(field.getName()));
                                break;
                            case "java.lang.String":
                                setFieldValue(field, object, rs.getString(field.getName()));
                                break;
                            case "java.lang.Integer":
                                setFieldValue(field, object, rs.getInt(field.getName()));
                                break;
                            default:
                                throw new IllegalArgumentException("Unsupported data type:" + type);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            }
            return null;
        })
    }

    private Object getFieldValue(Field field, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fieldName = field.getName();
        final String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
        Method method = object.getClass().getMethod(getterName);
        return method.invoke(object);
    }

    private void setFieldValue(Field field, Object object, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fieldName = field.getName();
        final String getterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
        Method method = object.getClass().getMethod(getterName, value.getClass());
        method.invoke(object, value);
    }

    private String insert() {
        final StringBuilder sbPlaces = new StringBuilder();
        for (int idx = 0; idx < columnsValueList.size(); idx++) {
            if (idx > 0) {
                sbPlaces.append(",");
            }
            sbPlaces.append("?");
        }


        final String insertSql = "insert into \"" + tableName +
                "\"(" + columnsValueList.stream().map(Field::getName).collect(Collectors.joining(",")) + ")" +
                "values" + "(" + sbPlaces.toString() + ")";

        System.out.println("insert sql:" + insertSql);
        return insertSql;
    }

    private String select() {
        final String selectSql = "select * from " + tableName +" where 1=1 \n";

        final StringBuilder sb = new StringBuilder();
        for (Field id : idList) {
            sb.append(" and ").append(id.getName()).append(" = ? \n");
        }
        System.out.println("select sql:" + selectSql + sb.toString());
        return selectSql + sb.toString();
    }

    public void update(String sql, ParamsSetInterface params) throws Exception {
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            if (params != null) {
                params.setParams(ps);
            }
            ps.execute();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }

    public <T> T queryExecutor(String sql, ParamsSetInterface params, ResultSetInterface<T> resultSetInterface) throws Exception {
        T result;
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            if (params != null) {
                params.setParams(ps);
            }
            try (ResultSet rs = ps.executeQuery()) {
                result = resultSetInterface.handle(rs);
            }
        }
        return result;
    }

    private void getObjectStructureInfo(Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class clazz = object.getClass();
        Annotation[] annotations = clazz.getAnnotations();

        boolean hasEntity = false;
        for (Annotation annotation: annotations) {
            if (annotation.annotationType().equals(Entity.class)) {
                hasEntity = true;
            }
            if (annotation.annotationType().equals(Table.class)) {
                tableName = ((Table) annotation).name();
            }
        }

        if (!hasEntity) {
            throw new IllegalArgumentException("Entity annotation not found");
        }

        if (tableName==null) {
            throw new IllegalArgumentException("Table name not found");
        }

        for(Field field: object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                columnsList.add(field);
                if (getFieldValue(field,object) != null) {
                    columnsValueList.add(field);
                }
                if (field.isAnnotationPresent(Id.class)) {
                    idList.add(field);
                }
            }
        }
    }
}
