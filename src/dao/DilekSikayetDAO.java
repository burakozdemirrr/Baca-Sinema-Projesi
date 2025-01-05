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

public class DilekSikayetDAO {

	private static Kullanici kullanici;

	public static void TalepEdenAl(Kullanici kullanici) {
	    DilekSikayetDAO.kullanici = kullanici;
	}
	
	public void TalepKaydet(String Talep) {

        String sql = "INSERT INTO Talepler (TalepEden, Talep) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, kullanici.getKullaniciAdi());
            statement.setString(2, Talep);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Talep Gönderildi. ", 
                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);  
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Talep Gönderilemedi. ", 
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public void ListeleTalep(JTable table) {
	    String sql = "SELECT * FROM Talepler";
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
	         model.addColumn("TalepNo");
	         model.addColumn("TalepEden");
	         model.addColumn("Talep");
	         
	         while (resultSet.next()) {
	             model.addRow(new Object[]{
	                 resultSet.getString("TalepNo"),
	                 resultSet.getString("TalepEden"),
	                 resultSet.getString("Talep")
	             });
	        }
	        table.setModel(model);
	        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        table.getColumnModel().getColumn(0).setPreferredWidth(50);
	        table.getColumnModel().getColumn(1).setPreferredWidth(150);
	        table.getColumnModel().getColumn(2).setPreferredWidth(588);

	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.getTableHeader().setReorderingAllowed(false);

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Talepler Getirilemedi: " + e.getMessage(),
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void TalepSil(int TalepNo) {
	    String checkSql = "SELECT COUNT(*) FROM Talepler WHERE TalepNo = ?";
	    String deleteSql = "DELETE FROM Talepler WHERE TalepNo = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	         PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

	        checkStatement.setInt(1, TalepNo);
	        ResultSet resultSet = checkStatement.executeQuery();

	        if (resultSet.next() && resultSet.getInt(1) > 0) {
	            deleteStatement.setInt(1, TalepNo);
	            deleteStatement.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Talep Silindi.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Silinecek kayıt bulunamadı.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Talep Silinemedi: " + e.getMessage(), 
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void TalepTemizle(JTable table) {
	    String sql = "DELETE FROM Talepler";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        int affectedRows = statement.executeUpdate();
	        
	        if (affectedRows > 0) {
	            JOptionPane.showMessageDialog(null, "Tüm kayıtlar başarıyla silindi.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	            
	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            model.setRowCount(0); 
	        } else {
	            JOptionPane.showMessageDialog(null, "Silinecek kayıt bulunamadı.", 
	                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Kayıtlar silinemedi: " + e.getMessage(), 
	                "Hata", JOptionPane.ERROR_MESSAGE);
	    }
	}

}
