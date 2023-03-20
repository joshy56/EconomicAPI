package io.github.joshy56.economicapi.player;

import io.github.joshy56.economicapi.attachable.SimpleAttachable;
import io.github.joshy56.economicapi.economy.Account;
import io.github.joshy56.economicapi.economy.Economy;
import io.github.joshy56.economicapi.event.HandlerList;
import io.github.joshy56.economicapi.events.AccountBalanceEvent;
import io.github.joshy56.economicapi.response.Response;
import io.github.joshy56.economicapi.response.ResponseType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public class PlayerAccount extends SimpleAttachable implements Account {
    private final Player player;
    private double balance;

    public PlayerAccount(@NotNull Player player) {
        super(new HashMap<>(Map.of(Economy.class, null)));
        this.player = player;
        balance = 0;
    }

    @Override
    public boolean has(double amount) {
        return amount <= balance();
    }

    @Override
    public double balance() {
        return balance;
    }

    @Override
    public @NotNull Response withdraw(double amount) {
        AccountBalanceEvent balanceEvent = new AccountBalanceEvent(
                this, balance(), (balance() - Math.abs(amount)), AccountBalanceEvent.BalanceAction.WITHDRAW
        );
        HandlerList.handle(balanceEvent);

        if (balanceEvent.cancelled())
            return new Response(ResponseType.FAILURE, new RuntimeException("Can't change balance, balance event cancelled."));

        balance = balanceEvent.newBalance();
        return new Response(ResponseType.SUCCESS, null);
    }

    @Override
    public @NotNull Response deposit(double amount) {
        AccountBalanceEvent balanceEvent = new AccountBalanceEvent(
                this, balance(), (balance() + Math.abs(amount)), AccountBalanceEvent.BalanceAction.DEPOSIT
        );
        HandlerList.handle(balanceEvent);

        if (balanceEvent.cancelled())
            return new Response(ResponseType.FAILURE, new RuntimeException("Can't change balance, balance event cancelled."));

        balance = balanceEvent.newBalance();
        return new Response(ResponseType.SUCCESS, null);
    }

    @Override
    public @NotNull String identifier() {
        return player.name();
    }

    public @NotNull Player player() {
        return player;
    }
}
