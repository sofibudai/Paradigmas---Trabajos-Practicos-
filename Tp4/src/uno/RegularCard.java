package uno;

public class RegularCard extends Card{
    public RegularCard(String color, String number) {
        super(color, number);
    }

    public boolean goesOnTop(Card pitCard) {
        return getColor().equals(pitCard.getColor()) || getNumber().equals(pitCard.getNumber());
    }
}