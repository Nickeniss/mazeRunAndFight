package utilities;

public class Combat {
	private int healingPotionAmount;
	
	public Combat(){	
		this.healingPotionAmount = 25;
	}
	public boolean getHitChance(int armorRating) {
		int hitCalculation = (int) (Math.random() * 20) + 1;

		if (hitCalculation >= armorRating) {
			return true;
		} else
			return false;
	}

	public int calculateDamage(int health, int damageTaken) {
		int currentHealth = health - damageTaken;
		if (currentHealth <= 0) {
			return 0;
		} else
			return currentHealth;
	}

	public int calculateHealing( int maxHealth, int currentHealth) {
		currentHealth += this.healingPotionAmount;
		if (currentHealth > maxHealth) {
			System.out.println("You restore " + (currentHealth - maxHealth) + "hp! You now have" + maxHealth + "hp");
			return maxHealth;
		}

		else {
			System.out.println("You restore " + this.healingPotionAmount + "hp! You now have" + currentHealth + "hp");
			return currentHealth;
		}
	}

	public boolean canRun() {
		boolean canRunAway = ((int) (Math.random() * 10) + 1 >= 5) ? true : false;
		return canRunAway;
	}
}
