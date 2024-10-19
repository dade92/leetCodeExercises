package com.myprojects;

import com.myprojects.lists.List;

public class App {
    public static void main(String[] args) {
        Person davide = new Person("Davide", "Botti");
        Person sergio = new Person("Sergio", "Botti");

        List<Person> people = new List<>(
            davide,
            sergio
        );

        System.out.println(people);

        people.removeElement(davide);

        System.out.println(people);
    }
}
