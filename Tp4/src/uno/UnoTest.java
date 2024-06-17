package uno;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UnoTest {
    List<Card> deck = new ArrayList();

    private UnoTest() {
        this.deck.add(new Card("Red", "1"));
        this.deck.add(new Card("Yellow", "3"));
        this.deck.add(new Card("Red", "1"));
        this.deck.add(new Card("Green", "1"));
        this.deck.add(new Card("Red", "2"));
        this.deck.add(new Card("Blue", "8"));
        this.deck.add(new Card("Yellow", "2")); //pit card
        this.deck.add(new Card("Yellow", "8"));
        this.deck.add(new Card("Red", "8"));
        this.deck.add(new Card("Blue", "3"));
        this.deck.add(new Card("Yellow", "3"));
        this.deck.add(new Card("Green", "3"));
        this.deck.add(new Card("Red", "4"));
        this.deck.add(new Card("Blue", "4"));
    }

    @Test public void notEnoughPlayersToStart(){
        List<String> players = new ArrayList<>();
        players.add("Player 1");
        assertThrowsLike("Cannot play with less than two players", IllegalArgumentException.class, () -> new UnoGame(players, deck));
    }

    @Test public void gameStartsCorrectly(){
        UnoGame game = this.validGame();
        Card actualCard = game.firstPitCard();
        Card expectedCard = new Card("Yellow", "2");
        assertColor(expectedCard, actualCard);
    }

    @Test public void playerPlaysCard(){
        UnoGame game = this.validGame();
        game.playCard("Player1", new Card("Yellow","3"));
        Card expectedCard = new Card("Yellow","3");
        Card actualCard = game.firstPitCard();
        assertColor(expectedCard, actualCard);
    }

    @Test public void cannotPlayCard(){
        UnoGame game = this.validGame();
        assertThrowsLike("Cannot play this card", IllegalArgumentException.class, () -> game.playCard("Player1", new Card("Red", "1")));
    }



    private UnoGame validGame() {
        List<String> players = new ArrayList<>();
        players.add("Player1");
        players.add("Player2");
        return new UnoGame(players, deck);
    }

    private void assertColor(Card expectedCard, Card actualCard) {
        assertEquals(expectedCard.getColor(), actualCard.getColor());

    }
    private void asserNumber(Card expectedCard, Card actualCard) {
        assertEquals(expectedCard.getNumber(), actualCard.getNumber());
    }

    private void assertThrowsLike(String message, Class<? extends Throwable> expectedType, Executable codeBlock) {
        assertEquals(message, assertThrows(expectedType, codeBlock).getMessage());
    }

}




