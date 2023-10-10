package sube.interviews.mareoenvios.enums;

public enum TaskStateEnum {

    SUCCESS("Éxito"),
    FAILED("Fallido"),
    IN_PROGRESS("En progreso"),
    PENDING("Pendiente");

    private final String value;

    TaskStateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
