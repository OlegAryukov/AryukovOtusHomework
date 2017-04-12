package ru.aryukov.mesurment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static java.lang.System.getProperty;

/**
 * Class for getting object size.
 * Created by olega on 10.04.17.
 */
public class ObjectSizeInstrument {

    /**
     * Constants for primitive types.
     */
    private static final boolean IS_CURRENT_JVM_64_BIT = getProperty("java.vm.name", "32").contains("64");
    private static final int LONG_SIZE = 8;
    private static final int INT_SIZE = 4;
    private static final int BYTE_SIZE = 1;
    private static final int BOOLEAN_SIZE = 1;
    private static final int CHAR_SIZE = 2;
    private static final int SHORT_SIZE = 2;
    private static final int FLOAT_SIZE = 4;
    private static final int DOUBLE_SIZE = 8;
    private static final int ALIGNMENT = 8;

    /**
     * Public method for mesurment.
     * @param o object witch size we wont.
     * @return long - size of object o.
     * @throws IllegalAccessException exception.
     */
    public static long sizeOf(Object o) throws IllegalAccessException {
        System.out.println(IS_CURRENT_JVM_64_BIT);
        return sizeOf(o, IS_CURRENT_JVM_64_BIT);
    }

    /**
     * Public method for mesurment, if we know a bit of our system.
     * @param o object witch size we wont.
     * @param jvm64bit boolean flag, if system is 64bit - true.
     * @return long - size of object o.
     * @throws IllegalAccessException exception.
     */
    public static long sizeOf(Object o, boolean jvm64bit) throws IllegalAccessException {
        return sizeOf(o, jvm64bit, new HashSet<>());
    }

    /**
     * Private method for calculating object size.
     * @param o object.
     * @param jvm64bit boolean flag.
     * @param visited HashSet for nested objects.
     * @return long - size of object o.
     * @throws IllegalAccessException exception.
     */
    private static long sizeOf(Object o, boolean jvm64bit, Set<ObjectWrapper> visited) throws IllegalAccessException {
        if (o == null) {
            return 0;
        }
        int referenceSize = jvm64bit ? 8 : 4;
        int headerSize = jvm64bit ? 16 : 8;
        ObjectWrapper objectWrapper = new ObjectWrapper(o);
        if (visited.contains(objectWrapper)) {
            // We have object reference graph with cycles
            return 0;
        }
        visited.add(objectWrapper);
        long size = headerSize;
        Class clazz = o.getClass();
        if (clazz.isArray()) {
            if (clazz == long[].class) {
                long[] objs = (long[]) o;
                size += objs.length * LONG_SIZE;
            } else if (clazz == int[].class) {
                int[] objs = (int[]) o;
                size += objs.length * INT_SIZE;
            } else if (clazz == byte[].class) {
                byte[] objs = (byte[]) o;
                size += objs.length * BYTE_SIZE;
            } else if (clazz == boolean[].class) {
                boolean[] objs = (boolean[]) o;
                size += objs.length * BOOLEAN_SIZE;
            } else if (clazz == char[].class) {
                char[] objs = (char[]) o;
                size += objs.length * CHAR_SIZE;
            } else if (clazz == short[].class) {
                short[] objs = (short[]) o;
                size += objs.length * SHORT_SIZE;
            } else if (clazz == float[].class) {
                float[] objs = (float[]) o;
                size += objs.length * FLOAT_SIZE;
            } else if (clazz == double[].class) {
                double[] objs = (double[]) o;
                size += objs.length * DOUBLE_SIZE;
            } else {
                Object[] objs = (Object[]) o;
                for (Object obj : objs) {
                    size += sizeOf(obj, jvm64bit, visited) + referenceSize;
                }
            }
            size += INT_SIZE;
        } else {
            List<Field> fields = new ArrayList<>();
            do {
                Field[] classFields = clazz.getDeclaredFields();
                for (Field field : classFields) {
                    if (!Modifier.isStatic(field.getModifiers())) {
                        fields.add(field);
                    }
                }
                clazz = clazz.getSuperclass();
            } while (clazz != null);
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                String fieldType = field.getGenericType().toString();
                switch (fieldType) {
                    case "long":
                        size += LONG_SIZE;
                        break;
                    case "int":
                        size += INT_SIZE;
                        break;
                    case "byte":
                        size += BYTE_SIZE;
                        break;
                    case "boolean":
                        size += BOOLEAN_SIZE;
                        break;
                    case "char":
                        size += CHAR_SIZE;
                        break;
                    case "float":
                        size += FLOAT_SIZE;
                        break;
                    case "double":
                        size += FLOAT_SIZE;
                        break;
                    default:
                        size += sizeOf(field.get(o), jvm64bit, visited) + referenceSize;
                        break;
                }
            }
        }
        if ((size % ALIGNMENT) != 0) {
            size = ALIGNMENT * (size / ALIGNMENT + 1);
        }
        return size;
    }

    /**
     * Class - wrapper for object.
     */
    private static final class ObjectWrapper {

        private final Object object;

        /**
         * Constructor.
         * @param object object.
         */
        ObjectWrapper(Object object) {
            this.object = object;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != ObjectWrapper.class) {
                return false;
            }
            return object == ((ObjectWrapper) obj).object;
        }

        @Override
        public int hashCode() {
            return 141 + System.identityHashCode(object);
        }
    }
}
