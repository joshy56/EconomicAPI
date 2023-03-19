package io.github.joshy56.player;

import io.github.joshy56.attachable.SimpleAttachable;
import io.github.joshy56.economy.Account;
import io.github.joshy56.economy.Economy;
import io.github.joshy56.response.Response;
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
        return null;
    }

    @Override
    public @NotNull Response deposit(double amount) {
        return null;
    }

    @Override
    public @NotNull String identifier() {
        return player.name();
    }

    public @NotNull Player player() {
        return player;
    }
}
