package io.github.joshy56.economy;

import io.github.joshy56.attachable.Attachable;
import io.github.joshy56.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public interface Account extends Attachable {
    boolean has(double amount);

    double balance();

    @NotNull Response withdraw(double amount);

    @NotNull Response deposit(double amount);

    default @NotNull Optional<Economy> economy() {
        return attached(Economy.class);
    }

    default @NotNull Response attachEconomy(@NotNull Economy economy) {
        return attach(Economy.class, economy);
    }

    default @NotNull Response detachEconomy() {
        return detach(Economy.class);
    }

    @NotNull String identifier();
}
