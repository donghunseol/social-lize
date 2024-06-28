package com.example.project._core.enums;

public enum ReportResultEnum {

    // 상태 (WAITING : 대기중, REFUSE : 부적합, APPROVAL : 적합)
    WAITING("대기중"),
    REFUSE("부적합"),
    APPROVAL("적합");;

    private final String value;

    ReportResultEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReportResultEnum fromValue(String value) {
        for (ReportResultEnum result : ReportResultEnum.values()) {
            if (result.value.equalsIgnoreCase(value)) {
                return result;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
