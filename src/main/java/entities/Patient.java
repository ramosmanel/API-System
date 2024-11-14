package entities;

public class Patient {
    private String patientName;
    private String dateOfBirth;

    public Patient(String patientName, String dateOfBirth) {
        this.patientName = patientName;
        this.dateOfBirth = dateOfBirth;
    }

    public Patient() {
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
