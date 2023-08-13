package utilities;

public class Armor {
	private int armorRating;
	private String armorName;
	
	public Armor(int armorRating, String armorName) {
		super();
		this.armorRating = armorRating;
		this.armorName = armorName;
	}

	public int getArmorRating() {
		return armorRating;
	}

	public void setArmorRating(int armorRating) {
		this.armorRating = armorRating;
	}

	public String getArmorName() {
		return armorName;
	}

	public void setArmorName(String armorName) {
		this.armorName = armorName;
	}
	
	
}
