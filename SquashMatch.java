import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class SquashMatch {
    static void Manual(Player player1, Player player2)
    {
        String winningPlayerName;
        Scanner scanWinningPlayer= new Scanner(System.in);
        System.out.println("Enter the name of the winning player");
        try {
            winningPlayerName= scanWinningPlayer.next();
        }
        catch (InputMismatchException e){
            System.out.println("Enter a valid name of the winning player");
            winningPlayerName= scanWinningPlayer.next();
        }
        while (!winningPlayerName.equals(player1.playerName) && !winningPlayerName.equals(player2.playerName))
        {
            System.out.println("Enter correct name");
            winningPlayerName= scanWinningPlayer.next();
        }
        if (winningPlayerName.equals(player1.playerName))
        {
            if (player1.noOfGamesWonPAR<3 && !player1.PARGameDone)
            {
                player1.incrementPlayerPARScore();
            }

            if (player1.noOfGamesWonHIHO<3 && !player1.HIHOGameDone)
            {
                if(player1.scoredPrev)
                {
                    player1.incrementPlayerHIHOScore();
                }
            }
            player1.scoredPrev=true;
            player2.scoredPrev=false;
            player2.letterScore="L";
            player1.letterScore="W";
        } else if (winningPlayerName.equals(player2.playerName)) {
            if (player2.noOfGamesWonPAR<3 && !player2.PARGameDone)
            {
                player2.incrementPlayerPARScore();
            }

            if (player2.noOfGamesWonHIHO<3 && !player2.HIHOGameDone)
            {
                if(player2.scoredPrev)
                {
                    player2.incrementPlayerHIHOScore();
                }
            }

            player2.scoredPrev=true;
            player1.scoredPrev=false;
            player1.letterScore="L";
            player2.letterScore="W";
        }
    }
    static void Random(Player player1, Player player2) {
        java.util.Random random = new java.util.Random();
        String[] choiceOfPlayers = {"Player1", "Player2"};
        int randomNumber = random.nextInt(choiceOfPlayers.length);
        if (randomNumber == 0) {
            if (player1.noOfGamesWonPAR<3 && !player1.PARGameDone)
            {
                player1.incrementPlayerPARScore();
            }
            if (player1.noOfGamesWonHIHO<3 && !player1.HIHOGameDone)
            {
                if(player1.scoredPrev)
                {
                    player1.incrementPlayerHIHOScore();
                }
            }
            player1.scoredPrev=true;
            player2.scoredPrev=false;
            player2.letterScore="L";
            player1.letterScore="W";
        } else if (randomNumber == 1) {
            if (player2.noOfGamesWonPAR<3 && !player2.PARGameDone)
            {
                player2.incrementPlayerPARScore();
            }
            if (player2.noOfGamesWonHIHO<3 && !player2.HIHOGameDone)
            {
                if(player2.scoredPrev)
                {
                    player2.incrementPlayerHIHOScore();
                }
            }
            player2.scoredPrev=true;
            player1.scoredPrev=false;
            player2.letterScore="W";
            player1.letterScore="L";
        }
    }
    static boolean haveAWinnerPAR(Player player1, Player player2, boolean PARGameDone, boolean PARMatchDone) {
        if (PARGameDone) {
            player1.PARGameDone = true;
            player2.PARGameDone = true;
            return true;
        }
        if (PARMatchDone)
            return true;
        int difference;
        int greater;
        if (player1.playerPARScore>player2.playerPARScore)
        {
            greater=1;
            difference=player1.playerPARScore-player2.playerPARScore;
        }
        else {
            greater=2;
            difference = player2.playerPARScore - player1.playerPARScore;
        }

        if (player1.playerPARScore==11 && player2.playerPARScore<10)//player1 won
        {
            if (player1.noOfGamesWonPAR<3)
            {
                player1.incrementGamesWonPAR();
            }
            player1.PARGameDone=true;
            player2.PARGameDone=true;
            return true;
        }
        if (player2.playerPARScore==11 && player1.playerPARScore<10)//player2 won
        {
            if (player2.noOfGamesWonPAR<3)
            {
                player2.incrementGamesWonPAR();
            }
            player1.PARGameDone=true;
            player2.PARGameDone=true;
            return true;
        }
        if (player1.playerPARScore==10 && player2.playerPARScore==10)
        {
            return false;
        }
        if (player1.playerPARScore>10 && player2.playerPARScore>10 && difference>1)
        {
            if (greater==1)
            {
                if (player1.noOfGamesWonPAR<3)
                    player1.incrementGamesWonPAR();
            }
            else
            {
                if (player2.noOfGamesWonPAR<3)
                    player2.incrementGamesWonPAR();
            }
            player1.PARGameDone=true;
            player2.PARGameDone=true;
            return true;
        }
        return false;
    }
    static boolean haveAWinnerHIHO(Player player1, Player player2, boolean HIHOGameDone, boolean HIHOMatchDone)
    {
        if (HIHOGameDone || HIHOMatchDone)
            return true;
        if (player1.playerHIHOScore==9 && player2.playerHIHOScore<9)//player 1 won
        {
            player1.incrementGamesWonHIHO();
            player1.HIHOGameDone=true;
            player2.HIHOGameDone=true;
            return true;
        }
        if (player2.playerHIHOScore==9 && player1.playerHIHOScore<9)//player 2 won
        {
            player2.incrementGamesWonHIHO();
            player1.HIHOGameDone=true;
            player2.HIHOGameDone=true;
            return true;
        }
        return false;
    }
    static void results(String gameMode, Player player1, Player player2)
    {
        int roundNumber=0;
        int gameNumber=0;
        boolean PARGameDone=false;
        boolean PARMatchDone=false;
        boolean HIHOGameDone=false;
        boolean HIHOMatchDone=false;

        while ((player1.noOfGamesWonPAR<3 && player2.noOfGamesWonPAR<3) || (player1.noOfGamesWonHIHO<3 && player2.noOfGamesWonHIHO<3)){
            gameNumber++;
            player1.PARGameDone=false;
            player2.PARGameDone=false;
            player1.HIHOGameDone=false;
            player2.HIHOGameDone=false;
            while (!haveAWinnerPAR(player1, player2, PARGameDone, PARMatchDone) || !haveAWinnerHIHO(player1, player2, HIHOGameDone, HIHOMatchDone))
            {
                roundNumber++;
                if (haveAWinnerPAR(player1, player2, PARGameDone, PARMatchDone))
                    PARGameDone=true;
                else if (haveAWinnerHIHO(player1, player2, HIHOGameDone, HIHOMatchDone))
                    HIHOGameDone=true;
                if (gameMode.equals("manual"))
                {
                    Manual(player1, player2);
                } else if (gameMode.equals("random")) {
                    Random(player1, player2);
                }

                System.out.printf(" %-5d   %-5d   %-5s   %-5s %n", gameNumber, roundNumber, player1.letterScore, player2.letterScore);
            }
            roundNumber=0;
            player1.playerPARScore=0;
            player2.playerPARScore=0;
            player1.playerHIHOScore=0;
            player2.playerHIHOScore=0;
            PARGameDone=false;
            HIHOGameDone=false;
            if ((player1.noOfGamesWonPAR>=3 || player2.noOfGamesWonPAR>=3))
            {
                PARMatchDone=true;
            }
            if ((player1.noOfGamesWonHIHO>=3 || player2.noOfGamesWonHIHO>=3))
            {
                HIHOMatchDone=true;
            }
        }
    }

    public static void main(String[] args)
    {
        String Player1Name = "", Player2Name = "";
        String gameMode;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the first player");
        try {
            Player1Name = scan.next();
        }catch (InputMismatchException e)
        {
            System.out.println("Enter a valid name");
            Player1Name = scan.next();
        }

        System.out.println("Enter the name of the second player");
        try {
            Player2Name = scan.next();
        }catch (InputMismatchException e)
        {
            System.out.println("Enter a valid name");
            Player2Name = scan.next();
        }
        while (Player1Name.equals(Player2Name)) {
            System.out.println("Enter a different name for the second player");
            Player2Name = scan.next();
        }

        Player player1= new Player();
        player1.playerName=Player1Name;
        Player player2= new Player();
        player2.playerName=Player2Name;
        System.out.println("Enter the game mode you would like, type 'manual' or 'random'");

        try {
            gameMode = scan.next();
        }catch (InputMismatchException e)
        {
            System.out.println("Enter the game mode you would like, type 'manual' or 'random'");
            gameMode= scan.next();
        }

        while (!gameMode.equals("manual") && !gameMode.equals("random"))
        {
            System.out.println("Type 'manual' or 'random'");
            gameMode=scan.next();
        }
        System.out.printf("Game#   " + "Round#   " + "%-5s  %-5s%n", player1.playerName, player2.playerName);

        results(gameMode, player1, player2);
        System.out.printf("Results: %n");
        if (player1.noOfGamesWonPAR>=3)
        {
            System.out.printf("According to PAR Judge, %s won the match with %d-%d games%n", player1.playerName, player1.noOfGamesWonPAR, player2.noOfGamesWonPAR);
        } else if (player2.noOfGamesWonPAR>=3) {
            System.out.printf("According to PAR Judge, %s won the match with %d-%d games%n", player2.playerName, player1.noOfGamesWonPAR, player2.noOfGamesWonPAR);
        }

        if (player1.noOfGamesWonHIHO==3)
        {
            System.out.printf("According to HIHO Judge, %s won the match with %d-%d games%n", player1.playerName, player1.noOfGamesWonHIHO, player2.noOfGamesWonHIHO);
        } else if (player2.noOfGamesWonHIHO==3) {
            System.out.printf("According to HIHO Judge, %s won the match with %d-%d games%n", player2.playerName, player1.noOfGamesWonHIHO, player2.noOfGamesWonHIHO);
        }

    }
}
