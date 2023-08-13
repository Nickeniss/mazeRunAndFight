package utilities;

public class DungeonRoom {
	private int dungeonRoomMonsterIndex;
	private boolean hasMonster;
	private boolean dungeonContainsTreasure;
	
	public DungeonRoom(int dungeonRoomMonsterIndex) {
		super();
		int randomNum = (int) (Math.random() * 100 + 1);
		this.dungeonRoomMonsterIndex = dungeonRoomMonsterIndex;
		this.hasMonster = (randomNum >= 20) ? true : false;;
		this.dungeonContainsTreasure = (randomNum >= 25) ? true : false;
	}

	public int getDungeonRoomMonsterIndex() {
		return dungeonRoomMonsterIndex;
	}

	public void setDungeonRoomMonsterIndex(int dungeonRoomMonsterIndex) {
		this.dungeonRoomMonsterIndex = dungeonRoomMonsterIndex;
	}

	public boolean hasMonsterValue() {
		return hasMonster;
	}

	public void setHasMonsterValues(boolean hasMonster) {
		this.hasMonster = hasMonster;
	}

	public boolean DungeonContainsTreasure() {
		return dungeonContainsTreasure;
	}

	public void setDungeonContainsTreasure(boolean dungeonContainsTreasure) {
		this.dungeonContainsTreasure = dungeonContainsTreasure;
	}
	
	public int claimTreasure(){
		int treasure = (int)Math.random() * 100 + 25;
		return treasure;
	}
	
}
