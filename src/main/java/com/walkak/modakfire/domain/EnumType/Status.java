package com.walkak.modakfire.domain.EnumType;

public enum Status {
    RAISING,WAITING,DELIVERING,FINISHED;

    public static Status fromOrdinal(int ordinal) {
        for (Status status : Status.values()) {
            if (status.ordinal() == ordinal) {
                return status;
            }
        }
        throw new IllegalArgumentException("No constant with ordinal " + ordinal + " found");
    }
}
