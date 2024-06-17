package uno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnoGame {
    List<Card> deck;
    List<String> players;
    List<Card> pit = new ArrayList<>();
    HashMap<String, List<Card>> playerCards = new HashMap<>();
    GameStatus gameStatus;

    private List<Card> playerDeck(String player) {
        return playerCards.get(player);
    }

    public UnoGame(List<String> players, List<Card> deck) {
        this.players = players;
        this.deck = deck;
        startGame(players, deck);
    }

    public void startGame(List<String> players, List<Card> deck) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("Cannot play with less than two players");
        }
        List<PlayerStatus> playerStatuses = new ArrayList<>();
        for (String player : players) {
            List<Card> playerDeck = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                playerDeck.add(deck.remove(0));
            }
            playerCards.put(player, playerDeck);
            playerStatuses.add(new PlayerStatus());

        }
        pit.add(deck.remove(0));
        gameStatus = new GameStatus(playerStatuses);
    }

    public Card firstPitCard() {
        return pit.get(0);
    }

    public boolean playableCard(Card card) {
        return card.goesOnTop(firstPitCard());
    }

    public boolean hasPlayableCard(String player, Card card) {
        if (!card.checkCard(card, playerDeck(player))) {
            throw new IllegalArgumentException("Player does not have this card");
        }
        return true;
    }

    public void playCard(String player, Card card) {
        gameStatus.isCurrentPlayerTurn(players.indexOf(player));
        hasPlayableCard(player, card);
        playableCard(card);
        playerDeck(player).remove(card);
        pit.add(0, card);
        gameStatus.checkGameOver(playerDeck(player));
        gameStatus.nextTurn();
    }
}

//le pido jugar una carta, si no la tiene, error "cannot play this card"


