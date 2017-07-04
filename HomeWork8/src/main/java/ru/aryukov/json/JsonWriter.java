package ru.aryukov.json;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Created by oaryukov on 02.07.2017.
 */
public class JsonWriter {
    private final StringBuilder jsonStr;
    private Field field;

    public JsonWriter() {
        jsonStr = new StringBuilder();
    }

    public String convertToJason(Object o) throws IllegalAccessException {
        return "{" + convert(o) + "}";
    }

    private String convert(Object o) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Field[] objFields = o.getClass().getDeclaredFields();

        for (Field field:objFields) {
            if (field.getType().isPrimitive() || field.getType().getCanonicalName().contains("java.lang")){
                sb.append(processPrimitive(field, getFieldContent(field, o)));
                sb.append(",");
            } else if (field.getType().isArray()){
                sb.append("\"").append(field.getName()).append("\":");
                sb.append("[");
                sb.append(getFieldContent(field, o));
                sb.append("]");
                sb.append(",");
            } else {
                sb.append("\"").append(field.getName()).append("\":");
                sb.append("{");
                sb.append(convert(getFieldContent(field,o)));
                sb.append("}");
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.toString().length()-1);
        return sb.toString();
    }

    private String processPrimitive(Field field, Object o) throws IllegalAccessException {
        field.setAccessible(true);
        final String str = field.getType().getCanonicalName().equals("java.lang.String") ? "\""
                + o + "\"" : o.toString();
        return "\"" + field.getName() + "\":" + str;
    }

    private Object getFieldContent(Field field, Object o){
        StringBuilder sb = new StringBuilder();
        try {
            field.setAccessible(true);
            final Object obj = field.get(o);
            final String fieldType = obj.getClass().getCanonicalName();
            if(field.getType().isArray()){
                switch (fieldType){
                    case "char" :
                        for(int i = 0; i < Array.getLength(obj); i++){
                            sb.append(Array.get(obj, i));
                            sb.append(",");
                        }
                        sb.deleteCharAt(sb.toString().length()-1);
                        return sb.toString();
                    case "java.lang.String" : {
                        for(int i = 0; i < Array.getLength(obj); i++){
                            sb.append("\"").append(Array.get(obj, i)).append("\"");
                            sb.append(",");
                        }
                        sb.deleteCharAt(sb.toString().length()-1);
                        return sb.toString();
                    }
                    default:
                        for(int i = 0; i < Array.getLength(obj); i++){
                            if(field.getType().isPrimitive()){
                                sb.append(Array.get(obj, i));
                                sb.append(",");
                            } else {
                                sb.append("{");
                                sb.append(convert(Array.get(obj, i)));
                                sb.append("}");
                                sb.append(",");
                            }
                        }
                        sb.deleteCharAt(sb.toString().length()-1);
                        return sb.toString();
                }
            }
            return field.get(o);
        }catch (IllegalAccessException e){
            e.printStackTrace();
            return "ERROR";
        }
    }
}
