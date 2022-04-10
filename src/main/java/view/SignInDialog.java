package view;

import model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInDialog implements EntityDialog<User> {
    @Override
    public User input() {
        Scanner scan = new Scanner(System.in);

        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,15}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        User user = new User();
        System.out.println("Enter username");
        String username = scan.next();
        user.setUsername(username);
        while (username == null) {
            if (username.length() < 2 || username.length() > 15) {
                System.out.println("Name must be between 2 and 15 chars");
                username = scan.next();
            }
        }
        System.out.println("Enter password");
        String password = scan.next();
        Matcher passwordMatcher = passwordPattern.matcher(password);
        user.setPassword(password);
        while (!user.getPassword().matches(passwordRegex)) {
            System.out.println("Password must be between 8 and 15 length, it MUST contain at least one digit, one capital letter, and one sign different than letter or digit");
            password = scan.next();
            user.setPassword(password);
        }
        user = new User(username, password);
        return user;
    }
}
