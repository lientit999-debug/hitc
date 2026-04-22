package com.example.hitc;

public class UserManager {
    public static String currentName = "Nguyễn Viết Lộc";
    public static String currentEmail = "user@gmail.com";
    public static String currentPassword = "123456";

    public static void register(String name, String email, String password) {
        currentName = name;
        currentEmail = email;
        currentPassword = password;
    }

    public static boolean login(String email, String password) {
        return currentEmail.equals(email) && currentPassword.equals(password);
    }

    public static boolean changePassword(String oldPass, String newPass) {
        if (currentPassword.equals(oldPass)) {
            currentPassword = newPass;
            return true;
        }
        return false;
    }
}