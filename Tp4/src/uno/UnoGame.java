package uno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UnoGame {
    public static final String PlayerDoesNotHaveThisCard = "Player does not have this card";
    List<Card> deck;
    List<String> players;
    List<Card> pit = new ArrayList<>();
    HashMap<String, List<Card>> playerCards = new HashMap<>();
    GameStatus gameStatus;

    public List<Card> playerDeck(String player) {
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

        List<PlayerStatus> playerStatuses = players.stream().map(player -> {
            List<Card> playerDeck = IntStream.range(0, 6)
                    .mapToObj(i -> deck.remove(0))
                    .collect(Collectors.toList());
            playerCards.put(player, playerDeck);
            return new PlayerStatus(player);
        }).collect(Collectors.toList());

        pit.add(deck.remove(0));
        gameStatus = new GameStatus(playerStatuses);
    }

    public Card firstPitCard() {
        return pit.get(0);
    }

    public boolean checkCard(Card card, List<Card> playerCards) {
        return playerCards.stream().anyMatch(c -> c.equals(card));
    }

    public boolean hasPlayableCard(String player, Card card) {
        if (!checkCard(card, playerDeck(player))) {
            throw new IllegalArgumentException(PlayerDoesNotHaveThisCard);
        }
        return true;
    }

    public void playCard(String player , Card card) {
        gameStatus.checkIfIsPlayerTurn(player);
        hasPlayableCard(player, card);
        card.cardAction(this, player, card);

        if (playerDeck(player).size() == 1) {
            if (!card.calloutUNO) {
                playerDeck(player).add(deck.remove(0));
            }
        }
        gameStatus.checkGameOver(playerDeck(player));
        gameStatus.nextTurn(gameStatus.getCurrentPlayerName());
    }
}



