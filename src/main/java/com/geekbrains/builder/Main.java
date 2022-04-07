package com.geekbrains.builder;

public class Main {

    public static void main(String[] args) {
        User user = User.builder()
                .setAge(121)
                .setName("Evpathiy")
                .setSurname("Burgomistroff")
                .setEmail("eb@gmail.com")
                .setPhone("034567")
                .setId(007)
                .setHomeAddress("Kremlin")
                .build();

    }
}
