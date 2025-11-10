package com.example.hospital.model;

public class Appointment {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String date;
    private String symptoms;
    private String diagnosis;

    public Appointment() {}
    public Appointment(Long id, Long patientId, Long doctorId, String date, String symptoms, String diagnosis) {
        this.id = id; this.patientId = patientId; this.doctorId = doctorId;
        this.date = date; this.symptoms = symptoms; this.diagnosis = diagnosis;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
}
