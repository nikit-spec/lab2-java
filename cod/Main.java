import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private final Scanner scanner;

    public Main(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        Scanner input = System.console() == null
                ? new Scanner(System.in, StandardCharsets.UTF_8)
                : new Scanner(System.console().reader());
        Main app = new Main(input);
        System.out.println(app);
        System.out.println("Вводите данные с клавиатуры. Необязательную часть имени можно пропустить клавишей Enter.");

        try {
            System.out.println("\n1.3 и 4.5. Имена");
            Name[] names = new Name[3];
            for (int i = 0; i < names.length; i++) {
                System.out.println("Имя " + (i + 1));
                names[i] = app.readName();
                System.out.println(names[i]);
            }
            System.out.println("Имя 4");
            System.out.println(app.readName());

            System.out.println("\n2.2. Человек с именем");
            for (Name name : names) {
                int height = app.readNumber("Рост (" + name + ")", 1);
                System.out.println(new Person(name, height));
            }

            System.out.println("\n2.3. Человек с родителем");
            System.out.println("Первый человек");
            Person first = new Person(app.readName(), app.readNumber("Рост", 1));
            System.out.println("Сын первого человека");
            Person second = new Person(app.readName(), app.readNumber("Рост", 1), first);
            System.out.println("Сын второго человека");
            Person third = new Person(app.readName(), app.readNumber("Рост", 1), second);
            System.out.println(first);
            System.out.println(second);
            System.out.println(third);

            System.out.println("\n3.3. Города");
            City a = new City(app.readLine("Название города A на схеме", true));
            City b = new City(app.readLine("Название города B на схеме", true));
            City c = new City(app.readLine("Название города C на схеме", true));
            City d = new City(app.readLine("Название города D на схеме", true));
            City e = new City(app.readLine("Название города E на схеме", true));
            City f = new City(app.readLine("Название города F на схеме", true));
            app.readRoad(a, b, true);
            app.readRoad(a, d, true);
            app.readRoad(a, f, false);
            app.readRoad(b, c, true);
            app.readRoad(c, d, true);
            app.readRoad(d, e, false);
            app.readRoad(e, f, true);
            app.readRoad(f, b, false);
            for (City city : new City[]{a, b, c, d, e, f}) {
                System.out.println(city);
            }

            System.out.println("\n4.6. Создаём человека");
            System.out.println("Первый человек");
            Person grandfather = new Person(app.readLine("Личное имя", true), app.readNumber("Рост", 1));
            System.out.println("Сын первого человека");
            Person father = new Person(app.readName(), app.readNumber("Рост", 1), grandfather);
            System.out.println("Сын второго человека");
            Person son = new Person(app.readLine("Личное имя", true), app.readNumber("Рост", 1), father);
            System.out.println(grandfather);
            System.out.println(father);
            System.out.println(son);

            System.out.println("\n5.2. Кот мяукает");
            Cat cat = new Cat(app.readLine("Имя кота", true));
            int times = app.readNumber("Число повторов во второй фразе", 1);
            System.out.println(cat);
            cat.meow();
            cat.meow(times);
        } catch (NoSuchElementException exception) {
            System.out.println("\nВвод завершён.");
        }
    }

    private String readLine(String label, boolean required) {
        while (true) {
            System.out.print(label + ": ");
            String value = scanner.nextLine().strip();
            if (!required || !value.isEmpty()) {
                return value;
            }
            System.out.println("Заполните поле.");
        }
    }

    private Name readName() {
        while (true) {
            String firstName = readLine("Личное имя", false);
            String surname = readLine("Фамилия", false);
            String patronymic = readLine("Отчество", false);
            try {
                if (!patronymic.isEmpty()) {
                    return new Name(firstName, surname, patronymic);
                }
                if (!surname.isEmpty()) {
                    return new Name(firstName, surname);
                }
                return new Name(firstName);
            } catch (IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    private int readNumber(String label, int minimum) {
        while (true) {
            String value = readLine(label, true);
            try {
                int number = Integer.parseInt(value);
                if (number >= minimum) {
                    return number;
                }
            } catch (NumberFormatException exception) {
                // После ошибки запрос повторится.
            }
            System.out.println("Введите целое число от " + minimum + " до " + Integer.MAX_VALUE + ".");
        }
    }

    private void readRoad(City from, City to, boolean twoWay) {
        String arrow = twoWay ? " ↔ " : " → ";
        int cost = readNumber("Стоимость " + from.getName() + arrow + to.getName(), 0);
        from.addRoute(to, cost);
        if (twoWay) {
            to.addRoute(from, cost);
        }
    }

    @Override
    public String toString() {
        return "Лабораторная работа № 2. Вариант 3";
    }
}
