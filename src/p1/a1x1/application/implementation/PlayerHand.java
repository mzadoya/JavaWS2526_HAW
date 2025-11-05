package p1.a1x1.application.implementation;

public class PlayerHand implements Comparable<PlayerHand>{
    private int playerIndex;
    private int playerRank;
    
    public PlayerHand(int playerIndex, int playerRank) {
        this.playerIndex = playerIndex;
        this.playerRank = playerRank;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(int playerRank) {
        this.playerRank = playerRank;
    }
    
    @Override
    public int compareTo(PlayerHand other) {
        return Integer.compare(this.playerRank, other.playerRank);
    }
  
}
