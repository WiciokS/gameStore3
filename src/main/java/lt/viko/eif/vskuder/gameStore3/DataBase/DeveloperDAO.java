package lt.viko.eif.vskuder.gameStore3.DataBase;

import gamestoresoap.Developer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that handles all the database operations related to Developer
 */
public class DeveloperDAO {
    /**
     * Database url
     */
    private final String url;
    /**
     * Database user
     */
    private final String user;
    /**
     * Database password
     */
    private final String password;
    /**
     * Constructor
     * @param url database url
     * @param user database user
     * @param password database password
     */

    public DeveloperDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    /**
     * Method that gets all the developers from the database
     * @return list of developers
     */
    public List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM Developer";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Developer developer = new Developer();
                developer.setId(rs.getInt("id"));
                developer.setName(rs.getString("name"));
                developers.add(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developers;
    }
    /**
     * Method that gets a developer by id
     * @param id developer id
     * @return developer
     */
    public Developer getDeveloperById(int id) {
        Developer developer = null;
        String sql = "SELECT * FROM Developer WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    developer = new Developer();
                    developer.setId(rs.getInt("id"));
                    developer.setName(rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developer;
    }
    /**
     * Method that inserts a developer into the database
     * @param developer developer
     * @return true if developer was inserted, false otherwise
     */
    public boolean insertDeveloper(Developer developer) {
        String sql = "INSERT INTO Developer (name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, developer.getName());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that updates a developer in the database
     * @param developer developer
     * @return true if developer was updated, false otherwise
     */
    public boolean updateDeveloper(Developer developer) {
        String sql = "UPDATE Developer SET name = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, developer.getName());
            stmt.setInt(2, developer.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that deletes a developer from the database
     * @param id developer id
     * @return true if developer was deleted, false otherwise
     */
    public boolean deleteDeveloper(int id) {
        String sql = "DELETE FROM Developer WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

