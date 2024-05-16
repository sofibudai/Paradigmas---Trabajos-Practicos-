package axiom;     // sacarlo del package?

import java.util.Arrays;
import java.util.List;

public class Roseta {
    private List<String> directions = Arrays.asList("North", "East", "South", "West");
    private int currentDirectionIndex = 0;

    public String getCurrentDirection() {
        return directions.get(currentDirectionIndex);
    }

    public void turnRight() {
        currentDirectionIndex = (currentDirectionIndex + 1) % directions.size();
    }

    public void turnLeft() {
        currentDirectionIndex = (currentDirectionIndex - 1 + directions.size()) % directions.size();
    }
}


