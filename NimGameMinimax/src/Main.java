/**
 *
 * @author Muhammad Bangun Agung
 */

import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {
        int stick, choose_move;
        
        Scanner scan = new Scanner(System.in);

        System.out.print("Number of Stick : ");
        stick       = scan.nextInt();

        System.out.println("Choose who goes first : ");
        System.out.println("1. Human");
        System.out.println("2. Computer");
        System.out.print("Choice :");
        choose_move = scan.nextInt();

        State startState    = new State(stick);
        Tree tree           = new Tree(startState);
        tree.build();

        Game game = new Game(startState, choose_move);
        game.start();
    }
}
