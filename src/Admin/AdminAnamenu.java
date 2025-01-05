package Admin;

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
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminAnamenu extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLayeredPane layeredPane;
    private static FilmEkle filmEkle = null;
    private static SeansEkle seanslar = null;
    private static Rezervasyon rezervasyonlar = null;
    private static Kullanicilar kullanicilar = null;
    private static YntmSoruCevapla sorular = null;
    private static YntmDilekSikayet dilekSikayet = null;
    private static Ayarlar ayarlar = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminAnamenu frame = new AdminAnamenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminAnamenu() {
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
        setBounds(100, 100, 939, 639);

        layeredPane = new JLayeredPane();

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Tahoma", Font.BOLD, 12));
        setJMenuBar(menuBar);
        
        JMenuItem mnıtmNewMenuItem = new JMenuItem("ANAMENÜ");
        mnıtmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem.setFont(new Font("Tahoma", Font.BOLD, 12));
        mnıtmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seanslar != null) seanslar.setVisible(false);
                if (rezervasyonlar != null) rezervasyonlar.setVisible(false);
                if (kullanicilar != null) kullanicilar.setVisible(false);
                if (sorular != null) sorular.setVisible(false);
                if (dilekSikayet != null) dilekSikayet.setVisible(false);
                if (filmEkle != null) filmEkle.setVisible(false);
                layeredPane.repaint();
                layeredPane.revalidate();
            }
        });
        menuBar.add(mnıtmNewMenuItem);
        
        JMenuItem mnıtmNewMenuItem_1 = new JMenuItem("FİLM EKLE");
        mnıtmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_1.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        mnıtmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (filmEkle != null) {
                    filmEkle.dispose();
                }
                filmEkle = new FilmEkle();
                filmEkle.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(filmEkle, JLayeredPane.PALETTE_LAYER);
                filmEkle.setVisible(true);
                filmEkle.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(filmEkle);
                layeredPane.repaint();
                layeredPane.revalidate();
                filmEkle.repaint();
            }
        });
        menuBar.add(mnıtmNewMenuItem_1);
        
        JMenuItem mnıtmNewMenuItem_2 = new JMenuItem("SEANSLAR");
        mnıtmNewMenuItem_2.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_2.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        mnıtmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seanslar != null) {
                    seanslar.dispose();
                }
                seanslar = new SeansEkle();
                seanslar.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(seanslar, JLayeredPane.PALETTE_LAYER);
                seanslar.setVisible(true);
                seanslar.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(seanslar);
                layeredPane.repaint();
                layeredPane.revalidate();
                seanslar.repaint();
            }
        });
        menuBar.add(mnıtmNewMenuItem_2);
        
        JMenuItem mnıtmNewMenuItem_3 = new JMenuItem("REZERVASYONLAR");
        mnıtmNewMenuItem_3.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_3.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        mnıtmNewMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rezervasyonlar != null) {
                    rezervasyonlar.dispose();
                }
                rezervasyonlar = new Rezervasyon();
                rezervasyonlar.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(rezervasyonlar, JLayeredPane.PALETTE_LAYER);
                rezervasyonlar.setVisible(true);
                rezervasyonlar.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(rezervasyonlar);
                layeredPane.repaint();
                layeredPane.revalidate();
                rezervasyonlar.repaint();
            }
        });
        menuBar.add(mnıtmNewMenuItem_3);
        
        JMenuItem mnıtmNewMenuItem_4 = new JMenuItem("KULLANICILAR");
        mnıtmNewMenuItem_4.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_4.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_4.setFont(new Font("Tahoma", Font.BOLD, 12));
        mnıtmNewMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (kullanicilar != null) {
                    kullanicilar.dispose();
                }
                kullanicilar = new Kullanicilar();
                kullanicilar.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(kullanicilar, JLayeredPane.PALETTE_LAYER);
                kullanicilar.setVisible(true);
                kullanicilar.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(kullanicilar);
                layeredPane.repaint();
                layeredPane.revalidate();
                kullanicilar.repaint();
            }
        });
        menuBar.add(mnıtmNewMenuItem_4);
        
        JMenuItem mnıtmNewMenuItem_5 = new JMenuItem("SORULAR");
        mnıtmNewMenuItem_5.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_5.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_5.setFont(new Font("Tahoma", Font.BOLD, 12));
        mnıtmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sorular != null) {
                    sorular.dispose();
                }
                sorular = new YntmSoruCevapla();
                sorular.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(sorular, JLayeredPane.PALETTE_LAYER);
                sorular.setVisible(true);
                sorular.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(sorular);
                layeredPane.repaint();
                layeredPane.revalidate();
                sorular.repaint();
            }
        });
        menuBar.add(mnıtmNewMenuItem_5);
        
        JMenuItem mnıtmNewMenuItem_6 = new JMenuItem("DİLEK/ŞİKAYET");
        mnıtmNewMenuItem_6.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_6.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_6.setFont(new Font("Tahoma", Font.BOLD, 12));
        mnıtmNewMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (dilekSikayet != null) {
                    dilekSikayet.dispose();
                }
                dilekSikayet = new YntmDilekSikayet();
                dilekSikayet.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(dilekSikayet, JLayeredPane.PALETTE_LAYER);
                dilekSikayet.setVisible(true);
                dilekSikayet.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(dilekSikayet);
                layeredPane.repaint();
                layeredPane.revalidate();
                dilekSikayet.repaint();
            }
        });
        menuBar.add(mnıtmNewMenuItem_6);
        
        JMenuItem mnıtmNewMenuItem_7 = new JMenuItem("ÇIKIŞ YAP");
        mnıtmNewMenuItem_7.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_7.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_7.setFont(new Font("Tahoma", Font.BOLD, 12));
        mnıtmNewMenuItem_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose(); 
        		JFrame acilis = new Acilis(); 
        		acilis.setVisible(true);
            }
        });
        
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
        mnıtmNewMenuItem_8.setForeground(new Color(0, 128, 0));
        mnıtmNewMenuItem_8.setHorizontalAlignment(SwingConstants.CENTER);
        mnıtmNewMenuItem_8.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuBar.add(mnıtmNewMenuItem_8);
        menuBar.add(mnıtmNewMenuItem_7);

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
        lblNewLabel_1.setBounds(298, 187, 292, 90);
        layeredPane.add(lblNewLabel_1, JLayeredPane.DEFAULT_LAYER);

        JLabel lblYnetimPaneli = new JLabel("YÖNETİM PANELİ");
        lblYnetimPaneli.setForeground(Color.WHITE);
        lblYnetimPaneli.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblYnetimPaneli.setBounds(359, 287, 186, 28);
        layeredPane.add(lblYnetimPaneli, JLayeredPane.DEFAULT_LAYER);
        
        setLocationRelativeTo(null);
    }
}
