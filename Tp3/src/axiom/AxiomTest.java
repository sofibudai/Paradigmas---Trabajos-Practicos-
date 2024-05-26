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
        assertEquals(Direction.north, newDrone().getDirection());
    }

    @Test public void turnRight() {
        assertEquals( Direction.east, newDrone().process("r").getDirection());
    }

    @Test public void turnLeft() {
        assertEquals(Direction.west, newDrone().process("l").getDirection());
    }

    @Test public void turnRightTwice() {
        assertEquals(Direction.south, newDrone().process("rr").getDirection());
    }

    @Test public void turnRightFourTimes() {
        assertEquals(Direction.north, newDrone().process("rrrr").getDirection());
    }

    @Test public void turnBothWays() {
        assertEquals(Direction.west, newDrone().process("llr").getDirection());
    }

    @Test public void turnRightWhileMoving() {
        assertEquals(Direction.east, newDrone().process("ir").getDirection());
    }

    @Test public void  turnAfterRetract(){
        assertEquals(Direction.east, newDrone().process("idfr").getDirection());
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
        assertThrowsLike('x', IllegalArgumentException.class, () -> newDrone().process("ix"));
    }

    @Test public void variousSameCommands(){
        assertEquals(true, newDrone().process("idddddddd").getProbe().isDeployed() );
    }

    private void assertThrowsLike(String message, Class<? extends Throwable> expectedType, Executable codeBlock) {
        assertEquals(message, assertThrows(expectedType, codeBlock).getMessage());
    }
    private void assertThrowsLike(char command, Class<? extends Throwable> expectedType, Executable codeBlock) {
        assertEquals(Axiom.getUnknownCommand(command), assertThrows(expectedType, codeBlock).getMessage());
    }

    private axiom.Axiom newDrone(){
        return  new axiom.Axiom();
    }
}

