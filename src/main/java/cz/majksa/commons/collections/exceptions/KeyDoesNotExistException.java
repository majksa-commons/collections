package cz.majksa.commons.collections.exceptions;

import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

/**
 * Thrown when key of the map already exists.
 *
 * @see cz.majksa.commons.collections.exceptions.IllegalMapArgumentException
 * @see java.lang.IllegalArgumentException
 * @author Majksa
 */
@Getter
public class KeyDoesNotExistException extends IllegalMapArgumentException {

    private static final long serialVersionUID = -2637065731730377159L;

    /**
     * the {@link String} key that does not found
     */
    protected final String key;

    /**
     * {@link cz.majksa.commons.collections.exceptions.KeyDoesNotExistException} constructor
     *
     * @param key the {@link String} key that was not found
     * @param map the {@link java.util.Map} with the problem
     */
    public KeyDoesNotExistException(@NonNull String key, @NonNull Map<?, ?> map) {
        super(String.format("Key %s does not exist", key), map);
        this.key = key;
    }

}
