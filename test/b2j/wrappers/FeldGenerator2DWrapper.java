package b2j.wrappers;

import io.github.valentinherrmann.levenshtein.ClassWrapper;
import io.github.valentinherrmann.levenshtein.MethodWrapper;
import io.github.valentinherrmann.levenshtein.AttributeWrapper;

import b2j.test.Tests;

public class FeldGenerator2DWrapper<T> extends ClassWrapper<T>
{
    private final AttributeWrapper<T, ?> feld;

    private final MethodWrapper<T, ?> generiereFeld;
    private final MethodWrapper<T, ?> spalteErzeugen;
    private final MethodWrapper<T, ?> setzeFarbe;

    public FeldGenerator2DWrapper()
    {
        super(
            "FeldGenerator2D",
            "",
            Tests.feldGenerator,
            new ClassWrapper<?>[]{},
            new String[]{ "public" }
        );

        Class<?> filledShape2DArrayClass = null;
        try {
            filledShape2DArrayClass = Class.forName("[[LFilledShape;");
        } catch (ClassNotFoundException e) {
            try {
                filledShape2DArrayClass = Class.forName("[[Ljava.lang.Object;");
            } catch (ClassNotFoundException ignored) {}
        }

        feld = new AttributeWrapper<>(
            this,
            "feld",
            filledShape2DArrayClass,
            new String[]{ "private" }
        );

        generiereFeld = new MethodWrapper<>(
            this,
            "generiereFeld",
            void.class,
            new Class<?>[]{ int.class, int.class },
            new String[]{ "public" }
        );

        Class<?> filledShapeArrayClass = null;
        try {
            filledShapeArrayClass = Class.forName("[LFilledShape;");
        } catch (ClassNotFoundException e) {
            try {
                filledShapeArrayClass = Class.forName("[Ljava.lang.Object;");
            } catch (ClassNotFoundException ignored) {}
        }

        spalteErzeugen = new MethodWrapper<>(
            this,
            "spalteErzeugen",
            filledShapeArrayClass,
            new Class<?>[]{ int.class, int.class },
            new String[]{ "public" }
        );

        setzeFarbe = new MethodWrapper<>(
            this,
            "setzeFarbe",
            void.class,
            new Class<?>[]{ String.class, int.class, int.class },
            new String[]{ "public" }
        );
    }

    @Override
    public Object getObj(boolean forceNew, boolean useByteBuddy) {
        return getObj(forceNew, useByteBuddy, null);
    }

    public Object getObj() {
        return getObj(false, true);
    }

    public AttributeWrapper<T, ?> feld() {
        return feld;
    }

    public MethodWrapper<T, ?> generiereFeld() {
        return generiereFeld;
    }

    public MethodWrapper<T, ?> spalteErzeugen() {
        return spalteErzeugen;
    }

    public MethodWrapper<T, ?> setzeFarbe() {
        return setzeFarbe;
    }
}
