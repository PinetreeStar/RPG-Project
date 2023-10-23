package players;

public class Assassin extends Player {
	public Assassin(String playerName) {
		playerClass = "Assassin";
		name = playerName;
		totalHealth = r.nextInt(var)+30;
		runningHealth = totalHealth;
		basicAttack = r.nextInt(var)+20;
		defense = r.nextInt(var)+5;
		totalMana = r.nextInt(var)+15;
		runningMana = totalMana;
		magicAttack = r.nextInt(var)+10;
		regenerateHealth = 0;
		regenerateMana = 2;
		alive = true;
		listOfActions = new String[] {"Info","Heal","Dagger","Shock"};
		requiredMana = new int [] {0,10,0,10,20};
		listOfUpgrades = new String[] {"Increase speed","Increase defense","Increase mana regen","Increase health regen","Unlock shuriken"};
		damageModifier = new double[] {1,1,1.5,2};
		doesBasicDamage = new boolean[] {true,false,true,true,false,true};
		listOfTools = new String[] {"Shuriken","Poison","Kunai","Makibishi","Snapfreeze","Katana"};
	}
	public void unlock() {
		super.unlock();
		System.out.println();
		System.out.println("You unlocked " +listOfActions[listOfActions.length-1]);
	}
	public void chooseSomething() {
		super.chooseSomething();
		if(actionIndex==0 || actionIndex==1) {
			willAttack = false;
			//Specific to Info and Heal
		}else {
			willAttack = true;
			if (actionIndex==2) {
				damage = (int)((double)basicAttack*damageModifier[0]);
			}else {
				damage = (int)((double)magicAttack*damageModifier[actionIndex-2]);
				runningMana -= requiredMana[actionIndex];
			}
		}
	}
}