public final class Person {
    private final Name name;
    private final int height;
    private final Person father;

    public Person(String firstName, int height) {
        this(new Name(firstName), height, null);
    }

    public Person(String firstName, int height, Person father) {
        this(new Name(firstName), height, father);
    }

    public Person(Name name, int height) {
        this(name, height, null);
    }

    public Person(Name name, int height, Person father) {
        if (name == null || height <= 0) {
            throw new IllegalArgumentException("Укажите имя и положительный рост.");
        }
        this.father = father;
        this.name = this.father == null ? name : name.withFather(this.father.name);
        this.height = height;
    }

    @Override
    public String toString() {
        return name + ", " + height;
    }
}
