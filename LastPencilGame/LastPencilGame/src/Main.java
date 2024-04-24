import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num_pencils = getNumPencils(scanner);

        System.out.println("Who will be the first (John, Jack):");
        String name_1 = "";
        String name_2 = "";
        while(true){
            name_1 = scanner.nextLine();
            if(!name_1.toLowerCase().equals("john") && !name_1.toLowerCase().equals("jack")){
                System.out.println("Choose between 'John' and 'Jack'");
                continue;
            }

            if (name_1.toLowerCase().equals("jack")){
                name_2 = "John";
                name_1 = "Jack";
            } else {
                name_2 = "Jack";
                name_1 = "John";
            }

            break;
        }

        //prints our initial number of pencils for first player
        for(int i = 0; i < num_pencils; i++){
            System.out.print("|");
        }

        //----------start game!----------------
        while (num_pencils > 0){
            //checks who the player wants to go first
            //Jack is always the bot
            //the first branch of the if statement is the bot going first
            //the second branch (else) of the if statement is the bot going second
            if(name_1.toLowerCase().equals("jack")){

                //tells player that it's the bots move
                System.out.println("\n" + name_1 + "'s turn");
                int move = botMove(num_pencils);

                //minus their "move" from the number of pencils on the board
                num_pencils -= move;

                //checks if bot removed last pencil
                //if so calls player 2 the winner and gameover!
                if(GameOver(num_pencils, name_2) == true){
                    break;
                }

                //tell player 2 (not bot) that it is their move
                //accept their input and check for incorrect inputs
                //minus their "move" from the number of pencils on the board
                System.out.println("\n" + name_2 + "'s turn");
                move = playerMove(num_pencils, scanner);


                num_pencils -= move;

                //checks if player 2 removed last pencil
                //if so calls player 1 (the bot) the winner and gameover!
                if(GameOver(num_pencils, name_1) == true){
                    break;
                }
            } else {
                //tells the player that it is their move
                //accept their input and check for incorrect inputs
                System.out.println("\n" + name_1 + "'s turn");
                int move = playerMove(num_pencils, scanner);

                //minus their "move" from the number of pencils on the board
                num_pencils -= move;

                //checks if player 1 removed last pencil
                //if so calls player 2 (bot) the winner and gameover!
                if(GameOver(num_pencils, name_2) == true){
                    break;
                }


                //tells player that it is the bots turn
                System.out.println("\n" + name_2 + "'s turn");
                move = botMove(num_pencils);

                //minus their "move" from the number of pencils on the board
                num_pencils -= move;

                //checks if the bot removed last pencil
                //if so calls the player the winner and gameover!
                if(GameOver(num_pencils, name_1) == true){
                    break;
                }
            }
        }
    }

    private static int getNumPencils(Scanner scanner){
        int num_pencils = 0;
        System.out.println("How many pencils would you like to use:");
        while(true){
            try{
                num_pencils = Integer.parseInt(scanner.nextLine());

                if (num_pencils <= 0){
                    System.out.println("The number of pencils should be positive");
                    continue;
                }

                return num_pencils;

            } catch (NumberFormatException e){
                System.out.println("The number of pencils should be numeric");
            }
        }
    }

    private static int playerMove(int num_pencils, Scanner scanner) {
        int move = 0;
        while (num_pencils > 0) {

            while (true) {
                try {
                    move = Integer.parseInt(scanner.nextLine());
                    if (move != 1 && move != 2 && move != 3) { //
                        System.out.println("Possible values: '1', '2' or '3'");
                        continue;
                    } else if (move > 3 || move > num_pencils) {
                        System.out.println("Too many pencils were taken");
                        continue;
                    }

                    return move;

                } catch (InputMismatchException e) {
                    System.out.println("Possible values: '1', '2' or '3'");
                } catch (NumberFormatException e) {
                    System.out.println("Possible values: '1', '2' or '3'");
                }
            }
        }

        return move;
    }

    private static boolean GameOver(int num_pencils, String name){
        if(num_pencils == 0) {
            System.out.println(name + " won!");
            return true;
        } else if (num_pencils > 0){
            for(int i = 0; i < num_pencils; i++){
                System.out.print("|");
            }
        }
        return false;
    }

    private static int botMove(int num_pencils){
        int move = 0;
        Random rng = new Random();
        if(num_pencils == 1){
            move = 1;
            System.out.println(1);
        } else {
            switch (num_pencils % 4){
                //case 4, 8, 12, 16:
                case 0:
                    move = 3;
                    System.out.println(3);
                    break;
                //case 5, 9, 13, 17:
                case 1:
                    move = rng.nextInt(1,4);
                    System.out.println(move);
                    break;
                //case 2, 6, 10, 14:
                case 2:
                    move = 1;
                    System.out.println(1);
                    break;
                //case 3, 7, 11, 15:
                case 3:
                    move = 2;
                    System.out.println(2);
                    break;
            }
        }


        return move;
    }
}