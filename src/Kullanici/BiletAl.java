package Kullanici;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import dao.SeanslarDAO;
import model.SeansParametre;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BiletAl extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTable table;
    private JTextField textField_4;
    private JTextField textField_5;
    private static SeansParametre seansParametre;
    private JFrame anaMenuFrame;

    public BiletAl(JFrame anaMenuFrame) {
        this.anaMenuFrame = anaMenuFrame;
        initialize();
    }

    public void gizleAnamenu() {
        if (anaMenuFrame != null) {
            anaMenuFrame.dispose();  // Kapatmak için dispose() kullanıyoruz
        }

        JFrame odemeEkrani = new OdemeEkrani();
        odemeEkrani.setVisible(true);
    }
    
    public static void BiletAlEkraniBilgiAl(SeansParametre seansParametre) {
        BiletAl.seansParametre = seansParametre;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BiletAl frame = new BiletAl(new Anamenu());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initialize() {
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                SeanslarDAO seanslarDAO = new SeanslarDAO();
                seanslarDAO.ListeleSeanslar(table);
            }
        });
        setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 904, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(197, 10, 472, 64);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("Film Adı :");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(25, 104, 94, 20);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(162, 95, 291, 37);
        contentPane.add(textField);

        JLabel lblTarihi = new JLabel("Film Tarihi :");
        lblTarihi.setForeground(Color.WHITE);
        lblTarihi.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTarihi.setBounds(25, 155, 94, 20);
        contentPane.add(lblTarihi);

        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(162, 147, 291, 37);
        contentPane.add(textField_1);

        JLabel lblFilmSaati = new JLabel("Film Saati :");
        lblFilmSaati.setForeground(Color.WHITE);
        lblFilmSaati.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmSaati.setBounds(25, 214, 111, 20);
        contentPane.add(lblFilmSaati);

        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_2.setColumns(10);
        textField_2.setBounds(162, 203, 291, 37);
        contentPane.add(textField_2);

        JLabel lblBiletFiyat = new JLabel("Bilet Fiyatı :");
        lblBiletFiyat.setForeground(Color.WHITE);
        lblBiletFiyat.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblBiletFiyat.setBounds(25, 377, 127, 20);
        contentPane.add(lblBiletFiyat);

        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_3.setColumns(10);
        textField_3.setBounds(162, 314, 291, 37);
        contentPane.add(textField_3);

        JLabel lblBiletAdedi = new JLabel("Bilet Adedi :");
        lblBiletAdedi.setForeground(Color.WHITE);
        lblBiletAdedi.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblBiletAdedi.setBounds(25, 435, 145, 20);
        contentPane.add(lblBiletAdedi);

        JComboBox<Integer> comboBox = new JComboBox<>();
        comboBox.setEditable(true);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBox.setMaximumRowCount(10);
        comboBox.setModel(new DefaultComboBoxModel<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        comboBox.setToolTipText("");
        comboBox.setBounds(162, 427, 291, 37);
        contentPane.add(comboBox);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(514, 95, 349, 369);
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
                    textField_5.setText(String.valueOf(seansBilgi.getSalonNo()));
                    textField_3.setText(String.valueOf(seansBilgi.getKapasite()));
                    textField_4.setText(String.valueOf(seansBilgi.getBiletFiyati()));
                }
            }
        });

        JButton btnDevamEt = new JButton("ÖDEME YAP");
        btnDevamEt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (textField.getText().trim().isEmpty() || 
                    textField_1.getText().trim().isEmpty() || 
                    textField_2.getText().trim().isEmpty() || 
                    textField_5.getText().trim().isEmpty() || 
                    textField_4.getText().trim().isEmpty() || 
                    comboBox.getSelectedItem() == null) {

                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!", 
                                                  "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int biletAdedi = (int) comboBox.getSelectedItem();
                int kapasite = Integer.parseInt(textField_3.getText().trim());

                if (biletAdedi > kapasite) {
                    JOptionPane.showMessageDialog(null, "Yeterli kapasite yok.", 
                                                  "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(seansParametre != null) {
                    seansParametre.setFilmAdi(textField.getText().trim());
                    seansParametre.setFilmTarihi(textField_1.getText().trim());
                    seansParametre.setFilmSaati(textField_2.getText().trim());
                    seansParametre.setSalonNo(Integer.parseInt(textField_5.getText().trim()));
                    seansParametre.setKapasite(kapasite);
                    seansParametre.setBiletFiyati(Double.parseDouble(textField_4.getText().trim()));
                    seansParametre.setBiletAdedi(biletAdedi);
                    OdemeEkrani.OdemeEkraniBilgiAl(seansParametre);
                }

                gizleAnamenu();
            }
        });
        btnDevamEt.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDevamEt.setBounds(672, 504, 191, 46);
        contentPane.add(btnDevamEt);

        textField_4 = new JTextField();
        textField_4.setEditable(false);
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_4.setColumns(10);
        textField_4.setBounds(162, 369, 291, 37);
        contentPane.add(textField_4);

        JLabel lblKapasite = new JLabel("Kapasite :");
        lblKapasite.setForeground(Color.WHITE);
        lblKapasite.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblKapasite.setBounds(25, 322, 111, 20);
        contentPane.add(lblKapasite);

        JLabel lblSalonNo = new JLabel("Salon No :");
        lblSalonNo.setForeground(Color.WHITE);
        lblSalonNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSalonNo.setBounds(25, 269, 111, 20);
        contentPane.add(lblSalonNo);

        textField_5 = new JTextField();
        textField_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_5.setEditable(false);
        textField_5.setColumns(10);
        textField_5.setBounds(162, 261, 291, 37);
        contentPane.add(textField_5);

        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
        ui.setNorthPane(null);
    }

    public void setSeansBilgileri(SeansParametre seansBilgi) {
        textField.setText(seansBilgi.getFilmAdi());
        textField_1.setText(seansBilgi.getFilmTarihi());
        textField_2.setText(seansBilgi.getFilmSaati());
        textField_5.setText(String.valueOf(seansBilgi.getSalonNo()));
        textField_3.setText(String.valueOf(seansBilgi.getKapasite()));
        textField_4.setText(String.valueOf(seansBilgi.getBiletFiyati()));
    }
}


                