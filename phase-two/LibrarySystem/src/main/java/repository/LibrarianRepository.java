package repository;

import domain.Librarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class LibrarianRepository implements ILibrarianRepository {
    private final DBUtils utils;

    public LibrarianRepository(DBUtils utils) {
        this.utils = utils;
    }

    @Override
    public Librarian findOne(String id, String password) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from librarians where id = ? and password = ?")) {

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                String name = result.getString("name");

                Librarian librarian = new Librarian(password, name);
                librarian.setId(id);

                return librarian;
            }
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        return null;
    }

    @Override
    public Librarian findOne(String id) {
        return null;
    }

    @Override
    public Collection<Librarian> findAll() {
        return null;
    }

    @Override
    public void add(Librarian librarian) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(String id, Librarian librarian) {

    }
}
