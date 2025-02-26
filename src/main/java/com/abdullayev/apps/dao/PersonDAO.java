package com.abdullayev.apps.dao;

import com.abdullayev.apps.PersonMapper;
import com.abdullayev.apps.models.Person;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {
    private static int COUNT = 0;

    private final JdbcTemplate jdbcTemplate;
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person show(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM person WHERE id = ?",
                    new BeanPropertyRowMapper<>(Person.class),
                    id // Передаём параметр без массива
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // Если запись не найдена, возвращаем null
        }
    }

    public void save(Person person) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO person VALUES (?, ?, ?, ?)",
                    ++COUNT, person.getName(), person.getAge(), person.getEmail());

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Person updatePerson, int id) {
        try {
            jdbcTemplate.update(
                    "UPDATE person SET name=?, age=?, email=?",
                    updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail());

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            jdbcTemplate.update(
                    "DELETE FROM person WHERE id=?",
                    id);

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
