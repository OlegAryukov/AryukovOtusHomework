package ru.aryukov.ORM;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oaryukov on 09.06.2017.
 */
public class PersistanceUtil {
    /**
     * Возвращает название таблицы из аннотации
     *
     * @param clazz тип передаваемого класса
     * @return database String.
     */
    public static String defineTable(Class clazz) {
        String table;

        if (clazz.isAnnotationPresent(Entity.class)) {
            table = clazz.getAnnotation(Entity.class).toString();
        } else {
            throw new IllegalStateException(clazz.getName() + " must be @Entity");
        }
        return table.toUpperCase();
    }

    /**
     * Возвращает названия и типы полей
     *
     * @param clazz тип передаваемого класса
     * @return oracle Map.
     */
    public static Map<Integer, Class<?>> defineFields(Class<?> clazz) {
        Map<Integer, Class<?>> map = new HashMap<Integer, Class<?>>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                map.put(field.getAnnotation(Id.class).hashCode(), Long.class);
            } else if (field.isAnnotationPresent(Column.class)) {
                if(field.getType().equals(String.class)){
                    map.put(field.getAnnotation(Column.class).hashCode(), String.class);
                }else if(field.getType().equals(Integer.class)){
                    map.put(field.getAnnotation(Column.class).hashCode(), Integer.class);
                }
            }
//            } else if (field.isAnnotationPresent(OraDate.class)) {
//                map.put(field.getAnnotation(OraDate.class).value(), Date.class);
//            } else if (field.isAnnotationPresent(OraTimestamp.class)) {
//                map.put(field.getAnnotation(OraTimestamp.class).value(), Timestamp.class);
//            } else if (field.isAnnotationPresent(OraBigDecimal.class)) {
//                map.put(field.getAnnotation(OraBigDecimal.class).value(), BigDecimal.class);
//            } else if (field.isAnnotationPresent(OraClob.class)) {
//                map.put(field.getAnnotation(OraClob.class).value(), Clob.class);
//            } else if (field.isAnnotationPresent(OraBlob.class)) {
//                map.put(field.getAnnotation(OraBlob.class).value(), Blob.class);
//            }
        }

        return map;
    }

    public static Map<Integer, String> defineFieldNames(Class<?> clazz) {
        Map<Integer, String> map = new HashMap<Integer, String>();

        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.isAnnotationPresent(OraInteger.class)) {
//                map.put(field.getAnnotation(OraInteger.class).value(),
//                        field.getAnnotation(OraInteger.class).field());
//            } else if (field.isAnnotationPresent(OraString.class)) {
//                map.put(field.getAnnotation(OraString.class).value(),
//                        field.getAnnotation(OraString.class).field());
//            } else if (field.isAnnotationPresent(OraDate.class)) {
//                map.put(field.getAnnotation(OraDate.class).value(),
//                        field.getAnnotation(OraDate.class).field());
//            } else if (field.isAnnotationPresent(OraTimestamp.class)) {
//                map.put(field.getAnnotation(OraTimestamp.class).value(),
//                        field.getAnnotation(OraTimestamp.class).field());
//            } else if (field.isAnnotationPresent(OraBigDecimal.class)) {
//                map.put(field.getAnnotation(OraBigDecimal.class).value(),
//                        field.getAnnotation(OraBigDecimal.class).field());
//            } else if (field.isAnnotationPresent(OraClob.class)) {
//                map.put(field.getAnnotation(OraClob.class).value(),
//                        field.getAnnotation(OraClob.class).field());
//            } else if (field.isAnnotationPresent(OraBlob.class)) {
//                map.put(field.getAnnotation(OraBlob.class).value(),
//                        field.getAnnotation(OraBlob.class).field());
//            }
//        }
//
//        return map;
//    }
//
//    public static Object getSqlValue(OracleResultSet orset, Class<?> oraType, Integer i) throws SQLException {
//        if (oraType.getName().equals("java.lang.Integer")) {
//            return orset.getInt(i);
//        } else if (oraType.getName().equals("java.lang.String")) {
//            return orset.getString(i);
//        } else if (oraType.getName().equals("java.sql.Date")) {
//            return orset.getDate(i);
//        } else if (oraType.getName().equals("java.sql.Timestamp")) {
//            return orset.getTimestamp(i);
//        } else if (oraType.getName().equals("java.math.BigDecimal")) {
//            return orset.getBigDecimal(i);
//        } else if (oraType.getName().equals("java.sql.Clob")) {
//            return orset.getClob(i);
//        } else if (oraType.getName().equals("java.sql.Blob")) {
//            return orset.getBlob(i);
//        } else {
//            return null;
//        }
//    }
//
//    public static void setSqlValue(OraclePreparedStatement stmt, Class<?> oraType, Integer i, Object val) throws SQLException, Exception {
//        if (oraType.getName().equals("java.lang.Integer")) {
//            stmt.setInt(i, (Integer) (val == null ? 0 : val));
//        } else if (oraType.getName().equals("java.lang.String")) {
//            stmt.setString(i, (String) val);
//        } else if (oraType.getName().equals("java.sql.Date")) {
//            stmt.setDate(i, (Date) val);
//        } else if (oraType.getName().equals("java.sql.Timestamp")) {
//            stmt.setTimestamp(i, (Timestamp) val);
//        } else if (oraType.getName().equals("java.math.BigDecimal")) {
//            stmt.setBigDecimal(i, (BigDecimal) val);
//        } else if (oraType.getName().equals("java.sql.Clob")) {
//            stmt.setClob(i, (Clob) val);
//        } else if (oraType.getName().equals("java.sql.Blob")) {
//            stmt.setBlob(i, (Blob) val);
//        } else {
//            throw new Exception("error in setSqlValue. Unknown type: " + oraType.getName());
//        }
//    }
//
//    public static void setStatementValue(OracleCallableStatement ocs, String key, Class<?> oraType, Integer i, Object val) throws SQLException, Exception {
//        if (key.equals("out")) {
//            if (oraType.getName().equals("java.lang.Integer")) {
//                ocs.registerOutParameter(1, OracleTypes.INTEGER);
//            } else if (oraType.getName().equals("java.lang.String")) {
//                ocs.registerOutParameter(1, OracleTypes.VARCHAR);
//            } else if (oraType.getName().equals("java.sql.Date")) {
//                ocs.registerOutParameter(1, OracleTypes.DATE);
//            } else if (oraType.getName().equals("java.sql.Timestamp")) {
//                ocs.registerOutParameter(1, OracleTypes.TIMESTAMP);
//            } else if (oraType.getName().equals("java.math.BigDecimal")) {
//                ocs.registerOutParameter(1, OracleTypes.NUMBER);
//            } else if (oraType.getName().equals("java.sql.Clob")) {
//                ocs.registerOutParameter(1, OracleTypes.CLOB);
//            } else if (oraType.getName().equals("java.sql.Blob")) {
//                ocs.registerOutParameter(1, OracleTypes.BLOB);
//            } else if (oraType.getName().equals("java.sql.Array")) {
//                ocs.registerOutParameter(1, OracleTypes.ARRAY);
//            } else {
//                throw new Exception("error in setStatementValue. Unknown out type: " + oraType.getName());
//            }
//        } else {
//            if (oraType.getName().equals("java.lang.Integer")) {
//                ocs.setInt(i, (Integer) val);
//            } else if (oraType.getName().equals("java.lang.String")) {
//                ocs.setString(i, (String) val);
//            } else if (oraType.getName().equals("java.sql.Date")) {
//                ocs.setDate(i, (Date) val);
//            } else if (oraType.getName().equals("java.sql.Timestamp")) {
//                ocs.setTimestamp(i, (Timestamp) val);
//            } else if (oraType.getName().equals("java.math.BigDecimal")) {
//                ocs.setBigDecimal(i, (BigDecimal) val);
//            } else if (oraType.getName().equals("java.sql.Clob")) {
//                ocs.setClob(i, (Clob) val);
//            } else if (oraType.getName().equals("java.sql.Blob")) {
//                ocs.setBlob(i, (Blob) val);
//            } else if (oraType.getName().equals("java.sql.Array")) {
//                ocs.setArray(i, (Array) val);
//            } else {
//                throw new Exception("error in setStatementValue. Unknown type: " + oraType.getName());
//            }
//        }
//    }
//
//    public static Object getStatementValue(OracleCallableStatement ocs, Class<?> oraType, Integer i) throws SQLException, Exception {
//        if (oraType.getName().equals("java.lang.Integer")) {
//            return ocs.getInt(1);
//        } else if (oraType.getName().equals("java.lang.String")) {
//            return ocs.getString(i);
//        } else if (oraType.getName().equals("java.util.Date")) {
//            return ocs.getDate(1);
//        } else if (oraType.getName().equals("java.sql.Timestamp")) {
//            return ocs.getTimestamp(1);
//        } else if (oraType.getName().equals("java.math.BigDecimal")) {
//            return ocs.getBigDecimal(1);
//        } else if (oraType.getName().equals("java.sql.Clob")) {
//            return ocs.getClob(1);
//        } else if (oraType.getName().equals("java.sql.Blob")) {
//            return ocs.getBlob(1);
//        } else if (oraType.getName().equals("java.sql.Array")) {
//            return ocs.getArray(1);
//        } else {
//            throw new Exception("error in setStatementValue. Unknown out type: " + oraType.getName());
//        }

    }
