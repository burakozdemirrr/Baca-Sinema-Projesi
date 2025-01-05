package Sinema;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.KullaniciDAO;
import model.Kullanici;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KayitOl extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JTextField textField_1;
    private JPasswordField passwordField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KayitOl frame = new KayitOl();
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
    public KayitOl() {
    	setBorder(null);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setBounds(100, 100, 642, 433);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 70));
        lblNewLabel_1.setBounds(55, 10, 518, 90);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("Kullanıcı Adı :");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(133, 151, 126, 27);
        contentPane.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField.setColumns(10);
        textField.setBounds(269, 146, 208, 32);
        contentPane.add(textField);
        
        JLabel lblifre = new JLabel("Şifre :");
        lblifre.setForeground(Color.WHITE);
        lblifre.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblifre.setBounds(133, 230, 126, 27);
        contentPane.add(lblifre);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.BOLD, 15));
        passwordField.setBounds(269, 228, 208, 31);
        contentPane.add(passwordField);
        
        JButton btnKaytOl = new JButton("Kayıt Ol");
        btnKaytOl.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        	    // Şifrelerin kontrolü
        	    String sifre = new String(passwordField.getPassword());
        	    String sifreTekrar = new String(passwordField_1.getPassword());
        	    if (!sifre.equals(sifreTekrar)) {
        	        JOptionPane.showMessageDialog(null, "Şifreler Eşleşmiyor", 
        	                "Uyarı", JOptionPane.WARNING_MESSAGE);
        	        return;
        	    }
        	    
        	    // Kullanıcı bilgilerini al
        	    String adSoyad = textField_2.getText().trim();
        	    String kullaniciAdi = textField.getText().trim();
        	    String email = textField_1.getText().trim();
        	    
        	    // Boş alan kontrolü
        	    if (adSoyad.isEmpty() || kullaniciAdi.isEmpty() || email.isEmpty() || sifre.isEmpty() || sifreTekrar.isEmpty()) {
        	        JOptionPane.showMessageDialog(null, "Tüm Alanları Doldurmanız Gerekmektedir", 
        	                "Uyarı", JOptionPane.WARNING_MESSAGE);
        	        return;
        	    }
        	    
        	    // E-posta kontrolü
        	    if (!email.contains("@")) {
        	        JOptionPane.showMessageDialog(null, "Geçerli Bir E-posta Adresi Giriniz", 
        	                "Uyarı", JOptionPane.WARNING_MESSAGE);
        	        return;
        	    }
        	    
        	    Kullanici kullanici = new Kullanici();
        	    kullanici.setAdSoyad(adSoyad);
        	    kullanici.setKullaniciAdi(kullaniciAdi);
        	    kullanici.seteMail(email);
        	    kullanici.setSifre(sifre);
        	    
        	    KullaniciDAO kullaniciDAO = new KullaniciDAO();
        	    kullaniciDAO.kullaniciKaydet(kullanici); 
        	    
        	    textField_2.setText(""); 
        	    textField.setText(""); 
        	    textField_1.setText(""); 
        	    passwordField.setText(""); 
        	    passwordField_1.setText("");
        	}
        });
        btnKaytOl.setForeground(Color.BLACK);
        btnKaytOl.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnKaytOl.setBounds(196, 310, 194, 32);
        contentPane.add(btnKaytOl);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(269, 188, 208, 30);
        contentPane.add(textField_1);
        
        JLabel lblEmail = new JLabel("E-Mail :");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblEmail.setBounds(133, 189, 126, 27);
        contentPane.add(lblEmail);
        
        JLabel lblifreTekrar = new JLabel("Şifre Tekrar :");
        lblifreTekrar.setForeground(Color.WHITE);
        lblifreTekrar.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblifreTekrar.setBounds(133, 269, 126, 27);
        contentPane.add(lblifreTekrar);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        passwordField_1.setBounds(269, 267, 208, 31);
        contentPane.add(passwordField_1);
        
        JLabel lblAdSoyad = new JLabel("Ad Soyad :");
        lblAdSoyad.setForeground(Color.WHITE);
        lblAdSoyad.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblAdSoyad.setBounds(133, 109, 126, 27);
        contentPane.add(lblAdSoyad);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField_2.setColumns(10);
        textField_2.setBounds(269, 104, 208, 32);
        contentPane.add(textField_2);
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
		ui.setNorthPane(null);
    } 
}
