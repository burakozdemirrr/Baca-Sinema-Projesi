package Admin;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import dao.KullaniciDAO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kullanicilar extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Kullanicilar frame = new Kullanicilar();
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
    public Kullanicilar() {
    	addInternalFrameListener(new InternalFrameAdapter() {
    		@Override
    		public void internalFrameOpened(InternalFrameEvent e) {
    			KullaniciDAO kullaniciDAO = new KullaniciDAO();
    			kullaniciDAO.ListeleKullanicilar(table);
    		}
    	});
    	setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 907, 604);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(214, 10, 472, 64);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("KULLANICILAR");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1.setBounds(23, 93, 224, 51);
        contentPane.add(lblNewLabel_1_1);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(57, 151, 791, 297);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);
        
        JLabel lblKullancNo = new JLabel("Kullanıcı No :");
        lblKullancNo.setForeground(Color.WHITE);
        lblKullancNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblKullancNo.setBounds(57, 498, 162, 20);
        contentPane.add(lblKullancNo);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(209, 490, 175, 37);
        contentPane.add(textField);
        
        JButton btnKullancySil = new JButton("KULLANICIYI SİL");
        btnKullancySil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
    		        int KullaniciNo = Integer.parseInt(textField.getText());
    		        KullaniciDAO kullaniciDAO = new KullaniciDAO();
    		        kullaniciDAO.KullaniciSil(KullaniciNo);
    		        kullaniciDAO.ListeleKullanicilar(table);
    		    } catch (NumberFormatException e1) {
    		        JOptionPane.showMessageDialog(null, "Geçerli bir değer giriniz.", 
    		                "Hata", JOptionPane.ERROR_MESSAGE);
    		    }
    		    textField.setText("");
        	}
        });
        btnKullancySil.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnKullancySil.setBounds(593, 490, 255, 37);
        contentPane.add(btnKullancySil);
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
		ui.setNorthPane(null);
    }
}
