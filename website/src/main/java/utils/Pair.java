package utils;

/**
 * Implementação de uma estrutura de dados do tipo Par.
 * 
 * @author Fabio Moreira
 * @version 1.0
 *
 * @param <F> Primeiro elemento do par.
 * @param <S> Segundo elemento do par.
 */
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
