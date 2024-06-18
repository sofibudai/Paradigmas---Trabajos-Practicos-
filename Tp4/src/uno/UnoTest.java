package uno;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class UnoTest {
    List<Card> deck = new ArrayList();
    List<Card> easyDeck = new ArrayList();


    private UnoTest() {
        this.deck.add(redOneCard);
        this.deck.add(yellowReverseCard);
        this.deck.add(yellowDrawTwoCard);
        this.deck.add(yellowSkipCard);
        this.deck.add(wildCard);
        this.deck.add(yellowFiveCard);

        this.deck.add(yellowTwoCard);
        this.deck.add(yellowEightCard);
        this.deck.add(blueFiveCard);
        this.deck.add(blueDrawTwoCard);
        this.deck.add(redReverseCard);
        this.deck.add(redEightCard);

        this.deck.add(yellowTwoCard);  // pit card for 2 players
        this.deck.add(yellowEightCard);
        this.deck.add(redEightCard);
        this.deck.add(redNineCard);
        this.deck.add(redReverseCard);
        this.deck.add(redDrawTwoCard);

        this.deck.add(yellowFiveCard); //pit card for 3 players
        this.deck.add(greenFiveCard);
        this.deck.add(blueSevenCard);
        this.deck.add(redTwoCard);
        this.deck.add(greenOneCard);
        this.deck.add(redFiveCard);
        this.deck.add(blueEightCard);

    }

    @Test public void notEnoughPlayersToStart(){
        List<String> players = new ArrayList<>();
        players.add("Player 1");
        assertThrowsLike("Cannot play with less than two players", IllegalArgumentException.class, () -> new UnoGame(players, deck));
    }

    @Test public void gameStartsCorrectlyTwoPlayers(){
        assertEquals(yellowTwoCard, this.validTwoPlayerGame().firstPitCard());
    }

    @Test public void player2playsOnWrongTurn(){
        UnoGame game = this.validTwoPlayerGame();
        assertThrowsLike(GameStatus.ItsNotYourTurn, IllegalArgumentException.class, () -> game.playCard("Player2", yellowTwoCard));
    }

    @Test public void player1PlaysCard(){
        UnoGame game = this.validTwoPlayerGame();
        game.playCard("Player1", yellowFiveCard);
        assertEquals(yellowFiveCard, game.firstPitCard());
    }

    @Test public void cannotPlayCard(){
        assertThrowsLike(UnoGame.PlayerDoesNotHaveThisCard, IllegalArgumentException.class, () -> this.validTwoPlayerGame().playCard("Player1", redTwoCard));
    }

    @Test public void player2PlaysCorrectCardAfterRedWildCard(){
        UnoGame game = this.validTwoPlayerGame();
        game.playCard("Player1",wildCard.setColor("Red"));
        game.playCard("Player2",redEightCard);
        assertEquals(redEightCard, game.firstPitCard());
    }

    @Test public void player2PlaysIncorrectCardAfterRedWildCard(){
        UnoGame game = this.validTwoPlayerGame();
        game.playCard("Player1",wildCard.setColor("Red"));
        assertThrowsLike(NumberedCard.CannotPlayThisCard, IllegalArgumentException.class, () -> game.playCard("Player2", blueFiveCard));
    }

    @Test public void player2CardsAfterPlayer1PlaysDrawTwoCard(){
        UnoGame game = this.validTwoPlayerGame();
        game.playCard("Player1", yellowDrawTwoCard);
        assertEquals(8, game.playerDeck("Player2").size());
    }

    @Test public void player1PlaysSkipCard() {
        UnoGame game = this.validTwoPlayerGame();
        game.playCard("Player1", yellowSkipCard);
        assertEquals("Player1", game.gameStatus.getCurrentPlayerName());
    }

    @Test public void player1PlaysReverseCard() {
        UnoGame game = this.validTwoPlayerGame();
        game.playCard("Player1", yellowReverseCard);
        assertEquals("Player2", game.gameStatus.getCurrentPlayerName());
    }

    @Test public void threePlayerGameStartsCorrectly(){
        assertEquals( yellowFiveCard, this.validThreePlayerGame().firstPitCard());
    }

    @Test public void player1PlaysSkipCardInThreePlayerGame() {
        UnoGame game = this.validThreePlayerGame();
        game.playCard("Player1", yellowSkipCard);
        assertEquals("Player3", game.gameStatus.getCurrentPlayerName());
    }

    @Test public void doubleDrawTwoCard(){
        UnoGame game = this.validThreePlayerGame();
        game.playCard("Player1", yellowDrawTwoCard);
        game.playCard("Player2", blueDrawTwoCard);
        assertEquals(7, game.playerDeck("Player2").size());
        assertEquals(8, game.playerDeck("Player3").size());
    }

    @Test public void player1PlaysReverseCardInThreePlayerGame() {
        UnoGame game = this.validThreePlayerGame();
        game.playCard("Player1", yellowReverseCard);
        assertEquals("Player3", game.gameStatus.getCurrentPlayerName());
    }

    @Test public void reverseCardPlayedTwiceInARowInThreePlayerGame() {
        UnoGame game = this.validThreePlayerGame();
        game.playCard("Player1",yellowReverseCard);
        game.playCard("Player3",redReverseCard);
        assertEquals("Player1",game.gameStatus.getCurrentPlayerName());
    }

    @Test public void player1DoesNotCallOutUNO(){
        UnoGame game = this.validTwoPlayerGameWithEasyDeck();
        game.playCard("Player1",yellowFiveCard);
        game.playCard("Player2",yellowFiveCard);
        game.playCard("Player1",yellowEightCard);
        game.playCard("Player2",yellowEightCard);
        game.playCard("Player1",yellowFiveCard);
        game.playCard("Player2",yellowFiveCard);
        game.playCard("Player1",yellowEightCard);
        game.playCard("Player2",yellowEightCard);
        game.playCard("Player1",yellowEightCard);
        assertEquals(2, game.playerDeck("Player1").size());
    }

    @Test public void player1CallsOutUNO() {
        UnoGame game = this.validTwoPlayerGameWithEasyDeck();
        game.playCard("Player1",yellowFiveCard);
        game.playCard("Player2",yellowFiveCard);
        game.playCard("Player1",yellowEightCard);
        game.playCard("Player2",yellowEightCard);
        game.playCard("Player1",yellowFiveCard);
        game.playCard("Player2",yellowFiveCard);
        game.playCard("Player1",yellowEightCard);
        game.playCard("Player2",yellowEightCard);
        game.playCard("Player1",yellowEightCard.uno());
        assertEquals(1, game.playerDeck("Player1").size());
    }

    @Test public void player1Wins() {
        UnoGame game = this.validTwoPlayerGameWithEasyDeck();
        game.playCard("Player1",yellowFiveCard);
        game.playCard("Player2",yellowFiveCard);
        game.playCard("Player1",yellowEightCard);
        game.playCard("Player2",yellowEightCard);
        game.playCard("Player1",yellowFiveCard);
        game.playCard("Player2",yellowFiveCard);
        game.playCard("Player1",yellowEightCard);
        game.playCard("Player2",yellowEightCard);
        game.playCard("Player1",yellowEightCard.uno());
        game.playCard("Player2",yellowEightCard);
        game.playCard("Player1",yellowEightCard);
        assertTrue(game.gameStatus.gameOver);


    }

    private UnoGame validTwoPlayerGame() {
        List<String> players = new ArrayList<>();
        players.add("Player1");
        players.add("Player2");
        return new UnoGame(players, deck);
    }

    private UnoGame validTwoPlayerGameWithEasyDeck() {
        List<String> players = new ArrayList<>();
        players.add("Player1");
        players.add("Player2");
        return new UnoGame(players, easyDeck);
    }

    private UnoGame validThreePlayerGame() {
        List<String> players = new ArrayList<>();
        players.add("Player1");
        players.add("Player2");
        players.add("Player3");
        return new UnoGame(players, deck);
    }

    private void assertThrowsLike(String message, Class<? extends Throwable> expectedType, Executable codeBlock) {
        assertEquals(message, assertThrows(expectedType, codeBlock).getMessage());
    }

    private Card yellowFiveCard = new NumberedCard("Yellow", "5");
    private Card yellowEightCard = new NumberedCard("Yellow", "8");

    private Card redOneCard = new NumberedCard("Red", "1");
    private Card redTwoCard = new NumberedCard("Red", "2");
    private Card greenOneCard = new NumberedCard("Green", "1");
    private Card redFiveCard = new NumberedCard("Red", "5");
    private Card blueEightCard = new NumberedCard("Blue", "8");
    private Card yellowTwoCard = new NumberedCard("Yellow", "2");
    private Card redEightCard = new NumberedCard("Red", "8");
    private Card redNineCard = new NumberedCard("Red", "9");
    private Card blueFiveCard = new NumberedCard("Blue", "5");
    private Card blueSevenCard = new NumberedCard("Blue", "7");
    private Card greenFiveCard = new NumberedCard("Green", "5");


    private Card yellowReverseCard = new ReverseCard("Yellow", "Reverse");
    private Card redReverseCard = new ReverseCard("Red", "Reverse");
    private Card redDrawTwoCard = new DrawTwoCard("Red", "DrawTwo");
    private Card yellowDrawTwoCard = new DrawTwoCard("Yellow", "DrawTwo");
    private Card blueDrawTwoCard = new DrawTwoCard("Blue", "DrawTwo");

    private Card yellowSkipCard = new SkipCard("Yellow", "Skip");

    private WildCard wildCard = new WildCard();



    {
        this.easyDeck.add(yellowFiveCard);
        this.easyDeck.add(yellowEightCard);
        this.easyDeck.add(yellowEightCard);
        this.easyDeck.add(yellowFiveCard);
        this.easyDeck.add(yellowEightCard);
        this.easyDeck.add(yellowEightCard);

        this.easyDeck.add(yellowFiveCard);
        this.easyDeck.add(yellowEightCard);
        this.easyDeck.add(yellowEightCard);
        this.easyDeck.add(yellowFiveCard);
        this.easyDeck.add(yellowEightCard);
        this.easyDeck.add(yellowEightCard);

        this.easyDeck.add(yellowEightCard);
        this.easyDeck.add(yellowEightCard);
    }
}




