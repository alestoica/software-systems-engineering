package service;

import domain.Book;
import domain.Librarian;
import domain.Loan;
import domain.Member;

import java.util.Collection;

public interface IService {
    Member findOneMember(String id, String password);
    Collection<Member> findAllMembers();
    void addMember(Member member);
    void deleteMember(String id);
    void updateMember(String id, Member member);
    Librarian findOneLibrarian(String id, String password);
    Collection<Book> findAllBooks();
    Collection<Book> findAllAvailableBooks();
    Collection<Book> findAllLoanedBooks(Member member);
    void addBook(Book book);
    void deleteBook(String id);
    Collection<Loan> findAllLoans();
    void addLoan(Loan loan);
    void deleteLoan(String id);

}
