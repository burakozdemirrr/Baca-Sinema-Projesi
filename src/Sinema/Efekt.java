package Sinema;

import javax.swing.*;
import java.awt.*;

public class Efekt extends JWindow {
    private static final long serialVersionUID = 1L;

    public Efekt() {
        JLabel label = new JLabel("BACA", SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 99));
        label.setForeground(new Color(0, 128, 0));
        add(label);
        setSize(400, 200);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 0, 0, 0));

        Timer timer = new Timer(50, e -> {
            float opacity = getOpacity();
            if (opacity > 0.01f) {
                setOpacity(opacity - 0.02f);
            } else {
                ((Timer) e.getSource()).stop();
                dispose();
                showMainWindow();
            }
        });

        setVisible(true);
        timer.start();
    }

    private void showMainWindow() {
        JFrame acilis = new Acilis();
        acilis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        acilis.setLocationRelativeTo(null);
        acilis.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Efekt::new);
    }
}

