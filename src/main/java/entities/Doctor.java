package entities;

public class Doctor {
    private String nameDoctor;
    private String specialtyDoctor;

    public Doctor(String nameDoctor, String specialtyDoctor) {
        this.nameDoctor = nameDoctor;
        this.specialtyDoctor = specialtyDoctor;
    }

    public Doctor() {
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getSpecialtyDoctor() {
        return specialtyDoctor;
    }

    public void setSpecialtyDoctor(String specialtyDoctor) {
        this.specialtyDoctor = specialtyDoctor;
    }
}
