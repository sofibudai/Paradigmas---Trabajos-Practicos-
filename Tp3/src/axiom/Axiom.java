package axiom;

public class Axiom {
    private int speed = 0;
    private Roseta roseta = new Roseta();
    private String bearing = roseta.getCurrentDirection();
    private boolean deployed = false;

    public int getSpeed() {
        return speed;
    }

    public String getBearing() {
        bearing = roseta.getCurrentDirection();
        return bearing;
    }

    public boolean deployed() {
        return deployed;
    }

    public void deploy() {
        if (speed == 0) {
            throw new RuntimeException("Cannot deploy probe while static");
        }
        deployed = true;
    }

    public void retract() {
        deployed = false;
    }


    public Axiom process(String commands) {
        for (char command : commands.toCharArray()) {
            if (command == 'i') {
                speed += 1;
            } else if (command == 's') {
                if (speed > 0 && !deployed) {
                    speed -= 1;
                } else if (deployed) {
                    throw new RuntimeException("Cannot stop while probe is deployed");
                }
            } else if (command == 'l' || command == 'r') {
                if (!deployed) {
                    if (command == 'l') {
                        roseta.turnLeft();
                    } else {
                        roseta.turnRight();
                    }
                    bearing = roseta.getCurrentDirection();
                } else {
                    throw new RuntimeException("Cannot turn while probe is deployed");
                }
            } else if (command == 'd') {
                if (speed > 0) {
                    deployed = true;
                } else {
                    throw new RuntimeException("Cannot deploy probe while static");
                }
            } else if (command == 'f') {
                deployed = false;
            } else {
                throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
        return this;
    }
}




