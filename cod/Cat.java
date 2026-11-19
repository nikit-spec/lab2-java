public final class Cat {
    private final String name;

    public Cat(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("У кота должно быть имя.");
        }
        this.name = name.strip();
    }

    public void meow() {
        meow(1);
    }

    public void meow(int times) {
        if (times < 1) {
            throw new IllegalArgumentException("Число повторов должно быть положительным.");
        }
        System.out.print(name + ": мяу");
        for (int i = 1; i < times; i++) {
            System.out.print("-мяу");
        }
        System.out.println("!");
    }

    @Override
    public String toString() {
        return "кот: " + name;
    }
}
