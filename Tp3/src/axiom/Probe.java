package axiom;

public class Probe {
    private String action;
    private boolean deployed = false;

    public String getAction() {
        return action;
    }

    public void deploy() {
        this.action = "deploy";
        this.deployed = true;
    }

    public void retract() {
        this.action = "retract";
        this.deployed = false;
    }

    public boolean isDeployed() {
        return deployed;
    }

    public void checkIfDeployed(String action) {
        this.action = action;
        assert  !deployed : getErrorMessage(action);
    }

    public static String getErrorMessage(String action) {
        return "Cannot " + action + " while probe is deployed";
    }
}