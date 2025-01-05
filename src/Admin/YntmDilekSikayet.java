package Admin;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.DilekSikayetDAO;

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

public class YntmDilekSikayet extends JInternalFrame {

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
                    YntmDilekSikayet frame = new YntmDilekSikayet();
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
    public YntmDilekSikayet() {
    	addInternalFrameListener(new InternalFrameAdapter() {
    		@Override
    		public void internalFrameOpened(InternalFrameEvent e) {
    			DilekSikayetDAO dilekSikayetDAO = new DilekSikayetDAO();
    			dilekSikayetDAO.ListeleTalep(table);
    		}
    	});
    	setBorder(null);
        setBackground(new Color(0, 128, 0));
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 910, 604);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(212, 10, 472, 64);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("DİLEK VE ŞİKAYETLER");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1.setBounds(66, 92, 224, 51);
        contentPane.add(lblNewLabel_1_1);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(66, 151, 791, 297);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        JLabel lblDilekikayetNo = new JLabel("Dilek/Şikayet No :");
        lblDilekikayetNo.setForeground(Color.WHITE);
        lblDilekikayetNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblDilekikayetNo.setBounds(66, 498, 162, 20);
        contentPane.add(lblDilekikayetNo);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(218, 490, 175, 37);
        contentPane.add(textField);
        
        JButton btnListeyiTemizle = new JButton("LİSTEYİ TEMİZLE");
        btnListeyiTemizle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DilekSikayetDAO dilekSikayetDAO = new DilekSikayetDAO();
    			dilekSikayetDAO.TalepTemizle(table);
        	}
        });
        btnListeyiTemizle.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnListeyiTemizle.setBounds(662, 490, 195, 37);
        contentPane.add(btnListeyiTemizle);
        
        JButton btnDilekikayetSil = new JButton("DİLEK/ŞİKAYET SİL");
        btnDilekikayetSil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		    try {
        		        int talepNo = Integer.parseInt(textField.getText());
        		        DilekSikayetDAO dilekSikayetDAO = new DilekSikayetDAO();
        		        dilekSikayetDAO.TalepSil(talepNo);
            			dilekSikayetDAO.ListeleTalep(table);
        		    } catch (NumberFormatException e1) {
        		        JOptionPane.showMessageDialog(null, "Geçerli bir değer giriniz.", 
        		                "Hata", JOptionPane.ERROR_MESSAGE);
        		    }
        		    textField.setText("");
        	}
        });
        btnDilekikayetSil.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDilekikayetSil.setBounds(457, 490, 195, 37);
        contentPane.add(btnDilekikayetSil);
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
		ui.setNorthPane(null);
    }
}
