package b2j.wrappers;

import de.tum.cit.aet.levenshtein.ClassWrapper;
import de.tum.cit.aet.levenshtein.MethodWrapper;

public class MainWrapper<T> extends ClassWrapper<T>
{
    private final MethodWrapper<T, ?> main;

    public MainWrapper()
    {
        super(
            "Main",
            "", 
            "public"
        );

        main = new MethodWrapper<>(
                this,
                "main",
                void.class,
                "public",
                "static"
            );
        
    }

    @Override
    public Object getObj(boolean forceNew, boolean useByteBuddy) {
        return getObj(forceNew, useByteBuddy, null);
    }

    public Object getObj() {
        return getObj(false, true);
    }

    public MethodWrapper<T, ?> main() {
        return main;
    }
}
