package uno;

public class DrawTwo extends Card{
    public DrawTwo(String color) {
        super(color);
    }

    public boolean goesOnTopDrawTwo(Card pitCard){
        return true;
    }

    public boolean goesOnTop(Card pitCard) {
        return getColor().equals(pitCard.getColor())|| goesOnTopDrawTwo(pitCard);
    }

}

