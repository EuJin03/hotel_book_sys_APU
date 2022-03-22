/*******************************************************************************
 Main class that loads up the initial Login.fxml and starts up application.
 ******************************************************************************/
package main.java;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Eugene Tin
 */
public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    try {
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("controllers/Login.fxml")
      );
      Parent root = loader.load();
      loader.getController();
      Scene scene = new Scene(root);

      stage.setTitle("Resort Room Booking System");
      stage.centerOnScreen();
      stage.setResizable(false);
      stage.setScene(scene);
      stage.show();
    } catch (Exception err) {
      err.printStackTrace();
    }
  }

  public static void main(String[] args)
    throws IOException, InterruptedException, ClassNotFoundException {
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

    // new Seeder().seedStaff();
    // new Seeder().seedRoom();
    // new Seeder().seedReservation();

    // invoke javafx GUI
    launch(args);
  }
}
