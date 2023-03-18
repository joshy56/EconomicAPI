package io.github.joshy56.storage;

import io.github.joshy56.attachable.Attachable;
import io.github.joshy56.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author joshy56
 * @since 17/3/2023
 */
public interface EconomyStorage extends Attachable {
    @NotNull Response loadAll();
    @NotNull Response saveAll();
    default @NotNull Optional<EconomyManager> manager() {
        return attached(EconomyManager.class);
    }
    default @NotNull Response attach(@NotNull final EconomyManager manager) {
        return attach(EconomyManager.class, manager);
    }
    default @NotNull Response detach() {
        return detach(EconomyManager.class);
    }
}
