package io.github.joshy56.economicapi;

import io.github.joshy56.economicapi.storage.EconomyManager;
import io.github.joshy56.economicapi.storage.EconomyRepository;
import io.github.joshy56.economicapi.storage.EconomyStorage;
import org.jetbrains.annotations.NotNull;

/**
 * @author joshy56
 * @since 19/3/2023
 */
public final class EconomicAPI {
    private final EconomyManager manager;
    private final EconomyStorage storage;
    private final EconomyRepository repository;


    public EconomicAPI(@NotNull final EconomyManager manager, @NotNull final EconomyStorage storage, @NotNull final EconomyRepository repository) {
        this.manager = manager;
        this.storage = storage;
        this.repository = repository;
    }

    public @NotNull EconomyManager manager() {
        return manager;
    }

    public @NotNull EconomyStorage storage() {
        return storage;
    }

    public @NotNull EconomyRepository repository() {
        return repository;
    }
}
