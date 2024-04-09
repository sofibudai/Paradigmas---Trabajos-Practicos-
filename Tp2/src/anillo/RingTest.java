package anillo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class RingTest {
    @Test void test00NextOnEmpty() {
        assertThrows( Exception.class, () -> new Ring().next() );
    }

    @Test void test01CurrentOnEmpty() {
        assertThrows( Exception.class, () -> new Ring().current() );
    }

    @Test void test02CurrentAfterAdd() {
        assertEquals( "Hola" , new Ring().add( "Hola" ).current() );
    }

    @Test void test03NextAfterOneAdd() {
        assertEquals( "Hola" , new Ring().add( "Hola" ).next().current() );
    }

    @Test void test04CurrentAfterTwoAdds() {
        assertEquals( 42, new Ring().add( "Hola" )
                                    .add( new Integer( 42 ) )
                                    .current() );
    }

    @Test void test05CurrentAndNextAfterTwoAdds() {
        assertEquals( "Hola", new Ring().add( "Hola" )         // agrega un elemento nuevo al anillo: "hola"
                                        .add( new Integer( 42 ) ) // luego agrega otro elemento: 42, en la posicion del "hola"
                                        .next()                         // pasa al siguiente elemento: el "hola"
                                        .current() );                   // devuelve el elemento actual: "hola"
    }

    @Test void test06CyclesWithTwoAdds() {
        assertEquals( 42, new Ring().add( "Hola" )
                                    .add( new Integer( 42 ) )
                                    .next()
                                    .next()
                                    .current() );
    }

    @Test void test07InsertsAfterTwoAdds() {
        assertEquals( LocalDate.now(), new Ring().add( "Hola" )
                                                 .add( new Integer( 42 ) )
                                                 .next()
                                                 .add( LocalDate.now() )
                                                 .current() );
    }

    @Test void test08NextOnInsertsAfterTwoAdds() {
        assertEquals( "Hola", new Ring().add( "Hola" )             // agrega un elemento nuevo al anillo: "hola"
                                        .add( new Integer( 42 ) )     // luego le agrega otro elemento: 42, en la posicion del "hola"
                                        .next()                             // situado en el 42, pasa a ubicarse sobre el siguiente elemento: el "hola"
                                        .add( LocalDate.now() )             // agrega la fecha en la posicion del "hola"
                                        .next()                             // situado en la fecha, pasa al siguiente elemento: el "hola"
                                        .current() );                       // devuelve el elemento actual: "hola"
    }

    @Test void test09CyclesOnTreeElements() {
        assertEquals( LocalDate.now() , new Ring().add( "Hola" )
                                                  .add( new Integer( 42 ) )
                                                  .add( LocalDate.now() )
                                                  .next()
                                                  .next()
                                                  .next()
                                                  .current() );
    }

    @Test void test10EmptyaRing() {
        Ring ring = new Ring().add( "Hola" ).remove();

        assertThrows( Exception.class, () -> ring.current() );
        assertThrows( Exception.class, () -> ring.next() );
    }

    @Test void test11RemoveCurrent() {
        assertEquals( "Hola", new Ring().add( "Hola" )           // agrega un elemento nuevo al anillo: "hola"
                                        .add( new Integer( 42 ) )   // luego agrega otro elemento: 42, en la posicion del "hola"
                                        .remove()                         // remueve el elemento actual: 42 y el nuevo elemento actual es "hola"
                                        .current() );                     // devuelve el elemento actual: "hola"
    }

    @Test void test12RemoveNext() {
        assertEquals( "Hola", new Ring().add( "Hola" )           // agrega un elemento nuevo al anillo: "hola"
                                        .add( new Integer( 42 ) )   // luego agrega otro elemento: 42, en la posicion del "hola"
                                        .add( LocalDate.now() )           // luego agrega otro elemento: la fecha, en la posicion del 42
                                        .next()                           // pasa al siguiente elemento: el 42
                                        .remove().current() );            // remueve el elemento actual: 42 y el nuevo elemento actual es "hola"
    }

    @Test void test13RemoveLast() {
        assertEquals( LocalDate.now(), new Ring().add( "Hola" )
                                                 .add( new Integer( 42 ) )
                                                 .add( LocalDate.now() )
                                                 .next()
                                                 .next()
                                                 .remove()
                                                 .current() );
    }
}
