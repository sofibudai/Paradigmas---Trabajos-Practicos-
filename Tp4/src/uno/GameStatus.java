package uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public  class GameStatus {
    public static final String ItsNotYourTurn = "It's not your turn";
    private List<PlayerStatus> playerStatuses;
    private PlayerStatus currentPlayer;
    public boolean gameOver = false;


    public GameStatus(List<PlayerStatus> playerStatuses) {
        ArrayList<PlayerStatus> states = playerStatuses.stream()
                .map(p -> new PlayerStatus(p.getName()))
                .collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < states.size(); i++) {
            states.get(i).setRight(states.get((i + 1) % states.size()));
            states.get(i).setLeft(states.get((i - 1 + states.size()) % states.size()));
            states.get(i).setTurnStatus(false);
        }

        this.currentPlayer = states.get(0);
        this.currentPlayer.setTurnStatus(true);
        this.playerStatuses = states;
    }

    public void nextTurn(String player) {
        if (!this.currentPlayer.getName().equals(player)) {
            throw new IllegalArgumentException(ItsNotYourTurn);
        }

        this.currentPlayer.setTurnStatus(false);
        this.currentPlayer = this.currentPlayer.getRight();
        this.currentPlayer.setTurnStatus(true);
    }

    public void reverseDirection() {
        Collections.reverse(this.playerStatuses);
        for (int i = 0; i < playerStatuses.size(); i++) {
            playerStatuses.get(i).setRight(playerStatuses.get((i + 1) % playerStatuses.size()));
            playerStatuses.get(i).setLeft(playerStatuses.get((i - 1 + playerStatuses.size()) % playerStatuses.size()));
        }
    }

    public String getNextPlayerName() {
        return this.currentPlayer.getRight().getName();
    }

    public String getCurrentPlayerName() {
        return this.currentPlayer.getName();
    }

    public boolean checkIfIsPlayerTurn(String playerName) {
        if (!this.currentPlayer.getName().equals(playerName)) {
            throw new IllegalArgumentException(ItsNotYourTurn);
        }
        return true;
    }

    public void checkGameOver(List<Card> playerDeck) {
        if (playerDeck.isEmpty()) {
            this.setGameOver(true);
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}