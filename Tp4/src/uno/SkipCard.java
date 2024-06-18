package uno;

public class SkipCard extends Card{
    public static final String CannotPlayThisCard = "Cannot play this card";
    private String color;
    private String action;

    public SkipCard(String color, String value) {
        this.color = color;
        this.action = "Skip";
    }

    public void cardAction(UnoGame game, String player, Card card) {
        card.goesOnTop(game.firstPitCard());
        game.playerDeck(player).remove(card);
        game.pit.add(0, card);
        game.gameStatus.nextTurn(player);
    }

    public boolean goesOnTop(Card pitCard) {
        if (pitCard.likesColorOf(this.color) || pitCard.likesValueOf(this.action)){
            return true;
        }
        throw new IllegalArgumentException(CannotPlayThisCard);
    }

    public boolean likesColorOf(Object color) {
        return this.color.equals(color);    }

    public boolean likesValueOf(Object color) {
        return this.action.equals(color);
    }

}



