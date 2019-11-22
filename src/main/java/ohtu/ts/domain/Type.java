package ohtu.ts.domain;

/**
 * Model of a reading tip type (e.g., book).
 * @author Joonas Häkkinen
 */
public class Type {
    int id;
    String name;

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
