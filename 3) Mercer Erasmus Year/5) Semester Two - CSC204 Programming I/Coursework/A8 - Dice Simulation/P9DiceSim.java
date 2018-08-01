// Yu-Ching Ho
// Assignment 8 - Dice Simulation

import java.util.Scanner;

public class P9DiceSim {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in); 
		
		System.out.print("How many dice do you have? ");
		int arg3 = keyboard.nextInt();
		
		System.out.print("How many sides of each of the dice? ");
		int arg2 = keyboard.nextInt();
		
		System.out.print("How many times to roll these dice? ");
		int arg1 = keyboard.nextInt();
		
		DiceSim dice = new DiceSim(arg1, arg2, arg3);
		dice.runSimulation();
		dice.displayCount();
		System.out.println("");
		dice.graphCount();
		
		keyboard.close();
	}
}
