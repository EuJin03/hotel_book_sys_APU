package main.java.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.dao.*;
import main.java.entities.Staff;
import main.java.util.EncryptionService;
import main.java.util.FileService;
import main.java.util.ValidationService;

public class LoginController extends CommonMethods implements StaffDao {

  @FXML
  TextField usernameInput, passwordInput, confirmPasswordInput;

  @FXML
  Label usernameErr, usernameExistErr, passwordErr, confirmPasswordErr, registerSuccessLabel;

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
            HomeController homeController = loader.getController();
            homeController.displayName(username);
            new CommonMethods().refreshRoomDetails();
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
  public void register(ActionEvent e) throws IOException {
    ArrayList<Staff> staffAl = new FileService().readStaffData();
    ArrayList<String> validateRegister = new ArrayList<String>();

    String username = usernameInput.getText();
    String password = passwordInput.getText();
    String confirmPassword = confirmPasswordInput.getText();
    int recentID;

    boolean CONFIRMATION = new CommonMethods()
    .appendAlert(
        "Register Confirmation",
        "Registration for username: " + username,
        "Are you sure to register a new staff account?"
      );

    if (CONFIRMATION) {
      // Error label reset
      usernameErr.setStyle("-fx-opacity: 0");
      usernameExistErr.setStyle("-fx-opacity: 0");
      passwordErr.setStyle("-fx-opacity: 0");
      confirmPasswordErr.setStyle("-fx-opacity: 0");

      validateRegister =
        new ValidationService()
        .registerValidation(username, password, confirmPassword);

      if (validateRegister.size() != 0) {
        for (String field : validateRegister) {
          switch (field) {
            case "username":
              usernameErr.setStyle("-fx-opacity: 1");
              break;
            case "usernameExist":
              usernameExistErr.setStyle("-fx-opacity: 1");
              break;
            case "password":
              passwordErr.setStyle("-fx-opacity: 1");
              break;
            case "confirmPassword":
              confirmPasswordErr.setStyle("-fx-opacity: 1");
              break;
            default:
              break;
          }
        }
      } else {
        if (staffAl.size() != 0) recentID =
          staffAl
            .stream()
            .max(Comparator.comparing(Staff::get_id))
            .get()
            .get_id() +
          1; else recentID = 1000;

        Staff newStaff = new Staff(recentID, username, password);
        staffAl.add(newStaff);
        System.out.println(newStaff);
        new FileService().writeStaffData(staffAl);
        clearInput();
        FXMLLoader loader = new CommonMethods().loadButtonScene(e);
        LoginController loginController = loader.getController();
        loginController.displayRegisterSuccess();
      }
    }
  }

  public void clearInput() {
    usernameInput.clear();
    passwordInput.clear();
    confirmPasswordInput.clear();
    usernameErr.setStyle("-fx-opacity: 0");
    usernameExistErr.setStyle("-fx-opacity: 0");
    passwordErr.setStyle("-fx-opacity: 0");
    confirmPasswordErr.setStyle("-fx-opacity: 0");
  }

  public void displayRegisterSuccess() {
    registerSuccessLabel.setStyle("-fx-opacity: 1");
  }

  public void registerPage(ActionEvent e) {
    new CommonMethods().loadLinkScene(e);
  }

  public void loginPage(ActionEvent e) {
    new CommonMethods().loadLinkScene(e);
  }
}
