package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Kullanici;
import util.DatabaseConnection;
import Admin.AdminAnamenu; 
import Kullanici.Anamenu;
import Kullanici.OdemeEkrani;
import Sinema.Ayarlar;

public class KullaniciDAO {
	
	private static AdminAnamenu adminAnamenu = null;
	private static Anamenu anamenu = null;	
	
	private static boolean GirisKontrol = false;	
	public static boolean GirisBasarili() { 
		return GirisKontrol; }			
	
	//Şifre unuttum metodu
	public String SifreUnuttum(String kullaniciAdi, String email) {
	    String sql = "SELECT Sifre FROM Hesaplar WHERE KullaniciAdi COLLATE Latin1_General_BIN = ? AND eMail COLLATE Latin1_General_BIN = ?";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setString(1, kullaniciAdi);
	        statement.setString(2, email);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                return resultSet.getString("Sifre");
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Şifre sorgulama sırasında hata: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	    return null;
	}
	
	// Kullanıcı adını kontrol eden metod
    public boolean kullaniciAdiVarMi(String kullaniciAdi) {
        String sql = "SELECT COUNT(*) FROM Hesaplar WHERE KullaniciAdi COLLATE Latin1_General_BIN = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, kullaniciAdi);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Kullanıcı adı var mı kontrolü
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kullanıcı adını kontrol ederken hata: " + e.getMessage(), 
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Kullanıcı kaydetme metodu
    public void kullaniciKaydet(Kullanici kullanici) {
        if (kullaniciAdiVarMi(kullanici.getKullaniciAdi())) {
            JOptionPane.showMessageDialog(null, "Bu Kullanıcı Adı Zaten Kullanılıyor ", 
                    "Uyarı", JOptionPane.WARNING_MESSAGE);           
            return; // Metodu sonlandır
        }

        String sql = "INSERT INTO Hesaplar (AdSoyad, KullaniciAdi, eMail, Sifre, HesapTuru) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, kullanici.getAdSoyad());
            statement.setString(2, kullanici.getKullaniciAdi());
            statement.setString(3, kullanici.geteMail());
            statement.setString(4, kullanici.getSifre());
            statement.setString(5, "Kullanıcı");
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Kayıt Oluşturuldu. BACA Ailesine Hoşgeldin. ", 
                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);  
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kayıt Oluşturulamadı. ", 
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Kullanıcı giriş metodu
    public void kullaniciGirisi(String kullaniciAdi, String sifre) {
        String sql = "SELECT * FROM Hesaplar WHERE KullaniciAdi COLLATE Latin1_General_BIN = ? AND Sifre COLLATE Latin1_General_BIN = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, kullaniciAdi);
            statement.setString(2, sifre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hesapTuru = resultSet.getString("HesapTuru");
                    Kullanici kullanici = new Kullanici();
                    kullanici.setKullaniciNo(resultSet.getInt("KullaniciNo"));
                	kullanici.setAdSoyad(resultSet.getString("AdSoyad"));
                	kullanici.setKullaniciAdi(resultSet.getString("KullaniciAdi"));
                	kullanici.seteMail(resultSet.getString("eMail"));
                	kullanici.setSifre(resultSet.getString("Sifre"));
                	Ayarlar.AyarlarMetinYaz(kullanici);
                	DilekSikayetDAO.TalepEdenAl(kullanici);
                	SorularDAO.SoruSoranAl(kullanici);
                	OdemeEkrani.OdemeEkraniKullaniciAl(kullanici);
                	
                    if ("Admin".equalsIgnoreCase(hesapTuru)) {
                    	adminAnamenu = new AdminAnamenu();
                    	adminAnamenu.setVisible(true);
                    	GirisKontrol = true;
                    } else if ("Kullanıcı".equalsIgnoreCase(hesapTuru)) {                      	
                    	anamenu = new Anamenu();
                    	anamenu.setVisible(true);
                    	GirisKontrol = true;
                    } else {
                    	GirisKontrol = false;
                        JOptionPane.showMessageDialog(null, "Geçersiz Hesap Türü!", 
                                "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                	GirisKontrol = false;
                    JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre yanlış!", 
                            "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
        	GirisKontrol = false;
            JOptionPane.showMessageDialog(null, "Giriş işlemi sırasında hata: " + e.getMessage(), 
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void kullaniciGuncelle(Kullanici kullanici) {
    	if(Ayarlar.KullaniciDegistiBasarili()) {
	    	if (kullaniciAdiVarMi(kullanici.getKullaniciAdi())) {
	            JOptionPane.showMessageDialog(null, "Bu Kullanıcı Adı Zaten Kullanılıyor ", 
	                    "Uyarı", JOptionPane.WARNING_MESSAGE);
	            Ayarlar.EskiyeDon();
	            return; // Metodu sonlandır
	        }
    	}

        String sql = "UPDATE Hesaplar SET AdSoyad = ?, KullaniciAdi = ?, eMail = ?, Sifre = ? WHERE KullaniciNo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, kullanici.getAdSoyad());
            statement.setString(2, kullanici.getKullaniciAdi());
            statement.setString(3, kullanici.geteMail());
            statement.setString(4, kullanici.getSifre());
            statement.setInt(5, kullanici.getKullaniciNo());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Güncelleme Yapıldı.", 
                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Güncelleme Yapılamadı: " + e.getMessage(), 
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ListeleKullanicilar(JTable table) {
        String sql = "SELECT * FROM Hesaplar WHERE HesapTuru = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "Kullanıcı");
            
            ResultSet resultSet = statement.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel() {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            model.addColumn("KullaniciNo");
            model.addColumn("AdSoyad");
            model.addColumn("KullaniciAdi");
            model.addColumn("eMail");
            model.addColumn("Sifre");
            
            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getInt("KullaniciNo"),
                    resultSet.getString("AdSoyad"),
                    resultSet.getString("KullaniciAdi"),
                    resultSet.getString("eMail"),
                    resultSet.getString("Sifre")
                });
           }
           table.setModel(model);
           table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
           table.getColumnModel().getColumn(0).setPreferredWidth(70);
           table.getColumnModel().getColumn(1).setPreferredWidth(185);
           table.getColumnModel().getColumn(2).setPreferredWidth(178);
           table.getColumnModel().getColumn(3).setPreferredWidth(170);
           table.getColumnModel().getColumn(4).setPreferredWidth(185);
           
           table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           table.getTableHeader().setReorderingAllowed(false);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kullanıcılar getirilemedi: " + e.getMessage(),
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void KullaniciSil(int KullaniciNo) {
	    String checkSql = "SELECT COUNT(*) FROM Hesaplar WHERE KullaniciNo = ?";
	    String deleteSql = "DELETE FROM Hesaplar WHERE KullaniciNo = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	         PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

	        checkStatement.setInt(1, KullaniciNo);
	        ResultSet resultSet = checkStatement.executeQuery();

	        if (resultSet.next() && resultSet.getInt(1) > 0) {
	            deleteStatement.setInt(1, KullaniciNo);
	            deleteStatement.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Kullanıcı Silindi.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Silinecek kayıt bulunamadı.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Kullanici Silinemedi: " + e.getMessage(), 
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
