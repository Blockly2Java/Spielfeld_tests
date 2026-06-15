package b2j.wrappers;

import io.github.valentinherrmann.levenshtein.ClassWrapper;
import io.github.valentinherrmann.levenshtein.MethodWrapper;
import io.github.valentinherrmann.levenshtein.AttributeWrapper;

public class FeldGeneratorWrapper<T> extends ClassWrapper<T>
{
    private final AttributeWrapper<T, ?> anzahlZeilen;
    private final AttributeWrapper<T, ?> anzahlSpalten;
    private final AttributeWrapper<T, ?> feld;

    private final MethodWrapper<T, ?> generiereFeld;
    private final MethodWrapper<T, ?> setzeFarbe;

    public FeldGeneratorWrapper()
    {
        super(
            "FeldGenerator",
            "",
            "public"
        );

        anzahlZeilen = new AttributeWrapper<>(
            this,
            "anzahlZeilen",
            int.class,
            new String[]{ "private" }
        );

        anzahlSpalten = new AttributeWrapper<>(
            this,
            "anzahlSpalten",
            int.class,
            new String[]{ "private" }
        );

        Class<?> arrayClass = null;
        try {
            arrayClass = Class.forName("[LFilledShape;");
        } catch (ClassNotFoundException e) {
            try {
                arrayClass = Class.forName("[Ljava.lang.Object;");
            } catch (ClassNotFoundException ignored) {}
        }

        feld = new AttributeWrapper<>(
            this,
            "feld",
            arrayClass,
            new String[]{ "private" }
        );

        generiereFeld = new MethodWrapper<>(
            this,
            "generiereFeld",
            void.class,
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

    public AttributeWrapper<T, ?> anzahlZeilen() {
        return anzahlZeilen;
    }

    public AttributeWrapper<T, ?> anzahlSpalten() {
        return anzahlSpalten;
    }

    public AttributeWrapper<T, ?> feld() {
        return feld;
    }

    public MethodWrapper<T, ?> generiereFeld() {
        return generiereFeld;
    }

    public MethodWrapper<T, ?> setzeFarbe() {
        return setzeFarbe;
    }
}
