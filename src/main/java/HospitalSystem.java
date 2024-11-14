import entities.Appointment;
import entities.Doctor;
import entities.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalSystem {
    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        HospitalSystem system = new HospitalSystem();
        system.displayMenu();
    }

    private void displayMenu() {
        int option;
        do {
            System.out.println("1. Register Patient");
            System.out.println("2. Register Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. List Appointments");
            System.out.println("5. View Patient's Appointment History");
            System.out.println("6. List Patients");
            System.out.println("7. List Doctors");
            System.out.println("8. Exit");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> registerPatient();
                case 2 -> registerDoctor();
                case 3 -> scheduleAppointment();
                case 4 -> listAppointments();
                case 5 -> viewPatientHistory();
                case 6 -> listPatients();
                case 7 -> listDoctors();
                case 8 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option. Please try again.");
            }
            clearConsole();
        } while (option != 8);
    }

    private void registerPatient() {
        System.out.println("Enter patient's name:");
        String name = scanner.nextLine();
        System.out.println("Enter patient's birthdate (dd/MM/yyyy or yyyy-MM-dd):");
        String birthdate = scanner.nextLine();

        if (patients.stream().anyMatch(p -> p.getPatientName().equalsIgnoreCase(name))) {
            System.out.println("Patient is already registered!");
            return;
        }

        patients.add(new Patient(name, birthdate));
        System.out.println("Patient registered successfully!");
    }

    private void registerDoctor() {
        System.out.println("Enter doctor's name:");
        String name = scanner.nextLine();
        System.out.println("Enter doctor's specialty:");
        String specialty = scanner.nextLine();

        if (doctors.stream().anyMatch(d -> d.getNameDoctor().equalsIgnoreCase(name))) {
            System.out.println("Doctor is already registered!");
            return;
        }

        doctors.add(new Doctor(name, specialty));
        System.out.println("Doctor registered successfully!");
    }

    private void scheduleAppointment() {
        System.out.println("Enter patient's name:");
        String patientName = scanner.nextLine();
        System.out.println("Enter doctor's name:");
        String doctorName = scanner.nextLine();

        Patient patient = patients.stream().filter(p -> p.getPatientName().equalsIgnoreCase(patientName)).findFirst().orElse(null);
        Doctor doctor = doctors.stream().filter(d -> d.getNameDoctor().equalsIgnoreCase(doctorName)).findFirst().orElse(null);

        if (patient == null || doctor == null) {
            System.out.println("Patient or doctor not found.");
            return;
        }

        System.out.println("Enter appointment date (dd/MM/yyyy or yyyy-MM-dd):");
        String appointmentDateStr = scanner.nextLine();
        LocalDate appointmentDate;
        try {
            appointmentDate = LocalDate.parse(appointmentDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            try {
                appointmentDate = LocalDate.parse(appointmentDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date.");
                return;
            }
        }

        if (appointmentDate.isAfter(LocalDate.now())) {
            System.out.println("Appointment date cannot be in the future.");
            return;
        }

        System.out.println("Enter appointment fee:");
        double appointmentFee = scanner.nextDouble();
        scanner.nextLine();

        if (appointmentFee < 0) {
            System.out.println("Appointment fee cannot be negative.");
            return;
        }

        appointments.add(new Appointment(patient, doctor, appointmentDate, appointmentFee));
        System.out.println("Appointment scheduled successfully!");
    }

    private void listAppointments() {
        for (Appointment appointment : appointments) {
            System.out.printf("Patient: %s, Doctor: %s, Date: %s, Fee: $ %.2f\n",
                    appointment.getPatient().getPatientName(),
                    appointment.getDoctor().getNameDoctor(),
                    appointment.getAppointmentDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    appointment.getAppointmentFee()
            );
        }
    }

    private void viewPatientHistory() {
        System.out.println("Enter patient's name:");
        String patientName = scanner.nextLine();
        List<Appointment> patientAppointments = appointments.stream()
                .filter(a -> a.getPatient().getPatientName().equalsIgnoreCase(patientName))
                .toList();

        if (patientAppointments.isEmpty()) {
            System.out.println("No appointments found for the patient.");
            return;
        }

        double totalSpent = 0;
        for (Appointment appointment : patientAppointments) {
            System.out.printf("Date: %s, Doctor: %s, Fee: $ %.2f\n",
                    appointment.getAppointmentDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    appointment.getDoctor().getNameDoctor(),
                    appointment.getAppointmentFee()
            );
            totalSpent += appointment.getAppointmentFee();
        }
        System.out.printf("Total spent: $ %.2f\n", totalSpent);
    }

    private void listPatients() {
        patients.forEach(p -> System.out.println("Name: " + p.getPatientName() + ", Birthdate: " + p.getDateOfBirth()));
    }

    private void listDoctors() {
        doctors.forEach(d -> System.out.println("Name: " + d.getNameDoctor() + ", Specialty: " + d.getSpecialtyDoctor()));
    }

    private void clearConsole() {
        System.out.println("*".repeat(10));
        System.out.println();
    }
}
