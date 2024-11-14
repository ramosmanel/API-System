package entities;

import java.time.LocalDate;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private  LocalDate appointmentDate;
    private double appointmentFee;

    public Appointment(Patient patient, Doctor doctor, LocalDate appointmentDate, double appointmentFee) {
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentFee = appointmentFee;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public double getAppointmentFee() {
        return appointmentFee;
    }
}
