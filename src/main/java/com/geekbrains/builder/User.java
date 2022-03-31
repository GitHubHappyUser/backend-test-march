package com.geekbrains.builder;

public class User {

    // Original Class "final" Property//
    private final long id;
    private final int age;
    private final String name;
    private final String surname;
    private final String phone;
    private final String email;
    private final String homeAddress;

    // hidden "private" constructor with the field values//
    private User(Builder builder){
        id = builder.id;
        age = builder.age;
        name = builder.name;
        surname = builder.surname;
        phone = builder.phone;
        email = builder.email;
        homeAddress = builder.homeAddress;

    }

    // "getters" for @User //
    public long getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getHomeAddress() {
        return homeAddress;
    }

    // Static Method function //
    public static Builder builder() {
        return new Builder();
    }

    // Constructor with Data Type = "static" //
    public static class Builder {
        private long id;
        private int age;
        private String name;
        private String surname;
        private String phone;
        private String email;
        private String homeAddress;

        // setter's with Data Type (value return type) = "Builder"
        // allows get Setter of every field on the Builder step by step //
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public Builder setId(long id) {
            this.id = id;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }
        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setHomeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        // passing inside the User method of the builder instance //
        public User build() {
            return new User(this);
        }

    }


    /*
     public User(long id, int age, String name, String surname, String phone, String email, String homeAddress) {
         this.id = id;
         this.age = age;
         this.name = name;
         this.surname = surname;
         this.phone = phone;
         this.email = email;
         this.homeAddress = homeAddress;
     }
    */
}
