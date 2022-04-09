package view;

import model.Gender;
import model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.Gender.FEMALE;
import static model.Gender.MALE;
import static model.Role.REGISTERED_USER;
import static model.Role.RESTAURANT_OWNER;
import static model.StatusReader.ACTIVE;

public class UpdateUser{
    public static Scanner scan = new Scanner(System.in);

    public User input(User user) {

        String regex = "^(.+)@(.+)$";
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,15}$";
        Pattern pattern = Pattern.compile(regex);
        Pattern passwordPattern = Pattern.compile(passwordRegex);

        System.out.println("Enter first name");
        String firstName = scan.next();
        while(firstName.length() < 2 || firstName.length() > 15) {
                System.out.println("First name must be between 2 and 15 chars");
                firstName = scan.next();
        }
        user.setFirstName(firstName);

        System.out.println("Enter last name");
        String lastName = scan.next();
        while (lastName.length() < 2 || lastName.length() > 15) {
                System.out.println("Last name must be between 2 and 15 chars");
                lastName = scan.next();
            }
        user.setLastName(lastName);
        System.out.println("Enter email");
        String email = scan.next();
        Matcher matcher = pattern.matcher(email);
        while(!matcher.matches()) {
                System.out.println("Invalid email");
                email = scan.next();
            }
        user.setEmail(email);
        System.out.println("Enter username");
        String username = scan.next();
        while(username.length() < 2 || username.length() > 15) {
                System.out.println("Username must be between 2 and 15 chars");
                username = scan.next();
        }
        user.setUsername(username);
        System.out.println("Enter password");
        String password = scan.next();
        Matcher passwordMatcher = passwordPattern.matcher(password);
        while(!passwordMatcher.matches()) {
                System.out.println("Password must be between 8 and 15 length, it MUST contain at least one digit, one capital letter, and one sign different than letter or digit");
                password = scan.next();
        }
        user.setPassword(password);

        return user;
    }
}
