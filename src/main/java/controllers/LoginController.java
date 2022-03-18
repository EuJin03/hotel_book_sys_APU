package main.java.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.dao.*;
import main.java.entities.Staff;
import main.java.util.EncryptionService;
import main.java.util.FileService;

public class LoginController extends CommonMethods implements StaffDao {

  @FXML
  TextField usernameInput;

  @FXML
  TextField passwordInput;

  @FXML
  TextField confirmPasswordInput;

  @FXML
  Label usernameErr;

  @FXML
  Label passwordErr;

  @FXML
  Label confirmPasswordErr;

  @Override
  public Staff login(ActionEvent e) throws IOException {
    boolean foundUser = false;
    String username = usernameInput.getText();
    String password = passwordInput.getText();
    ListIterator<Staff> li = null;
    try {
      ArrayList<Staff> staffAl = new FileService().readStaffData();
      li = staffAl.listIterator();
      while (li.hasNext()) {
        Staff staff = (Staff) li.next();
        if (staff.getUsername().equals(username)) {
          foundUser = true;
          boolean valid = EncryptionService.verifyUserPassword(
            password,
            staff.getPassword()
          );

          if (valid) {
            // prompt user to home page
            FXMLLoader loader = new CommonMethods().loadButtonScene(e);
            // HomeController homeController = loader.getController();
            // homeController.getUsername();

          } else {
            // set incorrect password label to visible
            passwordErr.setStyle("-fx-opacity: 1");
          }
        }
      }
      // set incorrect username label to visible
      if (!foundUser) usernameErr.setStyle(
        "-fx-opacity: 1"
      ); else usernameErr.setStyle("-fx-opacity: 0");
    } catch (Exception err) {
      err.printStackTrace();
      return null;
    }

    return null;
  }

  @Override
  public void register(ActionEvent e) {
    // TODO Auto-generated method stub
    System.out.println(usernameInput.getText());
    System.out.println(passwordInput.getText());
    System.out.println(confirmPasswordInput.getText());
    usernameErr.setStyle("-fx-opacity: 1");
    passwordErr.setStyle("-fx-opacity: 1");
    confirmPasswordErr.setStyle("-fx-opacity: 1");
  }

  public void registerPage(ActionEvent e) {
    new CommonMethods().loadLinkScene(e);
  }

  public void loginPage(ActionEvent e) {
    new CommonMethods().loadLinkScene(e);
  }
}
