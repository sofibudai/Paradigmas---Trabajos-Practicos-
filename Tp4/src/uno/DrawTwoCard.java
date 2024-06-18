package uno;

import java.util.Objects;

public class DrawTwoCard extends Card {
    public static final String CannotPlayThisCard = "Cannot play this card";
    private final String action;
    private final String color;

    public DrawTwoCard(String color, String value) {
        this.color = color;
        this.action = "DrawTwo";
    }

    public void cardAction(UnoGame game, String player, Card card) {
        card.goesOnTop(game.firstPitCard());
        game.playerDeck(player).remove(card);
        game.pit.add(0, card);
        drawCards(game, game.gameStatus.getNextPlayerName());
    }

    public void drawCards(UnoGame game, String player) {
        for (int i = 0; i < 2; i++) {
            Card card = game.deck.remove(0);
            game.playerDeck(player).add(card);
        }
    }

    public boolean goesOnTop(Card pitCard) {
        if (pitCard.likesColorOf(this.color) || pitCard.likesValueOf(this.action)) {
            return true;
        }
        throw new IllegalArgumentException(CannotPlayThisCard);
    }

    public boolean likesColorOf(Object color) {
        return this.color.equals(color);
    }

    public boolean likesValueOf(Object value) {
        return Objects.equals(this.action, value);
    }
}