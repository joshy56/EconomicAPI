package io.github.joshy56.event;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author joshy56
 * @since 19/3/2023
 */
public class HandlerList {
    private static final Map<Class<? extends Event>, Collection<Listener<?>>> HANDLERS;
    private static boolean closed;

    static {
        HANDLERS = new HashMap<>();
        closed = false;
    }

    public static @NotNull Map<Class<? extends Event>, Collection<Listener<?>>> handlers() {
        if (closed())
            return Map.of();
        return HANDLERS;
    }

    public static <T extends Event> void handle(T event) {
        if (closed())
            return;

        handlers().put(
                event.getClass(),
                handlers().get(event.getClass())
                        .stream()
                        .filter(listener -> !listener.closed())
                        .map(listener -> ((Listener<T>) listener))
                        .peek(listener -> listener.on(event))
                        .collect(Collectors.toList())
        );
    }

    public static void close() {
        if (closed())
            return;

        if (handlers().isEmpty())
            return;

        handlers().values()
                .parallelStream()
                .reduce(
                        new ArrayList<>(),
                        (left, right) -> {
                            right.addAll(left);
                            return right;
                        }
                )
                .parallelStream()
                .forEach(
                        listener -> {
                            try {
                                listener.close();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

        handlers().clear();
        closed = true;
    }

    public static boolean closed() {
        return closed;
    }
}
