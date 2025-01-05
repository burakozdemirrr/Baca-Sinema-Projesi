package Kullanici;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;
import Sinema.Acilis;
import Sinema.Ayarlar;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Anamenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    static JLayeredPane layeredPane;
    static BiletAl biletAl = null;
    private static DilekSikayet DlkSkyt = null;
    private static Filmler filmler = null;
    private static SoruSor soruSor = null;
    private static Ayarlar ayarlar = null;   
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Anamenu frame = new Anamenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Anamenu() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); 
                JFrame acilis = new Acilis(); 
                acilis.setVisible(true);
            }
        });
        setTitle("BACA SİNEMA");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 929, 628);

        layeredPane = new JLayeredPane();

        JMenuBar menuBar = new JMenuBar();
        menuBar.setForeground(new Color(0, 128, 0));
        menuBar.setFont(new Font("Tahoma", Font.BOLD, 12));
        setJMenuBar(menuBar);

        JMenuItem mnıtmNewMenuItem_1 = new JMenuItem("ANAMENÜ");
        mnıtmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (biletAl != null) biletAl.setVisible(false);
                if (DlkSkyt != null) DlkSkyt.setVisible(false);
                if (filmler != null) filmler.setVisible(false);
                if (soruSor != null) soruSor.setVisible(false);
                layeredPane.repaint();
                layeredPane.revalidate();
            }
        });
        mnıtmNewMenuItem_1.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuBar.add(mnıtmNewMenuItem_1);

        JMenuItem mnıtmNewMenuItem = new JMenuItem("FİLMLER");
        mnıtmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (filmler != null) {
                    filmler.dispose();
                }
                filmler = new Filmler(Anamenu.this);
                filmler.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(filmler, JLayeredPane.PALETTE_LAYER);
                filmler.setVisible(true);
                filmler.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(filmler);
                layeredPane.repaint();
                layeredPane.revalidate();
                filmler.repaint();
            }
        });
        mnıtmNewMenuItem.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuBar.add(mnıtmNewMenuItem);

        JMenuItem mnıtmNewMenuItem_5 = new JMenuItem("BİLET AL");
        mnıtmNewMenuItem_5.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (biletAl != null) {
                    biletAl.dispose();
                }
                biletAl = new BiletAl(Anamenu.this);
                biletAl.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(biletAl, JLayeredPane.PALETTE_LAYER);
                biletAl.setVisible(true);
                biletAl.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(biletAl);
                layeredPane.repaint();
                layeredPane.revalidate();
                biletAl.repaint();
            }
        });
        mnıtmNewMenuItem_5.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_5.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuBar.add(mnıtmNewMenuItem_5);

        JMenuItem mnıtmNewMenuItem_2 = new JMenuItem("SORU SOR");
        mnıtmNewMenuItem_2.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (soruSor != null) {
                    soruSor.dispose();
                }
                soruSor = new SoruSor();
                soruSor.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(soruSor, JLayeredPane.PALETTE_LAYER);
                soruSor.setVisible(true);
                soruSor.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(soruSor);
                layeredPane.repaint();
                layeredPane.revalidate();
                soruSor.repaint();
            }
        });
        mnıtmNewMenuItem_2.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuBar.add(mnıtmNewMenuItem_2);
        JMenuItem mnıtmNewMenuItem_3 = new JMenuItem("DİLEK/ŞİKAYET");
        mnıtmNewMenuItem_3.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (DlkSkyt != null) {
                    DlkSkyt.dispose();
                }
                DlkSkyt = new DilekSikayet();
                DlkSkyt.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(DlkSkyt, JLayeredPane.PALETTE_LAYER);
                DlkSkyt.setVisible(true);
                DlkSkyt.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(DlkSkyt);
                layeredPane.repaint();
                layeredPane.revalidate();
                DlkSkyt.repaint();
            }
        });
        mnıtmNewMenuItem_3.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuBar.add(mnıtmNewMenuItem_3);

        JMenuItem mnıtmNewMenuItem_4 = new JMenuItem("ÇIKIŞ YAP");
        mnıtmNewMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                JFrame acilis = new Acilis(); 
                acilis.setVisible(true);
            }
        });
        mnıtmNewMenuItem_4.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_4.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_4.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuBar.add(mnıtmNewMenuItem_4);

        JMenuItem mnıtmNewMenuItem_8 = new JMenuItem("AYARLAR");
        mnıtmNewMenuItem_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ayarlar != null) {
                    ayarlar.dispose();
                }
                ayarlar = new Ayarlar();
                ayarlar.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(ayarlar, JLayeredPane.PALETTE_LAYER);
                ayarlar.setVisible(true);
                ayarlar.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(ayarlar);
                layeredPane.repaint();
                layeredPane.revalidate();
                ayarlar.repaint();
            }
        });
        mnıtmNewMenuItem_8.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_8.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_8.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuBar.add(mnıtmNewMenuItem_8);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        contentPane.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("BACA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 99));
        lblNewLabel_1.setBounds(206, 145, 495, 222);
        layeredPane.add(lblNewLabel_1, JLayeredPane.DEFAULT_LAYER);
        
        setLocationRelativeTo(null);
    }
}
