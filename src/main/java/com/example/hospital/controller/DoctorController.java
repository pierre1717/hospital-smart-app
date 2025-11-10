package com.example.hospital.controller;

import com.example.hospital.model.Doctor;
import com.example.hospital.service.JsonService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final JsonService<Doctor> json = new JsonService<>("doctors.json", new TypeReference<>(){});
    private final List<Doctor> doctors = json.load();

    @GetMapping public List<Doctor> getAll() { return doctors; }

    @PostMapping
    public Doctor add(@RequestBody Doctor d) {
        d.setId((long) (doctors.size() + 1));
        doctors.add(d); json.save(doctors);
        return d;
    }
}
