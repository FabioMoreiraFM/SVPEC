package utils;

public class Pair<F, S> extends java.util.AbstractMap.SimpleImmutableEntry<F, S> {
	private static final long serialVersionUID = 1L;

	public  Pair( F f, S s ) {
        super( f, s );
    }

    public F getFirst() {
        return getKey();
    }

    public S getSecond() {
        return getValue();
    }

}
