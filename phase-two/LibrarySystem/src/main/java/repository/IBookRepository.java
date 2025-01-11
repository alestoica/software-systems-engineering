package repository;

import domain.Book;
import domain.Member;

import java.util.Collection;

public interface IBookRepository extends IRepository<String, Book> {
    Collection<Book> findAllAvailable();
    Collection<Book> findAllLoaned(Member member);
}
