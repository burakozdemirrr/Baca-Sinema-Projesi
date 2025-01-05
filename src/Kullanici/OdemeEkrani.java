package Kullanici;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.Kullanici;
import model.SeansParametre;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import dao.RezervasyonDAO;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class OdemeEkrani extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private static SeansParametre seansParametre;
    private static Kullanici kullanici;
    
    public void gosterAnamenu() {
        for (Window window : Window.getWindows()) {
            if (window instanceof Anamenu) {
                window.setVisible(true);
                break;
            }
        }
    }
    
    public void YenileAnamenu() {
        for (Window window : Window.getWindows()) {
            if (window instanceof Anamenu) {
                window.dispose();
                break;
            }
        }
        JFrame anamenu = new Anamenu();
		anamenu.setVisible(true);
    }   


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OdemeEkrani frame = new OdemeEkrani();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void OdemeEkraniBilgiAl(SeansParametre seansParametre) {
        OdemeEkrani.seansParametre = seansParametre;
    }
    
    public static void OdemeEkraniKullaniciAl(Kullanici kullanici) {
        OdemeEkrani.kullanici = kullanici;
    }
    
    /**
     * Create the frame.
     */
    public OdemeEkrani() {
  	
        setTitle("Ödeme Ekranı");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 633, 608);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(67, 10, 472, 64);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblIlemGemii = new JLabel("İŞLEM GEÇMİŞİ");
        lblIlemGemii.setForeground(Color.WHITE);
        lblIlemGemii.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblIlemGemii.setBounds(51, 84, 195, 28);
        contentPane.add(lblIlemGemii);
        
        JLabel lblIlemGemii_1 = new JLabel("-----");
        lblIlemGemii_1.setForeground(Color.WHITE);
        lblIlemGemii_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIlemGemii_1.setBounds(51, 144, 195, 28);
        contentPane.add(lblIlemGemii_1);
        
        JLabel lblIlemGemii_1_1 = new JLabel("x-----");
        lblIlemGemii_1_1.setForeground(Color.WHITE);
        lblIlemGemii_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIlemGemii_1_1.setBounds(256, 144, 195, 28);
        contentPane.add(lblIlemGemii_1_1);
        
        JLabel lblIlemGemii_1_2 = new JLabel("Tutar :");
        lblIlemGemii_1_2.setForeground(Color.WHITE);
        lblIlemGemii_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIlemGemii_1_2.setBounds(51, 209, 195, 28);
        contentPane.add(lblIlemGemii_1_2);
        
        JLabel lblIlemGemii_1_2_1 = new JLabel("----$");
        lblIlemGemii_1_2_1.setForeground(Color.WHITE);
        lblIlemGemii_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIlemGemii_1_2_1.setBounds(256, 209, 195, 28);
        contentPane.add(lblIlemGemii_1_2_1);
        
        JLabel lbldemeBilgileri = new JLabel("ÖDEME BİLGİLERİ");
        lbldemeBilgileri.setForeground(Color.WHITE);
        lbldemeBilgileri.setFont(new Font("Tahoma", Font.BOLD, 18));
        lbldemeBilgileri.setBounds(51, 277, 195, 28);
        contentPane.add(lbldemeBilgileri);
        
        JLabel lblIsimSoyisim = new JLabel("İSİM/SOYİSİM :");
        lblIsimSoyisim.setForeground(Color.WHITE);
        lblIsimSoyisim.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIsimSoyisim.setBounds(51, 324, 127, 20);
        contentPane.add(lblIsimSoyisim);

        class BuyukHarfFiltre extends DocumentFilter {
            @Override
            public void insertString(FilterBypass fb, int offset, String metin, AttributeSet attr) throws BadLocationException {
                if (metin != null) {
                    metin = metin.replaceAll("[^a-zA-Z]", "");
                    metin = metin.toUpperCase();
                }
                super.insertString(fb, offset, metin, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String metin, AttributeSet attrs) throws BadLocationException {
                if (metin != null) {
                    metin = metin.replaceAll("[^a-zA-Z]", "");
                    metin = metin.toUpperCase(); 
                }
                super.replace(fb, offset, length, metin, attrs);
            }
        }

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(199, 315, 181, 37);
        PlainDocument doc1 = (PlainDocument) textField.getDocument();
        doc1.setDocumentFilter(new BuyukHarfFiltre());
        contentPane.add(textField);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(390, 315, 181, 37);
        PlainDocument doc2 = (PlainDocument) textField_1.getDocument();
        doc2.setDocumentFilter(new BuyukHarfFiltre());
        contentPane.add(textField_1);

        
        JLabel lblKartNumaras = new JLabel("KART NUMARASI :");
        lblKartNumaras.setForeground(Color.WHITE);
        lblKartNumaras.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblKartNumaras.setBounds(51, 381, 152, 20);
        contentPane.add(lblKartNumaras);       
        
        JLabel lblAayy = new JLabel("AA/YY :");
        lblAayy.setForeground(Color.WHITE);
        lblAayy.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblAayy.setBounds(51, 437, 152, 20);
        contentPane.add(lblAayy);
        
        try {
            MaskFormatter cardMask = new MaskFormatter("#### #### #### ####");
            textField_2 = new JFormattedTextField(cardMask);
            textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
            textField_2.setColumns(10);
            textField_2.setBounds(199, 373, 372, 37);
            contentPane.add(textField_2);
            
            MaskFormatter dateMask = new MaskFormatter("##/##");
            textField_3 = new JFormattedTextField(dateMask);
            textField_3.setHorizontalAlignment(SwingConstants.CENTER);
            textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
            textField_3.setColumns(10);
            textField_3.setBounds(199, 429, 181, 37);
            contentPane.add(textField_3);
	        
            MaskFormatter cvvMask = new MaskFormatter("###");
            textField_4 = new JFormattedTextField(cvvMask);
            textField_4.setHorizontalAlignment(SwingConstants.CENTER);
            textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
            textField_4.setColumns(10);
            textField_4.setBounds(444, 429, 127, 37);
            contentPane.add(textField_4);
            
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        
        JLabel lblCcv = new JLabel("CCV :");
        lblCcv.setForeground(Color.WHITE);
        lblCcv.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblCcv.setBounds(390, 437, 47, 20);
        contentPane.add(lblCcv);
        
        JButton btnGeriDn = new JButton("GERİ DÖN");
        btnGeriDn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		gosterAnamenu();
        	}
        });
        btnGeriDn.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnGeriDn.setBounds(298, 503, 127, 46);
        contentPane.add(btnGeriDn);
        
     // JButton btnOnayla oluşturuluyor ve actionPerformed metodu tanımlanıyor
        JButton btnOnayla = new JButton("ONAYLA");
        btnOnayla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().trim().isEmpty() || textField_1.getText().trim().isEmpty() || textField_2.getText().trim().isEmpty() ||
                        textField_3.getText().trim().isEmpty() || textField_4.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tüm Alanları Doldurmanız Gerekmektedir", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (seansParametre != null && kullanici != null) {
                    int seansNo = seansParametre.getSeansNo();
                    String adSoyad = kullanici.getAdSoyad();
                    String kullaniciAdi = kullanici.getKullaniciAdi();
                    String filmAdi = seansParametre.getFilmAdi();
                    String filmTarihi = seansParametre.getFilmTarihi();
                    String filmSaati = seansParametre.getFilmSaati();
                    int salonNo = seansParametre.getSalonNo();
                    double biletFiyati = seansParametre.getBiletFiyati();
                    int biletAdedi = seansParametre.getBiletAdedi();

                    RezervasyonDAO rezervasyonDAO = new RezervasyonDAO();
                    int mevcutStok = rezervasyonDAO.KapasiteKontrol(seansNo);

                    if (mevcutStok >= biletAdedi) {
                        rezervasyonDAO.RezervasyonEkle(adSoyad, kullaniciAdi, filmAdi, filmTarihi, filmSaati, salonNo, biletFiyati, biletAdedi);
                        int yeniStok = mevcutStok - biletAdedi;
                        rezervasyonDAO.KapasiteGuncelle(seansNo, yeniStok);

                        // Rezervasyon onay e-postası gönderimi
                        String email = kullanici.geteMail();
                        String gonderen = "okulicinacildi0@gmail.com";
                        String sifreGonderen = "t ba v k r l y a o ye i k f e";

                        // SMTP sunucusu ayarları
                        Properties properties = new Properties();
                        properties.put("mail.smtp.auth", "true");
                        properties.put("mail.smtp.starttls.enable", "true");
                        properties.put("mail.smtp.host", "smtp.gmail.com");
                        properties.put("mail.smtp.port", "587");

                        // Oturum oluşturma
                        Session session = Session.getInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(gonderen, sifreGonderen);
                            }
                        });

                        try {
                            // Mesaj oluşturma
                            MimeMessage message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(gonderen)); // Gönderen
                            message.setRecipient(Message.RecipientType.TO, // Alıcı
                                    new InternetAddress(email));
                            message.setSubject("Rezervasyon Hakkında"); // Konu
                            message.setText("BACA SİNEMA\n\n" +
                                    "Sayın " + adSoyad + ",\n\n" +
                                    "Rezervasyonunuz başarıyla oluşturulmuştur. Detaylar aşağıdaki gibidir:\n" +
                                    "Film: " + filmAdi + "\n" +
                                    "Tarih: " + filmTarihi + "\n" +
                                    "Saat: " + filmSaati + "\n" +
                                    "Salon: " + salonNo + "\n" +
                                    "Bilet Fiyatı: " + biletFiyati + " TL\n" +
                                    "Bilet Adedi: " + biletAdedi + "\n\n" +
                                    "Rezervasyonunuzu iptal etmek isterseniz, lütfen bu numaradan bizimle iletişime geçiniz: 0212-000-0000\n\n" +
                                    "Keyifli seyirler dileriz.");


                            // Mesajı gönder
                            Transport.send(message);

                        } catch (Exception error) {

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Yeterli bilet stoğu yok.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Rezervasyon Oluşturulamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
                YenileAnamenu();
            }
        });
        btnOnayla.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnOnayla.setBounds(444, 503, 127, 46);
        contentPane.add(btnOnayla);


        
        addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {

    		}
    		@Override
    		public void windowOpened(WindowEvent e) {
    		    if (seansParametre != null) {
    		        lblIlemGemii_1.setText(seansParametre.getFilmAdi());
    		        lblIlemGemii_1_1.setText("x" + seansParametre.getBiletAdedi());
    		        lblIlemGemii_1_2_1.setText(seansParametre.getBiletAdedi() + " x " + seansParametre.getBiletFiyati() + " = " + (seansParametre.getBiletAdedi() * seansParametre.getBiletFiyati()) + " ₺");
    		    }
    		}

    	});
        
        setLocationRelativeTo(null);
    }
}
