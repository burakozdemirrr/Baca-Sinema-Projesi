package Sinema;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.KullaniciDAO;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import model.Kullanici;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ayarlar extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldAdSoyad;
    private static JTextField textFieldKullaniciAdi;
    private JTextField textFieldEmail;
    private static Kullanici kullanici;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private static String eskiKullaniciAdi;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ayarlar frame = new Ayarlar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void AyarlarMetinYaz(Kullanici kullanici) {
        Ayarlar.kullanici = kullanici;
    }
    
    private static boolean KullaniciDegisti = false;	
	public static boolean KullaniciDegistiBasarili() { 
		return KullaniciDegisti; }	
	
	public static String getEskiKullaniciAdi() { 
		return eskiKullaniciAdi; }
	
	public static void EskiyeDon() { 
		kullanici.setKullaniciAdi(getEskiKullaniciAdi());
		textFieldKullaniciAdi.setText(kullanici.getKullaniciAdi());  }

    /**
     * Create the frame.
     */
    public Ayarlar() {
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                if (kullanici != null) {
                    textFieldAdSoyad.setText(kullanici.getAdSoyad());
                    textFieldKullaniciAdi.setText(kullanici.getKullaniciAdi());                    
                    textFieldEmail.setText(kullanici.geteMail());
                    passwordField.setText(kullanici.getSifre());
                    passwordField_1.setText(kullanici.getSifre());
                    
                    eskiKullaniciAdi = kullanici.getKullaniciAdi();
                }
            }
        });
        setBorder(null);
        getContentPane().setBackground(new Color(0, 128, 0));
        setForeground(new Color(0, 128, 0));
        getContentPane().setForeground(new Color(0, 128, 0));
        getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
        setBounds(100, 100, 916, 592);
        getContentPane().setLayout(null);
        
        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı :");
        lblKullaniciAdi.setForeground(Color.WHITE);
        lblKullaniciAdi.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblKullaniciAdi.setBounds(230, 193, 127, 20);
        getContentPane().add(lblKullaniciAdi);
        
        textFieldKullaniciAdi = new JTextField();
        textFieldKullaniciAdi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldKullaniciAdi.setColumns(10);
        textFieldKullaniciAdi.setBounds(367, 185, 291, 37);
        getContentPane().add(textFieldKullaniciAdi);
        
        JLabel lblEmail = new JLabel("E-Mail :");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblEmail.setBounds(230, 257, 94, 20);
        getContentPane().add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(367, 246, 291, 37);
        getContentPane().add(textFieldEmail);
        
        JLabel lblSifre = new JLabel("Şifre :");
        lblSifre.setForeground(Color.WHITE);
        lblSifre.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSifre.setBounds(230, 316, 111, 20);
        getContentPane().add(lblSifre);
        
        JLabel lblBacaSinema = new JLabel("BACA SİNEMA");
        lblBacaSinema.setHorizontalAlignment(SwingConstants.CENTER);
        lblBacaSinema.setForeground(Color.WHITE);
        lblBacaSinema.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblBacaSinema.setBounds(213, 10, 472, 64);
        getContentPane().add(lblBacaSinema);
        
        JLabel lblHesapBilgileri = new JLabel("HESAP BİLGİLERİ");
        lblHesapBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
        lblHesapBilgileri.setForeground(Color.WHITE);
        lblHesapBilgileri.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblHesapBilgileri.setBounds(372, 66, 161, 20);
        getContentPane().add(lblHesapBilgileri);
        
        JButton btnBilgileriGuncelle = new JButton("BİLGİLERİ GÜNCELLE");
        btnBilgileriGuncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String sifre = new String(passwordField.getPassword());
                String sifreTekrar = new String(passwordField_1.getPassword());
                if (!sifre.equals(sifreTekrar)) {
                    JOptionPane.showMessageDialog(null, "Şifreler Eşleşmiyor",
                            "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String adSoyad = textFieldAdSoyad.getText().trim();
                String yeniKullaniciAdi = textFieldKullaniciAdi.getText().trim();
                String email = textFieldEmail.getText().trim();

                char[] password = passwordField.getPassword();
                String sifre2 = new String(password).trim();

                char[] passwordAgain = passwordField.getPassword();
                String sifreTekrar2 = new String(passwordAgain).trim();

                if (adSoyad.isEmpty() || yeniKullaniciAdi.isEmpty() || email.isEmpty() || sifre2.isEmpty() || sifreTekrar2.isEmpty()) {
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

                if (kullanici != null) {
                    kullanici.setKullaniciNo(kullanici.getKullaniciNo());
                    kullanici.setAdSoyad(adSoyad);
                    kullanici.seteMail(email);
                    kullanici.setSifre(sifre2);

                    // Kullanıcı adı değişti mi kontrol et ve sadece değiştiyse güncelle
                    if (!kullanici.getKullaniciAdi().equals(yeniKullaniciAdi)) {
                        kullanici.setKullaniciAdi(yeniKullaniciAdi);
                        KullaniciDegisti = true;
                    }
                    else { KullaniciDegisti = false; }
                }

                KullaniciDAO kullaniciDAO = new KullaniciDAO();
                kullaniciDAO.kullaniciGuncelle(kullanici);
                KullaniciDegisti = false;
            }
        });

        btnBilgileriGuncelle.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnBilgileriGuncelle.setBounds(307, 447, 278, 46);
        getContentPane().add(btnBilgileriGuncelle);
        
        JLabel lblSifreTekrar = new JLabel("Şifre Tekrar :");
        lblSifreTekrar.setForeground(Color.WHITE);
        lblSifreTekrar.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSifreTekrar.setBounds(230, 380, 111, 20);
        getContentPane().add(lblSifreTekrar);
        
        JLabel lblAdSoyad = new JLabel("Ad Soyad :");
        lblAdSoyad.setForeground(Color.WHITE);
        lblAdSoyad.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblAdSoyad.setBounds(230, 128, 127, 20);
        getContentPane().add(lblAdSoyad);
        
        textFieldAdSoyad = new JTextField();
        textFieldAdSoyad.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldAdSoyad.setColumns(10);
        textFieldAdSoyad.setBounds(367, 120, 291, 37);
        getContentPane().add(textFieldAdSoyad);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setBounds(367, 310, 291, 37);
        getContentPane().add(passwordField);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField_1.setBounds(367, 369, 291, 37);
        getContentPane().add(passwordField_1);
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
        ui.setNorthPane(null);
    }
}
