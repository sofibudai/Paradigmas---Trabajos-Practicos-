package uno;

import java.util.List;

public abstract class Card {
    boolean calloutUNO = false;

    public Card uno() {
        calloutUNO = true;
        return this;
    }

    public abstract boolean goesOnTop(Card pitCard);
    public abstract boolean likesColorOf(Object color);
    public abstract boolean likesValueOf(Object value);
    public abstract void cardAction(UnoGame game, String player, Card card);
    }





