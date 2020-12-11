package ru.dabutskikh.questionnaires.model;

public enum Permission {

    QUESTIONNAIRES_READ("questionnaires:read"),
    QUESTIONNAIRES_WRITE("questionnaires:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
