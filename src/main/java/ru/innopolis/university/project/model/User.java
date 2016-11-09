package ru.innopolis.university.project.model;

/**
 * Created by innopolis on 28.10.16.
 * Модель User
 */
public class User {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private int age;

    public User(Integer id, String email, String password, String name, int age) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
