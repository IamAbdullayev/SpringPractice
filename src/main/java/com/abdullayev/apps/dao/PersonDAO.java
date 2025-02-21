package com.abdullayev.apps.dao;

import com.abdullayev.apps.models.Person;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Jerry"));
        people.add(new Person(++PEOPLE_COUNT, "Shelby"));
        people.add(new Person(++PEOPLE_COUNT, "Ford"));
        people.add(new Person(++PEOPLE_COUNT, "Davidoff"));
        people.add(new Person(++PEOPLE_COUNT, "Konstantin"));
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


}
