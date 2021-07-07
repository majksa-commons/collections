package cz.majksa.commons.collections.exceptions;

import lombok.NonNull;

import java.util.Map;

/**
 * Thrown when key of the map already exists.
 *
 * @see cz.majksa.commons.collections.exceptions.IllegalMapArgumentException
 * @see java.lang.IllegalArgumentException
 * @author Majksa
 */
public class KeyAlreadyExistsException extends IllegalMapArgumentException {

    private static final long serialVersionUID = 6014089554908080967L;

    /**
     * the {@link String} key that already exists
     */
    protected final String key;

    /**
     * {@link cz.majksa.commons.collections.exceptions.KeyAlreadyExistsException} constructor
     *
     * @param key {@link String} the key that already exists
     * @param map the {@link java.util.Map} with the problem
     */
    public KeyAlreadyExistsException(@NonNull String key, @NonNull Map<?, ?> map) {
        super(String.format("Key %s already exists", key), map);
        this.key = key;
    }

}
