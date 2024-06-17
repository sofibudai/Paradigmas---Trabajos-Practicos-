package uno;

public class Reverse extends Card{

    public Reverse(String color, String action) {
        super(color, action);
    }
    public boolean goesOnTopReverse(Card pitCard){
        return true;
    }

    public boolean goesOnTop(Card pitCard) {
        return getColor().equals(pitCard.getColor())|| goesOnTopReverse(pitCard);
    }

}
