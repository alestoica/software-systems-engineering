package repository;

import domain.Book;
import domain.Loan;
import domain.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoanRepository implements ILoanRepository {
    private final DBUtils utils;
    private IMemberRepository memberRepo;
    private IBookRepository bookRepo;

    public LoanRepository(DBUtils utils, IMemberRepository memberRepo, IBookRepository bookRepo) {
        this.utils = utils;
        this.memberRepo = memberRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public Loan findOne(String id) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from loans where id = ?")) {

            preparedStatement.setString(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                String idBook = result.getString("id_book");
                String idMember = result.getString("id_member");
                String loanDate = result.getDate("loan_date").toString();

                Book book = bookRepo.findOne(idBook);
                Member member = memberRepo.findOne(idMember);

                Loan loan = new Loan(member, book, loanDate);
                loan.setId(id);

                return loan;
            }
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        return null;
    }

    @Override
    public Collection<Loan> findAll() {
        Connection con = utils.getConnection();
        List<Loan> loans = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from loans");
             ResultSet result = preparedStatement.executeQuery();) {

            while (result.next()) {
                String id = result.getString("id");
                String idBook = result.getString("id_book");
                String idMember = result.getString("id_member");
                String loanDate = result.getDate("loan_date").toString();

                Book book = bookRepo.findOne(idBook);
                Member member = memberRepo.findOne(idMember);

                Loan loan = new Loan(member, book, loanDate);
                loan.setId(id);

                loans.add(loan);
            }

        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        return loans;
    }

    @Override
    public void add(Loan loan) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("insert into loans (id, id_member, id_book, loan_date values (?, ?, ?, ?)")) {

            preparedStatement.setString(1, loan.getId());
            preparedStatement.setString(2, loan.getMember().getId());
            preparedStatement.setString(3, loan.getBook().getId());
            preparedStatement.setString(4, loan.getLoanDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }
    }

    @Override
    public void delete(String id) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("delete from loans where id = ?")) {

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }
    }

    @Override
    public void update(String id, Loan loan) {

    }
}
