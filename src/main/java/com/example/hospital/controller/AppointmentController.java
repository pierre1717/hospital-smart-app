package com.example.hospital.controller;

import com.example.hospital.model.Appointment;
import com.example.hospital.service.JsonService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final JsonService<Appointment> json = new JsonService<>("appointments.json", new TypeReference<>(){});
    private final List<Appointment> appointments = json.load();

    private String analyze(String s) {
        if (s == null) return "Analyse non disponible";
        s = s.toLowerCase();
        if (s.contains("fièvre") || s.contains("toux")) return "Suspicion d'infection respiratoire";
        if (s.contains("maux de tête") || s.contains("fatigue")) return "Possible migraine ou surmenage";
        if (s.contains("nausée") || s.contains("vomissement")) return "Trouble digestif suspecté";
        if (s.contains("douleur") && s.contains("poitrine")) return "Risque cardiaque – examen urgent recommandé";
        return "Aucun diagnostic précis – consulter un médecin";
    }

    @GetMapping public List<Appointment> getAll() { return appointments; }

    @PostMapping
    public Appointment add(@RequestBody Appointment a) {
        a.setId((long) (appointments.size() + 1));
        a.setDiagnosis(analyze(a.getSymptoms()));
        appointments.add(a);
        json.save(appointments);
        return a;
    }
}
