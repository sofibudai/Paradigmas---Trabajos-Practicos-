package uno;

public class Card {
    private String color;
    private String number;

    public Card(String color) {
        this.color = color;
        this.number = null; // No se proporciona número
    }

    public Card(String color, String number) {
        this.color = color;
        this.number = number;
    }

    public String getColor(){
        return color;
    }

    public String getNumber() {
        return number;
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




    // vos vas arriba de ... ?
    // dividir entre numericas y de color
    // todas las cartas deben decir si les gusta un color / numero o no
    // card goes on top of applied card if the applied card likes the color or the number
    // goes on top esta implementado diferente para cada clase/tipo de carta (double dispatch)
    // el color de la wild card se asigna en los test
