import java.util.ArrayList;

public class Device {
    private String name;
    private Firmware firmware;
    private ArrayList<Patient> patient;

    public Device(String name) {
        this.name = name;
        this.firmware = new Firmware("");
        this.patient = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean hasPatient(String name) {
        for (int i = 0; i < this.patient.size(); i++) {
            if (this.patient.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean addPatient(Patient p) {
        for (int i = 0; i < this.patient.size(); i++) {
            if (this.patient.get(i).getName().equals(p.getName())) {
                return false;
            }
        }
        this.patient.add(p);
        return true;
    }

    public boolean deletePatient(Patient p) {
        for (int i = 0; i < this.patient.size(); i++) {
            if (this.patient.get(i).getName().equals(p.getName())) {
                this.patient.remove(i);
                return true;
            }
        }
        return false;
    }

    public Firmware getFirmware() {
        return firmware;
    }

    public void setFirmware(Firmware f) {
        this.firmware = f;
    }
}
