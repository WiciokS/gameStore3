package lt.viko.eif.vskuder.gameStore3.DataBase;

import gamestoresoap.Game;
import gamestoresoap.Library;
import gamestoresoap.User;

import java.sql.*;
import java.util.List;
/**
 * Class that handles all the database operations related to Library
 */
public class LibraryDAO {
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

    public LibraryDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    /**
     * Method that gets all the libraries from the database
     * @return list of libraries
     */

    public Library getLibraryByUserId(int userId) {
        Library library = new Library();
        //get all games from library where user_id = userId
        String sql = "SELECT * FROM Library WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    library.setId(rs.getInt("id"));

                    // Get the user data and set it to the model
                    User user = new UserDAO(url, this.user, password).getUserById(rs.getInt("user_id"));
                    library.setUser(user);

                    // Get the all games data and set it to the model using getAllGamesByUserId
                    List<Game> games = new GameDAO(url, this.user, password).getAllGamesByUserId(rs.getInt("user_id"));
                    library.getGames().addAll(games);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return library;
    }
    /**
     * Method that gets all the libraries from the database
     * @return list of libraries
     */
    public boolean insertGameIntoLibrary(User user, Game game) {
        String sql = "INSERT INTO Library (user_id, game_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getId());
            stmt.setInt(2, game.getId());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that gets all the libraries from the database
     * @return list of libraries
     */
    public boolean removeGameFromLibrary(User user, Game game) {
        String sql = "DELETE FROM Library WHERE user_id = ? AND game_id = ?";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getId());
            stmt.setInt(2, game.getId());

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that gets all the libraries from the database
     * @return list of libraries
     */
    public boolean deleteLibrary(int id) {
        String sql = "DELETE FROM Library WHERE id = ?";

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
