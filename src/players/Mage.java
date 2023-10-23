package players;

public class Mage extends Player {
	public Mage(String playerName) {
		playerClass = "Mage";
		name = playerName;
		totalHealth = r.nextInt(var)+30;
		runningHealth = totalHealth;
		basicAttack = r.nextInt(var);
		defense = r.nextInt(var);
		totalMana = r.nextInt(var)+30;
		runningMana = totalMana;
		magicAttack = r.nextInt(var)+15;
		regenerateHealth = 0;
		regenerateMana = 3;
		alive = true;
		listOfActions = new String[] {"Info","Heal","Punch","Fireball"};
		requiredMana = new int [] {0,10,0,10,20,40,60,80};
		listOfUpgrades = new String[] {"Increase speed","Increase defense","Increase mana regen","Increase health regen","Unlock thunderbolt"};
		damageModifier = new double[] {1,1,1.5,2,2.5,3};
		doesBasicDamage = new boolean[] {false,true,false,false,true,false};
		listOfTools = new String[] {"Thunderbolt","Staff","Ice shard","Deluge","Scepter","Earthquake"};
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