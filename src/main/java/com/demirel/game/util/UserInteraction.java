package com.demirel.game.util;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static java.util.logging.Level.WARNING;

public final class UserInteraction {

    private static final Logger LOGGER = Logger.getLogger(UserInteraction.class.getName());

    private final String prompt;
    private final Pattern pattern;
    private final String warning;
    private final Scanner scanner = new Scanner(System.in);

    private UserInteraction(final String prompt, final Pattern pattern, final String warning) {
        this.prompt = prompt;
        this.pattern = pattern;
        this.warning = warning;
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public String askUserForInput() {
        print(this.prompt);

        //boolean validChoice = false;
        String userInput = "";
        while (Boolean.TRUE) {
            if (!scanner.hasNextLine()) {
                break;
            }
            userInput = scanner.nextLine().trim();

            boolean isUserInputValid = !userInput.trim().isEmpty() && pattern.matcher(userInput).matches();

            if (isUserInputValid) {
                break;
            } else {
                LOGGER.log(WARNING, "Player provided wrong input: {0}", userInput);
                print(warning);
            }

        }
        return userInput.trim();
    }

    public Integer askUserForNumberInput() {
        return Integer.valueOf(askUserForInput());
    }

    public static final class Builder {
        private String prompt;
        private String pattern;
        private String warning;

        private Builder() {
        }

        public static Builder aChoiceScanner() {
            return new Builder();
        }

        public Builder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder pattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public Builder warning(String warning) {
            this.warning = warning;
            return this;
        }

        public UserInteraction build() {
            return new UserInteraction(
                    prompt,
                    Pattern.compile(this.pattern),
                    warning
            );
        }
    }

}
