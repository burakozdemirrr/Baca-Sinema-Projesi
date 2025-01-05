package Sinema;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import dao.KullaniciDAO;

public class SifreUnuttum extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField emailField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SifreUnuttum frame = new SifreUnuttum();
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
    public SifreUnuttum() {
        setBackground(new Color(0, 128, 0));
        setBorder(null);
        setBounds(100, 100, 645, 429);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(new Color(0, 128, 0));
        getContentPane().add(layeredPane, BorderLayout.CENTER);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 70));
        lblNewLabel_1.setBounds(60, 10, 518, 90);
        layeredPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("Kullanıcı Adı :");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(134, 145, 126, 27);
        layeredPane.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(270, 138, 208, 41);
        layeredPane.add(textField);
        
        JLabel lblMa = new JLabel("E-Mail :");
        lblMa.setForeground(Color.WHITE);
        lblMa.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMa.setBounds(134, 202, 126, 27);
        layeredPane.add(lblMa);
        
        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        emailField.setBounds(270, 195, 208, 41);
        layeredPane.add(emailField);
        
        JButton btnGnder = new JButton("Gönder");
        btnGnder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  String kullaniciAdi = textField.getText();
                  String email = emailField.getText();
                  
                  if (kullaniciAdi.isEmpty() || email.isEmpty()) {
                      JOptionPane.showMessageDialog(null, "Kullanıcı adı ve e-posta alanları boş bırakılamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  
                  if (!email.contains("@")) {
                      JOptionPane.showMessageDialog(null, "Geçerli bir e-posta adresi giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  
                  // Veritabanı kontrolü ve şifre alma
                  KullaniciDAO kullaniciDAO = new KullaniciDAO();
                  String sifre = kullaniciDAO.SifreUnuttum(kullaniciAdi, email);
                  
                  if (sifre != null) {
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
                          message.setFrom(new InternetAddress(gonderen));// Gönderen
                          message.setRecipient(Message.RecipientType.TO, // Alıcı
                                  new InternetAddress(email));
                          message.setSubject("Şifre Hatırlatıcı"); // Konu
                          message.setText("Şifreniz: " + sifre); // İçerik

                          // Mesajı gönder
                          Transport.send(message);
                          JOptionPane.showMessageDialog(null, "Mail gönderildi.", "Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);
                          textField.setText("");
                          emailField.setText("");
                          
                      } catch (Exception error) {
                          error.printStackTrace();
                          JOptionPane.showMessageDialog(null, "Mail gönderme hatası.", "Hata", JOptionPane.ERROR_MESSAGE);
                      }
                  } else {
                      JOptionPane.showMessageDialog(null, "Kullanıcı adı veya e-posta adresi bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
                  }
            }
        });
        btnGnder.setForeground(Color.BLACK);
        btnGnder.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnGnder.setBounds(206, 269, 194, 41);
        layeredPane.add(btnGnder);
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
        ui.setNorthPane(null);

    }
}
