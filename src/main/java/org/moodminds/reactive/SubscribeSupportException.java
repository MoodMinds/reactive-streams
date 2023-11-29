package org.moodminds.reactive;

/**
 * An exception to indicate that subscription is not supported in a particular {@link SubscribeSupport}.
 */
public class SubscribeSupportException extends RuntimeException {

    private static final long serialVersionUID = -4171822423113457558L;

    /**
     * Construct the object with the given message string.
     *
     * @param message the given message string
     */
    public SubscribeSupportException(String message) {
        super(message);
    }
}
