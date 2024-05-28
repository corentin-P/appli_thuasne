import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();

        Window window = new Window("Mini Projet : Device Management", c);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
