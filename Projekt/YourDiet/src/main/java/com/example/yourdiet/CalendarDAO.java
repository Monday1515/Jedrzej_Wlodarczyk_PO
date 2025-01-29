package com.example.yourdiet;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalendarDAO extends BaseDAO{

    CalendarDAO(Connection connection){
        super(connection);
    }

    void addLv(ListView listView, String mealName, int kcal, int weight){
        String txt = "";
        if (mealName.equals("woda")){
            txt += mealName;
            txt += " ";
            txt += weight;
            txt += "ml";
        }else{
            txt += mealName;
            txt += "  ";
            txt += kcal;
            txt += "kcal";
            txt += " ";
            txt += weight;
            txt += "g";
        }
        listView.getItems().add(txt);
    }

    void deleteLV(ListView listView, String mealName, int kcal, int weight){
        String txt = "";
        if (mealName.equals("woda")){
            txt += mealName;
            txt += " ";
            txt += weight;
            txt += "ml";
        }else{
            txt += mealName;
            txt += "  ";
            txt += kcal;
            txt += "kcal";
            txt += " ";
            txt += weight;
            txt += "g";
        }
        listView.getItems().remove(txt);
    }
    void addMealToDB(String date, String email, String mealName, int kcal, int weight, String mealType, ListView listView) throws SQLException {
        String query = "INSERT INTO `calendar_details` (`date`, `user_email`, `meal_name`, `kcal`, `weight`, `meal_type`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,date);
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,mealName);
        preparedStatement.setInt(4,kcal);
        preparedStatement.setInt(5,weight);
        preparedStatement.setString(6,mealType);

        preparedStatement.executeUpdate();

        addLv(listView,mealName,kcal,weight);

    }

    void addToListView(String email, String mealType, ListView listView, String date) throws SQLException {
        String query = "SELECT meal_name, kcal, weight FROM calendar_details WHERE date=? AND user_email=? AND meal_type=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,date);
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,mealType);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            addLv(listView,resultSet.getString("meal_name"),resultSet.getInt("kcal"),resultSet.getInt("weight"));
        }
    }

    int getCalorieSum(String email, String date) throws SQLException {
        String query = "SELECT SUM(kcal) AS total_kcal FROM calendar_details WHERE user_email=? AND date=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,date);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("total_kcal");
        }else{
            return 0;
        }
    }

    int getWaterSum(String email, String date) throws SQLException {
        String query = "SELECT SUM(weight) AS total_ml FROM calendar_details WHERE user_email=? AND date=? AND meal_type=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,date);
        preparedStatement.setString(3,"water");

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("total_ml");
        }else{
            return 0;
        }
    }

    void deleteFromCalendar(String email, String mealName, String mealType, String date, int kcal, int weight, ListView listView) throws SQLException {
        String query = "DELETE FROM calendar_details WHERE user_email=? AND meal_name=? AND date=? AND kcal=? AND weight=? AND meal_type=? LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if(!mealType.equals("water"))
        {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2,mealName);
            preparedStatement.setString(3,date);
            preparedStatement.setInt(4,kcal);
            preparedStatement.setInt(5,weight);
            preparedStatement.setString(6,mealType);

            preparedStatement.executeUpdate();
            deleteLV(listView,mealName,kcal,weight);
        }else {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2,"woda");
            preparedStatement.setString(3,date);
            preparedStatement.setInt(4,0);
            preparedStatement.setInt(5,weight);
            preparedStatement.setString(6,mealType);

            preparedStatement.executeUpdate();
            deleteLV(listView,mealName,0,weight);
        }



    }

    void updateInDB(String email, String date, String oldMealName, int oldKcal, int oldWeight, String mealName, int kcal, int weight, String mealType, ListView listView) throws SQLException {
        String query = "UPDATE calendar_details SET meal_name=?, kcal=?, weight=? WHERE user_email=? AND date=? AND meal_name=? AND kcal=? AND weight=? AND meal_type=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,mealName);
        preparedStatement.setInt(2,kcal);
        preparedStatement.setInt(3,weight);
        preparedStatement.setString(4,email);
        preparedStatement.setString(5,date);
        preparedStatement.setString(6,oldMealName);
        preparedStatement.setInt(7,oldKcal);
        preparedStatement.setInt(8,oldWeight);
        preparedStatement.setString(9,mealType);

        preparedStatement.executeUpdate();
        deleteLV(listView,oldMealName,oldKcal,oldWeight);
        addLv(listView,mealName,kcal,weight);
    }

    void getFavortieRecipes(String email, ChoiceBox choiceBox) throws SQLException {
        String query = "SELECT recipes.name, recipes.kcal, recipes.weight FROM recipes JOIN favorite_recipes ON recipes.id = favorite_recipes.recipe_id WHERE user_email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String name = resultSet.getString("name");
            String kcal = String.valueOf(resultSet.getInt("kcal"));
            String weight = String.valueOf(resultSet.getInt("weight"));

            String txt = "";
            txt += name + " " + kcal + "kcal " + weight + "g";
            choiceBox.getItems().add(txt);
        }
    }

}
