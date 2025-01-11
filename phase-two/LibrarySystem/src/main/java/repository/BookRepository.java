package repository;

import domain.AvailabilityStatus;
import domain.Book;
import domain.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final DBUtils utils;

    public BookRepository(DBUtils utils) {
        this.utils = utils;
    }

    @Override
    public Collection<Book> findAllAvailable() {
        Connection con = utils.getConnection();
        List<Book> books = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from books where status = 'AVAILABLE'");
             ResultSet result = preparedStatement.executeQuery();) {

            while (result.next()) {
                String title = result.getString("title");
                String id = result.getString("id");
                String author = result.getString("author");
                AvailabilityStatus status = AvailabilityStatus.valueOf(result.getString("status"));

                Book book = new Book(title, author, status);
                book.setId(id);

                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        System.out.println(books.size());

        return books;
    }

    @Override
    public Collection<Book> findAllLoaned(Member member) {
        Connection con = utils.getConnection();
        List<Book> books = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from books b inner join loans l on b.id = l.id_book where l.id_member = ?")) {

            preparedStatement.setString(1, member.getId());
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String title = result.getString("title");
                String id = result.getString("id");
                String author = result.getString("author");
                AvailabilityStatus status = AvailabilityStatus.valueOf(result.getString("status"));

                Book book = new Book(title, author, status);
                book.setId(id);

                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        return books;
    }

    @Override
    public Book findOne(String id) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from books where id = ?")) {

            preparedStatement.setString(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                String title = result.getString("title");
                String author = result.getString("author");
                AvailabilityStatus status = AvailabilityStatus.valueOf(result.getString("status"));

                Book book = new Book(title, author, status);
                book.setId(id);

                return book;
            }
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        return null;
    }

    @Override
    public Collection<Book> findAll() {
        Connection con = utils.getConnection();
        List<Book> books = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from books");
             ResultSet result = preparedStatement.executeQuery();) {

            while (result.next()) {
                String title = result.getString("title");
                String id = result.getString("id");
                String author = result.getString("author");
                AvailabilityStatus status = AvailabilityStatus.valueOf(result.getString("status"));

                Book book = new Book(title, author, status);
                book.setId(id);

                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        return books;
    }

    @Override
    public void add(Book book) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("insert into books (id, title, author, status) values (?, ?, ?, ?)")) {

            preparedStatement.setString(1, book.getId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getStatus().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }
    }

    @Override
    public void delete(String id) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("delete from books where id = ?")) {

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }
    }

    @Override
    public void update(String id, Book book) {

    }
}
