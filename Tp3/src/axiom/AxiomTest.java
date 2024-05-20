package axiom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public  class AxiomTest {
    @Test public void speedIsZero(){ assertEquals(0, newDrone().getSpeed().getValue()); }

    @Test public void speedAfterSlowingWhileStatic() {
        assertEquals(0, newDrone().process("s").getSpeed().getValue());
    }

    @Test public void speedAfterOneIncrease() {
        assertEquals(1, newDrone().process("i").getSpeed().getValue());
    }

    @Test public void speedAfterOneDecrease() {
        assertEquals(0, newDrone().process("is").getSpeed().getValue());
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

    @Test public void turnRightTwice() {
        assertEquals("South", newDrone().process("rr").getBearing());
    }

    @Test public void turnRightFourTimes() {
        assertEquals("North", newDrone().process("rrrr").getBearing());
    }

    @Test public void turnBothWays() {
        assertEquals("West", newDrone().process("llr").getBearing());
    }

    @Test public void turnRightWhileMoving() {
        assertEquals("East", newDrone().process("ir").getBearing());
    }

    @Test public void  turnAfterRetract(){
        assertEquals("East", newDrone().process("idfr").getBearing());
    }

    @Test public void cannotDeployProbeWhileStatic() {
        assertThrowsLike("Cannot deploy probe while static",AssertionError.class, () -> newDrone().process("d"));
    }

    @Test public void cannotTurnWhileProbeIsDeployed() {
        assertThrowsLike("Cannot turn while probe is deployed",AssertionError.class, () -> newDrone().process("idr"));
    }

    @Test public void cannotStopWhileProbeIsDeployed() {
        assertThrowsLike("Cannot stop while probe is deployed",AssertionError.class, () -> newDrone().process("ids"));
    }

    @Test public void increaseSpeedWhileProbeIsDeployed() {
        assertEquals(2, newDrone().process("idi").getSpeed().getValue());
    }

    @Test public void executeNonExistentCommand() {
        assertThrowsLike("Unknown command: x",IllegalArgumentException.class, () -> newDrone().process("ix"));
    }

    @Test public void variousSameCommands(){
        assertEquals(true, newDrone().process("idddddddd").getProbe().isDeployed() );
    }

    private void assertThrowsLike(String message, Class<? extends Throwable> expectedType, Executable codeBlock) {
        assertEquals(message, assertThrows(expectedType, codeBlock).getMessage());
    }

    private axiom.Axiom newDrone(){
        return  new axiom.Axiom();
    }
}

