package io.github.joshy56.economicapi.response;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 17/3/2023.
 */
public enum ResponseType {
    SUCCESS(200), FAILURE(404), NOT_IMPLENTED(504);

    private int id;
    ResponseType(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}
