package Kullanici;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import dao.FilmlerDAO;
import dao.SeanslarDAO;
import model.FilmParametre;
import model.SeansParametre;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Filmler extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextArea textArea;
    private JTextArea textArea_1;
    private JPanel panel;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Filmler frame = new Filmler(new Anamenu());
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
    public Filmler(JFrame anaMenuFrame) {
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                FilmlerDAO filmlerDAO = new FilmlerDAO();
                filmlerDAO.ListeleFilmler(table);
            }
        });
        setBorder(null);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setBounds(100, 100, 907, 603);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(199, 10, 472, 64);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("Film Adı :");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(22, 97, 94, 20);
        contentPane.add(lblNewLabel);

        JLabel lblFilmTr = new JLabel("Film Türü :");
        lblFilmTr.setForeground(Color.WHITE);
        lblFilmTr.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmTr.setBounds(22, 152, 94, 20);
        contentPane.add(lblFilmTr);

        JLabel lblFilmSresi = new JLabel("Film Süresi :");
        lblFilmSresi.setForeground(Color.WHITE);
        lblFilmSresi.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmSresi.setBounds(22, 211, 111, 20);
        contentPane.add(lblFilmSresi);

        JLabel lblFilmFragman = new JLabel("Film Fragmanı :");
        lblFilmFragman.setForeground(Color.WHITE);
        lblFilmFragman.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmFragman.setBounds(22, 274, 127, 20);
        contentPane.add(lblFilmFragman);

        JLabel lblFilmOyuncular = new JLabel("Film Oyuncuları :");
        lblFilmOyuncular.setForeground(Color.WHITE);
        lblFilmOyuncular.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmOyuncular.setBounds(22, 322, 145, 20);
        contentPane.add(lblFilmOyuncular);

        JLabel lblFilmzeti = new JLabel("Film Özeti :");
        lblFilmzeti.setForeground(Color.WHITE);
        lblFilmzeti.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmzeti.setBounds(22, 414, 94, 20);
        contentPane.add(lblFilmzeti);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBounds(159, 88, 331, 37);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(159, 141, 331, 37);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_2.setColumns(10);
        textField_2.setBounds(159, 200, 331, 37);
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_3.setColumns(10);
        textField_3.setBounds(159, 263, 331, 37);
        contentPane.add(textField_3);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textArea.setBounds(159, 322, 331, 69);
        contentPane.add(textArea);

        textArea_1 = new JTextArea();
        textArea_1.setEditable(false);
        textArea_1.setLineWrap(true);
        textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textArea_1.setBounds(159, 414, 331, 120);
        contentPane.add(textArea_1);

        panel = new JPanel();
        panel.setBounds(612, 88, 184, 212);
        contentPane.add(panel);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_4.setColumns(10);
        textField_4.setBounds(528, 322, 268, 40);
        contentPane.add(textField_4);

        JButton btnAra = new JButton("Ara");
        btnAra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FilmlerDAO filmlerDAO = new FilmlerDAO();
                String ArananFilm = textField_4.getText().trim();
                if(ArananFilm.isEmpty()) {
                    filmlerDAO.ListeleFilmler(table);
                }
                else {
                    filmlerDAO.FilmArama(table, ArananFilm);
                }
            }
        });
        btnAra.setForeground(Color.BLACK);
        btnAra.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAra.setBounds(806, 322, 71, 41);
        contentPane.add(btnAra);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(528, 372, 349, 162);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                int filmNo = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                FilmlerDAO filmlerDAO = new FilmlerDAO();
                FilmParametre filmBilgi = filmlerDAO.FilmBilgileri(filmNo);

                if (filmBilgi != null) {
                    textField.setText(filmBilgi.getFilmAdi());
                    textField_1.setText(filmBilgi.getFilmTuru());
                    textField_2.setText(filmBilgi.getFilmSuresi());
                    textField_3.setText(filmBilgi.getFilmFragmani());
                    textArea.setText(filmBilgi.getFilmOyunculari());
                    textArea_1.setText(filmBilgi.getFilmOzeti());
                    try {
                        panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
                        panel.getGraphics().drawImage(javax.imageio.ImageIO.read(new File("src/resimler/" + filmBilgi.getFilmResim())), 0, 0, panel.getWidth(), panel.getHeight(), null);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                if (e.getClickCount() == 2) {
                    String filmAdi = table.getValueAt(selectedRow, 1).toString();
                    SeanslarDAO seanslarDAO = new SeanslarDAO();
                    if (seanslarDAO.FilmSeansiVarMi(filmAdi)) {
                        if (Anamenu.biletAl != null) {
                            Anamenu.biletAl.dispose();
                        }
                        Anamenu.biletAl = new BiletAl(anaMenuFrame);
                        Anamenu.biletAl.setBounds(0, 0, Anamenu.layeredPane.getWidth(), Anamenu.layeredPane.getHeight());
                        Anamenu.layeredPane.add(Anamenu.biletAl, JLayeredPane.PALETTE_LAYER);
                        Anamenu.biletAl.setVisible(true);
                        Anamenu.biletAl.setBounds(0, 0, Anamenu.layeredPane.getWidth(), Anamenu.layeredPane.getHeight());
                        Anamenu.layeredPane.moveToFront(Anamenu.biletAl);
                        Anamenu.layeredPane.repaint();
                        Anamenu.layeredPane.revalidate();
                        Anamenu.biletAl.repaint();
                        SeansParametre seans = seanslarDAO.getSeansBilgileri(filmAdi);
                        Anamenu.biletAl.setSeansBilgileri(seans);
                    } else {
                        JOptionPane.showMessageDialog(null, "Bu film için seans bulunamadı.", "Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }
}
