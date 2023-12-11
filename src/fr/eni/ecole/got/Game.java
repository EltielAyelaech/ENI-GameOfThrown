package fr.eni.ecole.got;

import java.util.Scanner;

import fr.eni.ecole.got.bo.Dice;
import fr.eni.ecole.got.bo.Player;

public class Game {
    private static final int MAXIMUM_SCORE = 21;
    private static Player player1;
    private static Player player2;
    private static Dice dice;
    private static final Scanner stdin = new Scanner(System.in);
    private static boolean running = true;

    private static void initPlayers(
    ) {
        String player1Name = "";
        String player2Name = "";

        System.out.println("what is player 1's name?");
        while (player1Name.equals("")) {
            System.out.print("> ");
            player1Name = Game.stdin.nextLine();
        }
        Game.setPlayer1(new Player(player1Name));
        System.out.printf("Hi %s!\n\n", Game.getPlayer1().getName());

        System.out.println("What about player 2's?");
        while (player2Name.equals("")) {
            System.out.print("> ");
            player2Name = Game.stdin.nextLine();
        }
        Game.setPlayer2(new Player(player2Name));
        System.out.printf("Ok, hi %s!\n\n", Game.getPlayer2().getName());
    }

    private static boolean isUInt(
        String str
    ) {
        if (str.equals(""))
            return false;
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    private static void initDice(
    ) {
        String userInput = "$";

        Game.setDice(new Dice());
        System.out.println("What kind of dice do you want to use?");
        System.out.printf("(input the number of sides, leave empty to use a %d-sided die)\n", Game.getDice().getNbFaces());

        while (!userInput.equals("") && !Game.isUInt(userInput)) {
            System.out.print("> ");
            userInput = Game.stdin.nextLine();
        }

        if (!userInput.equals(""))
            Game.setDice(new Dice(Integer.parseInt(userInput)));
        System.out.printf("This game will be using a %d-sided die.\n\n", Game.getDice().getNbFaces());
    }

    private static void init(
    ) {
        System.out.println("=========================");
        System.out.println("Welcome to Game of Thrown");
        System.out.println("=========================");
        System.out.println();
        Game.initPlayers();
        Game.initDice();
    }

    private static boolean isRunning(
    ) {
        return Game.player1.getPlaying() || Game.player2.getPlaying();
    }

    private static void roll(
        Player player
    ) {
        if (!player.getPlaying())
            return;

        String userInput = "";

        System.out.printf(
            String.join("\n",
                "%s, your current score is %d.",
                "Roll the dice? (Y/N)",
                ""
            ),
            player.getName(),
            player.getScore()
        );

        while (!userInput.equals("Y") && !userInput.equals("N")) {
            System.out.print("> ");
            userInput = Game.stdin.nextLine().toUpperCase();
        }

        if (userInput.equals("Y")) {
            System.out.printf(
                "You rolled %d. your score is now %d.\n",
                Game.dice.roll(),
                player.getScore() + Game.dice.getCurrentFace()
            );
            player.addPoints(dice.getCurrentFace());
            if (player.getScore() > 21) {
                System.out.println("Your greed is your downfall; you lost.");
                player.lost();
            }
        } else {
            player.stopped();
        }
    }

    private static void playTurn(
        int turnNb
    ) {
        System.out.println("=======");
        System.out.printf("Turn %01d\n", turnNb);
        System.out.println("=======");

        Game.roll(Game.player1);
        if (Game.player1.getPlaying() && Game.player2.getPlaying())
            System.out.println("-----------------------------");
        Game.roll(Game.player2);
    }

    private static void end(
    ) {
        if (Game.player1.getScore() == Game.player2.getScore()) {
            System.out.println("It's a tie.");
        } else if (Game.player1.getScore() > Game.player2.getScore()) {
            System.out.printf("%s won!\n", Game.player1.getName());
        } else {
            System.out.printf("%s won!\n", Game.player2.getName());
        }
    }

    public static void main(
        String[] args
    ) {
        int currentTurn = 1;
        Game.init();

        while (Game.isRunning()) {
            Game.playTurn(currentTurn++);
        }
        Game.end();
        Game.stdin.close();
    }

    private static Player getPlayer1() { return player1; }
    private static void setPlayer1(Player player1) { Game.player1 = player1; }
    private static Player getPlayer2() { return player2; }
    private static void setPlayer2(Player player2) { Game.player2 = player2; }
    private static Dice getDice() { return dice; }
    private static void setDice(Dice dice) { Game.dice = dice; }
}
