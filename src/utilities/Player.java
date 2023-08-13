package utilities;

public class Player {
	public static final int maxHealth = 100;
	
	private String playerName;
	private int level;
	private int health;
	private int playerHealingPotions;
	private int coinAmount;
	private Weapon playerWeapon;
	private Armor playerArmor;
	
	public Player(String name) {
		this.playerName = name;
		this.level = 1;
		this.health = 100;
		this.playerHealingPotions = 3;
		this.coinAmount = 0;
		this.playerWeapon = new Weapon(10, "Iron sword");
		this.playerArmor = new Armor(17, "Platebody");
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHealth() {
		return health;
	}
	
	public int getCoinAmount() {
		return coinAmount;
	}

	public void setCointAmount(int coinAmount) {
		this.coinAmount = coinAmount;
	}
	public void setHealth(int health) {
		this.health = health;
	}

	public int getPlayerHealingPotions() {
		return playerHealingPotions;
	}

	public void setPlayerHealingPotions(int playerHealingPotions) {
		this.playerHealingPotions = playerHealingPotions;
	}

	public Weapon getPlayerWeapon() {
		return playerWeapon;
	}
	
	public String getPlayerWeaponName() {
		return playerWeapon.getWeaponName();
	}
	
	public int getPlayerWeaponDamage() {
		return playerWeapon.getDamage();
	}

	public void setPlayerWeapon(Weapon playerWeapon) {
		this.playerWeapon = playerWeapon;
	}

	public int getPlayerArmor() {
		return playerArmor.getArmorRating();
	}

	public void setPlayerArmor(Armor playerArmor) {
		this.playerArmor = playerArmor;
	}
	
	@Override
	public String toString() {
		String formattedString = String.format("%s\n\nName: %s \nLevel: %d\nHp: %d\nCoins: %d\nHealth Potions left: %d","Stats", this.playerName, this.level, this.health, this.coinAmount, this.playerHealingPotions);
		return formattedString;
		 
	}
}
