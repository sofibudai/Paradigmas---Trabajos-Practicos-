package axiom;

public class Probe {

    private boolean deployed = false;

    public void deploy() {
        this.deployed = true;
    }

    public void retract() {
        this.deployed = false;
    }

    public boolean isDeployed() {
        return deployed;
    }

    public void checkIfDeployed(String action) {
        assert  !deployed : "Cannot " + action + " while probe is deployed";
    }
}