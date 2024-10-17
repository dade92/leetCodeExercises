package com.myprojects;

import com.myprojects.lists.List;

public class App {
    public static void main(String[] args) {
        List<Person> people = new List<>(
            new Person("Davide", "Botti"),
            new Person("Sergio", "Botti")
        );

        System.out.println(people);
    }

}
