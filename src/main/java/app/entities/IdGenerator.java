package app.entities;

public class IdGenerator {
    private static int nextId = 0;

    public static int getNextId() {
        return ++nextId;
    }
}
