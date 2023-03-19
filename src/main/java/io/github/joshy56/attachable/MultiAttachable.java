package io.github.joshy56.attachable;

import io.github.joshy56.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public interface MultiAttachable extends Attachable {
    @NotNull <T> Collection<T> attachments(@NotNull Class<T> type);
    @NotNull <T> Response detach(@NotNull Class<T> type, @NotNull T value);
}
