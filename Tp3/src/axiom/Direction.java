package axiom;

public abstract class Direction {

    public static final String north = "North";
    public static final String south = "South";
    public static final String east = "East";
    public static final String west = "West";

    public abstract Direction turnLeft();
    public abstract Direction turnRight();
    public abstract String getDirection();

    public static class North extends Direction {
        public Direction turnLeft() {
            return new West();
        }

        public Direction turnRight() {
            return new East();
        }

        public String getDirection() {
            return north;
        }
    }

    public static class South extends Direction {
        public Direction turnLeft() {
            return new East();
        }

        public Direction turnRight() {
            return new West();
        }

        public String getDirection() {
            return south;
        }
    }

    public static class East extends Direction {
        public Direction turnLeft() {
            return new North();
        }

        public Direction turnRight() {
            return new South();
        }

        public String getDirection() {
            return east;
        }
    }

    public static class West extends Direction {
        public Direction turnLeft() {
            return new South();
        }

        public Direction turnRight() {
            return new North();
        }

        public String getDirection() {
            return west;
        }
    }


}
