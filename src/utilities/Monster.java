package utilities;

public class Monster {
	private int monsterHealth;
	private int monsterDamage;
	private int monsterArmor;
	private String monsterName;
	public Monster(int health, int damage, int armor, String monsterName) {
		super();
		this.monsterHealth = health;
		this.monsterDamage = damage;
		this.monsterArmor = armor;
		this.monsterName = monsterName;
	}
	public int getHealth() {
		return monsterHealth;
	}
	public void setHealth(int health) {
		this.monsterHealth = health;
	}
	public int getDamage() {
		return monsterDamage;
	}
	public void setDamage(int damage) {
		this.monsterDamage = damage;
	}
	public int getArmor() {
		return monsterArmor;
	}
	public void setArmor(int armor) {
		this.monsterArmor = armor;
	}
	public String getMonsterName() {
		return monsterName;
	}
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	
	
}
