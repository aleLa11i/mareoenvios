package sube.interviews.mareoenvios.enums;

public enum TaskStateEnum {

    SUCCESS("Success"),
    FAILED("Failed"),
    IN_PROGRESS("In progress");

    private final String value;

    TaskStateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
