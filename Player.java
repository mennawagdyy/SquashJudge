public class Player {
    public String playerName;
    public int playerPARScore=0;
    public int playerHIHOScore=0;
    public int noOfGamesWonPAR=0;
    public int noOfGamesWonHIHO=0;
    boolean scoredPrev=false;
    String letterScore;
    boolean PARGameDone=false;
    boolean HIHOGameDone=false;

    void incrementPlayerPARScore()
    {
        playerPARScore++;
    }

    void incrementPlayerHIHOScore()
    {
        playerHIHOScore++;
    }
    void incrementGamesWonPAR()
    {
        noOfGamesWonPAR++;
    }

    void incrementGamesWonHIHO()
    {
        noOfGamesWonHIHO++;
    }
}
