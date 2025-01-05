package Kullanici;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
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
import javax.swing.JScrollPane;

public class SoruSor extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SoruSor frame = new SoruSor();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SoruSor() {
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                SorularDAO sorularDAO = new SorularDAO();
                sorularDAO.ListeleSorular(table, "TÜMÜ");
            }
        });
        setBorder(null);
        setBackground(new Color(0, 128, 0));
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 903, 601);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(201, 10, 472, 64);
        contentPane.add(lblNewLabel_1);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(39, 113, 815, 264);
        contentPane.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        JLabel lblIlemGemii_1_2 = new JLabel("Soru :");
        lblIlemGemii_1_2.setForeground(Color.WHITE);
        lblIlemGemii_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIlemGemii_1_2.setBounds(39, 417, 73, 28);
        contentPane.add(lblIlemGemii_1_2);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(111, 417, 743, 37);
        contentPane.add(textField);
        
        JButton btnGnder = new JButton("GÖNDER");
        btnGnder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Soru = textField.getText().trim();
                
                if (Soru.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tüm Alanları Doldurmanız Gerekmektedir",
                            "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                SorularDAO sorularDAO = new SorularDAO();
                sorularDAO.SoruKaydet(Soru);
                sorularDAO.ListeleSorular(table, "TÜMÜ");
                textField.setText("");
            }
        });
        btnGnder.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnGnder.setBounds(727, 485, 127, 46);
        contentPane.add(btnGnder);
        
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"TÜMÜ", "CEVAPLANAN", "CEVAPLANMAYAN"}));
        comboBox.setToolTipText("");
        comboBox.setMaximumRowCount(10);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBox.setBounds(668, 75, 186, 28);
        contentPane.add(comboBox);
        
        JLabel lblSorular = new JLabel("SORULAR");
        lblSorular.setForeground(Color.WHITE);
        lblSorular.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblSorular.setBounds(39, 75, 328, 28);
        contentPane.add(lblSorular);
        
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
