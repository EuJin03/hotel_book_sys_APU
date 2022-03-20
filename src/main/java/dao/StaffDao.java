package main.java.dao;

import java.io.IOException;
import javafx.event.ActionEvent;
import main.java.entities.Staff;

public interface StaffDao {
  // instanstiate methods
  Staff login(ActionEvent e) throws IOException;
  void register(ActionEvent e) throws IOException;
}
