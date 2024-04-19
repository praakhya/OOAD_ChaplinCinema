package com.pes.chaplincinemabackend.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// ScreeningTime.java
@Getter

public enum ScreeningTime {
    TIMING_1("10:00 am - 1:00 pm"),
    TIMING_2("1:30 pm - 4:00 pm"),
    TIMING_3("4:30 pm - 8:00 pm"),
    TIMING_4("8:30 pm - 12:00 am");

    private final String description;

    ScreeningTime(String description) {
        this.description = description;
    }


    public static String getDescriptionByValue(String value) {
        for (ScreeningTime time : values()) {
            if (time.name().equals(value)) {
                return time.getDescription();
            }
        }
        return null; // Handle case when value is not found
    }

}
