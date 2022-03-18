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

public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {
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
  }

  public static void main(String[] args)
    throws IOException, InterruptedException, ClassNotFoundException {
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

    LocalDate date1 = LocalDate.parse("2022-03-18");
    LocalDate date2 = LocalDate.parse("2021-03-21");

    Reservation test = new Reservation(
      2,
      "Tin",
      "020603101803",
      "0183216766",
      "tin@mail.com",
      201,
      date2,
      date1
    );

    ArrayList<Reservation> al = new ArrayList<Reservation>();
    al = new FileService().readReservationData();

    System.out.println(al);

    // always last
    launch(args);
  }
}
