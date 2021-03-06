package view;

import exception.InvalidEntityDataException;
import exception.NonexistingEntityException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoggedUser {
    public static class Option {
        private String text;
        private Command command;

        public Option(String text, Command command) {
            this.text = text;
            this.command = command;
        }

        public String getText() {
            return text;
        }

        public Command getCommand() {
            return command;
        }

        @Override
        public String toString() {
            return "MenuOption{" +
                    "text='" + text + '\'' +
                    ", command=" + command +
                    '}';
        }
    }

    public interface Command {
        String execute() throws InvalidEntityDataException, NonexistingEntityException;
    }

    public class ExitCommand implements Command {
        @Override
        public String execute() {
            return String.format("Logged out '%s'.", LoggedUser.this.title);
        }
    }

    public static class Logout implements Command {
        @Override
        public String execute() {
            return String.format("Logout");
        }
    }


    // Main class methods and attributes.
    private String title;
    private List<Option> options = List.of(new Option("Logout", new Logout()));
    private Scanner scanner = new Scanner(System.in);

    public LoggedUser() {
    }

    public LoggedUser(String title, List<Option> options) {
        this.title = title;
        var oldOptions= this.options;
        this.options = new ArrayList<>();
        this.options.addAll(options);
        this.options.addAll(oldOptions);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Homepage)) return false;

        Homepage menu = (Homepage) o;

        if (getTitle() != null ? !getTitle().equals(menu.getTitle()) : menu.getTitle() != null) return false;
        return getOptions() != null ? getOptions().equals(menu.getOptions()) : menu.getOptions() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getOptions() != null ? getOptions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", options=" + options +
                '}';
    }

    public void show() {

        while(true) {
            System.out.println(title);
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("%2d. %s%n", i + 1, options.get(i).getText());
            }
            int choice = -1;
            do {
                System.out.printf("Enter your choice (1 - %s):", options.size());
                var choiceStr = scanner.nextLine();
                try {
                    choice = Integer.parseInt(choiceStr);
                } catch (NumberFormatException ex) {
                    System.out.println("Error: Invalid choice. Please enter a valid number between 1 and " + options.size());
                }
            } while (choice < 1 || choice > options.size());
            String result = null;
            try {
                result = options.get(choice - 1).getCommand().execute();
            } catch (InvalidEntityDataException | NonexistingEntityException e) {
                System.out.println("Error: " + e.getMessage());;
            }
            System.out.println(result);
            if(choice == options.size()) { // Exit command chosen
                break;
            }
        }
    }
}