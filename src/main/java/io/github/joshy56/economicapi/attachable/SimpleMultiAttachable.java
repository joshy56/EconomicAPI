package io.github.joshy56.economicapi.attachable;

import io.github.joshy56.economicapi.response.Response;
import io.github.joshy56.economicapi.response.ResponseType;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author joshy56
 * @since 18/3/2023
 */
public class SimpleMultiAttachable implements MultiAttachable {
    private final Map<Class<?>, Collection<Object>> attachments;

    public SimpleMultiAttachable(@NotNull final Map<Class<?>, Collection<Object>> attachments) {
        this.attachments = attachments;
    }

    @Override
    public @NotNull <T> Optional<T> attached(@NotNull Class<T> type) {
        return attachments(type).stream().findAny();
    }

    @Override
    public @NotNull <T> Response attach(@NotNull Class<T> type, @NotNull T attachable) {
        if(!attachments.containsKey(type))
            return new Response(ResponseType.FAILURE, new IllegalArgumentException("Can't attach an value for a type don't mapped previously"));

        Collection<T> attachments = attachments(type);
        if(attachments.isEmpty())
            attachments = new ArrayList<>(1);

        attachments.add(attachable);
        this.attachments.put(type, (Collection<Object>) attachments);
        return new Response(ResponseType.SUCCESS, null);
    }

    @Override
    public @NotNull <T> Response detach(@NotNull Class<T> type) {
        if(!attachments.containsKey(type))
            return new Response(ResponseType.FAILURE, new IllegalArgumentException("Can't detach, because this type is not allowed here (don't mapped)"));

        attachments.put(type, null);
        return new Response(ResponseType.SUCCESS, null);
    }

    @Override
    public @NotNull <T> Collection<T> attachments(@NotNull Class<T> type) {
        try{
            Collection<T> attachments = (Collection<T>) this.attachments.get(type);
            if(attachments == null)
                return Collections.emptyList();

            return attachments.parallelStream()
                    .map(
                            attached -> {
                                if(attached instanceof Attachable attachable && attachable.attached(getClass()).isEmpty() ||
                                        attached instanceof MultiAttachable multiAttachable && !multiAttachable.attachments(getClass()).contains(this)) {
                                    detach(type, attached);
                                    return null;
                                }

                                return attached;
                            }
                    )
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (ClassCastException | NullPointerException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public @NotNull <T> Response detach(@NotNull Class<T> type, @NotNull T value) {
        Collection<T> attachments = attachments(type);
        if(attachments.isEmpty())
           return new Response(ResponseType.SUCCESS, null);

        attachments.remove(value);
        this.attachments.put(type, (Collection<Object>) attachments);
        return new Response(ResponseType.SUCCESS, null);
    }
}
