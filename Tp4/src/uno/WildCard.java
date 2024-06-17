package uno;

public class WildCard extends Card{
    public WildCard(String action) {
        super("none", action);
    }

    public boolean goesOnTop(Card pitCard) {
        return true;
    }

    @Override
    public void setColor (String newColor) {
        this.setColor(newColor);
    }
}
