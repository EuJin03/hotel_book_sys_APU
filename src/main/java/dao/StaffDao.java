/**
 * * Data Access Object Interface - This interface defines the standard operations to be performed on a model object(s).
 * * Data Access Object concrete class -This class implements above interface. This class is responsible to get data from a datasource which can be database or any other storage
 * * Model Object or Value Object - This object is simple POJO containing get/set methods to store data retrieved using DAO class.
 */

/******************************************************************************************************************************************************
 * StaffDAO is a DAO interface class that defines the instructions to be performed on a model object(s), Staff.
 * It should be implemented on the Controller
 *****************************************************************************************************************************************************/

package main.java.dao;

import java.io.IOException;
import javafx.event.ActionEvent;

public interface StaffDao {
  // instanstiate methods
  void login(ActionEvent e) throws IOException;
  void register(ActionEvent e) throws IOException;
}
