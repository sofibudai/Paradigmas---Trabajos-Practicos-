package axiom;

public class IncreaseSpeedCommand extends Command {
    private final char identifier = 'i';

    public void execute(Axiom axiom) {
        axiom.getSpeed().increase();
    }

    public char getIdentifier() {
        return identifier;
    }
}