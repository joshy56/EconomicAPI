package io.github.joshy56.economy;

import io.github.joshy56.player.Player;
import io.github.joshy56.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public interface Bank extends Account {
    boolean isOwner(@NotNull Player player);
    boolean isMember(@NotNull Player player);
    @NotNull Response addMember(@NotNull Player player);
    @NotNull Response quitMember(@NotNull Player player);
    @NotNull Player owner();
    @NotNull Collection<Player> members();
}
