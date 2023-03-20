package io.github.joshy56.economicapi.player;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public record Player(@NotNull UUID identifier, @NotNull String name) {
}
