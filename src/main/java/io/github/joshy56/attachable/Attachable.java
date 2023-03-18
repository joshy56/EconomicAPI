package io.github.joshy56.attachable;

import io.github.joshy56.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author joshy56
 * @since 17/3/2023
 */
public interface Attachable {
    @NotNull <T> Optional<T> attached(@NotNull Class<T> type);
    @NotNull <T> Response attach(@NotNull Class<T> type, @NotNull final T attachable);
    @NotNull <T> Response detach(@NotNull Class<T> type);
}
