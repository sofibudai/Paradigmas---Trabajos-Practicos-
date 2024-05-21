package axiom;

public class RetractCommand extends Command {
    private final char identifier = 'f';

    public void execute(Axiom axiom) {
        axiom.getProbe().retract();
    }

    public char getIdentifier() {
        return identifier;
    }
}