import java.util.ArrayList;

public class Controller {
    ArrayList<Device> d = new ArrayList <>();
    ArrayList<Firmware> f = new ArrayList<>();
    ArrayList<Patient> p = new ArrayList<>();

    public Controller() {
        for (int i = 0; i < 3; i++) {
            d.add(new Device("SLB-0" + (i+1)));
            f.add(new Firmware("FW-0" + (i+1)));
            p.add(new Patient("User-0" + (i+1)));
        }
    }

    public ArrayList<Device> getDevices() {
        return d;
    }

    public ArrayList<String> getDevicesNames() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < d.size(); i++) {
            names.add(d.get(i).getName());
        }
        return names;
    }

    public ArrayList<Firmware> getFirmwares() {
        return f;
    }

    public String[] getFirmwareNames() {
        String[] names = new String[f.size()];
        for (int i = 0; i < f.size(); i++) {
            names[i] = f.get(i).getName();
        }
        return names;
    }

    public ArrayList<Patient> getPatients() {
        return p;
    }

    public String[] getPatientNames() {
        String[] names = new String[p.size()];
        for (int i = 0; i < p.size(); i++) {
            names[i] = p.get(i).getName();
        }
        return names;
    }

    public boolean newDeviceWithFirmware(String name, Firmware f) {
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).getName().equals(name) && d.get(i).getFirmware().getName().equals(f.getName()))
                return false;
            else if (d.get(i).getName().equals(name)) {
                d.get(i).setFirmware(f);
            }
        }
        return true;
    }

    public boolean updateFirmwareOfDevice (String name, Firmware f) {
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).getName().equals(name)) {
                d.get(i).setFirmware(f);
                return true;
            }
        }
        return false;
    }

    public boolean newDeviceWithPatient(String name, Patient p) {
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).getName().equals(name) && d.get(i).hasPatient(p.getName()))
                return false;
            else if (d.get(i).getName().equals(name)) {
                d.get(i).addPatient(p);
            }
        }
        return true;
    }

    public boolean dissociatePatientFromDevice(String name, Patient p) {
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).getName().equals(name)) {
                if (d.get(i).deletePatient(p)) {
                    return true;
                }
            }

        }
        return false;
    }
}
