package axiom;

public abstract class Direction {
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
            return "North";
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
            return "South";
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
            return "East";
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
            return "West";
        }
    }


}
