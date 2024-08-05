package com.javaproject.gestion_immobiliere.utils;

public class FileNameUtils {

    public static String cleanFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }
}
