package Sinema;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Acilis extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private static GirisEkrani girisEkrani = null;
    private static KayitOl kayitOl = null;
    private static SifreUnuttum sifreUnuttum = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acilis frame = new Acilis();
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
	public Acilis() {
		setTitle("BACA SİNEMA");
		setBackground(new Color(0, 128, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 429);
		
		layeredPane = new JLayeredPane();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mnıtmNewMenuItem = new JMenuItem("BACA");
		mnıtmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (girisEkrani != null) girisEkrani.setVisible(false);
                if (kayitOl != null) kayitOl.setVisible(false);               
                layeredPane.repaint();
                layeredPane.revalidate();
			}
		});
		mnıtmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mnıtmNewMenuItem.setForeground(new Color(0, 128, 0));
		mnıtmNewMenuItem.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(mnıtmNewMenuItem);
		
		JMenuItem mnıtmNewMenuItem_1 = new JMenuItem("GİRİŞ YAP");
		mnıtmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (girisEkrani != null) {
					girisEkrani.dispose();
                }
				girisEkrani = new GirisEkrani();
				girisEkrani.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(girisEkrani, JLayeredPane.PALETTE_LAYER);
                girisEkrani.setVisible(true);
                girisEkrani.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(girisEkrani);
                layeredPane.repaint();
                layeredPane.revalidate();
                girisEkrani.repaint();
			}
		});
		mnıtmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnıtmNewMenuItem_1.setForeground(new Color(0, 128, 0));
		mnıtmNewMenuItem_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(mnıtmNewMenuItem_1);
		
		JMenuItem mnıtmNewMenuItem_2 = new JMenuItem("KAYIT OL");
		mnıtmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (kayitOl != null) {
					kayitOl.dispose();
                }
				kayitOl = new KayitOl();
				kayitOl.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(kayitOl, JLayeredPane.PALETTE_LAYER);
                kayitOl.setVisible(true);
                kayitOl.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(kayitOl);
                layeredPane.repaint();
                layeredPane.revalidate();
                kayitOl.repaint();
			}
		});
		mnıtmNewMenuItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		mnıtmNewMenuItem_2.setForeground(new Color(0, 128, 0));
		mnıtmNewMenuItem_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(mnıtmNewMenuItem_2);
		
		JMenuItem mnıtmNewMenuItem_3 = new JMenuItem("ÇIKIŞ YAP");
		mnıtmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dispose();
			}
		});
		
		JMenuItem mnıtmNewMenuItem_4 = new JMenuItem("UNUTTUM");
		mnıtmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sifreUnuttum != null) {
					sifreUnuttum.dispose();
                }
				sifreUnuttum = new SifreUnuttum();
				sifreUnuttum.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.add(sifreUnuttum, JLayeredPane.PALETTE_LAYER);
                sifreUnuttum.setVisible(true);
                sifreUnuttum.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                layeredPane.moveToFront(sifreUnuttum);
                layeredPane.repaint();
                layeredPane.revalidate();
                sifreUnuttum.repaint();
			}
		});
		mnıtmNewMenuItem_4.setHorizontalAlignment(SwingConstants.CENTER);
		mnıtmNewMenuItem_4.setForeground(new Color(0, 128, 0));
		mnıtmNewMenuItem_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(mnıtmNewMenuItem_4);
		mnıtmNewMenuItem_3.setForeground(new Color(0, 128, 0));
		mnıtmNewMenuItem_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(mnıtmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setForeground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("HOŞGELDİNİZ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel_1.setBounds(77, 62, 495, 222);
		layeredPane.add(lblNewLabel_1);
		
		setLocationRelativeTo(null);
	}
}
