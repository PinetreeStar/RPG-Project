package enemies;

public class Rat extends Enemy {

	public Rat(int levelNumber) {
		enemyClass = "Rat";
		totalHealth = (int)((((double)r.nextInt(var)+20)*(1+(levelNumber/10)))+.5);
		runningHealth = totalHealth;
		basicAttack = (int)(((double)r.nextInt(var)+10+(Math.sqrt(levelNumber)))+.5);
		magicAttack = 0;
		defense = r.nextInt(var)+10;
		totalMana = 0;
		regenerateHealth = 1;
		regenerateMana = 0;
		alive = true;
	}
	public void getInfo() {
		System.out.println();
		System.out.println("This rodent is fairly harmless with slightly more attack power and defense than a slime, also without magical abilities.");
	}
	public void chooseSomething() {
		willAttack = true;
		damage = basicAttack;
	}
}
