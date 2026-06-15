package b2j.test;

import b2j.wrappers.MainWrapper;
import static org.junit.jupiter.api.Assertions.fail;

public class Tests {
    static MainWrapper<?> main = new MainWrapper<>(); 

    public static void testMain() {
        fail("Diese Aufgabe wird (derzeit) nicht automatisch überprüft. Überprüfe selbst, ob deine Abgabe korrekt ist!");
        //main.main().invoke();
    }
}
