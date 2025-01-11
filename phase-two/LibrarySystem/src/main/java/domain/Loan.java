package domain;

public class Loan extends Entity<String> {
    private Member member;
    private Book book;
    private String loanDate;

    public Loan(Member member, Book book, String loanDate) {
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    @Override
    public String toString() {
        return "Loan { " +
                "id: " + id +
                ", member: " + member +
                ", book: " + book +
                ", loanDate: " + loanDate +
                " }";
    }
}
