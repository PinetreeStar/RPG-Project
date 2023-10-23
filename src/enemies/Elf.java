package enemies;

public class Elf extends Enemy {
	
	public Elf(int levelNumber) {
		enemyClass = "Elf";
		totalHealth = (int)((((double)r.nextInt(var)+25)*(1+(levelNumber/10)))+.5);
		runningHealth = totalHealth;
		basicAttack = (int)(((double)r.nextInt(var)+5+(Math.sqrt(levelNumber)))+.5);
		magicAttack = (int)(((double)r.nextInt(var)+15+(Math.sqrt(levelNumber)))+.5);
		defense = r.nextInt(var)+5;
		totalMana = (int)(((double)r.nextInt(var)+15+(Math.sqrt(levelNumber)))+.5);
		runningMana = totalMana;
		regenerateHealth = 0;
		regenerateMana = 2;
		alive = true;
	}
	public void getInfo() {
		System.out.println();
		System.out.println("This humanoid figure uses its deep knowledge of magic to accomplish its goals.");
	}
	public void chooseSomething() {
		if (runningMana>=10) {
			if (runningHealth<((double)totalHealth*(1/3))) {
				this.doSomething();
			}else {
				runningMana -= 10;
				willAttack = true;
				damage = magicAttack;
			}
		}else {
			willAttack = true;
			damage = basicAttack;
		}
	}
	public void doSomething() {
		runningMana -= 10;
		if ((runningHealth + (int)((double)totalHealth*.25))>totalHealth) {
			System.out.println();
			System.out.println("The elf healed for " +(totalHealth-runningHealth));
			runningHealth = totalHealth;
		}else {
			System.out.println();
			System.out.println("The elf healed for " +(int)((double)totalHealth*.25));
			runningHealth += (int)((double)totalHealth*.25);
		}
	}
}
