package Sinema;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import dao.KullaniciDAO;

public class GirisEkrani extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GirisEkrani frame = new GirisEkrani();
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
    public GirisEkrani() {
        setBorder(null);
        setBackground(new Color(0, 128, 0));
        setForeground(new Color(0, 0, 0));
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 637, 424);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnNewButton = new JButton("Giriş Yap");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi = textField.getText().trim();
                String sifre = new String(passwordField.getPassword()).trim();
                
                // Boş alan kontrolü
                if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kullanıcı Adı ve Şifre Alanlarını Doldurmanız Gerekmektedir", 
                            "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Kullanıcı giriş işlemi
                KullaniciDAO kullaniciDAO = new KullaniciDAO();
                kullaniciDAO.kullaniciGirisi(kullaniciAdi, sifre);   
                
                if (KullaniciDAO.GirisBasarili()) { 
                	Acilis BagliForm = (Acilis) SwingUtilities.getWindowAncestor(GirisEkrani.this); BagliForm.dispose(); }
            }
        });
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButton.setBounds(203, 269, 194, 41);
        contentPane.add(btnNewButton);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField.setBounds(267, 138, 208, 41);
        contentPane.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.BOLD, 15));
        passwordField.setBounds(267, 195, 208, 41);
        contentPane.add(passwordField);
        
        JLabel lblNewLabel = new JLabel("Kullanıcı Adı :");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(131, 145, 126, 27);
        contentPane.add(lblNewLabel);
        
        JLabel lblifre = new JLabel("Şifre :");
        lblifre.setForeground(Color.WHITE);
        lblifre.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblifre.setBounds(131, 202, 126, 27);
        contentPane.add(lblifre);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 70));
        lblNewLabel_1.setBounds(57, 10, 518, 90);
        contentPane.add(lblNewLabel_1);
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
        ui.setNorthPane(null);
    }
}
