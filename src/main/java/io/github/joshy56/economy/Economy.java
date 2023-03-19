package io.github.joshy56.economy;

import io.github.joshy56.attachable.MultiAttachable;
import io.github.joshy56.player.Player;
import io.github.joshy56.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public interface Economy extends MultiAttachable {
    boolean has(@NotNull Player player, double amount);
    double balance(@NotNull Player player);
    @NotNull Response withdraw(@NotNull Player player, double amount);
    @NotNull Response deposit(@NotNull Player player, double amount);
    default @NotNull Collection<Bank> banks() {
        return attachments(Bank.class);
    }
    default @NotNull Collection<Player> players() {
        return attachments(Player.class);
    }
    default @NotNull Response attachPlayer(@NotNull Player player) {
        return attach(Player.class, player);
    }
    default @NotNull Response detachPlayer(@NotNull Player player) {
        return detach(Player.class, player);
    }
    default @NotNull Response detachAllPlayers() {
        return detach(Player.class);
    }
    default @NotNull Response attachBank(@NotNull Bank bank) {
        return attach(Bank.class, bank);
    }
    default @NotNull Response detachBank(@NotNull Bank bank) {
        return detach(Bank.class, bank);
    }
    default @NotNull Response detachAllBanks() {
        return detach(Bank.class);
    }
    @NotNull String name();
    int fractionalDigits();
    @NotNull String singularDisplayName();
    @NotNull String pluralDisplayName();
    @NotNull String format();
}
