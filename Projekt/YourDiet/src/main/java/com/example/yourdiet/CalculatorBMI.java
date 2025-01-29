package com.example.yourdiet;

import javafx.scene.text.Text;

public class CalculatorBMI {
    private float weight;
    private float height;

    CalculatorBMI(float weight, float height){
        this.weight = weight;
        this.height = height;
    }

    void calculate(Text text){
        float result = weight/height;

        if (height > 0 && weight > 0) {
            if (result < 18.5) {
                text.setText("Niedowaga! Podnieś zapotrzebowanie kaloryczne");
                text.setStyle("-fx-text-fill: red;");
                text.setVisible(true);
            } else if (result >= 18.5 && result <= 29.5) {
                text.setText("Pożądana masa ciała!");
                text.setStyle("-fx-color: green;");
                text.setVisible(true);
            } else if (result >= 25 && result <= 29.9) {
                text.setText("Nadwaga! Obniż zapotrzebowanie kaloryczne");
                text.setStyle("-fx-text-fill: red;");
                text.setVisible(true);
            } else {
                text.setText("Nadwaga! Obniż zapotrzebowanie kaloryczne");
                text.setStyle("-fx-text-fill: red;");
                text.setVisible(true);
            }
        }
    }

}
