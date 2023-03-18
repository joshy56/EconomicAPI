package io.github.joshy56.response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author joshy56
 * @since 17/3/2023
 */
public record Response(@NotNull ResponseType type, @Nullable Throwable error) {
}
