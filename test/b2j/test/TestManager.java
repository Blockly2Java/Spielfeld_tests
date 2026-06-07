package b2j.test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import b2j.wrappers.MainWrapper;
import de.tum.cit.aet.levenshtein.LevenshteinTest;
import static de.tum.cit.aet.levenshtein.StructuralLevenshtein.DetailLevel.ONE_FOR_EVERYTHING;
import static de.tum.cit.aet.levenshtein.StructuralLevenshtein.structuralTestFactory;



@LevenshteinTest
public class TestManager {


    @BeforeAll
    static void beforeAll() {
        testCompilationAndSetup();
    }

    static void testCompilationAndSetup() {
        assertThat(Tests.main).isNotNull();
        assertThat(Tests.main).isInstanceOf(MainWrapper.class);
    }
    
    @TestFactory
    List<DynamicTest> strukturTests() {
        return structuralTestFactory(
            ONE_FOR_EVERYTHING,
            Tests.main
        );
    }

    @Test
    void testMain() {
        try {
            Tests.testMain();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }
}    

