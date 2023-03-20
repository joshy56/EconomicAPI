package io.github.joshy56.economicapi.events;

import io.github.joshy56.economicapi.economy.Account;
import io.github.joshy56.economicapi.event.Cancellable;
import org.jetbrains.annotations.NotNull;

/**
 * @author joshy56
 * @since 19/3/2023
 */
public class AccountBalanceEvent extends AccountEvent implements Cancellable {
    private final double oldBalance;
    private double newBalance;
    private final BalanceAction action;
    private boolean cancelled;

    public AccountBalanceEvent(@NotNull Account account, double oldBalance, double newBalance, @NotNull BalanceAction action) {
        super(account);
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
        this.action = action;
        cancelled = false;
    }

    public double oldBalance() {
        return oldBalance;
    }

    public double amountApplied() {
        return Math.abs((newBalance() - oldBalance()));
    }

    public double newBalance() {
        return newBalance;
    }

    public void newBalance(double amount) {
        newBalance = Math.abs(amount);
    }

    public @NotNull BalanceAction action() {
        return action;
    }

    @Override
    public boolean cancelled() {
        return cancelled;
    }

    @Override
    public void cancelled(boolean cancel) {
        cancelled = cancel;
    }

    public enum BalanceAction {
        DEPOSIT, WITHDRAW
    }
}
