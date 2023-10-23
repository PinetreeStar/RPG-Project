package players;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Player {
	//Possible rounding error with tornado
	protected Scanner scan = new Scanner(System.in);
	//
	protected Random r = new Random();
	protected int var = 11;
	protected String playerClass;
	protected String name;
	protected int totalHealth;
	protected int runningHealth;
	protected int basicAttack;
	protected int defense;
	protected int totalMana;
	protected int runningMana;
	protected int magicAttack;
	protected int regenerateHealth;
	protected int regenerateMana;
	protected boolean alive;
	protected String listOfActions[] = new String[] {};
	protected int requiredMana [] = new int[] {};
	protected String listOfUpgrades[] = new String[] {"Increase speed","Increase defense","Increase mana regen","Increase health regen"};//Reminder for constant unlocks
	protected double damageModifier[] = new double[] {};
	protected boolean doesBasicDamage[] = new boolean[] {};//Values added in each playerclass, used for unlocked actions
	protected String listOfTools[] = new String[] {};
	//Above variables are used in character creation
	private double speed = 50.5;
	protected String action;
	protected String prevAction;
	private String upgrade;
	protected int damage;
	protected boolean willAttack;
	protected boolean notEnoughMana = false;
	protected int unlockNumber = -1;
	protected int actionIndex;
	private String listOfUpgrades2[] = new String[listOfUpgrades.length-1];
	//Separating variables above from methods below
	public int getTotalHealth() {
		return totalHealth;
	}
	public int getDefense() {
		return defense;
	}
	public int getTotalMana() {
		return totalMana;
	}
	public int getBasicAttack() {
		return basicAttack;
	}
	public int getMagicAttack() {
		return magicAttack;
	}
	public double getSpeed() {

		return speed;
	}
	public String getAction() {
		return action;
	}
	public boolean getWillAttack() {
		return willAttack;
	}
	public int getDamage() {
		return damage;
	}
	public int getRunningHealth() {
		return runningHealth;
	}
	public int getRunningMana() {
		return runningMana;
	}
	public boolean isAlive() {
		return alive;
	}
	public String getName() {
		return name;
	}
	public void chooseSomething() {
		actionIndex = 0;
		do {
			while(true) {
				notEnoughMana = false;
				System.out.println();
				System.out.println("What would you like to do?");
				for (int i = 0; i < listOfActions.length; i ++) {
					System.out.print(listOfActions[i]+ "   ");
				}
				while(true) {
					action = scan.nextLine();
					if(action.equals("q")) {
						if (prevAction!=null) {
							action = prevAction;
							break;
						}else {
							continue;
						}
					}else if(action.length()>1) {
						break;
					}
				}
				action = action.toUpperCase();
				String actionn = action.substring(1);
				actionn = actionn.toLowerCase();
				action = action.substring(0,1);
				action = (action+actionn);
				//Variable action is now one of the action choices
				for (int i = 0; i < listOfActions.length; i ++) {
					if (listOfActions[i].equals(action)) {
						actionIndex = i;
						if (requiredMana[i]>0) {
							if (runningMana<requiredMana[i]) {
								notEnoughMana = true;
								System.out.println();
								System.out.println("You don't have enough mana for that");
								break;
							}
						}
					}
				}
				if(notEnoughMana==false) {
					break;
				}
			}
		}while((Arrays.asList(listOfActions).contains(action))==false);
		prevAction = action;
	}
	public void beingAttacked(int damageDone) {
		runningHealth -= (int)(((double)damageDone*(1-((double)defense/100)))+.5);
		if (runningHealth<=0) {
			alive = false;
		}
	}
	public void doSomething() {
		if (action.equals("Heal")) {
			runningMana -= 10;
			if ((runningHealth + (int)((double)totalHealth*.25))>totalHealth) {
				System.out.println();
				System.out.println("You heal for " +(totalHealth-runningHealth));
				runningHealth = totalHealth;
			}else {
				System.out.println();
				System.out.println("You heal for " +(int)((double)totalHealth*.25));
				runningHealth += (int)((double)totalHealth*.25);
			}
		}
	}
	public void reset() {
		runningHealth = totalHealth;
		runningMana = totalMana;
	}
	public void regenerate() {
		runningHealth += regenerateHealth;
		if (runningHealth>totalHealth) {
			runningHealth = totalHealth;
		}
		runningMana += regenerateMana;
		if (runningMana>totalMana) {
			runningMana = totalMana;
		}
	}
	public void levelUp(int levelNumber) {
		if ((levelNumber%3)==0) {
			//Special stats that only level up every x number of rounds, determined by the if statement above
			while(true) {
				System.out.println();
				System.out.println();
				System.out.println("What would you like to do?");
				for (int i = 0; i < listOfUpgrades.length; i ++) {
					System.out.print(listOfUpgrades[i]+ "   ");
				}
				while(true) {
					upgrade = scan.nextLine();
					if(upgrade.length()>0) {
						break;
					}
				}
				upgrade = upgrade.toUpperCase();
				String upgradee = upgrade.substring(1);
				upgradee = upgradee.toLowerCase();
				upgrade = upgrade.substring(0,1);
				upgrade = (upgrade+upgradee);
				if (Arrays.asList(listOfUpgrades).contains(upgrade)) {
					break;
				}
			}
			//Variable upgrade is now one of the upgrade choices
			if (upgrade.equalsIgnoreCase("Increase Speed")) {
				speed += 5;
				if (speed>100) {
					for (int i = 0; i < listOfUpgrades.length; i ++) {
						if((listOfUpgrades[i].equalsIgnoreCase("Increase Speed"))==false) {
							for (int j = 0; j < listOfUpgrades2.length; j ++) {
								if(listOfUpgrades2[j]==null) {
									listOfUpgrades2[j] = listOfUpgrades[i];
									break;
								}
							}
						}
					}
					listOfUpgrades = listOfUpgrades2;
					listOfUpgrades2 = new String[listOfUpgrades.length-1];
					System.out.println();
					System.out.println("Congrats, you have maxed out your speed.  You now will always go first.");
					System.out.println();
					speed = 100.5;
				}else {
					System.out.println();
					System.out.println("Your speed has been increased");
					System.out.println();
				}
			}else if(upgrade.equalsIgnoreCase("Increase defense")) {
				defense += 2;
				if (defense>=70) {
					for (int i = 0; i < listOfUpgrades.length; i ++) {
						if((listOfUpgrades[i].equalsIgnoreCase("Increase Defense"))==false) {
							for (int j = 0; j < listOfUpgrades2.length; j ++) {
								if(listOfUpgrades2[j]==null) {
									listOfUpgrades2[j] = listOfUpgrades[i];
									break;
								}
							}
						}
					}
					listOfUpgrades = listOfUpgrades2;
					listOfUpgrades2 = new String[listOfUpgrades.length-1];
					System.out.println();
					System.out.println("Congrats, you have maxed out your defense.  You now block 70% of incoming damage.");
					System.out.println();
					defense = 70;
				}else {
					System.out.println();
					System.out.println("Your defense has increased");
					System.out.println();
				}
			}else if(upgrade.equalsIgnoreCase("Increase Mana Regen")) {
				regenerateMana += 2;
				if (regenerateMana==9) {
					for (int i = 0; i < listOfUpgrades.length; i ++) {
						if((listOfUpgrades[i].equalsIgnoreCase("Increase Mana Regen"))==false) {
							for (int j = 0; j < listOfUpgrades2.length; j ++) {
								if(listOfUpgrades2[j]==null) {
									listOfUpgrades2[j] = listOfUpgrades[i];
									break;
								}
							}
						}
					}
					listOfUpgrades = listOfUpgrades2;
					listOfUpgrades2 = new String[listOfUpgrades.length-1];
					System.out.println();
					System.out.println("Congrats, you have maxed out your mana regen.  You now regenerate 9 mana at the end of every turn.");
					System.out.println();
					regenerateMana = 9;
				}else {
					System.out.println();
					System.out.println("Your mana regen has been increased");
					System.out.println();
				}
			}else if(upgrade.equalsIgnoreCase("Increase Health Regen")) {
				regenerateHealth += 2;
				System.out.println();
				System.out.println("Your health regen has increased");
			}else {
				this.unlock();
			}
		}else {
			//Upgrades that happen after every battle
			totalHealth += r.nextInt(3);
			basicAttack += r.nextInt(2);
			totalMana += r.nextInt(2);
			magicAttack += r.nextInt(2);
		}
		if ((levelNumber%5)==0) {
			System.out.println();
			System.out.println("As a reminder, here are your stats");
			System.out.println();
			System.out.println("Your health is: " +totalHealth);
			System.out.println("Your defense is: " +defense);
			System.out.println("Your mana is: " +totalMana);
			System.out.println("Your basic attack power is: " +basicAttack);
			System.out.println("Your magic attack power is: " +magicAttack);
			System.out.println("Your speed is: " +(int)speed);
			System.out.println("Your health regen is: " +regenerateHealth);
			System.out.println("Your mana regen is: " +regenerateMana);
		}
	}
	public void unlock() {
		unlockNumber ++;
		//The if statement below chooses to override the basic damage attack but not the spells
		if (doesBasicDamage[unlockNumber]) {
			listOfActions[2] = listOfTools[unlockNumber];
			damageModifier[0] += .5;
		}else {
			listOfActions = Arrays.copyOf(listOfActions, listOfActions.length+1);
			listOfActions[listOfActions.length-1] = listOfTools[unlockNumber];
		}
		//Sets new upgrade below
		if ((unlockNumber+1)==listOfTools.length) {
			listOfUpgrades[listOfUpgrades.length-1] = null;
			listOfUpgrades2 = Arrays.copyOf(listOfUpgrades, listOfUpgrades.length-1);
			for(int i = 0; i < listOfUpgrades.length; i ++) {
				if (listOfUpgrades[i]!=null) {
					for(int j = 0; j < listOfUpgrades2.length; j ++) {
						if (listOfUpgrades2[j]==null) {
							listOfUpgrades2[j] = listOfUpgrades[i];
							break;
						}
					}
				}
			}
			//The loops above get rid the null variable in the listOfUpgrades
			listOfUpgrades = listOfUpgrades2;
			listOfUpgrades2 = new String[listOfUpgrades.length-1]; 
		}else {
			listOfUpgrades[listOfUpgrades.length-1] = ("Unlock " +listOfTools[unlockNumber+1].toLowerCase());
		}
	}
}
