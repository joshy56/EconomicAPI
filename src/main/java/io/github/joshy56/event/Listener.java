package io.github.joshy56.event;

import io.github.joshy56.response.Response;
import org.jetbrains.annotations.NotNull;

/**
 * @author joshy56
 * @since 19/3/2023
 */
public interface Listener<T extends Event> extends AutoCloseable {
    @NotNull Response on(T event);

    boolean closed();
}
