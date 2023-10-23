package baseGame;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import enemies.Boar;
import enemies.Elf;
import enemies.Enemy;
import enemies.Rat;
import enemies.Slime;
import players.Assassin;
import players.Mage;
import players.Player;
import players.Summoner;
import players.Warrior;

public class AlphaGame {
	
	public static void main(String[] args) {
		String playerName;
		String playerClass;
		Player player1 = null;
		Enemy enemy1 = null;
		int levelNumber = 0;
		int levelCounter;
		//String listOfEnemies[] = new String[] {"Slime","Rat"}; Could uncomment, but this array isn't necessary for the code, just nice to have.
		String listOfPlayers[] = new String[] {"Mage","Assassin","Warrior","Summoner"}; 
		Random r = new Random();
		Scanner scan = new Scanner(System.in);
		//Variables above this comment
		System.out.println("What is your name? ");
		while(true) {
			playerName = scan.nextLine();
			if(playerName.length()>0) {
				break;
			}
		}
		do {
			System.out.println();
			System.out.println("What is your class? (Assassin/Mage/Warrior/Summoner) ");
			playerClass = scan.nextLine();
			playerClass = playerClass.toUpperCase();
			String playerClasss = playerClass.substring(1);
			playerClasss = playerClasss.toLowerCase();
			playerClass = playerClass.substring(0,1);
			playerClass = (playerClass+playerClasss);
			//Variable playerClass is now one of the players
		}while((Arrays.asList(listOfPlayers).contains(playerClass))==false);
		if (playerClass.equalsIgnoreCase("Mage")) {
			player1 = new Mage(playerName);
		}else if (playerClass.equalsIgnoreCase("Assassin")) {
			player1 = new Assassin(playerName);
		}else if (playerClass.equalsIgnoreCase("Warrior")) {
			player1 = new Warrior(playerName);
		}else if (playerClass.equalsIgnoreCase("Summoner")) {
			player1 = new Summoner(playerName);
		}
		System.out.println();
		System.out.println("Nice, here are your stats");
		System.out.println("Your health is: " +player1.getTotalHealth());
		System.out.println("Your defense is: "+ player1.getDefense());
		System.out.println("Your mana is: "+ player1.getTotalMana());
		System.out.println("Your basic attack damage is: "+ player1.getBasicAttack());
		System.out.println("Your magic attack damage is: "+ player1.getMagicAttack());
		//Character Creation above this comment
		while(true) {
			levelNumber ++;
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("Encounter number " +levelNumber);
			if(levelNumber<14) {
				levelCounter = levelNumber;
			}else {
				levelCounter = 14;//Change this manipulate which enemies can generate.
			}
			int enemyType = r.nextInt(levelCounter)+1;
			if (enemyType<=2) {
				System.out.println();
				System.out.println("You come across a slime");
				enemy1 = new Slime(levelNumber);
			}else if(enemyType<=5) {
				System.out.println();
				System.out.println("You stumble across a rat");
				enemy1 = new Rat(levelNumber);
			}else if(enemyType<=9) {
				System.out.println();
				System.out.println("You run into an angry boar");
				enemy1 = new Boar(levelNumber);
			}else if(enemyType<=14) {
				System.out.println();
				System.out.println("You find an elf, he doesn't look friendly");
				enemy1 = new Elf(levelNumber);
			}
			System.out.println("It has " +enemy1.getTotalHealth()+ " health, " +(int)enemy1.getDefense()+ " defense, " +enemy1.getBasicAttack()+ " attack, " +enemy1.getTotalMana()+ " mana, and " +enemy1.getMagicAttack()+ " magic attack.");
			//The two if statements above this change which enemies generate and how often respectively
			try {
				Encounter.fight(player1, enemy1);
				System.out.println();
				System.out.println("Congratulations, you have defeated your foe!");
				player1.levelUp(levelNumber);
				player1.reset();
			}catch(PlayerIsDeadException e) {
				System.out.println();
				System.out.println(player1.getName()+ " has fallen at encounter " +levelNumber);
				scan.close();
				break;
			}
			
		}
		
	}
}
