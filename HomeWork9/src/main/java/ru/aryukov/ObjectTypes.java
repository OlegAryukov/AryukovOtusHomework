package ru.aryukov;

/**
 * Created by dev on 13.07.17.
 */
enum  ObjectTypes {

    LONG("java.lang.Long"),
    STRING("java.lang.String"),
    INT("java.lang.Integer");

    private final String fullName;

    public static ObjectTypes byFullName(String fullName) {
        ObjectTypes res = null;
        for (ObjectTypes objectTypes:ObjectTypes.values()) {
            if (objectTypes.getFullName().equals(fullName)){
                res = objectTypes;
            }
        }
        return res;
    }

    ObjectTypes(String fullName/*, String code*/) {
        this.fullName = fullName;
        //this.code = cod
    }

    public String getFullName() {
        return fullName;
    }
}
