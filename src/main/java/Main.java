/*******************************************************************************
 Main class that loads up the initial Login.fxml and starts up application.
 ******************************************************************************/
package main.java;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.entities.Reservation;
import main.java.util.FileService;

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

    ArrayList<Reservation> al = new ArrayList<Reservation>();
    Reservation Reservation1 = new Reservation(
      1000,
      "Eugene Tin",
      "020603101803",
      "0123787986",
      "eugenetin@gmail.com",
      100,
      "Jungle",
      LocalDate.parse("2022-03-21"),
      LocalDate.parse("2022-03-22")
    );
    Reservation Reservation2 = new Reservation(
      1001,
      "Wen Xuen",
      "020603101803",
      "01827272922",
      "wenxuen@gmail.com",
      101,
      "Jungle",
      LocalDate.parse("2022-03-21"),
      LocalDate.parse("2022-04-30")
    );

    al.add(Reservation1);
    al.add(Reservation2);
    // new FileService().writeReservationData(al);

    // invoke javafx GUI
    launch(args);
  }
}
