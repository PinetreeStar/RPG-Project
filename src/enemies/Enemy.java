package enemies;

import java.util.Random;

public class Enemy {
	protected String enemyClass;
	protected int totalHealth;
	protected int runningHealth;
	protected int basicAttack;
	protected int defense;
	protected int totalMana;
	protected int runningMana;
	protected int magicAttack;
	protected boolean alive;
	protected String action;
	protected int damage;
	protected int regenerateHealth;
	protected int regenerateMana;
	protected boolean willAttack;
	protected Random r = new Random();
	protected int var = 11;
	protected String listOfActions[] = new String[] {};
	
	public void chooseSomething() {
	}
	public void getInfo() {		
	}
	public void doSomething() {		
	}
	public boolean getWillAttack() {
		return willAttack;
	}
	public int getBasicAttack() {
		return basicAttack;
	}
	public int getTotalHealth() {
		return totalHealth;
	}
	public int getDefense() {
		return defense;
	}
	public int getMagicAttack() {
		return magicAttack;
	}
	public int getTotalMana() {
		return totalMana;
	}
	public int getDamage() {
		return damage;
	}
	public String getEnemyClass() {
		return enemyClass;
	}
	public boolean isAlive() {
		return alive;
	}
	public int getRunningHealth() {
		return runningHealth;
	}
	public int getRunningMana() {
		return runningMana;
	}
	public void beingAttacked(int damageDone) {
		runningHealth -= (int)(((double)damageDone*(1-((double)defense/100)))+.5);
		if (runningHealth<=0) {
			enemyClass = null;
			totalHealth = 0;
			runningHealth = 0;
			basicAttack = 0;
			defense = 0;
			totalMana = 0;
			runningMana = 0;
			magicAttack = 0;
			alive = false;
			action = null;
			damage = 0;
			willAttack = false;
		}
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
}








