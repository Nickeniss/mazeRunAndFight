package utilities;

public class Weapon {
	private int damage;
	private String weaponName;
	
	
	public Weapon(int damage, String weaponName) {
		super();
		this.damage = damage;
		this.weaponName = weaponName;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public String getWeaponName() {
		return weaponName;
	}
	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}
	
}
