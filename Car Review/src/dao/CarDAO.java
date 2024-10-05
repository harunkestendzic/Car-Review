package dao;

import javafx.collections.ObservableList;
import models.Cars;

public interface CarDAO {
	
	
	public void save(Cars car);
	public void update(Cars car);
	public void delete(Cars car);
	public ObservableList<Cars> getCars();

}
