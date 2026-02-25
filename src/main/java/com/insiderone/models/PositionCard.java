package com.insiderone.models;

import lombok.*;

@Getter
public class PositionCard {

    private final String title;
    private final String department;
    private final String location;

    public PositionCard(String title, String department, String location) {
        this.title = title;
        this.department = department;
        this.location = location;
    }
}
