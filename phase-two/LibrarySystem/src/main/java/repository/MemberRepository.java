package repository;

import domain.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MemberRepository implements IMemberRepository {
    private final DBUtils utils;

    public MemberRepository(DBUtils utils) {
        this.utils = utils;
    }

    @Override
    public Member findOne(String id, String password) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from members where id = ? and password = ?")) {

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                String name = result.getString("name");
                String CNP = result.getString("CNP");
                String address = result.getString("address");
                String phoneNumber = result.getString("phone_number");

                Member member = new Member(password, name, CNP, address, phoneNumber);
                member.setId(id);

                return member;
            }
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        return null;
    }

    @Override
    public Member findOne(String id) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from members where id = ?")) {

            preparedStatement.setString(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                String password = result.getString("password");
                String name = result.getString("name");
                String CNP = result.getString("CNP");
                String address = result.getString("address");
                String phoneNumber = result.getString("phone_number");

                Member member = new Member(password, name, CNP, address, phoneNumber);
                member.setId(id);

                return member;
            }
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        return null;
    }

    @Override
    public Collection<Member> findAll() {
        Connection con = utils.getConnection();
        List<Member> members = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement("select * from members");
             ResultSet result = preparedStatement.executeQuery();) {

            while (result.next()) {
                String id = result.getString("id");
                String password = result.getString("password");
                String name = result.getString("name");
                String CNP = result.getString("CNP");
                String address = result.getString("address");
                String phoneNumber = result.getString("phone_number");


                Member member = new Member(password, name, CNP, address, phoneNumber);
                member.setId(id);

                members.add(member);
            }

        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }

        return members;
    }

    @Override
    public void add(Member member) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("insert into members (id, password, name, CNP, address, phone_number) values (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, member.getId());
            preparedStatement.setString(2, member.getPassword());
            preparedStatement.setString(3, member.getName());
            preparedStatement.setString(4, member.getCNP());
            preparedStatement.setString(5, member.getAddress());
            preparedStatement.setString(6, member.getPhoneNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }
    }

    @Override
    public void delete(String id) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("delete from members where id = ?")) {

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }
    }

    @Override
    public void update(String id, Member member) {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("update members set password = ?, name = ?, CNP = ?, address = ?, phone_number = ? where id = ?")) {

            preparedStatement.setString(1, member.getId());
            preparedStatement.setString(2, member.getPassword());
            preparedStatement.setString(3, member.getName());
            preparedStatement.setString(4, member.getCNP());
            preparedStatement.setString(5, member.getAddress());
            preparedStatement.setString(6, member.getPhoneNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("db error: " + e);
        }
    }
}
