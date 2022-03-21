package main.java.dao;

import java.io.IOException;
import javafx.event.ActionEvent;

public interface StaffDao {
  // instanstiate methods
  void login(ActionEvent e) throws IOException;
  void register(ActionEvent e) throws IOException;
}
