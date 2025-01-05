package Admin;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import dao.SorularDAO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class YntmSoruCevapla extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    YntmSoruCevapla frame = new YntmSoruCevapla();
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
    public YntmSoruCevapla() {
    	addInternalFrameListener(new InternalFrameAdapter() {
    		@Override
    		public void internalFrameOpened(InternalFrameEvent e) {
    			SorularDAO sorularDAO = new SorularDAO();
    			sorularDAO.ListeleSorular(table,"TÜMÜ");
    		}
    	});
    	setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 906, 611);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(211, 10, 472, 64);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblKullancSorular = new JLabel("KULLANICI SORULARI");
        lblKullancSorular.setForeground(Color.WHITE);
        lblKullancSorular.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblKullancSorular.setBounds(40, 108, 328, 28);
        contentPane.add(lblKullancSorular);
        
        JButton btnGnder = new JButton("GÖNDER");
        btnGnder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
    		        int SoruNo = Integer.parseInt(textField.getText());
    		        String Soru = textField_1.getText();
    		        if (Soru.isEmpty()) {
        	            JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun ve bir resim seçin!");
        	            return; 
        	        }
    		        
    		        SorularDAO sorularDAO = new SorularDAO();
    		        sorularDAO.SoruCevapla(SoruNo, Soru);
        			sorularDAO.ListeleSorular(table, "TÜMÜ");
    		    } catch (NumberFormatException e1) {
    		        JOptionPane.showMessageDialog(null, "Geçerli bir değer giriniz.", 
    		                "Hata", JOptionPane.ERROR_MESSAGE);
    		    }
    		    textField.setText("");
    		    textField_1.setText("");
        	}
        });
        btnGnder.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnGnder.setBounds(641, 492, 102, 46);
        contentPane.add(btnGnder);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 146, 815, 297);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);
        
        JLabel lblSoruNo = new JLabel("Soru No :");
        lblSoruNo.setForeground(Color.WHITE);
        lblSoruNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSoruNo.setBounds(40, 500, 72, 20);
        contentPane.add(lblSoruNo);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(122, 492, 102, 44);
        contentPane.add(textField);
        
        JLabel lblCevap = new JLabel("Cevap :");
        lblCevap.setForeground(Color.WHITE);
        lblCevap.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblCevap.setBounds(241, 502, 72, 20);
        contentPane.add(lblCevap);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(304, 492, 327, 46);
        contentPane.add(textField_1);
        
        JButton btnSil = new JButton("SİL");
        btnSil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
    		        int SoruNo = Integer.parseInt(textField.getText());
    		        SorularDAO sorularDAO = new SorularDAO();
    		        sorularDAO.SoruSil(SoruNo);
        			sorularDAO.ListeleSorular(table, "TÜMÜ");
    		    } catch (NumberFormatException e1) {
    		        JOptionPane.showMessageDialog(null, "Geçerli bir değer giriniz.", 
    		                "Hata", JOptionPane.ERROR_MESSAGE);
    		    }
    		    textField.setText("");
    		    textField_1.setText("");
        	}
        });
        btnSil.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSil.setBounds(753, 492, 102, 46);
        contentPane.add(btnSil);
        
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"TÜMÜ", "CEVAPLANAN", "CEVAPLANMAYAN"}));
        comboBox.setToolTipText("");
        comboBox.setMaximumRowCount(10);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBox.setBounds(669, 108, 186, 28);
        contentPane.add(comboBox);
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
		ui.setNorthPane(null);
		
		comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFilter = (String) comboBox.getSelectedItem();
                SorularDAO sorularDAO = new SorularDAO();
                sorularDAO.ListeleSorular(table, selectedFilter);
            }
        });
    }
}
