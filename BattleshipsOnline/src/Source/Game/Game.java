package Source.Game;

public class Game {
    /**
     * Game constructor
     * @param gameID the corresponding ID of the game according to the server
     * @param host player that requested the creation of the game; he is
     *             automatically logged into the game
     */
    public Game(int gameID, Player host){
        player1 = host;
        this.gameID = gameID;
        player1.joinAGame(gameID);
    }

    public void addPlayer(Player joinedPlayer){
        joinedPlayer.joinAGame(gameID);
    }

    public boolean roomIsFull(){
        return (player1 != null && player2 != null);
    }

    public boolean isInDeploymentPhase(){
        return !(player1.getGameTable().allShipsAreDeployed() && player2.getGameTable().allShipsAreDeployed());
    }

    public int getGameID() {
        return gameID;
    }

    private Player player1;
    private Player player2;
    private int gameID;
}
