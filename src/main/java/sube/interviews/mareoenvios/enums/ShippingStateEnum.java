package sube.interviews.mareoenvios.enums;

public enum ShippingStateEnum {

    INITIAL("Inicial"),
    SEND_TO_MAIL("Entregado a correo"),
    IN_TRAVEL("En camino"),
    DELIVERED("Entregado"),
    CANCELLED("Cancelado");

    private final String value;

    ShippingStateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ShippingStateEnum getState( String value ) throws IllegalArgumentException{
        for (ShippingStateEnum state : ShippingStateEnum.values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException();
    }
}
