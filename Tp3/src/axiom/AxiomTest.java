package axiom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public  class AxiomTest {
    @Test public void speedIsZero(){ assertEquals(0, newDrone().getSpeed()); }

    @Test public void speedAfterSlowingWhileStatic() {
        assertEquals(0, newDrone().process("s").getSpeed());
    }

    @Test public void speedAfterOneIncrease() {
        assertEquals(1, newDrone().process("i").getSpeed());
    }

    @Test public void speedAfterOneDecrease() {
        Axiom drone = newDrone();
        drone.process("i");
        assertEquals(0, newDrone().process("s").getSpeed());
    }

    @Test public void indicatesBearing(){
        assertEquals("North", newDrone().getBearing());
    }

    @Test public void turnRight() {
        assertEquals( "East", newDrone().process("r").getBearing());
    }

    @Test public void turnLeft() {
        assertEquals("West", newDrone().process("l").getBearing());
    }

    // cuales test hacer para los turns?
    @Test public void turnRightTwice() {
        Axiom drone = newDrone();
        drone.process("r");
        assertEquals("South", drone.process("r").getBearing());
    }

    @Test public void turnRightFourTimes() {
        Axiom drone = newDrone();
        drone.process("rrr");
        assertEquals("North", drone.process("r").getBearing());
    }

    @Test public void turnBothWays() {
        Axiom drone = newDrone();
        drone.process("ll");
        assertEquals("West", drone.process("r").getBearing());
    }

    @Test public void turnRightWhileMoving() {
        Axiom drone = newDrone();
        drone.process("i");
        assertEquals("East", drone.process("r").getBearing());
    }

    @Test public void cannotDeployProbeWhileStatic() {
        assertThrowsLike("Cannot deploy probe while static", () -> newDrone().process("d"));
    }

    @Test public void cannotTurnWhileProbeIsDeployed() {
        Axiom drone = newDrone();
        drone.process("id");
        assertThrowsLike("Cannot turn while probe is deployed", () -> drone.process("r"));
    }

    @Test public void cannotStopWhileProbeIsDeployed() {
        Axiom drone = newDrone();
        drone.process("id");
        assertThrowsLike("Cannot stop while probe is deployed", () -> drone.process("s"));
    }

    @Test public void increaseSpeedWhileProbeIsDeployed() {
        Axiom drone = newDrone();
        drone.process("id");
        assertEquals(2, drone.process("i").getSpeed());
    }

    private void assertThrowsLike(String message, Executable codeBlock) {
        assertEquals(message, assertThrows(Exception.class, codeBlock).getMessage());
    }

    private axiom.Axiom newDrone(){
        return  new axiom.Axiom();
    }
    //preguntar si el newDrone() esta bien ahi
}

// despues hacer la parte de reducir el codigo