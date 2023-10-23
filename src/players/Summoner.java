package players;

import enemies.Boar;
import enemies.Elf;
import enemies.Enemy;
import enemies.Rat;
import enemies.Slime;

public class Summoner extends Player {
	//Idea, will generate enemies like Encounter and use their enemies' attacks as their own
	Enemy enemy1;
	public Summoner(String playerName) {
		playerClass = "Summoner";
		name = playerName;
		totalHealth = r.nextInt(var)+30;
		runningHealth = totalHealth;
		basicAttack = r.nextInt(var)+15;
		defense = r.nextInt(var)+5;
		totalMana = r.nextInt(var)+10;
		runningMana = totalMana;
		magicAttack = r.nextInt(var)+15;
		regenerateHealth = 0;
		regenerateMana = 3;
		alive = true;
		listOfActions = new String[] {"Info","Heal","Knife","Gust"};
		requiredMana = new int [] {0,10,0,10,20,40,60,80};
		listOfUpgrades = new String[] {"Increase speed","Increase defense","Increase mana regen","Increase health regen","Unlock slime"};
		damageModifier = new double[] {1,1,1.5,2,2.5,3};
		doesBasicDamage = new boolean[] {false,true,false,false,true,false};
		listOfTools = new String[] {"Slime","Bow","Rat","Boar","Crossbow","Elf"};
	}
	public void unlock() {
		super.unlock();
		System.out.println();
		if (doesBasicDamage[unlockNumber]==false) {
			System.out.println("You can now summon " +listOfActions[listOfActions.length-1].toLowerCase()+ "s to fight for you");
		}else {
			System.out.println("You unlocked " +listOfActions[listOfActions.length-1]);
		}
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
			}else{
				if (actionIndex==3) {
					damage = (int)((double)magicAttack*damageModifier[actionIndex-2]);
				}else {
					if (actionIndex==4) {
						enemy1 = new Slime(basicAttack);
					}else if(actionIndex==5) {
						enemy1 = new Rat(basicAttack);
					}else if(actionIndex==6) {
						enemy1 = new Boar(basicAttack);
					}else if(actionIndex==7) {
						enemy1 = new Elf(magicAttack);
					}
					enemy1.chooseSomething();
					damage = enemy1.getDamage();
				}
				runningMana -= requiredMana[actionIndex];
			}
		}
	}
}
