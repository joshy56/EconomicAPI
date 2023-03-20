package io.github.joshy56.economicapi.economy;

import io.github.joshy56.economicapi.attachable.MultiAttachable;
import io.github.joshy56.economicapi.player.Player;
import io.github.joshy56.economicapi.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public interface Bank extends Account, MultiAttachable {
    default boolean isOwner(@NotNull Player player) {
        return owner().equals(player);
    }

    default boolean isMember(@NotNull Player player) {
        return members().contains(player);
    }

    default @NotNull Response attachMember(@NotNull Player player) {
        return attach(Player.class, player);
    }

    default @NotNull Response detachMember(@NotNull Player player) {
        return detach(Player.class, player);
    }

    @NotNull Player owner();

    default @NotNull Collection<Player> members() {
        return attachments(Player.class);
    }
}
