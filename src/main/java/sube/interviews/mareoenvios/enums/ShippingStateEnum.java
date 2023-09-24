package sube.interviews.mareoenvios.enums;

public enum ShippingStateEnum {

    INITIAL("Initial"),
    SEND_TO_MAIL("Send to mail"),
    IN_TRAVEL("In travel"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

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
