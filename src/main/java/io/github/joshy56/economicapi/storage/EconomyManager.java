package io.github.joshy56.economicapi.storage;

import io.github.joshy56.economicapi.attachable.Attachable;
import io.github.joshy56.economicapi.response.Response;
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
    default @NotNull Optional<EconomyRepository> repository() {
        return attached(EconomyRepository.class);
    }
    default @NotNull Response attachRepository(@NotNull final EconomyRepository repository) {
        return attach(EconomyRepository.class, repository);
    }
    default @NotNull Response detachRepository() {
        return detach(EconomyRepository.class);
    }
}
