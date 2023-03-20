package io.github.joshy56.events;

import io.github.joshy56.economy.Account;
import io.github.joshy56.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * @author joshy56
 * @since 19/3/2023
 */
public class AccountEvent extends Event {
    private final Account account;

    public AccountEvent(@NotNull final Account account) {
        this.account = account;
    }

    public @NotNull Account account() {
        return account;
    }
}
