package uno;

import java.util.ArrayList;
import java.util.List;

public class WildCard extends Card{
    private String color;
    private String action;

    public WildCard() {
        this.action = "Wild";
    }

    public void cardAction(UnoGame game, String player, Card card) {
        card.goesOnTop(game.firstPitCard());
        game.playerDeck(player).remove(card);
        game.pit.add(0, card);
    }

    public WildCard setColor (String color){
        this.color = color;
        return this;
    }

    public boolean goesOnTop(Card pitCard) {
        return true;
    }

    public boolean likesColorOf(Object color) {
        return this.color.equals(color);
    }

    public boolean likesValueOf(Object action) {
        return this.action.equals(action);
    }
}
