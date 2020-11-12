package org.microdeaf.common.enums;

public enum ActiveType {

    /**
     * @param value
     */

    ENABLED(1),
    DISABLE(2),
    /**
     * در انتظار تأئيد
     */
    WAITE(3);

    private ActiveType(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        return String.valueOf(value);
    }
}
