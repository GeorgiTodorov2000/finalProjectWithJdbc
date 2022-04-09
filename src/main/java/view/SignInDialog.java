package view;

import model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInDialog implements EntityDialog<User>{
    @Override
    public User input() {
        Scanner scan = new Scanner(System.in);

        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,15}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);

        System.out.println("Enter username");
        String username = scan.next();
        while(true) {
            assert username != null;
            if (!(username.length() < 2 || username.length() > 15)) break;
            System.out.println("Name must be between 2 and 15 chars");
                username = null;
        }
        System.out.println("Enter password");
        String password = scan.next();
        Matcher passwordMatcher = passwordPattern.matcher(password);
        while(!passwordMatcher.matches() || (password != null ? password.length() : 0) < 8 || password.length() > 15) {
                System.out.println("Password must be between 8 and 15 length, it MUST contain at least one digit, one capital letter, and one sign different than letter or digit");
                password = null;
        }
        User user = new User(username, password);
        return user;
    }
}
