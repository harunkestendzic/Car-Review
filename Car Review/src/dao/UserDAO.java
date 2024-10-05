package dao;

import javafx.collections.ObservableList;
import models.User;

public interface UserDAO {
	
	public void register(User user);
	public boolean login(String username, String password);
	

}
