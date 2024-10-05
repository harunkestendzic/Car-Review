package models;

public class Cars {
	
	private int id;
	private String brand;
	private String model;
	private String type;
	private String color;
	
    public Cars() {
		
	}
	
	public Cars(int id, String brand, String model, String type, String color) {
	super();
	this.id = id;
	this.brand=brand;
	this.model=model;
	this.type=type;
	this.color=color;
	
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	

	
}
