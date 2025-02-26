package com.abdullayev.apps.dao;

import com.abdullayev.apps.models.Person;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/people_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Roma.251101";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM person")) {

            while(resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public Person show(int id) {
        Person person = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM person WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    public void save(Person person) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO person VALUES (1, ?, ?, ?)")) {

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void update(Person updatePerson, int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE person SET name=?, age=?, email=? WHERE id=?")) {

            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM person WHERE id=?")) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
