package io.github.joshy56.economicapi.attachable;

import io.github.joshy56.economicapi.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public interface MultiAttachable {
    @NotNull <T> Collection<T> attachments(@NotNull Class<T> type);

    @NotNull <T> Response attach(@NotNull Class<T> type, @NotNull T value);

    @NotNull <T> Response detach(@NotNull Class<T> type, @NotNull T value);
    @NotNull <T> Response detachAll(@NotNull Class<T> type);
}
