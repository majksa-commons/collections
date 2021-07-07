package cz.majksa.commons.collections.exceptions;

import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

/**
 * Thrown when there is an exception when handling map argument.
 *
 * @see java.lang.IllegalArgumentException
 * @author Majksa
 */
public class IllegalMapArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = -5044849357797216639L;

    /**
     * the {@link java.util.Map} with the problem
     */
    @Getter
    protected final Map<?, ?> map;

    /**
     * {@link cz.majksa.commons.collections.exceptions.IllegalMapArgumentException} constructor
     *
     * @param map the {@link java.util.Map} with the problem
     */
    public IllegalMapArgumentException(@NonNull String description, @NonNull Map<?, ?> map) {
        super(String.format("%s in %s", description, map));
        this.map = map;
    }

}
