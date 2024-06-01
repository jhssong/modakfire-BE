package com.walkak.modakfire.domain.EnumType;

import lombok.Getter;

@Getter
public enum CenterType {
    WHOLE(0),
    WELFARE(1),
    DISABLED(2),
    CHILD(3),
    ONEPARENT(4),
    HOMELESS(5),
    MENTALCARE(6),
    REHABILITATION(7);

    private final int value;

    CenterType(int value) {
        this.value = value;
    }

    public static CenterType fromValue(int value) {
        for (CenterType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CenterType value: " + value);
    }

}
