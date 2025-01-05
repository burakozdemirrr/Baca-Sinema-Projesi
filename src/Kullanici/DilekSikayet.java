package Kullanici;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import dao.DilekSikayetDAO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DilekSikayet extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DilekSikayet frame = new DilekSikayet();
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
    public DilekSikayet() {
    	setBorder(null);
        setBackground(new Color(0, 128, 0));
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 908, 602);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(199, 24, 472, 64);
        contentPane.add(lblNewLabel_1);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Tahoma", Font.BOLD, 18));
        textArea.setLineWrap(true);
        textArea.setBounds(48, 164, 813, 300);
        contentPane.add(textArea);
        
        JButton btnGnder = new JButton("GÖNDER");
        btnGnder.addActionListener(new ActionListener() {        	        	
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		String Talep = textArea.getText().trim();
        		
        		if (Talep.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tüm Alanları Doldurmanız Gerekmektedir",
                            "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }
        		
        		DilekSikayetDAO dilekSikayetDAO = new DilekSikayetDAO();
        		dilekSikayetDAO.TalepKaydet(Talep);
        		textArea.setText(""); 
        	}
        });
        btnGnder.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnGnder.setBounds(736, 495, 127, 46);
        contentPane.add(btnGnder);
        
        JLabel lblDilekVeikayetinizi = new JLabel("DİLEK VE ŞİKAYETİNİZİ YAZINIZ");
        lblDilekVeikayetinizi.setForeground(Color.WHITE);
        lblDilekVeikayetinizi.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDilekVeikayetinizi.setBounds(48, 111, 328, 28);
        contentPane.add(lblDilekVeikayetinizi);
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
		ui.setNorthPane(null);
    }
}
