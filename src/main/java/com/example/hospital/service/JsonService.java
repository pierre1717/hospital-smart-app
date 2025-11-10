package com.example.hospital.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonService<T> {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file;
    private final TypeReference<List<T>> type;

    public JsonService(String filename, TypeReference<List<T>> type) {
        this.file = new File(filename);
        this.type = type;
    }

    public List<T> load() {
        try {
            if (!file.exists()) return new java.util.ArrayList<>();
            return mapper.readValue(file, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

    public void save(List<T> list) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
