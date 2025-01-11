package domain;

public class Book extends Entity<String> {
    private String title, author;
    private AvailabilityStatus status;

    public Book(String title, String author, AvailabilityStatus status) {
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public AvailabilityStatus getStatus() {
        return status;
    }

    public void setStatus(AvailabilityStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book { " +
                "id: " + id +
                ", title: " + title +
                ", author: " + author +
                ", status: " + status +
                " }";
    }
}
