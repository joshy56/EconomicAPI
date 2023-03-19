package io.github.joshy56.event;

/**
 * @author joshy56
 * @since 19/3/2023
 */
public interface Cancellable {
    boolean cancelled();
    void cancelled(boolean cancel);
}
