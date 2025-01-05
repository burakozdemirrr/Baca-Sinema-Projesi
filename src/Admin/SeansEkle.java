package Admin;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.MaskFormatter;
import dao.SeanslarDAO;
import model.SeansParametre;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeansEkle extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTable table;
    private JTextField textField_6;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SeansEkle frame = new SeansEkle();
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
    public SeansEkle() {
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                SeanslarDAO seanslarDAO = new SeanslarDAO();
                seanslarDAO.ListeleSeanslar(table);         
            }
        });
        setBorder(null);
        setBackground(new Color(0, 128, 0));
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 912, 600);
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
        
        JLabel lblNewLabel = new JLabel("Film Adı :");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(29, 117, 94, 20);
        contentPane.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(166, 108, 291, 37);
        contentPane.add(textField);
        
        JLabel lblTarihi = new JLabel("Film Tarihi :");
        lblTarihi.setForeground(Color.WHITE);
        lblTarihi.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTarihi.setBounds(29, 168, 94, 20);
        contentPane.add(lblTarihi);
        
        try {
            MaskFormatter dateMask = new MaskFormatter("##-##-####");
            dateMask.setPlaceholderCharacter('_');
            textField_1 = new JFormattedTextField(dateMask);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(166, 160, 291, 37);
        contentPane.add(textField_1);

        try {
            MaskFormatter timeMask = new MaskFormatter("##.##");
            timeMask.setPlaceholderCharacter('_');
            textField_2 = new JFormattedTextField(timeMask);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_2.setColumns(10);
        textField_2.setBounds(166, 216, 291, 37);
        contentPane.add(textField_2);

        
        JLabel lblFilmSaati = new JLabel("Film Saati :");
        lblFilmSaati.setForeground(Color.WHITE);
        lblFilmSaati.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmSaati.setBounds(29, 227, 111, 20);
        contentPane.add(lblFilmSaati);
        
        JLabel lblSalonNo = new JLabel("Salon No :");
        lblSalonNo.setForeground(Color.WHITE);
        lblSalonNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSalonNo.setBounds(29, 282, 111, 20);
        contentPane.add(lblSalonNo);
        
        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_4.setColumns(10);
        textField_4.setBounds(166, 327, 291, 37);
        contentPane.add(textField_4);
        
        JLabel lblKapasite = new JLabel("Kapasite :");
        lblKapasite.setForeground(Color.WHITE);
        lblKapasite.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblKapasite.setBounds(29, 335, 111, 20);
        contentPane.add(lblKapasite);
        
        JLabel lblBiletFiyat = new JLabel("Bilet Fiyatı :");
        lblBiletFiyat.setForeground(Color.WHITE);
        lblBiletFiyat.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblBiletFiyat.setBounds(29, 390, 127, 20);
        contentPane.add(lblBiletFiyat);
        
        textField_5 = new JTextField();
        textField_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_5.setColumns(10);
        textField_5.setBounds(166, 382, 291, 37);
        contentPane.add(textField_5);
        
        JComboBox<Integer> comboBox_1 = new JComboBox<Integer>();
        comboBox_1.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4, 5}));
        comboBox_1.setToolTipText("");
        comboBox_1.setMaximumRowCount(10);
        comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBox_1.setBounds(166, 274, 291, 37);
        contentPane.add(comboBox_1);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(518, 108, 349, 369);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                int SeansNo = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                SeanslarDAO seanslarDAO = new SeanslarDAO();
                SeansParametre seansBilgi = seanslarDAO.SeansBilgileri(SeansNo);

                if (seansBilgi != null) {
                    textField.setText(seansBilgi.getFilmAdi());
                    textField_1.setText(seansBilgi.getFilmTarihi());
                    textField_2.setText(seansBilgi.getFilmSaati());
                    comboBox_1.setSelectedItem(seansBilgi.getSalonNo());
                    textField_4.setText(String.valueOf(seansBilgi.getKapasite()));
                    textField_5.setText(String.valueOf(seansBilgi.getBiletFiyati()));
                }
            }
        });
        
        JButton btnSeansEkle = new JButton("SEANS EKLE");
        btnSeansEkle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
	                String FilmAdi = textField.getText().trim();
	                String FilmTarihi = textField_1.getText().trim();
	                String FilmSaati = textField_2.getText().trim();
	                int SalonNo = (int) comboBox_1.getSelectedItem();
	                int Kapasite = Integer.parseInt(textField_4.getText().trim());
	                Double BiletFiyati = Double.parseDouble(textField_5.getText().trim());
	                
	                if (FilmAdi.isEmpty() || FilmTarihi.isEmpty() || FilmSaati.isEmpty() || 
	                        textField_4.getText().trim().isEmpty() || textField_5.getText().trim().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!");
	                } else {
	                    SeanslarDAO seanslarDAO = new SeanslarDAO();
	                    seanslarDAO.SeansEkle(FilmAdi, FilmTarihi, FilmSaati, SalonNo, Kapasite, BiletFiyati);
	                    seanslarDAO.ListeleSeanslar(table);
	                    textField.setText("");
	                    textField_1.setText("");
	                    textField_2.setText("");                   
	                    textField_4.setText("");
	                    textField_5.setText("");
	                }
            	} catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Geçerli bir sayı giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSeansEkle.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSeansEkle.setBounds(29, 440, 428, 37);
        contentPane.add(btnSeansEkle);
        
        JLabel lblSeansNo = new JLabel("Seans No :");
        lblSeansNo.setForeground(Color.WHITE);
        lblSeansNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSeansNo.setBounds(29, 513, 127, 20);
        contentPane.add(lblSeansNo);

        textField_6 = new JTextField();
        textField_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_6.setColumns(10);
        textField_6.setBounds(166, 502, 291, 37);
        contentPane.add(textField_6);

        JButton btnSeansSil = new JButton("SEANS SİL");
        btnSeansSil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int SeansNo = Integer.parseInt(textField_6.getText().trim());
                    SeanslarDAO seanslarDAO = new SeanslarDAO();
                    seanslarDAO.SeansSil(SeansNo);
                    seanslarDAO.ListeleSeanslar(table);
                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");                   
                    textField_4.setText("");
                    textField_5.setText("");
                    textField_6.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Geçerli bir Seans No giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSeansSil.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSeansSil.setBounds(726, 502, 141, 37);
        contentPane.add(btnSeansSil);

        JButton btnSeansGuncelle = new JButton("SEANS GÜNCELLE");
        btnSeansGuncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String FilmAdi = textField.getText().trim();
                    String FilmTarihi = textField_1.getText().trim();
                    String FilmSaati = textField_2.getText().trim();
                    int SalonNo = (int) comboBox_1.getSelectedItem();
                    int Kapasite = Integer.parseInt(textField_4.getText().trim());
                    double BiletFiyati = Double.parseDouble(textField_5.getText().trim());
                    int SeansNo = Integer.parseInt(textField_6.getText().trim());

                    if (FilmAdi.isEmpty() || FilmTarihi.isEmpty() || FilmSaati.isEmpty() ||
                            textField_4.getText().trim().isEmpty() || textField_5.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!");
                    } else {
                        SeanslarDAO seanslarDAO = new SeanslarDAO();
                        seanslarDAO.SeansGuncelle(SeansNo, FilmAdi, FilmTarihi, FilmSaati, SalonNo, Kapasite, BiletFiyati);
                        seanslarDAO.ListeleSeanslar(table);
                        textField.setText("");
                        textField_1.setText("");
                        textField_2.setText("");
                        textField_4.setText("");
                        textField_5.setText("");
                        textField_6.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Geçerli bir değer giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSeansGuncelle.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSeansGuncelle.setBounds(519, 502, 186, 37);
        contentPane.add(btnSeansGuncelle);       
        
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
        ui.setNorthPane(null);
    }
}
