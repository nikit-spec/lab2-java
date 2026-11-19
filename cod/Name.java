public final class Name {
    private final String firstName;
    private final String surname;
    private final String patronymic;

    public Name(String firstName) {
        this(firstName, "", "");
    }

    public Name(String firstName, String surname) {
        this(firstName, surname, "");
    }

    public Name(String firstName, String surname, String patronymic) {
        this.firstName = clean(firstName);
        this.surname = clean(surname);
        this.patronymic = clean(patronymic);
        if (this.firstName.isEmpty() && this.surname.isEmpty() && this.patronymic.isEmpty()) {
            throw new IllegalArgumentException("Укажите хотя бы одну часть имени.");
        }
    }

    private static String clean(String part) {
        return part == null ? "" : part.strip();
    }

    public Name withFather(Name fatherName) {
        String family = surname.isEmpty() ? fatherName.surname : surname;
        String middle = patronymic;
        if (middle.isEmpty() && !fatherName.firstName.isEmpty()) {
            middle = fatherName.firstName + "ович";
        }
        return new Name(firstName, family, middle);
    }

    @Override
    public String toString() {
        String fullName = (surname + " " + firstName).trim();
        return (fullName + " " + patronymic).trim();
    }
}
