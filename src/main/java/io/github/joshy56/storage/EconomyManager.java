package io.github.joshy56.storage;

import io.github.joshy56.attachable.Attachable;
import io.github.joshy56.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public interface EconomyManager extends Attachable {
    default @NotNull Optional<EconomyStorage> storage() {
        return attached(EconomyStorage.class);
    }
    default @NotNull Response attachStorage(@NotNull final EconomyStorage storage) {
        return attach(EconomyStorage.class, storage);
    }
    default @NotNull Response detachStorage() {
        return detach(EconomyStorage.class);
    }
}
