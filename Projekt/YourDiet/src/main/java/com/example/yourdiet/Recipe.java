package com.example.yourdiet;

import javafx.fxml.Initializable;

public class Recipe {
    private String name;
    private int kcal;
    private String preparation;
    private String ingredients;
    private int weight;



    Recipe(String name, int kcal,int weight){
        this.name = name;
        this.kcal = kcal;
        this.weight = weight;
    }

    void setPreparation(String preparation){
        this.preparation = preparation;
    }

    void setIngredients(String ingredients){
        this.ingredients = ingredients;
    }


    public String[] separationOfIngredients(){
        String separatedIngredients[] = ingredients.split(",\\s*");
        return separatedIngredients;
    }

    public String getName() {
        return name;
    }

    public int getKcal() {
        return kcal;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getIngredients(){
        return ingredients;
    }

    public int getWeight() {
        return weight;
    }

}
