package uno;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NumberedCard extends Card {
    public static final String CannotPlayThisCard = "Cannot play this card";
    private final String value;
    private final String color;

    public NumberedCard(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public void cardAction(UnoGame game, String player, Card card) {
        card.goesOnTop(game.firstPitCard());
        game.playerDeck(player).remove(card);
        game.pit.add(0,card);
    }

    public boolean goesOnTop(Card pitCard) {
        if (pitCard.likesColorOf(this.color) || pitCard.likesValueOf(this.value)){
            return true;
        }
        throw new IllegalArgumentException(CannotPlayThisCard);
    }

    public boolean likesColorOf(Object color) {
        return this.color.equals(color);
    }

    public boolean likesValueOf(Object value) {
        return Objects.equals(this.value, value);
    }


}