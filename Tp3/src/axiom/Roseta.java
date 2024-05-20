package axiom;

import java.util.Arrays;
import java.util.List;

public class Roseta {
    private List<String> directions = Arrays.asList("North", "East", "South", "West");
    private int currentDirectionIndex = 0;

    public String getCurrentDirection() {
        return directions.get(currentDirectionIndex);
    }

    public int getCurrentDirectionIndex() {
        return currentDirectionIndex;
    }

    public void setCurrentDirectionIndex(int currentDirectionIndex) {
        this.currentDirectionIndex = currentDirectionIndex;
    }

    public List<String> getDirections() {
        return directions;
    }

    public void turnLeft() {
        currentDirectionIndex = (currentDirectionIndex + 3) % 4;
    }

    public void turnRight() {
        currentDirectionIndex = (currentDirectionIndex + 1) % 4;
    }
}
