package com.example.yourdiet;

import javax.mail.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAccountController {


    @FXML
    private ImageView arrow;
    @FXML
    private Button createAccountButton;

    @FXML
    private Text errorMessage;

    @FXML
    private TextField mailInput;

    @FXML
    private TextField nameInput;


    @FXML
    private PasswordField passwordInput1;

    @FXML
    private PasswordField passwordInput2;

    private boolean validateInputs(String name, String mail, String password1, String password2){
        boolean dot = false;
        boolean atSing = false;
        for(int i=0; i<mail.length(); i++){
            if (mail.charAt(i) == '@'){
                atSing = true;
            } else if (mail.charAt(i) == '.') {
                dot = true;
            }
        }
        if (dot == false || atSing == false){
            return false;
        }
        return !name.isEmpty() && !mail.isEmpty() && !password1.isEmpty() && !password2.isEmpty() && password1.equals(password2);
    }

    @FXML
    void createAccount(MouseEvent event) throws SQLException, IOException {
        String name = nameInput.getText();
        String mail = mailInput.getText();
        String password1 = passwordInput1.getText();
        String password2 = passwordInput2.getText();

        if (validateInputs(name,mail,password1,password2)){
            DataBaseConnection connectNow = new DataBaseConnection();
            Connection connection = connectNow.getConnection();
            UserDAO userDAO = new UserDAO(connection);

            if(userDAO.createUser(mail,password1,name)){
                userDAO.setDetails(mail);
                ChangeStage changeStage = new ChangeStage();
                Stage stage = (Stage) createAccountButton.getScene().getWindow();
                changeStage.newStage(stage,"loginPanel.fxml");
            }
            errorMessage.setVisible(false);
            connection.close();
        }
        else {
            errorMessage.setVisible(true);
        }
    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        ChangeStage changeStage = new ChangeStage();
        Stage stage = (Stage) arrow.getScene().getWindow();
        changeStage.newStage(stage,"loginPanel.fxml");
    }
}
