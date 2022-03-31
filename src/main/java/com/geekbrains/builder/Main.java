package com.geekbrains.builder;

import io.restassured.RestAssured;

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

        // return of the request-specification //
        // RestAssured.given()
        //        .queryParam()


    }
}
