package io.github.joshy56.economicapi.events;

import io.github.joshy56.economicapi.economy.Bank;
import io.github.joshy56.economicapi.event.Cancellable;
import io.github.joshy56.economicapi.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author joshy56
 * @since 19/3/2023
 */
public class BankMemberAddEvent extends AccountEvent implements Cancellable {
    private final Player member;
    private boolean cancelled;

    public BankMemberAddEvent(@NotNull Bank bank, @NotNull Player member) {
        super(bank);
        this.member = member;
        cancelled = false;
    }

    public @NotNull Player member() {
        return member;
    }

    @Override
    public boolean cancelled() {
        return cancelled;
    }

    @Override
    public void cancelled(boolean cancel) {
        cancelled = cancel;
    }
}
