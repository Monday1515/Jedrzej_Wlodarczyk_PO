package com.example.yourdiet;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeDAO extends BaseDAO{


    RecipeDAO(Connection connection){
        super(connection);
    }

    void displayCatogrie(String category, ListView<String> listView, String email) throws SQLException {
        String displayRecipes = "";

        if(category == "Wszystkie"){
            displayRecipes = "SELECT name, weight, kcal FROM recipes";
        } else if (category == "Ulubione") {
            displayRecipes = "SELECT recipes.name, recipes.weight, recipes.kcal FROM recipes JOIN favorite_recipes ON recipes.id = favorite_recipes.recipe_id WHERE user_email = ?;";
        } else {
            displayRecipes = "SELECT recipes.name, recipes.weight, recipes.kcal FROM recipes JOIN categories ON recipes.category_id = categories.id WHERE categories.name = ?;";
        }

        PreparedStatement preparedStatement = connection.prepareStatement(displayRecipes);
        if(!category.equals("Wszystkie") && !category.equals("Ulubione")){
            preparedStatement.setString(1,category);
        } else if (category.equals("Ulubione")) {
            preparedStatement.setString(1,email);
        }

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String name = resultSet.getString("name");
            int weight = resultSet.getInt("weight");
            int kcal = resultSet.getInt("kcal");

            String recipeInfo = name + " - " + weight + "g - " + kcal + " kcal";
            listView.getItems().add(recipeInfo);
        }
    }

    boolean showDetails(String name, String weight, String kcal) throws SQLException {
        String findId = "SELECT preparation, ingredients FROM recipes WHERE name=? AND weight=? AND kcal=?";

        PreparedStatement preparedStatement1 = connection.prepareStatement(findId);
        preparedStatement1.setString(1, name);
        preparedStatement1.setString(2, weight);
        preparedStatement1.setString(3, kcal);
        ResultSet resultSet1 = preparedStatement1.executeQuery();

        return resultSet1.next();
    }

    void addCategories(ChoiceBox<String> choiceBox){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();

        String addCategories = "SELECT name from categories;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(addCategories);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String categoryName = resultSet.getString("name");
                choiceBox.getItems().add(categoryName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    void setpreparationDetails(Recipe recipe, String name) throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();

        String preparation = "SELECT preparation from recipes WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(preparation);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            recipe.setPreparation(resultSet.getString("preparation"));
        }
    }

    void setIngredients(Recipe recipe, String name) throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();

        String preparation = "SELECT ingredients from recipes WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(preparation);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            recipe.setIngredients(resultSet.getString("ingredients"));
        }
    }

    int findId(String name, int weight, int kcal) throws SQLException {
        String query = "SELECT id from recipes WHERE name=? AND weight=? AND kcal=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,weight);
        preparedStatement.setInt(3,kcal);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("id");
        }else {
            return 0;
        }
    }


    void addToFav(User user, Recipe recipe) throws SQLException {
        String email = user.getEmail();
        int recipeId = findId(recipe.getName(), recipe.getWeight(), recipe.getKcal());

        String query = "INSERT INTO favorite_recipes (user_email, recipe_id) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        preparedStatement.setInt(2,recipeId);

        preparedStatement.executeUpdate();

    }

    void deleteFromFav(User user, Recipe recipe) throws SQLException {
        String email = user.getEmail();
        int recipeId = findId(recipe.getName(), recipe.getWeight(), recipe.getKcal());

        String query = "DELETE FROM favorite_recipes WHERE user_email=? AND recipe_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        preparedStatement.setInt(2,recipeId);

        preparedStatement.executeUpdate();
    }

    boolean ifInFav(User user, Recipe recipe) throws SQLException {
        String email = user.getEmail();
        int recipeId = findId(recipe.getName(), recipe.getWeight(), recipe.getKcal());

        String query = "SELECT * FROM favorite_recipes WHERE user_email=? AND recipe_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        preparedStatement.setInt(2,recipeId);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return true;
        } else{
            return false;
        }
    }

    int favCounter(Recipe recipe) throws SQLException {
        int recipeId = findId(recipe.getName(), recipe.getWeight(), recipe.getKcal());

        String query = "SELECT COUNT(*) FROM favorite_recipes WHERE recipe_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,recipeId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt(1);
        }else{
            return 0;
        }
    }

    int getCategoryId(String categoryName) throws SQLException {
        String query = "Select id FROM categories WHERE name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,categoryName);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("id");
        }else{
            return 0;
        }
    }

    void addRecipe(String name, String preparation, String ingredients, int weight, int kcal, String category) throws SQLException {
        int categoryId = getCategoryId(category);
        System.out.println("Category ID: " + categoryId);
        String query = "INSERT INTO recipes (category_id, name, preparation, ingredients, weight, kcal) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,categoryId);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,preparation);
        preparedStatement.setString(4,ingredients);
        preparedStatement.setInt(5,weight);
        preparedStatement.setInt(6,kcal);

        preparedStatement.executeUpdate();
    }
}
