package com.example.yourdiet;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainController implements Initializable{

    @FXML
    private TextField WeightOwnRecipe;

    @FXML
    private Button addOwnRecipe;
    @FXML
    private Text waterText;
    @FXML
    private AnchorPane addToCalendarOwn;
    @FXML
    private TextField kcalOwnRecpie;
    @FXML
    private TextField nameOwnRecipe;

    @FXML
    private Button addToCalendarBtn;
    @FXML
    private ImageView logOutIV;

    @FXML
    private ChoiceBox<String> calendarChoice;


    @FXML
    private AnchorPane addToCalendarPanel;

    @FXML
    private AnchorPane addToCalendarFromFav;

    @FXML
    private Button bmiCalculator;
    @FXML
    private DatePicker datePicker;

    @FXML
    private Text calorieText;
    @FXML
    private ProgressBar caloriePb;
    @FXML
    private ProgressBar waterPb;

    @FXML
    private AnchorPane calendarOptions;
    @FXML
    private Button openCalendarOptions;

    @FXML
    private AnchorPane bmiCalculatorPanel;

    @FXML
    private Text epep;

    @FXML
    private Button findRecipes;

    @FXML
    private TextField heightInput;

    @FXML
    private AnchorPane informationPanel;

    @FXML
    private Text kcalValue;

    @FXML
    private TextField calorieGoal;

    @FXML
    private TextField currentWeight;

    @FXML
    private TextField targetWeight;

    @FXML
    private ListView<String> listOfRecipes;

    @FXML
    private Button logInButton;

    @FXML
    private ListView<String> lvOfIngredients;

    @FXML
    private ImageView mailIcon;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Text name;

    @FXML
    private TextArea preparation;

    @FXML
    private AnchorPane recepieDetails;

    @FXML
    private AnchorPane recepiesPanel;

    @FXML
    private AnchorPane recepiesPanel2;
    @FXML
    private ImageView heartIcon;

    @FXML
    private Button recipeDatabase;

    @FXML
    private Text recipeName;

    @FXML
    private ChoiceBox<String> recipiesList;

    @FXML
    private Text resultText;

    @FXML
    private Text resultText1;
    @FXML
    private AnchorPane calendarPanel;

    @FXML
    private Button sendMailButton;

    @FXML
    private AnchorPane userDetailsPanel;

    @FXML
    private TextField weightInput;

    @FXML
    private Text numberOfFav;

    @FXML
    private Text weightValue;

    @FXML
    private ImageView breakfastAdd;

    @FXML
    private ImageView breakfastDelete;

    @FXML
    private ImageView breakfastEdit;

    @FXML
    private ImageView dinnerAdd;
    @FXML
    private ImageView dinnerEdit;
    @FXML
    private ImageView dinnerDelete;
    @FXML
    private ImageView supperAdd;
    @FXML
    private ImageView supperEdit;
    @FXML
    private ImageView supperDelete;
    @FXML
    private ImageView waterAdd;

    @FXML
    private ImageView waterDelete;
    @FXML
    private ImageView snacksAdd;
    @FXML
    private ImageView snacksEdit;
    @FXML
    private ImageView snacksDelete;

    @FXML
    private ListView<String> breakfastLV;
    @FXML
    private ListView<String> supperLV;
    @FXML
    private ListView<String> dinnerLV;
    @FXML
    private ListView<String> snacksLV;
    @FXML
    private ListView<String> waterLV;

    @FXML
    private AnchorPane editMealPanel;
    @FXML
    private Button editRecipe;
    @FXML
    private TextField editedKcal;
    @FXML
    private TextField editedName;
    @FXML
    private TextField editedWeight;

    @FXML
    private Button addWater;

    @FXML
    private AnchorPane addWaterPanel;
    @FXML
    private TextField waterAmount;

    @FXML
    private ChoiceBox<String> selectedFavRecipe;

    @FXML
    private ChoiceBox<String> numberOfServings;
    @FXML
    private Button addFavRecipe;
    @FXML
    private AnchorPane recipiesPanelMenu;
    @FXML
    private Button addRecipeBtn;
    @FXML
    private Button findRecipiesBtn;
    @FXML
    private AnchorPane recipeToDBPanel;
    @FXML
    private TextField nameToAdd;
    @FXML
    private ListView<String> lvOfIngredientsToAdd;
    @FXML
    private TextField quantityOfIngredient;
    @FXML
    private TextField ingredientName;
    @FXML
    private ImageView addIngredient;
    @FXML
    private ImageView deleteIngredient;
    @FXML
    private TextArea preparationToAdd;
    @FXML
    private TextField kcalToAdd;
    @FXML
    private TextField weightToAdd;
    @FXML
    private Button addRecipeToDB;
    @FXML
    private ChoiceBox<String> categoryToAdd;

    private List<AnchorPane> anchorPanes;




    @FXML
    void calculate(MouseEvent event) {
        float height = (float) Math.pow(Float.parseFloat(heightInput.getText())/100,2);
        float weight = Float.parseFloat(weightInput.getText());

        CalculatorBMI calculatorBMI = new CalculatorBMI(weight,height);
        calculatorBMI.calculate(resultText);
    }

    @FXML
    void displayBmi(MouseEvent event) {
        bmiCalculatorPanel.setVisible(true);
        mainAnchor.setVisible(false);
    }

    @FXML
    void displayRecipes(MouseEvent event) {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();
        RecipeDAO recipeDAO = new RecipeDAO(connection);

        recipiesPanelMenu.setVisible(true);
        mainAnchor.setVisible(false);

        findRecipiesBtn.setOnMouseClicked(find ->{
            recipiesPanelMenu.setVisible(false);
            recepiesPanel.setVisible(true);
        });
        addRecipeBtn.setOnMouseClicked(add ->{
            recipiesPanelMenu.setVisible(false);
            recipeToDBPanel.setVisible(true);

            recipeDAO.addCategories(categoryToAdd);

            addIngredient.setOnMouseClicked(addI ->{
                if(!quantityOfIngredient.getText().isEmpty() && !ingredientName.getText().isEmpty()){
                    lvOfIngredientsToAdd.getItems().add(quantityOfIngredient.getText() + " " + ingredientName.getText());
                    quantityOfIngredient.setText("");
                    ingredientName.setText("");
                }
            });
            deleteIngredient.setOnMouseClicked(delI ->{
                if(lvOfIngredientsToAdd.getSelectionModel().getSelectedItem() != null){
                    lvOfIngredientsToAdd.getItems().remove(lvOfIngredientsToAdd.getSelectionModel().getSelectedItem());
                }
            });

            addRecipeToDB.setOnMouseClicked(addToDB ->{
                if(!nameToAdd.getText().isEmpty() && !kcalToAdd.getText().isEmpty() && !weightToAdd.getText().isEmpty() && !preparationToAdd.getText().isEmpty() && !lvOfIngredientsToAdd.getItems().isEmpty() && categoryToAdd.getValue() != null){
                    String name = nameToAdd.getText();
                    String preparation = preparationToAdd.getText();
                    ObservableList<String> items = lvOfIngredientsToAdd.getItems();
                    String ingredients = String.join(", ", items);
                    int weight = Integer.parseInt(weightToAdd.getText());
                    int kcal = Integer.parseInt(kcalToAdd.getText());
                    String category = categoryToAdd.getValue();

                    try {
                        recipeDAO.addRecipe(name,preparation,ingredients,weight,kcal,category);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    recipeToDBPanel.setVisible(false);
                    mainAnchor.setVisible(true);

                    nameToAdd.setText("");
                    categoryToAdd.setValue(null);
                    preparationToAdd.setText("");
                    ingredientName.setText("");
                    quantityOfIngredient.setText("");
                    lvOfIngredientsToAdd.getItems().clear();
                    kcalToAdd.setText("");
                    weightToAdd.setText("");

                }
            });
        });
    }


    @FXML
    void displayUserDetails(MouseEvent event) {
        userDetailsPanel.setVisible(true);
    }



    void sendMail(Recipe recipe, String[] separatedIngredients){
        informationPanel.setVisible(true);

        String email = User.getCurrentUser().getEmail();
        String subject = "Przepis: " + recipe.getName();
        String text = "Nazwa przepisu: " + recipe.getName() + "\n\n" +
                "Składniki:\n" + String.join("\n", separatedIngredients) + "\n\n" +
                "Przygotowanie:\n" + recipe.getPreparation() + "\n\n" +
                "Waga: " + recipe.getWeight() + "g\n" +
                "Kalorie: " + recipe.getKcal() + " kcal";

        Task<Void> sendMailTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                MailSender mailSender = new MailSender();
                mailSender.sendMail(email, subject, text);
                return null;
            }
        };

        new Thread(sendMailTask).start();
    }

    void setHeartIcon(ImageView heartIcon, Image image, String imageType) {
        heartIcon.setImage(image);
        heartIcon.setUserData(imageType);
    }

    boolean isHeartEmpty(ImageView heartIcon) {
        Object userData = heartIcon.getUserData();
        return "emptyHeart".equals(userData);
    }

    void favController(RecipeDAO recipeDAO, Recipe recipe, Image filledHeart, Image emptyHeart){
        if (isHeartEmpty(heartIcon)) {
            try {
                recipeDAO.addToFav(User.getCurrentUser(),recipe);
                setHeartIcon(heartIcon,filledHeart,"filledHeart");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                recipeDAO.deleteFromFav(User.getCurrentUser(),recipe);
                setHeartIcon(heartIcon,emptyHeart,"emptyHeart");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @FXML
    void findRecipes(MouseEvent event) throws SQLException {
        recepiesPanel.setVisible(false);
        recepiesPanel2.setVisible(true);

        String category = recipiesList.getValue();

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();
        RecipeDAO recipeDAO = new RecipeDAO(connection);

        recipeDAO.displayCatogrie(category,listOfRecipes,User.getCurrentUser().getEmail());

        listOfRecipes.addEventHandler(MouseEvent.MOUSE_CLICKED, innerEvent -> {
            // Sprawdzamy, czy było to dwukrotne kliknięcie lewym przyciskiem myszy
            if (innerEvent.getButton() == MouseButton.PRIMARY && innerEvent.getClickCount() == 2) {
                String selectedItem = listOfRecipes.getSelectionModel().getSelectedItem();

                if (selectedItem != null) {
                    String[] parts = selectedItem.split(" - ");
                    String name = parts[0];
                    String weight = parts[1];
                    String kcal = parts[2];

                    weight = weight.replace("g","");
                    kcal = kcal.replace(" kcal","");



                    try {
                        if(recipeDAO.showDetails(name,weight,kcal)){
                            recepieDetails.setVisible(true);

                            Recipe recipe = new Recipe(name,Integer.parseInt(kcal),Integer.parseInt(weight));


                            recipeDAO.setpreparationDetails(recipe,name);
                            recipeDAO.setIngredients(recipe,name);


                            String separatedIngredients[] = recipe.separationOfIngredients();

                            for (String ingredient : separatedIngredients){
                                lvOfIngredients.getItems().add(ingredient);
                            }
                            recipeName.setText(recipe.getName());
                            preparation.setText(recipe.getPreparation());
                            kcalValue.setText(String.valueOf(recipe.getKcal()));
                            weightValue.setText(String.valueOf(recipe.getWeight())+'g');


                            sendMailButton.setOnMouseClicked(e -> {
                                sendMail(recipe,separatedIngredients);
                            });


                            //HEART ICON
                            Image filledHeart = new Image(getClass().getResourceAsStream("filledHeart.png"));
                            Image emptyHeart = new Image(getClass().getResourceAsStream("heart.png"));


                            boolean isFav = recipeDAO.ifInFav(User.getCurrentUser(),recipe);
                            if (isFav == true){
                                setHeartIcon(heartIcon,filledHeart,"filledHeart");
                            } else{
                                setHeartIcon(heartIcon,emptyHeart,"emptyHeart");
                            }

                            heartIcon.setOnMouseClicked(e -> {
                                favController(recipeDAO,recipe,filledHeart,emptyHeart);
                                try {
                                    numberOfFav.setText("("+ String.valueOf(recipeDAO.favCounter(recipe)) + ")");
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            });


                            numberOfFav.setText("("+ String.valueOf(recipeDAO.favCounter(recipe)) + ")");


                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }



                }
            }
        });

    }



    @FXML
    void closeInformationPanel(MouseEvent event) {
        informationPanel.setVisible(false);
    }


    @FXML
    void updateUserDeatils(MouseEvent event) throws SQLException {

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();
        UserDAO userDAO = new UserDAO(connection);

        int cGoal = Integer.parseInt(calorieGoal.getText());

        userDAO.updateDetails(User.getCurrentUser().getEmail(), cGoal);


        userDetailsPanel.setVisible(false);

    }

    String[] mealTypes = {"breakfast","dinner","supper","snack","water"};
    void test(CalendarDAO calendarDAO, String email, String date){
        for(int i=0; i<mealTypes.length; i++){
            if(mealTypes[i]=="breakfast"){
                try {
                    calendarDAO.addToListView(email,mealTypes[i],breakfastLV,date);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (mealTypes[i]=="supper") {
                try {
                    calendarDAO.addToListView(email,mealTypes[i],supperLV,date);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (mealTypes[i]=="dinner") {
                try {
                    calendarDAO.addToListView(email,mealTypes[i],dinnerLV,date);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (mealTypes[i]=="snack") {
                try {
                    calendarDAO.addToListView(email,mealTypes[i],snacksLV,date);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (mealTypes[i]=="water") {
                try {
                    calendarDAO.addToListView(email,mealTypes[i],waterLV,date);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    void checkProggres(CalendarDAO calendarDAO, UserDAO userDAO, ProgressBar progressBar, String email, String date, Text text){
        try {
            int currentCalorie = calendarDAO.getCalorieSum(email,date);
            int calorieGoal = userDAO.getCalorieGoal(User.getCurrentUser().getEmail());

            progressBar.setProgress((double) currentCalorie / calorieGoal);
            text.setText(String.valueOf(currentCalorie)+"/"+String.valueOf(calorieGoal));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    void checkProggresWater(CalendarDAO calendarDAO, ProgressBar progressBar, String email, String date, Text text) throws SQLException {
        int currentWater = calendarDAO.getWaterSum(email,date);

        progressBar.setProgress((double) currentWater/2000);
        text.setText(String.valueOf(currentWater)+"/"+String.valueOf("2000"));
    }


    void addToCalendar(UserDAO userDAO, CalendarDAO calendarDAO, String date, String email, String mealType, ListView listView){
        if(mealType == "water"){
            addWaterPanel.setVisible(true);
            if(waterAmount != null) {
                addWater.setOnMouseClicked(d -> {
                    int weight = Integer.parseInt(waterAmount.getText());
                    try {
                        calendarDAO.addMealToDB(date, email, "woda", 0, weight, mealType, listView);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        checkProggresWater(calendarDAO,waterPb,email,date,waterText);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    addWaterPanel.setVisible(false);
                });
            }
        }else {
                addToCalendarPanel.toFront();
                addToCalendarPanel.setVisible(true);


                addToCalendarBtn.setOnMouseClicked(b -> {
                    if (calendarChoice.getValue().equals("Własne danie")) {
                        addToCalendarOwn.toFront();
                        addToCalendarOwn.setVisible(true);
                        if (nameOwnRecipe.getText() != null && kcalOwnRecpie.getText() != null && WeightOwnRecipe != null) {
                            addOwnRecipe.setOnMouseClicked(c -> {
                                String mealName = nameOwnRecipe.getText();
                                int kcal = Integer.valueOf(kcalOwnRecpie.getText());
                                int weight = Integer.valueOf(WeightOwnRecipe.getText());
                                try {
                                    calendarDAO.addMealToDB(date, email, mealName, kcal, weight, mealType, listView);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                checkProggres(calendarDAO, userDAO, caloriePb, email, date, calorieText);
                                addToCalendarOwn.setVisible(false);
                                addToCalendarPanel.setVisible(false);

                                nameOwnRecipe.setText("");
                                kcalOwnRecpie.setText("");
                                WeightOwnRecipe.setText("");
                            });
                        }
                    } else if (calendarChoice.getValue().equals("Przepis z ulubionych")) {
                        addToCalendarFromFav.setVisible(true);
                        addToCalendarFromFav.toFront();
                        try {
                            calendarDAO.getFavortieRecipes(email,selectedFavRecipe);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        numberOfServings.getItems().add("1/2");
                        numberOfServings.getItems().add("1");
                        numberOfServings.getItems().add("2");

                        if(!selectedFavRecipe.getItems().isEmpty() && !numberOfServings.getItems().isEmpty()){
                            addFavRecipe.setOnMouseClicked(add ->{
                                String text = selectedFavRecipe.getValue();
                                String[] parts = text.split(" ");

                                String kcalString = parts[parts.length - 2];
                                String weightString = parts[parts.length - 1];


                                String mealName = text.substring(0, text.lastIndexOf(kcalString)).trim();

                                int kcal, weight;

                                if (numberOfServings.getValue().equals("1/2")){
                                    kcal = Integer.parseInt(kcalString.replace("kcal", ""))/2;
                                    weight = Integer.parseInt(weightString.replace("g", ""))/2;
                                } else if (numberOfServings.getValue().equals("2")) {
                                    kcal = Integer.parseInt(kcalString.replace("kcal", ""))*2;
                                    weight = Integer.parseInt(weightString.replace("g", ""))*2;
                                }else{
                                    kcal = Integer.parseInt(kcalString.replace("kcal", ""));
                                    weight = Integer.parseInt(weightString.replace("g", ""));
                                }


                                try {
                                    calendarDAO.addMealToDB(date, email, mealName, kcal, weight, mealType, listView);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }

                                checkProggres(calendarDAO, userDAO, caloriePb, email, date, calorieText);
                                addToCalendarFromFav.setVisible(false);
                                addToCalendarPanel.setVisible(false);

                                selectedFavRecipe.setValue(null);
                                numberOfServings.setValue(null);
                            });


                        }

                    }


                });
            }
    }

    void deleteFromCalendar(ListView listView, CalendarDAO calendarDAO, String email, String mealType, String date, UserDAO userDAO){
        if(listView.getSelectionModel().getSelectedItem() != null){
            String full = (String) listView.getSelectionModel().getSelectedItem();

            String regex = "(.*)\\s+(\\d+)kcal\\s+(\\d+)g";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(full);

            if(matcher.matches()){
                String name = matcher.group(1).trim();
                String calories = matcher.group(2);
                String weight = matcher.group(3);
                try {
                    calendarDAO.deleteFromCalendar(email,name,mealType,date,Integer.parseInt(calories),Integer.parseInt(weight),listView);
                    checkProggres(calendarDAO,userDAO,caloriePb,email,date,calorieText);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


        }
    }

    void editFromCalendar(ListView listView, CalendarDAO calendarDAO, String date, String mealType, UserDAO userDAO, String email){
        if(listView.getSelectionModel().getSelectedItem() != null){
            String full = (String) listView.getSelectionModel().getSelectedItem();

            String regex = "(.*)\\s+(\\d+)kcal\\s+(\\d+)g";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(full);

            if(matcher.matches()) {
                String name = matcher.group(1).trim();
                String calories = matcher.group(2);
                String weight = matcher.group(3);

                editMealPanel.setVisible(true);

                editedName.setText(name);
                editedKcal.setText(calories);
                editedWeight.setText(weight);

                if(editedName.getText() != null && editedKcal != null && editedWeight != null){
                    editRecipe.setOnMouseClicked(edit ->{
                        try {
                            calendarDAO.updateInDB(User.getCurrentUser().getEmail(), date, name, Integer.parseInt(calories), Integer.parseInt(weight), editedName.getText(), Integer.parseInt(editedKcal.getText()), Integer.parseInt(editedWeight.getText()), mealType, listView);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        editMealPanel.setVisible(false);
                        checkProggres(calendarDAO,userDAO,caloriePb,email,date,calorieText);
                    });
                }

            }


        }
    }

    boolean loaded = false;
    @FXML
    void openCalendar(MouseEvent event) {
        calendarPanel.setVisible(true);

        openCalendarOptions.setOnMouseClicked(e ->{
            if (datePicker.getValue() != null){
                String date = String.valueOf(datePicker.getValue());
                calendarPanel.setVisible(false);
                calendarOptions.setVisible(true);

                DataBaseConnection connectNow = new DataBaseConnection();
                Connection connection = connectNow.getConnection();
                CalendarDAO calendarDAO = new CalendarDAO(connection);
                UserDAO userDAO = new UserDAO(connection);

                String email = User.getCurrentUser().getEmail();

                checkProggres(calendarDAO,userDAO,caloriePb,email,date,calorieText);

                if (loaded == false){
                    test(calendarDAO,email,date);
                }
                loaded = true;



                try {
                    checkProggres(calendarDAO,userDAO,caloriePb,email,date,calorieText);
                    checkProggresWater(calendarDAO,waterPb,email,date,waterText);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                breakfastAdd.setOnMouseClicked(addBreakfast ->{
                    String mealType = "breakfast";
                    addToCalendar(userDAO, calendarDAO, date, email, mealType, breakfastLV);
                });
                breakfastDelete.setOnMouseClicked(deleteBreakfast ->{
                    String mealType = "breakfast";
                    deleteFromCalendar(breakfastLV,calendarDAO,email,mealType,date,userDAO);
                });
                breakfastEdit.setOnMouseClicked(editBreakfast ->{
                    String mealType = "breakfast";
                    editFromCalendar(breakfastLV,calendarDAO,date,mealType, userDAO, email);
                });

                dinnerAdd.setOnMouseClicked(addDinner ->{
                    String mealType = "dinner";
                    addToCalendar(userDAO, calendarDAO, date, email, mealType, dinnerLV);
                });
                dinnerDelete.setOnMouseClicked(deleteDinner ->{
                    String mealType = "dinner";
                    deleteFromCalendar(dinnerLV,calendarDAO,email,mealType,date,userDAO);
                });
                dinnerEdit.setOnMouseClicked(editDinner ->{
                    String mealType = "dinner";
                    editFromCalendar(dinnerLV,calendarDAO,date,mealType, userDAO, email);
                });

                supperAdd.setOnMouseClicked(addSupper ->{
                    String mealType = "supper";
                    addToCalendar(userDAO, calendarDAO, date, email, mealType, supperLV);
                });
                supperDelete.setOnMouseClicked(deleteSupper ->{
                    String mealType = "supper";
                    deleteFromCalendar(supperLV,calendarDAO,email,mealType,date,userDAO);
                });
                supperEdit.setOnMouseClicked(editSupper ->{
                    String mealType = "supper";
                    editFromCalendar(supperLV,calendarDAO,date,mealType, userDAO, email);
                });

                snacksAdd.setOnMouseClicked(addSnack ->{
                    String mealType = "snack";
                    addToCalendar(userDAO, calendarDAO, date, email, mealType, snacksLV);
                });
                snacksDelete.setOnMouseClicked(deleteSnack ->{
                    String mealType = "snack";
                    deleteFromCalendar(snacksLV,calendarDAO,email,mealType,date,userDAO);
                });
                snacksEdit.setOnMouseClicked(editSnack ->{
                    String mealType = "snack";
                    editFromCalendar(snacksLV,calendarDAO,date,mealType, userDAO, email);
                });

                waterAdd.setOnMouseClicked(addWater ->{
                    String mealType = "water";
                    addToCalendar(userDAO,calendarDAO,date,email,mealType,waterLV);
                });
                waterDelete.setOnMouseClicked(deleteWater ->{
                    String mealType = "water";
                    deleteFromCalendar(waterLV,calendarDAO,email,mealType,date,userDAO);
                });


            }
        });
    }

    void setAnchorsVisibility(){
        anchorPanes = new ArrayList<>();

        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getType() == AnchorPane.class) {
                field.setAccessible(true);
                try {
                    AnchorPane pane = (AnchorPane) field.get(this);
                    if (pane != null) {
                        anchorPanes.add(pane);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        anchorPanes.forEach(pane -> pane.setVisible(false));
        mainAnchor.setVisible(true);
    }


    @FXML
    void goBack(MouseEvent event) {
        setAnchorsVisibility();
        heightInput.setText("");
        weightInput.setText("");
        resultText.setText("");
        datePicker.setValue(null);
    }

    @FXML
    void goBackToCalendar(MouseEvent event){
        setAnchorsVisibility();
        calendarOptions.setVisible(true);
        waterAmount.setText("");
    }

    @FXML
    void goBackToAddOptions(MouseEvent event) {
        setAnchorsVisibility();
        calendarOptions.setVisible(true);
        addToCalendarPanel.setVisible(true);
        nameOwnRecipe.setText("");
        kcalOwnRecpie.setText("");
        WeightOwnRecipe.setText("");

    }

    @FXML
    void goBackToRecipiesPanel(MouseEvent event){
        setAnchorsVisibility();
        mainAnchor.setVisible(false);
        listOfRecipes.getItems().clear();
        recepiesPanel.setVisible(true);
    }
    @FXML
    void goBackToRecipiesPanel2(MouseEvent event){
        setAnchorsVisibility();
        mainAnchor.setVisible(false);
        recepiesPanel2.setVisible(true);
    }
    @FXML
    void goBackToRecipiesBase(MouseEvent event){
        setAnchorsVisibility();
        mainAnchor.setVisible(false);
        recepiesPanel.setVisible(true);
    }

    @FXML
    void logOut(MouseEvent event) throws IOException {
        ChangeStage changeStage = new ChangeStage();
        Stage stage = (Stage) logOutIV.getScene().getWindow();
        changeStage.newStage(stage,"loginPanel.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //przywiatanie uzykownika
        User currentUser = User.getCurrentUser();
        name.setText("Cześć " + currentUser.getName() + "!");

        //dodanie kategorii do wyszukiwarki przepisów
        recipiesList.getItems().add("Wszystkie");
        recipiesList.getItems().add("Ulubione");
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();
        RecipeDAO recipeDAO = new RecipeDAO(connection);
        recipeDAO.addCategories(recipiesList);

        //ustawienie danych uzytkownika
        UserDAO userDAO = new UserDAO(connection);
        try {
            calorieGoal.setText(String.valueOf(userDAO.getCalorieGoal(currentUser.getEmail())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        calendarChoice.getItems().add("Własne danie");
        calendarChoice.getItems().add("Przepis z ulubionych");


        //Calendar.setCurrentUser(new Calendar());

    }
}
