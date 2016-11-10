package ru.innopolis.university.project.entity;

/**
 * Created by innopolis on 10.11.16.
 * Enum пользовательских ролей
 */
public enum Roles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_ANONIMUS("ROLE_ANONIMUS");

    private String role;

    /**
     * Конструктор Roles
     * @param role String
     */
    private Roles(final String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
