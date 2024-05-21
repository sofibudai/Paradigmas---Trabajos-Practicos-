package axiom;

import java.util.Arrays;
import java.util.List;

public class Axiom {
    private Speed speed = new Speed();
    public Direction direction = new Direction.North();
    private Probe probe = new Probe();

    private static final List<Command> commands = Arrays.asList(
            new IncreaseSpeedCommand(),
            new DecreaseSpeedCommand(),
            new TurnLeftCommand(),
            new TurnRightCommand(),
            new DeployCommand(),
            new RetractCommand()
    );

    public Speed getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction.getDirection();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Probe getProbe() {
        return probe;
    }

    public Axiom process(String commandsString) {
        commandsString.chars()
                .mapToObj(c -> (char) c)
                .forEach(this::applyCommandToChar);
        return this;
    }

    private void applyCommandToChar(char parameter) {
        commands.stream()
                .filter(command -> command.getIdentifier() == parameter)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown command: " + parameter))
                .execute(this);
    }
}

