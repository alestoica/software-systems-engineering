package domain;

public class Librarian extends Entity<String> {
    private String password, name;

    public Librarian(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Librarian { " +
                "id: " + id +
                ", password: " + password +
                ", name: " + name +
                " }";
    }
}
