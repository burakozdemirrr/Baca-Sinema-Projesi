package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Kullanici.BiletAl;
import model.SeansParametre;
import util.DatabaseConnection;

public class SeanslarDAO {
	public void ListeleSeanslar(JTable table) {
        String sql = "SELECT SeansNo,FilmAdi FROM Seanslar";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

             DefaultTableModel model = new DefaultTableModel() {
                 private static final long serialVersionUID = 1L;

                 @Override
                 public boolean isCellEditable(int row, int column) {
                     return false;
                 }
             };
             model.addColumn("SeansNo");
             model.addColumn("FilmAdi");
             
             while (resultSet.next()) {
                 model.addRow(new Object[]{
                     resultSet.getInt("SeansNo"),
                     resultSet.getString("FilmAdi")
                 });
            }
            table.setModel(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setPreferredWidth(325);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.getTableHeader().setReorderingAllowed(false);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Filmler getirilemedi: " + e.getMessage(),
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public void SeansEkle(String FilmAdi, String FilmTarihi, String FilmSaati, int SalonNo, int Kapasite, double BiletFiyati) {

        String sql = "INSERT INTO Seanslar (FilmAdi, FilmTarihi, FilmSaati, SalonNo, Kapasite, BiletFiyati) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, FilmAdi);
            statement.setString(2, FilmTarihi);
            statement.setString(3, FilmSaati);
            statement.setInt(4, SalonNo);
            statement.setInt(5, Kapasite);
            statement.setDouble(6, BiletFiyati);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Seans Eklendi. ", 
                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);  
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Seans Eklenemedi. ", 
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public void SeansSil(int SeansNo) {
	    String checkSql = "SELECT COUNT(*) FROM Seanslar WHERE SeansNo = ?";
	    String deleteSql = "DELETE FROM Seanslar WHERE SeansNo = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	         PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

	        checkStatement.setInt(1, SeansNo);
	        ResultSet resultSet = checkStatement.executeQuery();

	        if (resultSet.next() && resultSet.getInt(1) > 0) {
	            deleteStatement.setInt(1, SeansNo);
	            deleteStatement.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Seans Silindi.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Silinecek seans bulunamadı.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Seans silinemedi: " + e.getMessage(), 
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void SeansGuncelle(int SeansNo, String FilmAdi, String FilmTarihi, String FilmSaati, int SalonNo, int Kapasite, double BiletFiyati) {
	    String checkSql = "SELECT COUNT(*) FROM Seanslar WHERE SeansNo = ?";
	    String sql = "UPDATE Seanslar SET FilmAdi = ?, FilmTarihi = ?, FilmSaati = ?, SalonNo = ?, Kapasite = ?, BiletFiyati = ? WHERE SeansNo = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        checkStatement.setInt(1, SeansNo);
	        ResultSet resultSet = checkStatement.executeQuery();

	        if (resultSet.next() && resultSet.getInt(1) > 0) {
	            statement.setString(1, FilmAdi);
	            statement.setString(2, FilmTarihi);
	            statement.setString(3, FilmSaati);
	            statement.setInt(4, SalonNo);
	            statement.setInt(5, Kapasite);
	            statement.setDouble(6, BiletFiyati);
	            statement.setInt(7, SeansNo);
	            statement.executeUpdate();

	            JOptionPane.showMessageDialog(null, "Seans Güncellendi.",
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);  
	        } else {
	            JOptionPane.showMessageDialog(null, "Güncellenecek seans bulunamadı.",
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Seans Güncellenemedi: " + e.getMessage(),
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public SeansParametre SeansBilgileri(int SeansNo) {
        String sql = "SELECT * FROM Seanslar WHERE SeansNo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, SeansNo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                SeansParametre seans = new SeansParametre();
                BiletAl.BiletAlEkraniBilgiAl(seans);
                seans.setSeansNo(resultSet.getInt("SeansNo"));
                seans.setFilmAdi(resultSet.getString("FilmAdi"));
                seans.setFilmTarihi(resultSet.getString("FilmTarihi"));
                seans.setFilmSaati(resultSet.getString("FilmSaati"));
                seans.setSalonNo(resultSet.getInt("SalonNo"));
                seans.setKapasite(resultSet.getInt("Kapasite"));
                seans.setBiletFiyati(resultSet.getDouble("BiletFiyati"));
                return seans;
            } else {
                JOptionPane.showMessageDialog(null, "Seans Detayları Bulunamadı",
                        "Hata", JOptionPane.ERROR_MESSAGE);
                return null;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Seans Detayları Getirilemedi: " + e.getMessage(),
                    "Hata", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
	
	public boolean FilmSeansiVarMi(String filmAdi) { 
	    String sql = "SELECT COUNT(*) FROM Seanslar WHERE FilmAdi LIKE ?"; 
	    try (Connection connection = DatabaseConnection.getConnection(); 
	         PreparedStatement statement = connection.prepareStatement(sql)) { 
	        statement.setString(1, "%" + filmAdi + "%"); 
	        ResultSet resultSet = statement.executeQuery(); 
	        if (resultSet.next()) { 
	            return resultSet.getInt(1) > 0; 
	        } 
	    } catch (SQLException e) { 
	        JOptionPane.showMessageDialog(null, "Seans kontrolü başarısız: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE); 
	    } 
	    return false; 
	}

	public SeansParametre getSeansBilgileri(String filmAdi) {
	    SeansParametre seans = new SeansParametre();
	    BiletAl.BiletAlEkraniBilgiAl(seans);
	    String sql = "SELECT * FROM Seanslar WHERE FilmAdi LIKE ?";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, "%" + filmAdi + "%");
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	        	seans.setSeansNo(resultSet.getInt("SeansNo"));
	            seans.setFilmAdi(resultSet.getString("FilmAdi"));
	            seans.setFilmTarihi(resultSet.getString("FilmTarihi"));
	            seans.setFilmSaati(resultSet.getString("FilmSaati"));
	            seans.setSalonNo(resultSet.getInt("SalonNo"));
	            seans.setKapasite(resultSet.getInt("Kapasite"));
	            seans.setBiletFiyati(resultSet.getDouble("BiletFiyati"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return seans;
	}
}
