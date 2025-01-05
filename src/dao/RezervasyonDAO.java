package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import util.DatabaseConnection;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.sql.ResultSet;

public class RezervasyonDAO {
	
	public void RezervasyonEkle(String AdSoyad, String KullaniciAdi, String FilmAdi, String FilmTarihi, String FilmSaati, int SalonNo, double BiletFiyati, int BiletAdedi) {

	    String sql = "INSERT INTO Rezervasyonlar (AdSoyad, KullaniciAdi, FilmAdi, FilmTarihi, FilmSaati, SalonNo, BiletFiyati, BiletAdedi) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, AdSoyad);
	        statement.setString(2, KullaniciAdi);
	        statement.setString(3, FilmAdi);
	        statement.setString(4, FilmTarihi);
	        statement.setString(5, FilmSaati);
	        statement.setInt(6, SalonNo);
	        statement.setDouble(7, BiletFiyati);
	        statement.setInt(8, BiletAdedi);
	        statement.executeUpdate();

	        JOptionPane.showMessageDialog(null, "Ödeme Onaylandı. Rezervasyon Oluşturuldu.", 
	                                      "Bilgi", JOptionPane.INFORMATION_MESSAGE);

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Rezervasyon Oluşturulamadı.", 
	                                      "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}

	public void RezervasyonArama(JTable table, String isim, String baslangicTarihi, String bitisTarihi) {
	    String sql = "SELECT RezervasyonNo, AdSoyad, KullaniciAdi, FilmAdi, FilmTarihi, FilmSaati, SalonNo, BiletFiyati, BiletAdedi FROM Rezervasyonlar WHERE " +
	            "(AdSoyad LIKE ? OR ? IS NULL OR ? = '') AND " +
	            "(FilmTarihi >= ? OR ? IS NULL OR ? = '') AND " +
	            "(FilmTarihi <= ? OR ? IS NULL OR ? = '')";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, "%" + isim + "%");
	        statement.setString(2, isim);
	        statement.setString(3, isim);
	        statement.setString(4, baslangicTarihi);
	        statement.setString(5, baslangicTarihi);
	        statement.setString(6, baslangicTarihi);
	        statement.setString(7, bitisTarihi);
	        statement.setString(8, bitisTarihi);
	        statement.setString(9, bitisTarihi);

	        ResultSet resultSet = statement.executeQuery();

	        DefaultTableModel model = new DefaultTableModel() {
	            private static final long serialVersionUID = 1L;

	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        model.addColumn("RezervasyonNo");
	        model.addColumn("AdSoyad");
	        model.addColumn("KullaniciAdi");
	        model.addColumn("FilmAdi");
	        model.addColumn("FilmTarihi");
	        model.addColumn("FilmSaati");
	        model.addColumn("SalonNo");
	        model.addColumn("BiletFiyati");
	        model.addColumn("BiletAdedi");

	        while (resultSet.next()) {
	            model.addRow(new Object[]{
	                    resultSet.getInt("RezervasyonNo"),
	                    resultSet.getString("AdSoyad"),
	                    resultSet.getString("KullaniciAdi"),
	                    resultSet.getString("FilmAdi"),
	                    resultSet.getString("FilmTarihi"),
	                    resultSet.getString("FilmSaati"),
	                    resultSet.getInt("SalonNo"),
	                    resultSet.getDouble("BiletFiyati"),
	                    resultSet.getInt("BiletAdedi")
	            });
	        }

	        table.setModel(model);
	        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        table.getColumnModel().getColumn(0).setPreferredWidth(100);
	        table.getColumnModel().getColumn(1).setPreferredWidth(200);
	        table.getColumnModel().getColumn(2).setPreferredWidth(150);
	        table.getColumnModel().getColumn(3).setPreferredWidth(200);
	        table.getColumnModel().getColumn(4).setPreferredWidth(150);
	        table.getColumnModel().getColumn(5).setPreferredWidth(100);
	        table.getColumnModel().getColumn(6).setPreferredWidth(100);
	        table.getColumnModel().getColumn(7).setPreferredWidth(100);
	        table.getColumnModel().getColumn(8).setPreferredWidth(100);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.getTableHeader().setReorderingAllowed(false);

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Rezervasyonlar getirilemedi: " + e.getMessage(),
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}

	public void RezervasyonSil(int RezervasyonNo) {
	    String checkSql = "SELECT BiletAdedi, FilmAdi, FilmTarihi, FilmSaati, SalonNo, AdSoyad, KullaniciAdi " +
	                      "FROM Rezervasyonlar WHERE RezervasyonNo = ? " +
	                      "GROUP BY BiletAdedi, FilmAdi, FilmTarihi, FilmSaati, SalonNo, AdSoyad, KullaniciAdi";
	    String deleteSql = "DELETE FROM Rezervasyonlar WHERE RezervasyonNo = ?";
	    String updateSql = "UPDATE Seanslar SET Kapasite = Kapasite + ? WHERE FilmAdi = ? AND FilmTarihi = ? AND FilmSaati = ? AND SalonNo = ?";
	    String emailSql = "SELECT eMail FROM Hesaplar WHERE KullaniciAdi = ?";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	         PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
	         PreparedStatement updateStatement = connection.prepareStatement(updateSql);
	         PreparedStatement emailStatement = connection.prepareStatement(emailSql)) {

	        checkStatement.setInt(1, RezervasyonNo);
	        ResultSet resultSet = checkStatement.executeQuery();

	        if (resultSet.next()) {
	            int biletAdedi = resultSet.getInt("BiletAdedi");
	            String filmAdi = resultSet.getString("FilmAdi");
	            String filmTarihi = resultSet.getString("FilmTarihi");
	            String filmSaati = resultSet.getString("FilmSaati");
	            int salonNo = resultSet.getInt("SalonNo");
	            String adSoyad = resultSet.getString("AdSoyad");
	            String kullaniciAdi = resultSet.getString("KullaniciAdi");

	            emailStatement.setString(1, kullaniciAdi);
	            ResultSet emailResultSet = emailStatement.executeQuery();
	            String email = null;
	            if (emailResultSet.next()) {
	                email = emailResultSet.getString("eMail");
	            }

	            if (email == null) {
	                JOptionPane.showMessageDialog(null, "Kullanıcıya ait e-posta adresi bulunamadı.", 
	                        "Hata", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            deleteStatement.setInt(1, RezervasyonNo);
	            deleteStatement.executeUpdate();

	            updateStatement.setInt(1, biletAdedi);
	            updateStatement.setString(2, filmAdi);
	            updateStatement.setString(3, filmTarihi);
	            updateStatement.setString(4, filmSaati);
	            updateStatement.setInt(5, salonNo);
	            updateStatement.executeUpdate();

	            JOptionPane.showMessageDialog(null, "Rezervasyon Silindi ve Kapasite Güncellendi.",
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);

	            // E-posta gönderimi
	            String gonderen = "okulicinacildi0@gmail.com";
	            String sifreGonderen = "t ba v k r l y a o ye i k f e";

	            // SMTP sunucusu ayarları
	            Properties properties = new Properties();
	            properties.put("mail.smtp.auth", "true");
	            properties.put("mail.smtp.starttls.enable", "true");
	            properties.put("mail.smtp.host", "smtp.gmail.com");
	            properties.put("mail.smtp.port", "587");

	            // Oturum oluşturma
	            Session session = Session.getInstance(properties, new Authenticator() {
	                @Override
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(gonderen, sifreGonderen);
	                }
	            });

	            try {
	                // Mesaj oluşturma
	                MimeMessage message = new MimeMessage(session);
	                message.setFrom(new InternetAddress(gonderen)); // Gönderen
	                message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); // Alıcı
	                message.setSubject("Rezervasyon İptali"); // Konu

	                String emailContent = "BACA SİNEMA\n\n" +
	                                      "Sayın " + adSoyad + ",\n\n" +
	                                      "Tarih: " + filmTarihi + "\n" +
	                                      "Saat: " + filmSaati + "\n" +
	                                      "Bilgilendirme: " + filmAdi + " rezervasyonunuz iptal edilmiştir.\n\n" +
	                                      "Aksaklık için özür dileriz. Para iadeniz gerçekleştirilmiştir.\n\n" +
	                                      "Daha fazla bilgi için bize bu numaradan ulaşabilirsiniz: 0212-000-0000\n\n" +
	                                      "Teşekkür eder, iyi günler dileriz.";	                                    

	                message.setText(emailContent); // İçerik

	                // Mesajı gönder
	                Transport.send(message);
	                JOptionPane.showMessageDialog(null, "Rezervasyon iptali e-postayla bildirildi.",
	                        "Bilgi", JOptionPane.INFORMATION_MESSAGE);

	            } catch (Exception error) {
	                error.printStackTrace();
	                JOptionPane.showMessageDialog(null, "E-posta gönderme hatası.", "Hata", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Silinecek kayıt bulunamadı.",
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Rezervasyon Silinemedi: " + e.getMessage(),
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}




	
	public int KapasiteKontrol(int seansNo) {
	    String sql = "SELECT Kapasite FROM Seanslar WHERE SeansNo = ?";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, seansNo);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            return resultSet.getInt("Kapasite");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Bilet stoğu kontrolü başarısız: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	    return -1;
	}
	
	public void KapasiteGuncelle(int seansNo, int yeniStok) {
	    String sql = "UPDATE Seanslar SET Kapasite = ? WHERE SeansNo = ?";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, yeniStok);
	        statement.setInt(2, seansNo);
	        statement.executeUpdate();
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Bilet stoğu güncelleme başarısız: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
