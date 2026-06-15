package b2j.test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import b2j.wrappers.MainWrapper;
import io.github.valentinherrmann.levenshtein.LevenshteinTest;
import static io.github.valentinherrmann.levenshtein.StructuralLevenshtein.DetailLevel.ONE_FOR_EVERYTHING;
import static io.github.valentinherrmann.levenshtein.StructuralLevenshtein.structuralTestFactory;



@LevenshteinTest
public class TestManager {


    @BeforeAll
    static void beforeAll() {
        testCompilationAndSetup();
    }

    static void testCompilationAndSetup() {
        assertThat(Tests.main).isNotNull();
        assertThat(Tests.main).isInstanceOf(MainWrapper.class);
        assertThat(Tests.feldGenerator).isNotNull();
        assertThat(Tests.feldGenerator2D).isNotNull();
    }
    
    @TestFactory
    List<DynamicTest> strukturTests() {
        return structuralTestFactory(
            ONE_FOR_EVERYTHING,
            Tests.main,
            Tests.feldGenerator,
            Tests.feldGenerator2D
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

    @Test
    void testFeldGeneratorGeneriereFeld() {
        try {
            Tests.testFeldGeneratorGeneriereFeld();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testFeldGeneratorSetzeFarbe() {
        try {
            Tests.testFeldGeneratorSetzeFarbe();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testFeldGenerator2DGeneriereFeld() {
        try {
            Tests.testFeldGenerator2DGeneriereFeld();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testFeldGenerator2DSpalteErzeugen() {
        try {
            Tests.testFeldGenerator2DSpalteErzeugen();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testFeldGenerator2DSetzeFarbe() {
        try {
            Tests.testFeldGenerator2DSetzeFarbe();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }
}    

