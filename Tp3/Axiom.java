package Axiom;

public class Axiom {
    private int speed = 0;
    private char heading = 'N';


    public int getSpeed(){
        return speed;
    }

    public char getHeading(){
        return heading;
    }
    public Axiom process(char command) {
        if (command == 's') {
            if (getSpeed() == 0) {
            }
        }
        if (command == 'd') {
            if (getSpeed() == 0) {
                throw new RuntimeException("Too slow");
            }



        }
        return this;
    }
}
