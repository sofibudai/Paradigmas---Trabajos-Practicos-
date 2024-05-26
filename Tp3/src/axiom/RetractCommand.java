package axiom;

public class RetractCommand extends Command {
    public final char identifier = 'f';

    public void execute(Axiom axiom) {
        axiom.getProbe().retract();
    }

    public char getIdentifier() {
        return identifier;
    }
}