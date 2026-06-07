package b2j.test;

import b2j.wrappers.MainWrapper;

public class Tests {
    static MainWrapper<?> main = new MainWrapper<>(); 

    public static void testMain() {
        main.main().invoke();
    }
}
