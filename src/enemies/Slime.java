package enemies;

public class Slime extends Enemy {

	public Slime(int levelNumber) {
		enemyClass = "Slime";
		totalHealth = (int)((((double)r.nextInt(var)+20)*(1+(levelNumber/10)))+.5);//Health increases at a faster rate than attack
		runningHealth = totalHealth;
		basicAttack = (int)(((double)r.nextInt(var)+(Math.sqrt(levelNumber)))+.5);
		magicAttack = 0;
		defense = r.nextInt(var)+5;
		totalMana = 0;
		regenerateHealth = 1;
		regenerateMana = 0;
		alive = true;
	}
	public void getInfo() {
		System.out.println();
		System.out.println("This gelatinous mass is fairly tanky, with not much attack as well as no magical abilities.");
	}
	public void chooseSomething() {
		willAttack = true;
		damage = basicAttack;
	}
}
