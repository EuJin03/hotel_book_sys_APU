/***************************************************************************************
        Controller class and logic implementation for Login.fxml and Register.fxml
 ***************************************************************************************/
package main.java.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
import main.java.util.ValidationService;

/**
 * @author Eugene Tin
 */
public class LoginController extends CommonMethods implements StaffDao {

  @FXML
  TextField usernameInput, passwordInput, confirmPasswordInput;

  @FXML
  Label usernameErr, usernameExistErr, passwordErr, confirmPasswordErr, registerSuccessLabel;

  /**
   * * Verify login user onButtonAction
   * @param ActionEvent retrieve user inputs and button clicked
   * @throws IOException
   */
  @Override
  public void login(ActionEvent e) throws IOException {
    boolean foundUser = false;
    String username = usernameInput.getText();
    String password = passwordInput.getText();
    ListIterator<Staff> li = null;
    try {
      // Retrieve staff data
      ArrayList<Staff> staffAl = new FileService().readStaffData();

      // loop through staff data and verify username
      li = staffAl.listIterator();
      while (li.hasNext()) {
        Staff staff = (Staff) li.next();
        if (staff.getUsername().equals(username)) {
          foundUser = true;

          // verify staff password
          boolean valid = EncryptionService.verifyUserPassword(
            password,
            staff.getPassword()
          );

          if (valid) {
            // prompt user to home page
            FXMLLoader loader = new CommonMethods().loadButtonScene(e);
            HomeController homeController = loader.getController();
            homeController.displayName(username);

            // automatically return checked out room and update room data
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
    }
  }

  /**
   * * Register new staff onButtonAction
   * @param ActionEvent retrieve user inputs and button clicked
   * @throws IOException
   */
  @Override
  public void register(ActionEvent e) throws IOException {
    // Read staff data
    ArrayList<Staff> staffAl = new FileService().readStaffData();
    // An array to catch validation error
    ArrayList<String> validateRegister = new ArrayList<String>();

    // Retrieve input data
    String username = usernameInput.getText();
    String password = passwordInput.getText();
    String confirmPassword = confirmPasswordInput.getText();
    int recentID;

    // Alert pop up confirmation
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

      // add validation errors
      validateRegister =
        new ValidationService()
        .registerValidation(username, password, confirmPassword);

      // If error exist
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
        // Add new user to database after CONFIRMATION
        // Search for the latest ID and add by 1
        if (staffAl.size() != 0) recentID =
          staffAl
            .stream()
            .max(Comparator.comparing(Staff::get_id))
            .get()
            .get_id() +
          1; else recentID = 1000;

        // Create new instance of staff from Staff entity
        Staff newStaff = new Staff(recentID, username, password);

        // Append new staff to existing staff list and update staff data
        staffAl.add(newStaff);
        new FileService().writeStaffData(staffAl);

        // Reset UI input
        clearInput();

        // Prompt user to login page to sign in
        FXMLLoader loader = new CommonMethods().loadButtonScene(e);
        LoginController loginController = loader.getController();
        loginController.displayRegisterSuccess();
      }
    }
  }

  /**
   * * UI input reset
   */
  public void clearInput() {
    usernameInput.clear();
    passwordInput.clear();
    confirmPasswordInput.clear();
    usernameErr.setStyle("-fx-opacity: 0");
    usernameExistErr.setStyle("-fx-opacity: 0");
    passwordErr.setStyle("-fx-opacity: 0");
    confirmPasswordErr.setStyle("-fx-opacity: 0");
  }

  /**
   * * set registerSuccessLabel to visible
   */
  public void displayRegisterSuccess() {
    registerSuccessLabel.setStyle("-fx-opacity: 1");
  }

  /**
   * * Prompt user to register page onLinkAction
   * @param ActionEvent retrieve hyperlink ID
   */
  public void registerPage(ActionEvent e) {
    new CommonMethods().loadLinkScene(e);
  }

  /**
   * * Prompt user to register page onLinkAction
   * @param ActionEvent retrieve hyperlink ID
   */
  public void loginPage(ActionEvent e) {
    new CommonMethods().loadLinkScene(e);
  }
}
