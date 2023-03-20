package io.github.joshy56.economicapi.events;

import io.github.joshy56.economicapi.economy.Account;
import io.github.joshy56.economicapi.event.Cancellable;
import org.jetbrains.annotations.NotNull;

/**
 * @author joshy56
 * @since 19/3/2023
 */
public class AccountDeleteEvent extends AccountEvent implements Cancellable {
    private boolean cancelled;

    public AccountDeleteEvent(@NotNull Account account) {
        super(account);
        cancelled = false;
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
