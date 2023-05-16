package lt.viko.eif.vskuder.gameStore3.DataBase;

import gamestoresoap.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that handles all the database operations related to User
 */
public class UserDAO {
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

    public UserDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    /**
     * Method that gets all the users from the database
     * @return list of users
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    /**
     * Method that gets a user by id from the database
     * @param id user id
     * @return user
     */
    public User getUserById(int id) {
        User user = null;
        String sql = "SELECT * FROM User WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    /**
     * Method that gets a user by username from the database
     * @param user
     * @return user
     */
    public boolean insertUser(User user) {
        String sql = "INSERT INTO User (username, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that updates a user in the database
     * @param user
     * @return true if user was updated, false otherwise
     */
    public boolean updateUser(User user) {
        String sql = "UPDATE User SET username = ?, password = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that deletes a user from the database
     * @param id user id
     * @return true if user was deleted, false otherwise
     */
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM User WHERE id = ?";

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

