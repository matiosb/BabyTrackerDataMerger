package com.baby.controller;

import com.baby.model.BabyData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.lingala.zip4j.ZipFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileManager {
    private static final String TEMP_DIR_PREFIX = "baby_tracker_";
    private static final String JSON_FILE_NAME = "baby.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<BabyData> TYPE_REF = new TypeReference<>() {};

    public static BabyData loadFrom(String abtFile) throws IOException {
        Path tempDir = Files.createTempDirectory(TEMP_DIR_PREFIX);
        File tempFile = Paths.get(tempDir.toString(), JSON_FILE_NAME).toFile();

        try {
            unzip(abtFile, tempDir.toString());
            return MAPPER.readValue(tempFile, TYPE_REF);
        } finally {
            Files.deleteIfExists(tempFile.toPath());
            Files.deleteIfExists(tempDir);
        }
    }

    public static void saveTo(BabyData babyData, String abtFile) throws IOException {
        Path tempDir = Files.createTempDirectory(TEMP_DIR_PREFIX);
        File tempFile = Paths.get(tempDir.toString(), JSON_FILE_NAME).toFile();

        try {
            MAPPER.writeValue(tempFile, babyData);
            zip(tempFile.toString(), abtFile);
        } finally {
            Files.deleteIfExists(tempFile.toPath());
            Files.deleteIfExists(tempDir);
        }
    }

    private static void unzip(String sourceFile, String destinationDir) throws IOException {
        ZipFile zipFile = new ZipFile(sourceFile);
        zipFile.extractAll(destinationDir);
    }

    private static void zip(String sourceFile, String destinationFile) throws IOException {
        ZipFile zipFile = new ZipFile(destinationFile);
        zipFile.addFile(sourceFile);
    }
}
