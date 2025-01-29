package com.example.yourdiet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInController {

    @FXML
    private Text errorMessage;

    @FXML
    private TextField mailInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private Button logInButton;

    @FXML
    private Button createAccountButton;

    private boolean validateInputs(String email, String password){
        return !email.isEmpty() && !password.isEmpty();
    }


    @FXML
    void logIn(MouseEvent event) throws SQLException, IOException {

        String email = mailInput.getText();
        String password = passwordInput.getText();

        if(!validateInputs(email,password)){
            errorMessage.setVisible(true);
        }

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();

        UserDAO userDAO = new UserDAO(connection);

        if(userDAO.verifyLogin(email,password)){
            errorMessage.setVisible(false);

            String name = userDAO.getUserName(email);
            User.setCurrentUser(new User(name,email));

            ChangeStage changeStage = new ChangeStage();
            Stage stage = (Stage) logInButton.getScene().getWindow();
            changeStage.newStage(stage,"mainPanel.fxml");
        } else{
            errorMessage.setVisible(true);
        }


    }

    @FXML
    void createAccountPanel(MouseEvent event) throws IOException {
        ChangeStage changeStage = new ChangeStage();
        Stage stage = (Stage) createAccountButton.getScene().getWindow();
        changeStage.newStage(stage,"createAccount.fxml");
    }
}
