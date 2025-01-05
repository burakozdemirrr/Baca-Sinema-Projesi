package Admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import dao.FilmlerDAO;
import model.FilmParametre;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FilmEkle extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTable table;
    private JTextField textField_4;
    private JLabel lblFilmFragman_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FilmEkle frame = new FilmEkle();
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
    public FilmEkle() {
    	addInternalFrameListener(new InternalFrameAdapter() {
    		@Override
    		public void internalFrameOpened(InternalFrameEvent e) {
    			FilmlerDAO filmlerDAO = new FilmlerDAO();
    			filmlerDAO.ListeleFilmler(table);
    		}
    	});
        setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 909, 594);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setColumns(10);
        textField.setBounds(160, 88, 291, 37);
        contentPane.add(textField);
        
        JLabel lblNewLabel_1 = new JLabel("BACA SİNEMA");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(216, 10, 472, 64);
        contentPane.add(lblNewLabel_1);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(160, 141, 291, 37);
        contentPane.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_2.setColumns(10);
        textField_2.setBounds(160, 200, 291, 37);
        contentPane.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_3.setColumns(10);
        textField_3.setBounds(160, 263, 291, 37);
        contentPane.add(textField_3);
        
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textArea.setBounds(160, 322, 291, 54);
        contentPane.add(textArea);
        
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setLineWrap(true);
        textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textArea_1.setBounds(160, 397, 291, 75);
        contentPane.add(textArea_1);
        
        JLabel lblFilmzeti = new JLabel("Film Özeti :");
        lblFilmzeti.setForeground(Color.WHITE);
        lblFilmzeti.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmzeti.setBounds(23, 398, 94, 20);
        contentPane.add(lblFilmzeti);
        
        JLabel lblFilmOyuncular = new JLabel("Film Oyuncuları :");
        lblFilmOyuncular.setForeground(Color.WHITE);
        lblFilmOyuncular.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmOyuncular.setBounds(23, 323, 145, 20);
        contentPane.add(lblFilmOyuncular);
        
        JLabel lblFilmFragman = new JLabel("Film Fragmanı :");
        lblFilmFragman.setForeground(Color.WHITE);
        lblFilmFragman.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmFragman.setBounds(23, 271, 127, 20);
        contentPane.add(lblFilmFragman);
        
        JLabel lblFilmSresi = new JLabel("Film Süresi :");
        lblFilmSresi.setForeground(Color.WHITE);
        lblFilmSresi.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmSresi.setBounds(23, 211, 111, 20);
        contentPane.add(lblFilmSresi);
        
        JLabel lblFilmTr = new JLabel("Film Türü :");
        lblFilmTr.setForeground(Color.WHITE);
        lblFilmTr.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmTr.setBounds(23, 152, 94, 20);
        contentPane.add(lblFilmTr);
        
        JLabel lblNewLabel = new JLabel("Film Adı :");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(23, 96, 94, 20);
        contentPane.add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBounds(570, 88, 184, 172);
        contentPane.add(panel);
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(512, 322, 349, 91);
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
                    lblFilmFragman_1.setText(filmBilgi.getFilmResim());                    
                    
                    try {
                    	panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
                        panel.getGraphics().drawImage(javax.imageio.ImageIO.read(new File("src/resimler/" + filmBilgi.getFilmResim())), 0, 0, panel.getWidth(), panel.getHeight(), null);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        JButton btnFilmEkle = new JButton("FİLM EKLE");
        btnFilmEkle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String FilmAdi = textField.getText().trim();
            	String FilmTuru = textField_1.getText().trim();
            	String FilmSuresi = textField_2.getText().trim();
            	String FilmFragmani = textField_3.getText().trim();
            	String FilmOyunculari = textArea.getText().trim();
            	String FilmOzeti = textArea_1.getText().trim();
            	String FilmResmi =  lblFilmFragman_1.getText().trim();
            	
                if (FilmAdi.isEmpty() || FilmTuru.isEmpty() || FilmSuresi.isEmpty() ||
                		FilmFragmani.isEmpty() || FilmOyunculari.isEmpty() || FilmOzeti.isEmpty() || FilmResmi.equals("---")) {
                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun ve bir resim seçin!");
                } else {
                    FilmlerDAO filmlerDAO = new FilmlerDAO();
                    filmlerDAO.FilmKaydet(FilmAdi,FilmTuru,FilmSuresi,FilmFragmani,FilmOyunculari,FilmOzeti,FilmResmi);
                    filmlerDAO.ListeleFilmler(table);
                    textField.setText("");
        		    textField_1.setText("");
        		    textField_2.setText("");
        		    textField_3.setText("");
        		    textField_4.setText("");
        		    textArea.setText("");
        		    textArea_1.setText("");
        		    lblFilmFragman_1.setText("---");
        		    panel.repaint();
                }
            }
        });
        btnFilmEkle.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnFilmEkle.setBounds(512, 426, 349, 46);
        contentPane.add(btnFilmEkle);
        
        JButton btnFilmSil = new JButton("FİLM SİL");
        btnFilmSil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
    		        int FilmNo = Integer.parseInt(textField_4.getText());
    		        FilmlerDAO filmlerDAO = new FilmlerDAO();
    		        filmlerDAO.FilSil(FilmNo);
    		        filmlerDAO.ListeleFilmler(table);
    		        textField.setText("");
        		    textField_1.setText("");
        		    textField_2.setText("");
        		    textField_3.setText("");
        		    textField_4.setText("");
        		    textArea.setText("");
        		    textArea_1.setText("");
        		    lblFilmFragman_1.setText("---");
        		    panel.repaint();
    		    } catch (NumberFormatException e1) {
    		        JOptionPane.showMessageDialog(null, "Geçerli bir değer giriniz.", 
    		                "Hata", JOptionPane.ERROR_MESSAGE);
    		    }   		    
        	}
        });
        btnFilmSil.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnFilmSil.setBounds(716, 483, 145, 46);
        contentPane.add(btnFilmSil);
        
        JLabel lblFilmNo = new JLabel("Film No :");
        lblFilmNo.setForeground(Color.WHITE);
        lblFilmNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmNo.setBounds(23, 496, 94, 20);
        contentPane.add(lblFilmNo);
        
        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_4.setColumns(10);
        textField_4.setBounds(160, 488, 291, 37);
        contentPane.add(textField_4);
        
        JButton btnFilmGncelle = new JButton("FİLM GÜNCELLE");
        btnFilmGncelle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    try {
        	        String FilmAdi = textField.getText().trim();
        	        String FilmTuru = textField_1.getText().trim();
        	        String FilmSuresi = textField_2.getText().trim();
        	        String FilmFragmani = textField_3.getText().trim();
        	        String FilmOyunculari = textArea.getText().trim();
        	        String FilmOzeti = textArea_1.getText().trim();
        	        String FilmResmi = lblFilmFragman_1.getText().trim();
        	        int FilmNo = Integer.parseInt(textField_4.getText().trim());

        	        if (FilmAdi.isEmpty() || FilmTuru.isEmpty() || FilmSuresi.isEmpty() ||
        	                FilmFragmani.isEmpty() || FilmOyunculari.isEmpty() || FilmOzeti.isEmpty() || FilmResmi.equals("---")) {
        	            JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun ve bir resim seçin!");
        	            return; 
        	        }
        	        FilmlerDAO filmlerDAO = new FilmlerDAO();
        	        filmlerDAO.FilmGüncelle(FilmAdi, FilmTuru, FilmSuresi, FilmFragmani, FilmOyunculari, FilmOzeti, FilmResmi, FilmNo);
        	        filmlerDAO.ListeleFilmler(table);

        	        textField.setText("");
        	        textField_1.setText("");
        	        textField_2.setText("");
        	        textField_3.setText("");
        	        textField_4.setText("");
        	        textArea.setText("");
        	        textArea_1.setText("");
        	        lblFilmFragman_1.setText("---");
        	        panel.repaint();
        	    } catch (NumberFormatException e1) {
        	        JOptionPane.showMessageDialog(null, "Geçerli bir değer giriniz.",
        	                "Hata", JOptionPane.ERROR_MESSAGE);
        	    }
        	}

        });
        btnFilmGncelle.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnFilmGncelle.setBounds(512, 483, 194, 46);
        contentPane.add(btnFilmGncelle);       
        
        JButton btnFilmSil_1 = new JButton("...");
        btnFilmSil_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnFilmSil_1.setBounds(764, 232, 32, 27);
        btnFilmSil_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif", "bmp"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    File destFile = new File("src/resimler/" + selectedFile.getName());
                    
                    // Mevcut dosya kontrolü
                    if (destFile.exists()) {
                        JOptionPane.showMessageDialog(null, "Bu resim zaten kullanılıyor. Lütfen başka bir resim seçin.");
                    } else {
                        try {
                            Files.copy(selectedFile.toPath(), destFile.toPath());
                            lblFilmFragman_1.setText(selectedFile.getName());
                            panel.getGraphics().drawImage(javax.imageio.ImageIO.read(selectedFile), 0, 0, panel.getWidth(), panel.getHeight(), null);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        contentPane.add(btnFilmSil_1);

        
        lblFilmFragman_1 = new JLabel("---");
        lblFilmFragman_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblFilmFragman_1.setForeground(Color.WHITE);
        lblFilmFragman_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFilmFragman_1.setBounds(553, 263, 222, 20);
        contentPane.add(lblFilmFragman_1);

        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI(); 
        ui.setNorthPane(null);
    }
}

