package com.baby;

import com.baby.controller.FileManager;
import com.baby.model.BabyData;

public class Main {
    public static void main(String[] args) {
        try {
            BabyData data1 = FileManager.loadFrom("/Users/matiosb/Personal/gayane-data.abt");
            System.out.println("Records loaded: " + data1.getRecords().size());

            BabyData data2 = FileManager.loadFrom("/Users/matiosb/Personal/matios-data.abt");
            System.out.println("Records loaded: " + data2.getRecords().size());

            data1.merge(data2);
            System.out.println("Records after merge: " + data1.getRecords().size());

            FileManager.saveTo(data1, "/Users/matiosb/Personal/merged-data.abt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
