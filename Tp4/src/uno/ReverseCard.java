package uno;

import java.util.Objects;

public class ReverseCard extends Card {
    public static final String CannotPlayThisCard = "Cannot play this card";
    private String color;
    private String action;

    public ReverseCard(String color,String value) {
        this.color = color;
        this.action = "Reverse";
    }

    public void cardAction(UnoGame game, String player, Card card) {
        card.goesOnTop(game.firstPitCard());
        game.playerDeck(player).remove(card);
        game.pit.add(0, card);
        game.gameStatus.reverseDirection();
    }

    public boolean goesOnTop(Card pitCard) {
        if (pitCard.likesColorOf(this.color) || pitCard.likesValueOf(this.action)){
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
