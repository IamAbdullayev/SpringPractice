package com.abdullayev.apps.dao;

import com.abdullayev.apps.models.Person;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private final List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, 15, "Tom", "tom@email.com"));
        people.add(new Person(++PEOPLE_COUNT, 22, "Jerry", "jerry@email.com"));
        people.add(new Person(++PEOPLE_COUNT, 17, "Shelby", "shelby@email.com"));
        people.add(new Person(++PEOPLE_COUNT, 35, "Ford", "ford@email.com"));
        people.add(new Person(++PEOPLE_COUNT, 66, "Davidoff", "davidoff@email.com"));
        people.add(new Person(++PEOPLE_COUNT, 11, "Konstantin", "konstantin@email.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
//        for (Person i : people) {
//            if (id == i.getId()) {
//                return i;
//            }
//        }
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        this.people.add(person);
    }

    public void update(Person updatePerson, int id) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }

}
