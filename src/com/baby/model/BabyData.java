package com.baby.model;

import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class BabyData {
    private String gender;
    private String name;
    private String[] notes;
    private Set<BabyRecord> records;
    private String[] sessions;
    private int version;

    public void merge(BabyData other) {
        this.records = Stream.concat(this.records.stream(), other.records.stream()).collect(Collectors.toSet());
    }

}
