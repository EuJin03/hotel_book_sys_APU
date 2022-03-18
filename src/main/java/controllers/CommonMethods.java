package main.java.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class CommonMethods {

  public FXMLLoader loadButtonScene(ActionEvent event) {
    String newscene = "";
    if (((Button) (event.getSource())).getId().equals("loginButton")) newscene =
      "Home.fxml";

    if (
      ((Button) (event.getSource())).getId().equals("logoutButton")
    ) newscene = "Login.fxml";

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(newscene));
      Parent root = loader.load();
      Node source = (Node) event.getSource();
      Stage stage = (Stage) source.getScene().getWindow();
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      return loader;
    } catch (IOException ex) {
      System.out.println("Error in switching stages");
      ex.printStackTrace();
    }
    return null;
  }

  public FXMLLoader loadLinkScene(ActionEvent event) {
    String newscene = "";
    if (
      ((Hyperlink) (event.getSource())).getId().equals("loginLink")
    ) newscene = "Login.fxml";

    if (
      ((Hyperlink) (event.getSource())).getId().equals("registerLink")
    ) newscene = "Register.fxml";

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(newscene));
      Parent root = loader.load();
      Node source = (Node) event.getSource();
      Stage stage = (Stage) source.getScene().getWindow();
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      return loader;
    } catch (IOException ex) {
      System.out.println("Error in switching stages");
      ex.printStackTrace();
    }
    return null;
  }
}
