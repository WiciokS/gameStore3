package lt.viko.eif.vskuder.gameStore3;

import gamestoresoap.*;
import lt.viko.eif.vskuder.gameStore3.DataBase.*;
import org.springframework.web.bind.annotation.*;
/**
 * RESTfulController class is used to create RESTful web services
 * for the GameStore application
 */
@RestController
public class RESTfulController {
    /**
     * Database url
     */
    private final String url = "jdbc:mysql://localhost:3306/gamestore11";
    /**
     * Database user
     */
    private final String user = "root";
    /**
     * Database password
     */
    private final String password = "root";
    /**
     * Method to create a new user
     * @param request CreateUserRequest object
     * @return CreateUserResponse object
     */
    @PostMapping("/library/user/createUser")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        //Using UserDAO to create a new user
        UserDAO userDAO = new UserDAO(url, user, password);

        //Creating a new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        //Inserting the user into the database
        boolean success = userDAO.insertUser(user);

        //if the user was successfully inserted
        if (success) {
            response.setAnswer("User was successfully created");
        } else {
            response.setAnswer("User was not created");
        }

        return response;
    }
    /**
     * Method to get a user
     * @param userId user id
     * @return GetUserResponse object
     */
    @GetMapping("/library/user/getUser/{userId}")
    public GetUserResponse getUser(@PathVariable Integer userId) {
        GetUserResponse response = new GetUserResponse();
        //Using UserDAO to get a user
        UserDAO userDAO = new UserDAO(url, user, password);

        //Getting the user from the database
        User user = userDAO.getUserById(userId);

        //if the user was successfully inserted
        if (user != null) {
            response.setUser(user);
        } else {
            response.setUser(null);
        }

        return response;
    }
    /**
     * Method to get all users
     * @return GetUsersResponse object
     */
    @GetMapping("/library/user/getUsers")
    public GetUsersResponse getUsers() {
        GetUsersResponse response = new GetUsersResponse();
        //Using UserDAO to get a user
        UserDAO userDAO = new UserDAO(url, user, password);

        //Getting the user from the database
        response.getUsers().addAll(userDAO.getAllUsers());

        return response;
    }
    /**
     * Method to update a user
     * @param request UpdateUserRequest object
     * @return UpdateUserResponse object
     */
    @PutMapping("/library/user/updateUser")
    public UpdateUserResponse updateUser(@RequestBody UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();
        //Using UserDAO to get a user
        UserDAO userDAO = new UserDAO(url, user, password);

        //creating a new user
        User user = new User();
        user.setId(request.getUserId());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        //Getting the user from the database
        boolean success = userDAO.updateUser(user);

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("User was successfully updated");
        } else {
            response.setAnswer("User was not updated");
        }

        return response;
    }
    /**
     * Method to remove a user
     * @param userId user id
     * @return RemoveUserResponse object
     */
    @DeleteMapping("/library/user/removeUser/{userId}")
    public RemoveUserResponse removeUser(@PathVariable Integer userId) {
        RemoveUserResponse response = new RemoveUserResponse();
        //Using UserDAO to get a user
        UserDAO userDAO = new UserDAO(url, user, password);

        //Getting the user from the database
        boolean success = userDAO.deleteUser(userId);

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("User was successfully removed");
        } else {
            response.setAnswer("User was not removed");
        }

        return response;
    }
    /**
     * Method to create a new category
     * @param request CreateCategoryRequest object
     * @return CreateCategoryResponse object
     */
    @PostMapping("/library/game/category/createCategory")
    public CreateCategoryResponse createCategory(@RequestBody CreateCategoryRequest request) {
        CreateCategoryResponse response = new CreateCategoryResponse();
        //Using UserDAO to create a new user
        CategoryDAO userDAO = new CategoryDAO(url, user, password);

        //Creating a new user
        Category category = new Category();
        category.setName(request.getCategoryName());

        //Inserting the user into the database
        boolean success = userDAO.insertCategory(category);

        //if the user was successfully inserted
        if (success) {
            response.setAnswer("Category was successfully created");
        } else {
            response.setAnswer("Category was not created");
        }

        return response;
    }
    /**
     * Method to get a category
     * @param categoryId category id
     * @return GetCategoryResponse object
     */
    @GetMapping("/library/game/category/getCategory/{categoryId}")
    public GetCategoryResponse getCategory(@PathVariable Integer categoryId) {
        GetCategoryResponse response = new GetCategoryResponse();
        //Using UserDAO to get a user
        CategoryDAO userDAO = new CategoryDAO(url, user, password);

        //Getting the user from the database
        Category category = userDAO.getCategoryById(categoryId);

        //if the user was successfully inserted
        if (category != null) {
            response.setCategory(category);
        } else {
            response.setCategory(null);
        }

        return response;
    }
    /**
     * Method to get all categories
     * @return GetCategoriesResponse object
     */
    @GetMapping("/library/game/category/getCategories")
    public GetCategoriesResponse getCategories() {
        GetCategoriesResponse response = new GetCategoriesResponse();
        //Using UserDAO to get a user
        CategoryDAO userDAO = new CategoryDAO(url, user, password);

        //Getting the user from the database
        response.getCategories().addAll(userDAO.getAllCategories());

        return response;
    }
    /**
     * Method to update a category
     * @param request UpdateCategoryRequest object
     * @return UpdateCategoryResponse object
     */
    @PutMapping("/library/game/category/updateCategory")
    public UpdateCategoryResponse updateCategory(@RequestBody UpdateCategoryRequest request) {
        UpdateCategoryResponse response = new UpdateCategoryResponse();
        //Using UserDAO to get a user
        CategoryDAO userDAO = new CategoryDAO(url, user, password);

        //creating a new user
        Category category = new Category();
        category.setId(request.getCategoryId());
        category.setName(request.getCategoryName());

        //Getting the user from the database
        boolean success = userDAO.updateCategory(category);

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("Category was successfully updated");
        } else {
            response.setAnswer("Category was not updated");
        }

        return response;
    }
    /**
     * Method to remove a category
     * @param categoryId category id
     * @return RemoveCategoryResponse object
     */
    @DeleteMapping("/library/game/category/removeCategory/{categoryId}")
    public RemoveCategoryResponse removeCategory(@PathVariable Integer categoryId) {
        RemoveCategoryResponse response = new RemoveCategoryResponse();
        //Using UserDAO to get a user
        CategoryDAO userDAO = new CategoryDAO(url, user, password);

        //Getting the user from the database
        boolean success = userDAO.deleteCategory(categoryId);

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("Category was successfully removed");
        } else {
            response.setAnswer("Category was not removed");
        }

        return response;
    }
    /**
     * Method to create a new developer
     * @param request CreateDeveloperRequest object
     * @return CreateDeveloperResponse object
     */
    @PostMapping("/library/game/developer/createDeveloper")
    public CreateDeveloperResponse createDeveloper(@RequestBody CreateDeveloperRequest request) {
        CreateDeveloperResponse response = new CreateDeveloperResponse();
        //Using UserDAO to create a new user
        DeveloperDAO userDAO = new DeveloperDAO(url, user, password);

        //Creating a new user
        Developer developer = new Developer();
        developer.setName(request.getDeveloperName());

        //Inserting the user into the database
        boolean success = userDAO.insertDeveloper(developer);

        //if the user was successfully inserted
        if (success) {
            response.setAnswer("Developer was successfully created");
        } else {
            response.setAnswer("Developer was not created");
        }

        return response;
    }
    /**
     * Method to get a developer
     * @param developerId developer id
     * @return GetDeveloperResponse object
     */
    @GetMapping("/library/game/developer/getDeveloper/{developerId}")
    public GetDeveloperResponse getDeveloper(@PathVariable Integer developerId) {
        GetDeveloperResponse response = new GetDeveloperResponse();
        //Using UserDAO to get a user
        DeveloperDAO userDAO = new DeveloperDAO(url, user, password);

        //Getting the user from the database
        Developer developer = userDAO.getDeveloperById(developerId);

        //if the user was successfully inserted
        if (developer != null) {
            response.setDeveloper(developer);
        } else {
            response.setDeveloper(null);
        }

        return response;
    }
    /**
     * Method to get all developers
     * @return GetDevelopersResponse object
     */
    @GetMapping("/library/game/developer/getDevelopers")
    public GetDevelopersResponse getDevelopers() {
        GetDevelopersResponse response = new GetDevelopersResponse();
        //Using UserDAO to get a user
        DeveloperDAO userDAO = new DeveloperDAO(url, user, password);

        //Getting the user from the database
        response.getDevelopers().addAll(userDAO.getAllDevelopers());

        return response;
    }
    /**
     * Method to update a developer
     * @param request UpdateDeveloperRequest object
     * @return UpdateDeveloperResponse object
     */
    @PutMapping("/library/game/developer/updateDeveloper")
    public UpdateDeveloperResponse updateDeveloper(@RequestBody UpdateDeveloperRequest request) {
        UpdateDeveloperResponse response = new UpdateDeveloperResponse();
        //Using UserDAO to get a user
        DeveloperDAO userDAO = new DeveloperDAO(url, user, password);

        //creating a new user
        Developer developer = new Developer();
        developer.setId(request.getDeveloperId());
        developer.setName(request.getDeveloperName());

        //Getting the user from the database
        boolean success = userDAO.updateDeveloper(developer);

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("Developer was successfully updated");
        } else {
            response.setAnswer("Developer was not updated");
        }

        return response;
    }
    /**
     * Method to remove a developer
     * @param developerId developer id
     * @return RemoveDeveloperResponse object
     */
    @DeleteMapping("/library/game/developer/removeDeveloper/{developerId}")
    public RemoveDeveloperResponse removeDeveloper(@PathVariable Integer developerId) {
        RemoveDeveloperResponse response = new RemoveDeveloperResponse();
        //Using UserDAO to get a user
        DeveloperDAO userDAO = new DeveloperDAO(url, user, password);

        //Getting the user from the database
        boolean success = userDAO.deleteDeveloper(developerId);

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("Developer was successfully removed");
        } else {
            response.setAnswer("Developer was not removed");
        }

        return response;
    }
    /**
     * Method to create a new game
     * @param request CreateGameRequest object
     * @return CreateGameResponse object
     */
    @PostMapping("/library/game/createGame")
    public CreateGameResponse createGameResponse(@RequestBody CreateGameRequest request) {
        CreateGameResponse response = new CreateGameResponse();
        //Using UserDAO to create a new user
        GameDAO userDAO = new GameDAO(url, user, password);
        DeveloperDAO developerDAO = new DeveloperDAO(url, user, password);
        CategoryDAO categoryDAO = new CategoryDAO(url, user, password);

        //Creating a new user
        Game game = new Game();
        game.setTitle(request.getGameTitle());
        game.setPrice(request.getGamePrice());
        game.setDeveloper(developerDAO.getDeveloperById(request.getGameDeveloperId()));
        game.setCategory(categoryDAO.getCategoryById(request.getGameCategoryId()));

        //Inserting the user into the database
        boolean success = userDAO.insertGame(game);

        //if the user was successfully inserted
        if (success) {
            response.setAnswer("Game was successfully created");
        } else {
            response.setAnswer("Game was not created");
        }

        return response;
    }
    /**
     * Method to get a game
     * @param gameId game id
     * @return GetGameResponse object
     */
    @GetMapping("/library/game/getGame/{gameId}")
    public GetGameResponse getGame(@PathVariable Integer gameId) {
        GetGameResponse response = new GetGameResponse();
        //Using UserDAO to get a user
        GameDAO userDAO = new GameDAO(url, user, password);

        //Getting the user from the database
        Game game = userDAO.getGameById(gameId);

        //if the user was successfully inserted
        if (game != null) {
            response.setGame(game);
        } else {
            response.setGame(null);
        }

        return response;
    }
    /**
     * Method to get all games
     * @return GetGamesResponse object
     */
    @GetMapping("/library/game/getGames")
    public GetGamesResponse getGames() {
        GetGamesResponse response = new GetGamesResponse();
        //Using UserDAO to get a user
        GameDAO userDAO = new GameDAO(url, user, password);

        //Getting the user from the database
        response.getGames().addAll(userDAO.getAllGames());

        return response;
    }
    /**
     * Method to update a game
     * @param request UpdateGameRequest object
     * @return UpdateGameResponse object
     */
    @PutMapping("/library/game/updateGame")
    public UpdateGameResponse updateGame(@RequestBody UpdateGameRequest request) {
        UpdateGameResponse response = new UpdateGameResponse();
        //Using UserDAO to get a user
        GameDAO userDAO = new GameDAO(url, user, password);
        DeveloperDAO developerDAO = new DeveloperDAO(url, user, password);
        CategoryDAO categoryDAO = new CategoryDAO(url, user, password);

        //creating a new user
        Game game = new Game();
        game.setId(request.getGameId());
        game.setTitle(request.getGameTitle());
        game.setPrice(request.getGamePrice());
        game.setDeveloper(developerDAO.getDeveloperById(request.getGameDeveloperId()));
        game.setCategory(categoryDAO.getCategoryById(request.getGameCategoryId()));

        //Getting the user from the database
        boolean success = userDAO.updateGame(game);

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("Game was successfully updated");
        } else {
            response.setAnswer("Game was not updated");
        }

        return response;
    }
    /**
     * Method to remove a game
     * @param gameId game id
     * @return RemoveGameResponse object
     */
    @DeleteMapping("/library/game/removeGame/{gameId}")
    public RemoveGameResponse removeGame(@PathVariable Integer gameId) {
        RemoveGameResponse response = new RemoveGameResponse();
        //Using UserDAO to get a user
        GameDAO userDAO = new GameDAO(url, user, password);

        //Getting the user from the database
        boolean success = userDAO.deleteGame(gameId);

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("Game was successfully removed");
        } else {
            response.setAnswer("Game was not removed");
        }

        return response;
    }
    /**
     * Method to add a game to library
     * @param request AddItemToLibraryRequest object
     * @return AddItemToLibraryResponse object
     */
    @PostMapping("/library/addItemToLibrary")
    public AddItemToLibraryResponse addItemToLibrary(@RequestBody AddItemToLibraryRequest request) {
        AddItemToLibraryResponse response = new AddItemToLibraryResponse();
        //Using UserDAO to get a user
        LibraryDAO userDAO = new LibraryDAO(url, user, password);
        UserDAO userDAO1 = new UserDAO(url, user, password);
        GameDAO gameDAO = new GameDAO(url, user, password);

        //Getting the user from the database
        boolean success = userDAO.insertGameIntoLibrary(userDAO1.getUserById(request.getUserId())
                , gameDAO.getGameById(request.getGameId()));

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("Item was successfully added to library");
        } else {
            response.setAnswer("Item was not added to library");
        }

        return response;
    }
    /**
     * Method to remove a game from library
     * @param userId user id
     * @param gameId game id
     * @return RemoveItemFromLibraryResponse object
     */
    @DeleteMapping("/library/removeItemFromLibrary/{userId}/{gameId}")
    public RemoveItemFromLibraryResponse removeItemFromLibrary(@PathVariable Integer userId, @PathVariable Integer gameId) {
        RemoveItemFromLibraryResponse response = new RemoveItemFromLibraryResponse();
        //Using UserDAO to get a user
        LibraryDAO userDAO = new LibraryDAO(url, user, password);
        UserDAO userDAO1 = new UserDAO(url, user, password);
        GameDAO gameDAO = new GameDAO(url, user, password);

        //Getting the user from the database
        boolean success = userDAO.removeGameFromLibrary(userDAO1.getUserById(userId)
                , gameDAO.getGameById(gameId));

        //if the user was successfully deleted
        if (success) {
            response.setAnswer("Item was successfully removed from library");
        } else {
            response.setAnswer("Item was not removed from library");
        }

        return response;
    }
    /**
     * Method to get a library
     * @param libraryId library id
     * @return GetLibraryResponse object
     */
    @GetMapping("/library/getLibrary/{libraryId}")
    public GetLibraryResponse getLibrary(@PathVariable Integer libraryId) {
        GetLibraryResponse response = new GetLibraryResponse();
        //Using UserDAO to get a user
        LibraryDAO userDAO = new LibraryDAO(url, user, password);
        GameDAO gameDAO = new GameDAO(url, user, password);

        //Getting the user from the database
        response.getGames().addAll(userDAO.getLibraryByUserId(libraryId).getGames());
        return response;
    }
    /**
     * Method to get a library user
     * @param libraryId library id
     * @return GetLibraryUserResponse object
     */
    @GetMapping("/library/getLibraryUser/{libraryId}")
    public GetLibraryUserResponse getLibraryUser(@PathVariable Integer libraryId) {
        GetLibraryUserResponse response = new GetLibraryUserResponse();
        //Using UserDAO to get a user
        LibraryDAO userDAO = new LibraryDAO(url, user, password);

        //Getting the user from the database
        response.setUser(userDAO.getLibraryByUserId(libraryId).getUser());
        response.getGames().addAll(userDAO.getLibraryByUserId(libraryId).getGames());
        return response;
    }
}
