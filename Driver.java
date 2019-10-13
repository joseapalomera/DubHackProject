package justAProject;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
public class Driver {

		public static void main(String[] args) {
			
			Scanner play = new Scanner(System.in);
			boolean playAgain = false;
			
			//Welcomes the players to the game and asks for their names
			System.out.println("Welcome to tic-tac-toe!");
			
			System.out.println("Enter player one's name...");
			Player userOne = new Player(play.next());

			System.out.println("Enter player two's name...");
			Player userTwo = new Player(play.next());
			
			//Do while loop to make sure that the players enter a valid
			//number and not a character
			do {
			Scanner scnr = new Scanner(System.in);
			
			System.out.println("Who wants to go first?"
					+ " \nPress 1 for player one, Press 2 for player two.");
			
			
			int number = 0;
			boolean canPlay = false;
			boolean playerSet = false;

			while(playerSet == false) {
				try {
					
					number = scnr.nextInt();
					
					if(number != 1 && number != 2) {
						throw new InputMismatchException();
					}
					
					playerSet = true;
					
				}catch(InputMismatchException e) {
					System.out.println("Enter a valid NUMBER");
					scnr.nextLine();
					playerSet = false;
				}
			}
	
			do{
				
				
				if(number == 1) {
					System.out.println("Great, " + userOne + " starts!");
					System.out.println("Your signal is X, " + userTwo + " your signal is O");
					userOne.setSignal('X');
					userTwo.setSignal('O');
					canPlay = true;
					TicTacToe game = new TicTacToe(userOne, userTwo);
					scnr.close();
					break;
				}
				
				if(number == 2) {
					System.out.println("Great, " + userTwo + " starts!");
					System.out.println("Your signal is X, " + userOne + " your signal is o");
					userTwo.setSignal('X');
					userOne.setSignal('O');
					canPlay = true;
					TicTacToe game = new TicTacToe(userTwo, userOne);
					scnr.close();
					break;
				}
				
				System.out.println("Enter only 1 or 2");
				
			}while(canPlay == false);
			
			//The players are asked if they want to play again
			System.out.println("Want to play again? (Y/N)");
			String answer = play.next();
			
			if(answer.equalsIgnoreCase("Y")) {
				playAgain = true;
			}else {
				playAgain = false;
			}
			
			}while(playAgain == true);
			
		}
	}


