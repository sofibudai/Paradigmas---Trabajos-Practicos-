package uno;

public class Skip extends Card{

    public Skip(String color, String action) {
        super(color, action);
    }
    public boolean goesOnTopSkip(Card pitCard){
        return true;
    }

    public boolean goesOnTop(Card pitCard) {
        return getColor().equals(pitCard.getColor()) || goesOnTopSkip(pitCard);
    }


}