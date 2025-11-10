package com.example.hospital.controller;

import com.example.hospital.model.Patient;
import com.example.hospital.service.JsonService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final JsonService<Patient> json = new JsonService<>("patients.json", new TypeReference<>(){});
    private final List<Patient> patients = json.load();

    @GetMapping public List<Patient> getAll() { return patients; }

    @PostMapping
    public Patient add(@RequestBody Patient p) {
        p.setId((long) (patients.size() + 1));
        patients.add(p); json.save(patients);
        return p;
    }
}
