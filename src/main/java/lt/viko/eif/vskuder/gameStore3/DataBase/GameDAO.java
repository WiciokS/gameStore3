package lt.viko.eif.vskuder.gameStore3.DataBase;

import gamestoresoap.Category;
import gamestoresoap.Developer;
import gamestoresoap.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that handles all the database operations related to Game
 */
public class GameDAO {
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

    public GameDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    /**
     * Method that gets all the games from the database
     * @return list of games
     */
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM Game";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setTitle(rs.getString("title"));
                game.setPrice(rs.getDouble("price"));

                //get developer and category data and set it to the model
                Developer developer = new DeveloperDAO(url, this.user, password).getDeveloperById(rs.getInt("developer_id"));
                game.setDeveloper(developer);
                Category category = new CategoryDAO(url, this.user, password).getCategoryById(rs.getInt("category_id"));
                game.setCategory(category);

                games.add(game);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }
    /**
     * Method that gets all the games from the database by category id
     * @param userId user id
     * @return list of games
     */
    public List<Game> getAllGamesByUserId(int userId) {
        List<Game> games = new ArrayList<>();
        //select all games From table Game Where GameId is in table Library where UserId is equal to userId
        String sql = "SELECT * FROM Game WHERE id IN (SELECT game_id FROM Library WHERE user_id = ?)";


        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Game game = new Game();
                    game.setId(rs.getInt("id"));
                    game.setTitle(rs.getString("title"));
                    game.setPrice(rs.getDouble("price"));

                    //get developer and category data and set it to the model
                    Developer developer = new DeveloperDAO(url, this.user, password).getDeveloperById(rs.getInt("developer_id"));
                    game.setDeveloper(developer);
                    Category category = new CategoryDAO(url, this.user, password).getCategoryById(rs.getInt("category_id"));
                    game.setCategory(category);

                    games.add(game);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }
    /**
     * Method that gets all the games from the database by id
     * @param id game id
     * @return list of games
     */
    public Game getGameById(int id) {
        Game game = null;
        String sql = "SELECT * FROM Game WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    game = new Game();
                    game.setId(rs.getInt("id"));
                    game.setTitle(rs.getString("title"));
                    game.setPrice(rs.getDouble("price"));

                    //get developer and category data and set it to the model
                    Developer developer = new DeveloperDAO(url, this.user, password).getDeveloperById(rs.getInt("developer_id"));
                    game.setDeveloper(developer);
                    Category category = new CategoryDAO(url, this.user, password).getCategoryById(rs.getInt("category_id"));
                    game.setCategory(category);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return game;
    }
    /**
     * Method that inserts game to the database
     * @param game game object
     * @return true if game was inserted, false otherwise
     */
    public boolean insertGame(Game game) {
        String sql = "INSERT INTO Game (title, price, developer_id, category_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, game.getTitle());
            stmt.setDouble(2, game.getPrice());
            stmt.setInt(3, game.getDeveloper().getId());
            stmt.setInt(4, game.getCategory().getId());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that updates game in the database
     * @param game game object
     * @return true if game was updated, false otherwise
     */
    public boolean updateGame(Game game) {
        String sql = "UPDATE Game SET title = ?, price = ?, developer_id = ?, category_id = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, game.getTitle());
            stmt.setDouble(2, game.getPrice());
            stmt.setInt(3, game.getDeveloper().getId());
            stmt.setInt(4, game.getCategory().getId());
            stmt.setInt(5, game.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that deletes game from the database
     * @param id game id
     * @return true if game was deleted, false otherwise
     */
    public boolean deleteGame(int id) {
        String sql = "DELETE FROM Game WHERE id = ?";

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
