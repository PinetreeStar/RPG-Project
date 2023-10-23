package baseGame;

import java.util.Random;

import enemies.Enemy;
import players.Player;

public class Encounter {
	public static void fight(Player player1, Enemy enemy1) throws PlayerIsDeadException{
		Random r = new Random();
		while(true) {
			System.out.println();
			System.out.println("Your health: " +player1.getRunningHealth()+ "/" +player1.getTotalHealth());
			System.out.println("Your mana: " +player1.getRunningMana()+ "/" +player1.getTotalMana());
			System.out.println("The " +enemy1.getEnemyClass()+ "'s health: " +enemy1.getRunningHealth()+ "/" +enemy1.getTotalHealth());
			System.out.println("The " +enemy1.getEnemyClass()+ "'s mana: " +enemy1.getRunningMana()+ "/" +enemy1.getTotalMana());
			int whoGoesFirst = r.nextInt(100)+1;
			player1.chooseSomething();
			enemy1.chooseSomething();
			if (player1.getAction().equals("Info")) {
				enemy1.getInfo();
				continue;
			}
			if (whoGoesFirst>player1.getSpeed()) {
				if (enemy1.getWillAttack()) {
					System.out.println();
					System.out.println("The " +enemy1.getEnemyClass()+ " strikes first!");
					player1.beingAttacked(enemy1.getDamage());
				}else {
					enemy1.doSomething();
				}
				if(player1.isAlive()==false) {
					throw new PlayerIsDeadException();
				}
			}
			// Enemy going first is above
			if (player1.getWillAttack()) {
				enemy1.beingAttacked(player1.getDamage());
				System.out.println();
				System.out.println("You strike with your " +player1.getAction());
			}else {
				player1.doSomething();
			}
			if (enemy1.isAlive()==false) {
				break;
			}
			// Player going is above
			if (whoGoesFirst<player1.getSpeed()) {
				if (enemy1.getWillAttack()) {
					System.out.println();
					System.out.println("The " +enemy1.getEnemyClass()+ " strikes back!");
					player1.beingAttacked(enemy1.getDamage());
				}else {
					enemy1.doSomething();
				}
				if(player1.isAlive()==false) {
					throw new PlayerIsDeadException();
				}
			}
			player1.regenerate();
			enemy1.regenerate();
		}
	}
}