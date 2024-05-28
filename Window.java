import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private JPanel show, buttons;
    private JButton declare, associate, dissociate, updateFirmware;
    private JLabel devices, firmware, patient;
    private Controller c;
    public Window(String titre, Controller c) {
        super(titre);
        this.c = c;
        // panels
        show = new JPanel(new BorderLayout());
        buttons = new JPanel();

        String StringDevices = "Devices: ";
        String StringFirmware = "Firmware: ";
        String StringPatients = "Patient: ";
        // labels
        for (int i = 0; i < c.getDevices().size(); i++) {
            StringDevices += c.getDevices().get(i).getName() + ", ";
        }
        for (int i = 0; i < c.getFirmwares().size(); i++) {
            StringFirmware += c.getFirmwares().get(i).getName() + ", ";
        }
        for (int i = 0; i < c.getPatients().size(); i++) {
            StringPatients += c.getPatients().get(i).getName() + ", ";
        }

        devices = new JLabel(StringDevices);
        firmware = new JLabel(StringFirmware);
        patient = new JLabel(StringPatients);

        // buttons
        declare = new JButton("Déclarer");
        declare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAction(1);
            }
        });
        associate = new JButton( "Associer");
        associate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAction(2);
            }
        });
        dissociate = new JButton("Dissocier");
        dissociate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAction(3);
            }
        });
        updateFirmware = new JButton("Mettre à jour firmware");
        updateFirmware.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAction(4);
            }
        });

        // add labels
        show.add(devices, BorderLayout.NORTH);
        show.add(firmware, BorderLayout.CENTER);
        show.add(patient, BorderLayout.SOUTH);

        // add buttons
        buttons.add(declare);
        buttons.add(associate);
        buttons.add(dissociate);
        buttons.add(updateFirmware);


        // add panels
        this.add(show, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
    }

    public void buttonAction(int action) {
        String p2Text;
        String[] p2ComboBox;
        if (action == 1 || action == 4) {
            p2Text = "Firmware : ";
            p2ComboBox = c.getFirmwareNames();
        } else {
            p2Text = "Patient : ";
            p2ComboBox = c.getPatientNames();
        }
        JFrame d = new JFrame();
        JPanel p1 = new JPanel();
        p1.add(new JLabel("Device : "));
        JComboBox comboBoxD = new JComboBox<>(c.getDevicesNames().toArray());
        p1.add(comboBoxD);
        JPanel p2 = new JPanel();
        p2.add(new JLabel(p2Text));
        JComboBox comboBoxP = new JComboBox<>(p2ComboBox);
        p2.add(comboBoxP);
        JButton b = new JButton("Valider");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (action == 1)
                    System.out.println("Declaration : " + c.newDeviceWithFirmware(comboBoxD.getSelectedItem().toString(), c.getFirmwares().get(comboBoxP.getSelectedIndex())));
                if (action == 2)
                    System.out.println("Association : " + c.newDeviceWithPatient(comboBoxD.getSelectedItem().toString(), c.getPatients().get(comboBoxP.getSelectedIndex())));
                if (action == 3)
                    System.out.println("Dissociation : " + c.dissociatePatientFromDevice(comboBoxD.getSelectedItem().toString(), c.getPatients().get(comboBoxP.getSelectedIndex())));
                if (action == 4)
                    System.out.println("Update : " + c.updateFirmwareOfDevice(comboBoxD.getSelectedItem().toString(), c.getFirmwares().get(comboBoxP.getSelectedIndex())));
                d.dispose();
            }
        });
        d.add(p1, BorderLayout.NORTH);
        d.add(p2, BorderLayout.CENTER);
        d.add(b, BorderLayout.SOUTH);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.pack();
        d.setVisible(true);
    }
}
