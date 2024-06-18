package uno;

public class PlayerStatus {
    private PlayerStatus right;
    private PlayerStatus left;
    private String name;
    private boolean turnStatus;

    public PlayerStatus(String name) {
        this.name = name;
    }

    public void setRight(PlayerStatus right) {
        this.right = right;
    }

    public void setLeft(PlayerStatus left) {
        this.left = left;
    }

    public PlayerStatus getRight() {
        return this.right;
    }

    public PlayerStatus getLeft() {
        return this.left;
    }

    public String getName() {
        return this.name;
    }

    public void setTurnStatus(boolean turnStatus) {
        this.turnStatus = turnStatus;
    }

}