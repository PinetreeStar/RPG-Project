package enemies;

public class Boar extends Enemy {
	
	public Boar(int levelNumber) {
		enemyClass = "Boar";
		totalHealth = (int)((((double)r.nextInt(var)+30)*(1+(levelNumber/10)))+.5);
		runningHealth = totalHealth;
		basicAttack = (int)(((double)r.nextInt(var)+10+(Math.sqrt(levelNumber)))+.5);
		magicAttack = 0;
		defense = r.nextInt(var)+15;
		totalMana = 0;
		regenerateHealth = 2;
		regenerateMana = 0;
		alive = true;
	}
	public void getInfo() {
		System.out.println();
		System.out.println("This wild creature is a distant relative of our domesticated pigs, they solve their problems with brute force instead of magic.");
	}
	public void chooseSomething() {
		willAttack = true;
		damage = basicAttack;
	}
}
