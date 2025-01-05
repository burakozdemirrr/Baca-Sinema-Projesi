package Admin;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.RezervasyonDAO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class Rezervasyon extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JLabel lblRezervasyonNo;
    private JTextField textField;
    private JButton btnRezervasyonuIptalEt;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Rezervasyon frame = new Rezervasyon();
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
    public Rezervasyon() {
    	addInternalFrameListener(new InternalFrameAdapter() {
    		@Override
    		public void internalFrameOpened(InternalFrameEvent e) {
    			String isim = textField_1.getText();
        		String baslangicTarihi = textField_2.getText();
        		String bitisTarihi = textField_3.getText();

        		RezervasyonDAO rezervasyonDAO = new RezervasyonDAO();
        		rezervasyonDAO.RezervasyonArama(table, isim, baslangicTarihi, bitisTarihi);
    		}
    	});
    	setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 905, 597);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(213, 10, 472, 64);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("REZERVASYONLAR");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1.setBounds(35, 77, 224, 51);
        contentPane.add(lblNewLabel_1_1);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(49, 135, 791, 267);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);
        
        lblRezervasyonNo = new JLabel("Rezervasyon No :");
        lblRezervasyonNo.setForeground(Color.WHITE);
        lblRezervasyonNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblRezervasyonNo.setBounds(49, 498, 162, 20);
        contentPane.add(lblRezervasyonNo);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(201, 490, 175, 37);
        contentPane.add(textField);
        
        btnRezervasyonuIptalEt = new JButton("REZERVASYONU İPTAL ET");
        btnRezervasyonuIptalEt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
    		        int RezervasyonNo = Integer.parseInt(textField.getText());
    		        String isim = textField_1.getText();
            		String baslangicTarihi = textField_2.getText();
            		String bitisTarihi = textField_3.getText();
    		        RezervasyonDAO rezervasyonDAO = new RezervasyonDAO();
    		        rezervasyonDAO.RezervasyonSil(RezervasyonNo);
    		        rezervasyonDAO.RezervasyonArama(table, isim, baslangicTarihi, bitisTarihi);
    		    } catch (NumberFormatException e1) {
    		        JOptionPane.showMessageDialog(null, "Geçerli bir değer giriniz.", 
    		                "Hata", JOptionPane.ERROR_MESSAGE);
    		    }
    		    textField.setText("");
    		    textField_1.setText("");
        	}
        });
        btnRezervasyonuIptalEt.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnRezervasyonuIptalEt.setBounds(585, 490, 255, 37);
        contentPane.add(btnRezervasyonuIptalEt);
        
        JLabel lblIsim = new JLabel("İsim :");
        lblIsim.setForeground(Color.WHITE);
        lblIsim.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIsim.setBounds(49, 428, 51, 20);
        contentPane.add(lblIsim);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(98, 421, 136, 37);
        contentPane.add(textField_1);
        
        JLabel lblBalangT = new JLabel("Başlangıç T :");
        lblBalangT.setForeground(Color.WHITE);
        lblBalangT.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblBalangT.setBounds(271, 428, 105, 20);
        contentPane.add(lblBalangT);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_2.setColumns(10);
        textField_2.setBounds(370, 420, 153, 37);
        contentPane.add(textField_2);
        
        JLabel lblBitiT = new JLabel("Bitiş T :");
        lblBitiT.setForeground(Color.WHITE);
        lblBitiT.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblBitiT.setBounds(530, 429, 59, 20);
        contentPane.add(lblBitiT);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_3.setColumns(10);
        textField_3.setBounds(594, 420, 153, 37);
        contentPane.add(textField_3);
        
        JButton btnAra = new JButton("ARA");
        btnAra.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String isim = textField_1.getText();
        		String baslangicTarihi = textField_2.getText();
        		String bitisTarihi = textField_3.getText();

        		RezervasyonDAO rezervasyonDAO = new RezervasyonDAO();
        		rezervasyonDAO.RezervasyonArama(table, isim, baslangicTarihi, bitisTarihi);
        		textField.setText("");
    		    textField_1.setText("");
				textField_2.setText("");
    		    textField_3.setText("");
        	}      	
        });
        btnAra.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAra.setBounds(764, 420, 76, 37);
        contentPane.add(btnAra);
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
		ui.setNorthPane(null);
    }
}
