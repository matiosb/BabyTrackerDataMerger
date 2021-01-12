package com.baby.model;

import lombok.Data;

@Data
public class BabyRecord {
    private String toDate;
    private double amount;
    private String details;
    private String category;
    private String unit;
    private String fromDate;
    private String subtype;
    private String type;
}
