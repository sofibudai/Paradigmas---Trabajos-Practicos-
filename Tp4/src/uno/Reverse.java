package uno;

public class Reverse extends Card{

    public Reverse(String color) {
        super(color);
    }
    public boolean goesOnTopReverse(Card pitCard){
        return true;
    }

    public boolean goesOnTop(Card pitCard) {
        return getColor().equals(pitCard.getColor())|| goesOnTopReverse(pitCard);
    }

}
