package io.github.joshy56.attachable;

import io.github.joshy56.response.Response;
import io.github.joshy56.response.ResponseType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

/**
 * @author joshy56
 * @since 17/3/2023
 */
public class SimpleAttachable implements Attachable {
    private final Map<Class<?>, Object> attachments;

    public SimpleAttachable(@NotNull Map<Class<?>, Object> attachments) {
        this.attachments = attachments;
    }

    @Override
    public @NotNull <T> Optional<T> attached(@NotNull Class<T> type) {
        try {
            T attached = type.cast(attachments.get(type));
            if (attached == null)
                return Optional.empty();

            if(attached instanceof MultiAttachable multiAttachable && multiAttachable.attachments(getClass()).stream().noneMatch(otherAttach -> otherAttach.equals(this))) {
                detach(type);
                return Optional.empty();
            }

            if (attached instanceof Attachable attachable && attachable.attached(getClass()).filter(otherAttach -> otherAttach.equals(this)).isEmpty()) {
                detach(type);
                return Optional.empty();
            }

            return Optional.of(attached);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException e) {
            return Optional.empty();
        }
    }

    @Override
    public @NotNull <T> Response attach(@NotNull Class<T> type, @NotNull T value) {
        try {
            if (!attachments.containsKey(type))
                return new Response(ResponseType.FAILURE, new IllegalArgumentException("This class type is not allowed here."));

            if (attached(type).isPresent())
                return new Response(ResponseType.FAILURE, new UnsupportedOperationException("An value already attached for this class type."));

            if (value instanceof Attachable attachable && !attachable.attached(SimpleAttachable.class).orElse(this).equals(this))
                return new Response(ResponseType.FAILURE, new IllegalArgumentException("This value has attached another value for his class."));

            attachments.put(type, value);
            return new Response(ResponseType.SUCCESS, null);
        } catch (ClassCastException | NullPointerException | UnsupportedOperationException |
                 IllegalArgumentException e) {
            return new Response(ResponseType.FAILURE, e);
        }
    }

    @Override
    public @NotNull <T> Response detach(@NotNull Class<T> type) {
        try {
            attachments.put(type, null);
            return new Response(ResponseType.SUCCESS, null);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException |
                 IllegalArgumentException e) {
            return new Response(ResponseType.FAILURE, e);
        }
    }
}
