package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.FilmParametre;
import util.DatabaseConnection;

public class FilmlerDAO {

    public void FilmKaydet(String FilmAdi, String FilmTuru, String FilmSuresi, String FilmFragmani, String FilmOyunculari, String FilmOzeti, String FilmResmi) {

        String sql = "INSERT INTO Filmler (FilmAdi, FilmTuru, FilmSuresi, FilmFragmani, FilmOyunculari, FilmOzeti, FilmResmi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, FilmAdi);
            statement.setString(2, FilmTuru);
            statement.setString(3, FilmSuresi);
            statement.setString(4, FilmFragmani);
            statement.setString(5, FilmOyunculari);
            statement.setString(6, FilmOzeti);
            statement.setString(7, FilmResmi);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Film Eklendi. ", 
                    "Bilgi", JOptionPane.INFORMATION_MESSAGE);  
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Film Eklenemedi. ", 
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ListeleFilmler(JTable table) {
        String sql = "SELECT FilmNo,FilmAdi FROM Filmler";
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
             model.addColumn("FilmNo");
             model.addColumn("FilmAdi");
             
             while (resultSet.next()) {
                 model.addRow(new Object[]{
                     resultSet.getInt("FilmNo"),
                     resultSet.getString("FilmAdi")
                 });
            }
            table.setModel(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setPreferredWidth(310);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.getTableHeader().setReorderingAllowed(false);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Filmler getirilemedi: " + e.getMessage(),
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public FilmParametre FilmBilgileri(int filmNo) {
        String sql = "SELECT * FROM Filmler WHERE FilmNo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, filmNo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                FilmParametre film = new FilmParametre();
                film.setFilmAdi(resultSet.getString("FilmAdi"));
                film.setFilmTuru(resultSet.getString("FilmTuru"));
                film.setFilmSuresi(resultSet.getString("FilmSuresi"));
                film.setFilmFragmani(resultSet.getString("FilmFragmani"));
                film.setFilmOyunculari(resultSet.getString("FilmOyunculari"));
                film.setFilmOzeti(resultSet.getString("FilmOzeti"));
                film.setFilmResim(resultSet.getString("FilmResmi"));
                return film;
            } else {
                JOptionPane.showMessageDialog(null, "Film Detayları Bulunamadı",
                        "Hata", JOptionPane.ERROR_MESSAGE);
                return null;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Film Detayları Getirilemedi: " + e.getMessage(),
                    "Hata", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void FilSil(int FilmNo) {
        String checkSql = "SELECT COUNT(*) FROM Filmler WHERE FilmNo = ?";
        String getResimPathSql = "SELECT FilmResmi FROM Filmler WHERE FilmNo = ?";
        String deleteSql = "DELETE FROM Filmler WHERE FilmNo = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql);
             PreparedStatement getResimPathStatement = connection.prepareStatement(getResimPathSql);
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

            checkStatement.setInt(1, FilmNo);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                getResimPathStatement.setInt(1, FilmNo);
                ResultSet resimPathResult = getResimPathStatement.executeQuery();

                String filePath = null;
                if (resimPathResult.next()) {
                    filePath = "src/resimler/" + resimPathResult.getString("FilmResmi");
                }

                deleteStatement.setInt(1, FilmNo);
                deleteStatement.executeUpdate();

                if (filePath != null) {
                    try {
                        Files.deleteIfExists(Paths.get(filePath));
                        JOptionPane.showMessageDialog(null, "Film ve Resmi Silindi.",
                                "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Film Silindi Fakat Resim Silinemedi: " + e.getMessage(),
                                "Uyarı", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Film Silindi Fakat Resim Yolu Bulunamadı.",
                            "Uyarı", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Silinecek Kayıt Bulunamadı.",
                        "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Film Silinemedi: " + e.getMessage(),
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void FilmGüncelle(String FilmAdi, String FilmTuru, String FilmSuresi, String FilmFragmani, String FilmOyunculari, String FilmOzeti, String FilmResmi, int FilmNo) {
        String checkSql = "SELECT COUNT(*) FROM Filmler WHERE FilmNo = ?";
        String sql = "UPDATE Filmler SET FilmAdi = ?, FilmTuru = ?, FilmSuresi = ?, FilmFragmani = ?, FilmOyunculari = ?, FilmOzeti = ?, FilmResmi = ? WHERE FilmNo = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            checkStatement.setInt(1, FilmNo);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                statement.setString(1, FilmAdi);
                statement.setString(2, FilmTuru);
                statement.setString(3, FilmSuresi);
                statement.setString(4, FilmFragmani);
                statement.setString(5, FilmOyunculari);
                statement.setString(6, FilmOzeti);
                statement.setString(7, FilmResmi);
                statement.setInt(8, FilmNo);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Film Güncellendi.", 
                        "Bilgi", JOptionPane.INFORMATION_MESSAGE);  
            } else {
                JOptionPane.showMessageDialog(null, "Güncellenecek kayıt bulunamadı.", 
                        "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Film Güncellenemedi: " + e.getMessage(), 
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void FilmArama(JTable table, String arananFilm) {
        String sql = "SELECT FilmNo, FilmAdi FROM Filmler WHERE FilmAdi LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, "%" + arananFilm + "%");
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel() {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            model.addColumn("FilmNo");
            model.addColumn("FilmAdi");

            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getInt("FilmNo"),
                    resultSet.getString("FilmAdi")
                });
            }
            table.setModel(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setPreferredWidth(326);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.getTableHeader().setReorderingAllowed(false);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Filmler getirilemedi: " + e.getMessage(),
                    "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

}
