package managers;

import java.awt.BorderLayout;
import java.util.*;

import utilities.*;

public class AppMethods {
	private boolean gameON = true;
	private int menuState = 1;
	private int inputState = 1;
	private int[] inputLimiter = { 4, 3, 4, 2};
	private Scanner in = new Scanner(System.in);
	// Monsters
	// new Monster(int health, int damage, int armor, String monsterName)
	private Monster Monster_1 = new Monster(25, 10, 12, "Goblin");
	private Monster Monster_2 = new Monster(30, 7, 13, "Skeleton");
	private Monster Monster_3 = new Monster(20, 15, 8, "Rat");
	private Monster[] monsterTable = { Monster_1, Monster_2, Monster_3 };

	// Player
	private Player player;

	// Combat
	private Combat combatCalcs = new Combat();

	// DungeonRoom
	private DungeonRoom dungeonRoom;

	public AppMethods() {
		// Starts by asking the player for a name.
		createNewPlayer();
		// Displays the main player hub with options
		while (gameON) {
			mainOptions(menuState);
			inputState = userInput(inputLimiter[menuState - 1]);
			playerInput(inputState);
		}
		in.close();
	}

	private void mainOptions(int menuState) {
		switch (menuState) {
		// For Main Menu
		case 1: {
			System.out.println("\nMain menu");
			System.out.printf("\n%-10s%s\n", "1.", "Venture into a dungeon");
			System.out.printf("%-10s%s\n", "2.", "Display current stats");
			System.out.printf("%-10s%s\n", "3.", "Rest and heal");
			System.out.printf("%-10s%s\n", "4.", "Quit the game");
			break;
		}
		// For dungeon choices
		case 2: {
			System.out.println("\nYou enter a room. What do you do?");
			System.out.printf("\n%-10s%s\n", "1.", "Search for Monsters");
			System.out.printf("%-10s%s\n", "2.", "Search for Treasures");
			System.out.printf("%-10s%s\n", "3.", "Leave");
			break;
		}
		// For combat choices
		case 3: {
			System.out.println("\nA " + monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getMonsterName()
					+ " wants to fight");
			System.out.println("What will you do?");
			System.out.printf("\n%-10s%s\n", "1.", "Fight");
			System.out.printf("%-10s%s\n", "2.", "Heal");
			System.out.printf("%-10s%s\n", "3.", "Check Health Amount");
			System.out.printf("%-10s%s\n", "4.", "Run Away");
			break;
		}
		// Continuing deeper into the dungeon
		case 4: {
			System.out.println("What will you do?");
			System.out.printf("\n%-10s%s\n", "1.", "Continue Deeper into the dungeon");
			System.out.printf("%-10s%s\n", "2.", "Leave");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + menuState);
		}
	}

	private void playerInput(int inputState) {
		switch (inputState) {
		case 1: {

			if (menuState == 1 || menuState == 4) {
				int monsterSelector = (int) (Math.random() * monsterTable.length);
				dungeonRoom = null;
				System.out.println((menuState == 1) ? "\nYou venture into a dungeon!" : "\nYou venture forward into the dungeon");
				dungeonRoom = new DungeonRoom(monsterSelector);

				menuState = 2;
			}
			// Checks for a monster in a dungeon room.
			else if (menuState == 2) {

				if (dungeonRoom.hasMonsterValue()) {

					menuState++;
				} else {
					System.out.println("No Monster is the room.");
				}
			} else if (menuState == 3) {
				System.out.println("\nYou swing your " + player.getPlayerWeaponName() + " at the "
						+ monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getMonsterName() + "!");
				if (combatCalcs.getHitChance(monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getArmor())) {
					monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].setHealth(combatCalcs.calculateDamage(monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getHealth(),
							player.getPlayerWeaponDamage()));
					System.out.println("You hit and deal " + player.getPlayerWeaponDamage() + " with your " + player.getPlayerWeaponName());
					if (monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getHealth() <= 0) {
						menuState++;
						switch (dungeonRoom.getDungeonRoomMonsterIndex()) {
						case 0: {
							monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].setHealth(25);
							break;
						}
						case 1: {
							monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].setHealth(30);
							break;
						}
						case 2: {
							monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].setHealth(20);
							break;
						}
						default:
							throw new IllegalArgumentException("Unexpected value: " + dungeonRoom);
						}
						System.out.println("\nYou defeated the " + monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getMonsterName());
						foundTreasure();
					}
				
				} else {
					System.err.println("Your swing was too large and you miss!");
				}
				monsterAttacks();
			}

			break;
		}
		case 2: {
			if (menuState == 1) {
				System.out.println(player.toString());
				;
			}
			// Checks for treasure in a dungeon room.
			else if (menuState == 2) {
				foundTreasure();
				if (dungeonRoom.hasMonsterValue()) {
					menuState++;
				}
				else {
					menuState = 4;
				}
			} else if (menuState == 3) {
				if (player.getPlayerHealingPotions() >= 1) {
					player.setPlayerHealingPotions(player.getPlayerHealingPotions() - 1);
					if (player.getHealth() < player.maxHealth) {
						combatCalcs.calculateHealing(player.maxHealth, player.getHealth());
					} else {
						System.out.println("You're already fully healed!");
					}

				}
				monsterAttacks();
			} else if (menuState == 4) {
				menuState = 2;
				playerInput(++inputState);
				
			}

			break;
		}
		case 3: {
			if (menuState == 1) {
				System.out.println("After a long rest. You feel rejunvenated.");
				player.setHealth(100);
				player.setPlayerHealingPotions(3);
			} else if (menuState == 2) {
				System.out.println("You leave the dungeon... ready to fight another day.");
				menuState--;
			} else if (menuState == 3) {
				System.out.println("Your Hp: " +  player.getHealth());
				System.out.println("The " + monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getMonsterName() + " Hp: " + monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getHealth());
			}

			break;
		}
		case 4: {
			if (menuState == 1) {
				System.out.println("Thank you for playing!");
				gameON = false;

			}else if (menuState == 3) {
				if (combatCalcs.canRun()) {
					System.out.println("You managed to escape!");
					playerInput(menuState--);
				} else {
					System.out.println("The " + monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getMonsterName()
							+ " blocks your escape!");
					monsterAttacks();
				}
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + inputState);
		}
	}

	private void createNewPlayer() {
		int playerConfirmation = 2;
		String enterNameString = "Please enter a name";
		String playerName = "";

		while (playerConfirmation != 1) {
			System.out.println(enterNameString);
			playerName = in.nextLine();

			System.out.println("So your name is " + playerName + "?(Type 1 for Yes and 2 for n)");
			playerConfirmation = userInput(2);
			System.out.println(playerConfirmation + " Woot");
			// Confirms the name Choice.
			in.nextLine();
		}
		player = new Player(playerName);
	}

	private int userInput(int validOptionsRange) {
		int input = integerInputValidation();
		while (!(input > 0 && input <= validOptionsRange)) {
			System.out.println("Please enter a number between 1 & " + validOptionsRange + "!");
			input = integerInputValidation();
		}
		return input;
	}
	
	private void foundTreasure() {
		if (dungeonRoom.DungeonContainsTreasure()) {
			int treasureFound = dungeonRoom.claimTreasure();
			player.setCointAmount(player.getCoinAmount() + treasureFound);
			System.out.println("You found " + treasureFound + " coins! You add it to your coin pouch.");
		} else {
			System.out.println("No treasure in the room.");
		}
	}
	private int integerInputValidation() {
		int userInput = 0;
		while (!in.hasNextInt()) {
			in.nextLine();
			System.out.println("Please enter a valid positive integer!");
		}
		userInput = in.nextInt();
		return userInput;
	}

	private void monsterAttacks() {
		System.out.println(monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getMonsterName() + " swings at you!");
		if (combatCalcs.getHitChance(player.getPlayerArmor())) {
			player.setHealth(combatCalcs.calculateDamage(player.getHealth(),
					monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getDamage()));
			System.out.println("The " + monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getMonsterName()
					+ " hits you dealing " + monsterTable[dungeonRoom.getDungeonRoomMonsterIndex()].getDamage());
			if (player.getHealth() == 0) {
				System.out.println("Oh no! You died!");
				gameON = false;
			}
		} else {
			System.out.println("You managed to dodge!");
		}
	}

}
