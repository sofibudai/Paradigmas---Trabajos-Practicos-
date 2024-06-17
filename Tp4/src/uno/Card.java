package uno;

import java.util.List;

public class Card {

    private String color;
    private String number;

    private boolean sameCard(Card card) {
        return this.color.equals(card.getColor()) && this.number.equals(card.getNumber());
    }

    public Card (String color, String number) {
        this.color = color;
        this.number = number;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public boolean checkCard(Card card, List<Card> playerCards) {
        return playerCards.stream().anyMatch(c -> c.sameCard(card));
    }


    public boolean goesOnTop(Card pitCard) {
        if (this.likesColorOf(pitCard) || this.likesNumber(pitCard)){
            return true;
        }
        throw new IllegalArgumentException("Cannot play this card");

    }

    public boolean likesColorOf(Card pitCard) {
        return this.color.equals(pitCard.getColor());
    }

    public boolean likesNumber(Card pitCard) {
        return this.number.equals(pitCard.getNumber());
    }
}


    /*

    public abstract class Card {

    protected String color;
    public Card(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public boolean cardMatch(Card aCard) {
        if (this == aCard) return true;
        if (aCard == null || getClass() != aCard.getClass()) return false;
        return color.equals(aCard.color) && getNumber().equals(aCard.getNumber());
    }

    public abstract String name();

    public abstract void executeAction(UnoGame game, String playerName);

    public boolean goesOnTop(Card aCard) {
        return aCard.color.equals(this.color) || aCard.getNumber().equals(this.getNumber());
        }
    }

    */

    // vos vas arriba de ... ?
    // dividir entre numericas y de color
    // todas las cartas deben decir si les gusta un color / numero o no
    // card goes on top of applied card if the applied card likes the color or the number
    // goes on top esta implementado diferente para cada clase/tipo de carta (double dispatch)
    // el color de la wild card se asigna en los test
