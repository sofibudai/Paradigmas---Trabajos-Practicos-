package uno;

public class DrawTwo extends Card{
    public DrawTwo(String color, String action) {
        super(color, action);
    }

    public boolean goesOnTopDrawTwo(Card pitCard){
        return true;
    }

    public boolean goesOnTop(Card pitCard) {
        return getColor().equals(pitCard.getColor()) || goesOnTopDrawTwo(pitCard);
    }


}

