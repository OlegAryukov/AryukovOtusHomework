package ru.aryukov;

/**
 * Created by dev on 13.07.17.
 */
enum  ObjectTypes {

    LONG("java.lang.Long"),
    STRING("java.lang.String"),
    INT("java.lang.Integer");

    private final String fullName;

    public static ObjectTypes byCode(String fullName) {
        return ObjectTypes.valueOf(fullName);
    }

    ObjectTypes(String sysCode) {
        this.fullName = sysCode;
    }

    public String getFullName() {
        return fullName;
    }
}
