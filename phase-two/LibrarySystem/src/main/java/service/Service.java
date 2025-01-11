package service;

import domain.Book;
import domain.Librarian;
import domain.Loan;
import domain.Member;
import repository.IBookRepository;
import repository.ILibrarianRepository;
import repository.ILoanRepository;
import repository.IMemberRepository;

import java.util.Collection;

public class Service implements IService {
    private final IMemberRepository memberRepo;
    private final ILibrarianRepository librarianRepo;
    private final IBookRepository bookRepo;
    private final ILoanRepository loanRepo;

    public Service(IMemberRepository memberRepo, ILibrarianRepository librarianRepo, IBookRepository bookRepo, ILoanRepository loanRepo) {
        this.memberRepo = memberRepo;
        this.librarianRepo = librarianRepo;
        this.bookRepo = bookRepo;
        this.loanRepo = loanRepo;
    }

    @Override
    public Member findOneMember(String id, String password) {
        return memberRepo.findOne(id, password);
    }

    @Override
    public Collection<Member> findAllMembers() {
        return memberRepo.findAll();
    }

    @Override
    public void addMember(Member member) {
        memberRepo.add(member);
    }

    @Override
    public void deleteMember(String id) {
        memberRepo.delete(id);
    }

    @Override
    public void updateMember(String id, Member member) {
        memberRepo.update(id, member);
    }

    @Override
    public Librarian findOneLibrarian(String id, String password) {
        return librarianRepo.findOne(id, password);
    }

    @Override
    public Collection<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Collection<Book> findAllAvailableBooks() {
        return bookRepo.findAllAvailable();
    }

    @Override
    public Collection<Book> findAllLoanedBooks(Member member) {
        return bookRepo.findAllLoaned(member);
    }

    @Override
    public void addBook(Book book) {
        bookRepo.add(book);
    }

    @Override
    public void deleteBook(String id) {
        bookRepo.delete(id);
    }

    @Override
    public Collection<Loan> findAllLoans() {
        return loanRepo.findAll();
    }

    @Override
    public void addLoan(Loan loan) {
        loanRepo.add(loan);
    }

    @Override
    public void deleteLoan(String id) {
        loanRepo.delete(id);
    }
}
