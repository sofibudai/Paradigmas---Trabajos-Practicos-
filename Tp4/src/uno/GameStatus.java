package uno;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {
    private List<PlayerStatus> playerStatuses;
    private int currentPlayerIndex = 0;
    private boolean gameOver = false;


    public GameStatus(List<PlayerStatus> playerStatuses) {
        this.playerStatuses = playerStatuses;
        this.playerStatuses.get(currentPlayerIndex).setTurnStatus(true);
    }

    public void nextTurn() {
        this.playerStatuses.get(currentPlayerIndex).setTurnStatus(false);
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.playerStatuses.size();
        this.playerStatuses.get(currentPlayerIndex).setTurnStatus(true);
    }

    public boolean isCurrentPlayerTurn(int playerIndex) {
        if (playerStatuses.get(playerIndex).getTurnStatus()){
            return true;
        }
        throw new IllegalArgumentException("It's not your turn");
    }

    public void checkGameOver(List<Card> playerDeck) {
        if (playerDeck.isEmpty()) {
            this.setGameOver(true);
        }
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }



    //izquierda o derecha

}
