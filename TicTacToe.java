
import java.util.*;

/**
 * This class is a connect four game 
 * This class creates a tic tac toe board for the players to play on
 * and allows the players to play on the board, and it will check if players have won
 * This program has methods to to create the tic tac toe board, check if a player has won, and place pieces on the board
 * This program assumes that the players know how to play tic tac toe.
 *
 * @author (Victor Ly, Jose Palomera)
 * @version (10/13/19)
 */
public class TicTacToe {
    //instance variable for the tic tac toe table
    private char[][] table;

    /**
     * This constructor creates and plays the tic tac toe game
     * It allows players to play connect four for as long as they want
     * and wll continue to track the wins of the players.
     * 
     * @param first     The first player in the game. Player
     * @param second    The second player in the game. Player
     */
    public TicTacToe(Player first, Player second) {

        //Char array will hold all the elements in the (X and O's)
        //as the game is being played
        table = new char[3][3];

        //Assigns every element to N meaning there is nothing 
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                table[i][j] = ' ';
            }
        }
        System.out.println("Welcome to Tic-Tac-Toe!");

        //Boolean to see if the game is over or not
        boolean gameOver = false;
        Scanner scnr = new Scanner(System.in);

        do {

            //If it's the start of the game, or player two hasn't won, run loop again
            showTheTable();
            System.out.println(first + " pick your row(1-3)");

            /*
             * A check is done to see that Player one inputs an actual row number
             * and catches any Exception if occurred
             */
            int row = -1;

            boolean playerSet = false;

            while(playerSet == false) {
                try {

                    row = scnr.nextInt();

                    if(row != 1 && row != 2 && row != 3) {
                        throw new InputMismatchException();
                    }

                    playerSet = true;

                }catch(InputMismatchException e) {
                    System.out.println("Enter a valid ROW NUMBER");
                    scnr.nextLine();
                    playerSet = false;
                }
            }

			
            row = shiftRows(row);

            /*
             * A check is done to see that Player One inputs an actual column number
             * and catches any Exception if occurred
             */
            int column = 0;
            System.out.println(first + " pick your column(1-3)");
            boolean validity = false;

            while(validity == false) {
                try {

                    column = scnr.nextInt() - 1;

                    if(column != 0 && column != 1 && column != 2) {
                        throw new InputMismatchException();
                    }

                    validity = true;
                }catch(InputMismatchException e) {
                    System.out.println("Enter a valid COLUMN NUMBER");
                    scnr.nextLine();
                    validity = false;
                }
            }

            //If there is still space available, call the method
            //to fill in a spot
            if(isFull() == false) {
                spotChosen(row, column, first.getSignal());
            }

            //Check to see if player one has won the game after his turn
            //If the player won, their score is incremented and the
            //current game is over
            if(anyWin(first.getSignal()) == true) {
                System.out.println(first + " won!");
                first.wonAGame();
                break;
            }

            //If no one has won the game, and the table is full
            //End the game
            if(isFull() == true) {
                boolean gameDone = isFull();
                break;
            }

            //Player Two has their turn if player one hasn't won
            showTheTable();
            System.out.println(second + " pick your row(1-3)");

            /*
             * A check is done to see that Player 2 inputs an actual row number
             * and catches any Exception if occurred
             */
            int secRow = -1;

            boolean secPlayerSet = false;

            while(secPlayerSet == false) {
                try {

                    secRow = scnr.nextInt();

                    if(secRow != 1 && secRow != 2 && secRow != 3) {
                        throw new InputMismatchException();
                    }

                    secPlayerSet = true;

                }catch(InputMismatchException e) {
                    System.out.println("Enter a valid ROW NUMBER");
                    scnr.nextLine();
                    secPlayerSet = false;
                }
            }

            secRow = shiftRows(secRow);

            /*
             * A check is done to see that Player Two inputs an actual column number
             * and catches any Exception if occurred
             */
            int secColumn = 0;
            System.out.println(second + " pick your column(1-3)");

            boolean secValidity = false;

            while(secValidity == false) {
                try {

                    secColumn = scnr.nextInt() - 1;

                    if(secColumn != 0 && secColumn != 1 && secColumn != 2) {
                        throw new InputMismatchException();
                    }

                    secValidity = true;
                }catch(InputMismatchException e) {
                    System.out.println("Enter a valid COLUMN NUMBER");
                    scnr.nextLine();
                    secValidity = false;
                }
            }

            //If there is still space available, call the method
            //to fill in a spot
            if(isFull() == false) {
                spotChosen(secRow, secColumn, second.getSignal());
            }

            //If player 2 has won the game after their game
            //End the game and increment their score
            if(anyWin(second.getSignal()) == true) {
                System.out.println(second + " won!");
                second.wonAGame();
                gameOver = true;
            }

            //If no one has won, and the table is full after player 2's turn
            //End the game
            if(isFull() == true) {
                boolean gameDone = isFull();
                break;
            }

            //While the game is still going
            //keep playing the game until someone wins
        }while(gameOver == false);

        scnr.close();

        //Show the table after a player has won
        showTheTable();
        System.out.println(first + "'s games won: " + first.getGamesWon());
        System.out.println(second + "'s games won: " + second.getGamesWon());

    }

    /**
     * This method prints the tic tac toe table 
     * It also prints out indicators for the rows and columns so the user knows the corresponding number to the box
     */
    public void showTheTable() {
        System.out.println("   [1][2][3]");
        System.out.println("   ----------");
        int rowNum = 3;
        for(int i = 0; i < table.length; i++) {

            System.out.print("[" + rowNum + "]");

            for(int j = 0; j < table[i].length; j++) {
                System.out.print("| " + table[i][j] );
            }
            System.out.print("|");
            System.out.println();
            System.out.println("   ----------");
            rowNum--;

        }

    }

    /**
     * This method checks if the spot that the tic-tac-toe piece to be put into is already filled
     * and if it is it allows the user to continue to keep trying to place a piece
     * 
     * @param row       The row that the piece is trying to be placed in. int
     * @param column    The column that the piece is trying to be placed in. int
     * @param sign      The symble of the player. char
     */
    public void spotChosen(int row, int column, char sign) {

        Scanner temp = new Scanner(System.in);

        boolean valid = false;		
        boolean spotFull = false;

        //Do while loop runs every time a player inputs an invalid row
        //or column number
        do {

            if (row <= 2 && row >=0 && column <= 2 && column >= 0 && table[row][column] == ' ') {
                table[row][column] = sign;
                spotFull = true;
            }
            else {
                System.out.println("Spot is invalid");
                System.out.println("Enter a different spot");

                /*
                 * A check is done to see that the user inputs an actual number
                 * and catches any Exception if occurred
                 */
                boolean playerSet = false;

                while(playerSet == false) {
                    try {

                        row = temp.nextInt();

                        if(row != 1 && row != 2 && row != 3) {
                            throw new InputMismatchException();
                        }

                        playerSet = true;

                    }catch(InputMismatchException e) {
                        System.out.println("Enter a valid ROW NUMBER");
                        temp.nextLine();
                        playerSet = false;
                    }
                }

			
                row = shiftRows(row);

                /*
                 * A check is done to see that the user inputs an actual number
                 * and catches any Exception if occurred
                 */

                boolean validity = false;

                while(validity == false) {
                    try {

                        column = temp.nextInt() - 1;

                        if(column != 0 && column != 1 && column != 2) {
                            throw new InputMismatchException();
                        }

                        validity = true;
                    }catch(InputMismatchException e) {
                        System.out.println("Enter a valid COLUMN NUMBER");
                        temp.nextLine();
                        validity = false;
                    }
                }

            }

        }while(spotFull == false);

    }
    /**
     * This method checks if the tic tac toe board is full of pieces.
     * 
     * @return true     returns true if the board is full. boolean
     * @return false    returns false if the board is not full. boolean
     */
    public boolean isFull() {

        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table.length; j++) {
                if(table[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * This method checks if a player has won the game of tic tac toe.
     * 
     * @param piece     the piece of the player that's being checked if they won. int
     * 
     * @return true     returns true if the player has won. boolean
     * @return false    returns false if the player has not won. boolean
     */
    public boolean anyWin(int piece) {

        //Checks diagonally
        if(table[0][0] == piece && table[1][1] == piece && table[2][2] == piece) {
            return true;
        }else if(table[0][2] == piece && table[1][1] == piece && table[2][0] == piece){
            return true;
        }

        //Checks across
        for(int i = 0; i < table.length; i++) {
            if(piece == table[i][0] && piece == table[i][1] && piece == table[i][2]) {
                return true;
            }
        }
        //Checks up/down
        for(int i = 0; i < table.length; i++) {
            if(piece == table[0][i] && piece == table[1][i] && piece == table[2][i]) {
                return true;
            }
        }

        //Returns false if there isn't 3 in a row
        return false;

    }

    /**
     * This method shifts the number for the row entered in by the player.
     * This shift is needed to allow the game to be more intuitive for the player
     * 
     * @param row       The row enetered in by the player. int
     * 
     * @return row      The row number after it has been shifted. int
     */
    public int shiftRows(int row) {

        //Adjusts the rows to make it more intuitive for the user
        if(row == 1) {
            return 2;
        }else if(row == 2){
            return 1;
        }else if(row == 3){
            return 0;
        }

        return row;
    }

}
