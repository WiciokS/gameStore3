package lt.viko.eif.vskuder.gameStore3.DataBase;

import gamestoresoap.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles all the database operations related to Category
 */
public class CategoryDAO {
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

    public CategoryDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    /**
     * Method that gets all the categories from the database
     * @return list of categories
     */
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
    /**
     * Method that gets category by id
     * @param id category id
     * @return category
     */
    public Category getCategoryById(int id) {
        Category category = null;
        String sql = "SELECT * FROM Category WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }
    /**
     * Method that inserts category to the database
     * @param category category
     * @return true if category was inserted, false otherwise
     */
    public boolean insertCategory(Category category) {
        String sql = "INSERT INTO Category (name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getName());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that updates category in the database
     * @param category category
     * @return true if category was updated, false otherwise
     */
    public boolean updateCategory(Category category) {
        String sql = "UPDATE Category SET name = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, this.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method that deletes category from the database
     * @param id category id
     * @return true if category was deleted, false otherwise
     */
    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM Category WHERE id = ?";

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

