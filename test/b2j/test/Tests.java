package b2j.test;

import b2j.wrappers.MainWrapper;
import b2j.wrappers.FeldGeneratorWrapper;
import b2j.wrappers.FeldGenerator2DWrapper;
import static org.junit.jupiter.api.Assertions.fail;

public class Tests {
    public static MainWrapper<?> main = new MainWrapper<>(); 
    public static FeldGeneratorWrapper<?> feldGenerator = new FeldGeneratorWrapper<>();
    public static FeldGenerator2DWrapper<?> feldGenerator2D = new FeldGenerator2DWrapper<>();

    public static void testMain() {
        // Run main method to ensure no exceptions are thrown
        try {
            main.main().invoke();
        } catch (Exception e) {
            fail("Fehler beim Ausführen der main-Methode: " + e.getMessage());
        }
    }

    public static void testFeldGeneratorGeneriereFeld() {
        Object obj = feldGenerator.getObj(true, true);
        try {
            feldGenerator.generiereFeld().invokeOnSpecificObject(obj, 3, 4);
        } catch (Exception e) {
            fail("Fehler beim Aufruf von generiereFeld: " + e.getMessage());
        }

        int actualZeilen = (int) feldGenerator.anzahlZeilen().getValue(obj);
        int actualSpalten = (int) feldGenerator.anzahlSpalten().getValue(obj);

        if (actualZeilen != 3) {
            fail("anzahlZeilen ist nicht korrekt. Erwartet: 3, Gefunden: " + actualZeilen);
        }
        if (actualSpalten != 4) {
            fail("anzahlSpalten ist nicht korrekt. Erwartet: 4, Gefunden: " + actualSpalten);
        }

        Object[] feldArray = (Object[]) feldGenerator.feld().getValue(obj);
        if (feldArray == null) {
            fail("Das feld-Array ist null.");
        }
        if (feldArray.length != 12) {
            fail("Das feld-Array hat nicht die erwartete Länge 12. Gefunden: " + feldArray.length);
        }

        // Check each rectangle positioning
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 4; j++) {
                int index = j + k * 4; // matches solution's index calculation
                if (index < 0 || index >= feldArray.length) {
                    fail("Berechneter Index " + index + " liegt außerhalb des Arrays.");
                }
                Object rectangle = feldArray[index];
                if (rectangle == null) {
                    fail("Rechteck an Index " + index + " (Zeile " + k + ", Spalte " + j + ") ist null.");
                }
                checkShape(rectangle, 50 * j, 50 * k);
            }
        }
    }

    public static void testFeldGeneratorSetzeFarbe() {
        Object obj = feldGenerator.getObj(true, true);
        try {
            feldGenerator.generiereFeld().invokeOnSpecificObject(obj, 3, 4);
            feldGenerator.setzeFarbe().invokeOnSpecificObject(obj, "#ff0000", 2, 1);
        } catch (Exception e) {
            fail("Fehler beim Aufruf von generiereFeld oder setzeFarbe: " + e.getMessage());
        }

        Object[] feldArray = (Object[]) feldGenerator.feld().getValue(obj);
        if (feldArray == null) {
            fail("Das feld-Array ist null.");
        }
        int index = 2 + 1 * 4; // x = 2, y = 1, columns = 4
        Object rectangle = feldArray[index];
        if (rectangle == null) {
            fail("Rechteck an Index " + index + " ist null.");
        }
        checkColor(rectangle, "#ff0000");
    }

    public static void testFeldGenerator2DGeneriereFeld() {
        Object obj = feldGenerator2D.getObj(true, true);
        try {
            feldGenerator2D.generiereFeld().invokeOnSpecificObject(obj, 3, 4);
        } catch (Exception e) {
            fail("Fehler beim Aufruf von generiereFeld: " + e.getMessage());
        }

        Object[][] feldArray = (Object[][]) feldGenerator2D.feld().getValue(obj);
        if (feldArray == null) {
            fail("Das 2D-feld-Array ist null.");
        }
        if (feldArray.length != 4) {
            fail("Das 2D-feld-Array hat nicht die erwartete Spaltenanzahl von 4. Gefunden: " + feldArray.length);
        }

        for (int j = 0; j < 4; j++) {
            Object[] rowArray = feldArray[j];
            if (rowArray == null) {
                fail("Spalte " + j + " im 2D-feld-Array ist null.");
            }
            if (rowArray.length != 3) {
                fail("Spalte " + j + " im 2D-feld-Array hat nicht die erwartete Zeilenanzahl von 3. Gefunden: " + rowArray.length);
            }
            for (int k = 0; k < 3; k++) {
                Object rectangle = rowArray[k];
                if (rectangle == null) {
                    fail("Rechteck an Spalte " + j + ", Zeile " + k + " ist null.");
                }
                checkShape(rectangle, 50 * j, 50 * k);
            }
        }
    }

    public static void testFeldGenerator2DSpalteErzeugen() {
        Object obj = feldGenerator2D.getObj(true, true);
        Object[] column = null;
        try {
            column = (Object[]) feldGenerator2D.spalteErzeugen().invokeOnSpecificObject(obj, 5, 150);
        } catch (Exception e) {
            fail("Fehler beim Aufruf von spalteErzeugen: " + e.getMessage());
        }

        if (column == null) {
            fail("Die erzeugte Spalte ist null.");
        }
        if (column.length != 5) {
            fail("Die erzeugte Spalte hat nicht die erwartete Länge von 5. Gefunden: " + column.length);
        }

        for (int m = 0; m < 5; m++) {
            Object rectangle = column[m];
            if (rectangle == null) {
                fail("Rechteck in der erzeugten Spalte an Index " + m + " ist null.");
            }
            checkShape(rectangle, 150, 50 * m);
        }
    }

    public static void testFeldGenerator2DSetzeFarbe() {
        Object obj = feldGenerator2D.getObj(true, true);
        try {
            feldGenerator2D.generiereFeld().invokeOnSpecificObject(obj, 3, 4);
            feldGenerator2D.setzeFarbe().invokeOnSpecificObject(obj, "#ff0000", 2, 1);
        } catch (Exception e) {
            fail("Fehler beim Aufruf von generiereFeld oder setzeFarbe: " + e.getMessage());
        }

        Object[][] feldArray = (Object[][]) feldGenerator2D.feld().getValue(obj);
        if (feldArray == null) {
            fail("Das 2D-feld-Array ist null.");
        }
        Object rectangle = feldArray[2][1];
        if (rectangle == null) {
            fail("Rechteck an Spalte 2, Zeile 1 ist null.");
        }
        checkColor(rectangle, "#ff0000");
    }

    private static void checkShape(Object shape, double expectedX, double expectedY) {
        try {
            double actualCenterX = (double) shape.getClass().getMethod("getCenterX").invoke(shape);
            double actualCenterY = (double) shape.getClass().getMethod("getCenterY").invoke(shape);
            double expectedCenterX = expectedX + 22.5;
            double expectedCenterY = expectedY + 22.5;
            if (Math.abs(actualCenterX - expectedCenterX) > 0.01 || Math.abs(actualCenterY - expectedCenterY) > 0.01) {
                fail(String.format("Die Position des Rechtecks ist nicht korrekt. Erwarteter Mittelpunkt: (%f, %f), Gefunden: (%f, %f)",
                    expectedCenterX, expectedCenterY, actualCenterX, actualCenterY));
            }
        } catch (Exception e) {
            fail("Konnte Rechteck-Eigenschaften nicht überprüfen: " + e.getMessage());
        }
    }

    private static void checkColor(Object shape, String expectedHex) {
        try {
            Object color = shape.getClass().getMethod("getFillColor").invoke(shape);
            if (color == null) {
                fail("Das Rechteck hat keine Füllfarbe.");
            }
            String colorStr = color.toString().toLowerCase();
            String normalizedExpected = expectedHex.replace("#", "").toLowerCase();
            String normalizedActual = colorStr.replace("0x", "").replace("#", "").toLowerCase();
            if (normalizedActual.length() == 8) {
                normalizedActual = normalizedActual.substring(0, 6);
            }
            if (!normalizedActual.equals(normalizedExpected)) {
                fail(String.format("Die Farbe des Rechtecks ist nicht korrekt. Erwartet: %s, Gefunden: %s", expectedHex, colorStr));
            }
        } catch (Exception e) {
            fail("Konnte Füllfarbe des Rechtecks nicht überprüfen: " + e.getMessage());
        }
    }
}
