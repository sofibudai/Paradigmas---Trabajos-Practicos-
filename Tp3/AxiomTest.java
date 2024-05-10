package Axiom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public  class AxiomTest {
    @Test public void test01(){
        assertEquals(0,newDrone().getSpeed());
    }

    @Test public void test02(){
        assertEquals('N',newDrone().getHeading());
    }

    @Test public void test03(){
        assertEquals(0,newDrone().process('s').getSpeed());
    }
    @Test public void test04(){
        assertThrowsLike("Too slow", () -> newDrone().process('d'));
    }

    private void assertThrowsLike(String message, Executable codeBlock) {
        assertEquals( message, assertThrows(Exception.class, codeBlock).getMessage());
    }

    private Axiom newDrone(){
        return  new Axiom();
    }
}

