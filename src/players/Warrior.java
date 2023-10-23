package players;

public class Warrior extends Player {
	public Warrior(String playerName) {
		playerClass = "Warrior";
		name = playerName;
		totalHealth = r.nextInt(var)+30;
		runningHealth = totalHealth;
		basicAttack = r.nextInt(var)+30;
		defense = r.nextInt(var)+15;
		totalMana = r.nextInt(var);
		runningMana = totalMana;
		magicAttack = r.nextInt(var);
		regenerateHealth = 0;
		regenerateMana = 2;
		alive = true;
		listOfActions = new String[] {"Info","Heal","Sword","Spark"};
		requiredMana = new int [] {0,10,0,10,20};
		listOfUpgrades = new String[] {"Increase speed","Increase defense","Increase mana regen","Increase health regen","Unlock mace"};
		damageModifier = new double[] {1,1,1.5,2};
		doesBasicDamage = new boolean[] {true,false,true,true,false,true};
		listOfTools = new String[] {"Mace","Tornado","Morningstar","Javelin","Torrent","Lance"};
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