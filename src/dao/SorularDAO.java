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

public class SorularDAO {
	
	private static Kullanici kullanici;

	public static void SoruSoranAl(Kullanici kullanici) {
	    SorularDAO.kullanici = kullanici;
	}

	public void SoruKaydet(String Soru) {

        String sql = "INSERT INTO Sorular (Soran, Cevaplayan, Soru, Cevap, Durum) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, kullanici.getKullaniciAdi());
            statement.setString(2, "---");
            statement.setString(3, Soru);
            statement.setString(4, "---");
            statement.setString(5, "Cevaplanmadı");
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Soru Gönderildi. ", 
                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);  
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Soru Gönderilemedi. ", 
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public void SoruCevapla(int SoruNo, String Cevap) {
	    String checkSql = "SELECT Durum FROM Sorular WHERE SoruNo = ?";
	    String updateSql = "UPDATE Sorular SET Cevaplayan = ?, Cevap = ?, Durum = ? WHERE SoruNo = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	         PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

	        checkStatement.setInt(1, SoruNo);
	        ResultSet resultSet = checkStatement.executeQuery();

	        if (resultSet.next()) {
	            String durum = resultSet.getString("Durum");
	            if ("Cevaplandı".equals(durum)) {
	                JOptionPane.showMessageDialog(null, "Bu soru zaten cevaplanmış.", 
	                        "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	                return;
	            }

	            updateStatement.setString(1, kullanici.getKullaniciAdi());
	            updateStatement.setString(2, Cevap);
	            updateStatement.setString(3, "Cevaplandı");
	            updateStatement.setInt(4, SoruNo);
	            updateStatement.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Soru Cevaplandı.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Cevaplanacak soru bulunamadı.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Soru Cevaplanmadı: " + e.getMessage(), 
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	public void SoruSil(int SoruNo) {
	    String checkSql = "SELECT COUNT(*) FROM Sorular WHERE SoruNo = ?";
	    String deleteSql = "DELETE FROM Sorular WHERE SoruNo = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	         PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

	        checkStatement.setInt(1, SoruNo);
	        ResultSet resultSet = checkStatement.executeQuery();

	        if (resultSet.next() && resultSet.getInt(1) > 0) {
	            deleteStatement.setInt(1, SoruNo);
	            deleteStatement.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Soru Silindi.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Silinecek kayıt bulunamadı.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Soru Silinemedi: " + e.getMessage(), 
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void ListeleSorular(JTable table, String filter) {
	    String sql = "SELECT * FROM Sorular";
	    if (filter.equals("CEVAPLANAN")) {
	        sql += " WHERE Durum = 'CEVAPLANDI'";
	    } else if (filter.equals("CEVAPLANMAYAN")) {
	        sql += " WHERE Durum != 'CEVAPLANDI'";
	    }
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
	         model.addColumn("SoruNo");
	         model.addColumn("Soran");
	         model.addColumn("Cevaplayan");
	         model.addColumn("Soru");
	         model.addColumn("Cevap");
	         
	         while (resultSet.next()) {
	             model.addRow(new Object[]{
	                 resultSet.getInt("SoruNo"),
	                 resultSet.getString("Soran"),
	                 resultSet.getString("Cevaplayan"),
	                 resultSet.getString("Soru"),
	                 resultSet.getString("Cevap"),
	             });
	        }
	        table.setModel(model);
	        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        table.getColumnModel().getColumn(0).setPreferredWidth(50);
	        table.getColumnModel().getColumn(1).setPreferredWidth(100);
	        table.getColumnModel().getColumn(2).setPreferredWidth(100);
	        table.getColumnModel().getColumn(3).setPreferredWidth(281);
	        table.getColumnModel().getColumn(4).setPreferredWidth(281);

	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.getTableHeader().setReorderingAllowed(false);

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Sorular getirilemedi: " + e.getMessage(),
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
