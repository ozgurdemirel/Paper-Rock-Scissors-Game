package com.demirel.game.util;

public final class MessageResource {

    public static final String GAME_LOOP_EXIT_OR_CONTINUE_MESSAGE = "Enter 4 continue , 5 for exit";

    public static final String GAME_LOOP_EXIT_OR_CONTINUE_MESSAGE_WARNING = "Input has to be numeric! and you can only type 4, 5!";

    public static final String WHICH_TYPE_OF_HAND_QUESTION = "" +
            "Please enter your choice : \n" +
            "For Rock enter 1\n" +
            "For Paper enter 2\n" +
            "For Scissor enter 3\n";

    public static final String HAS_TO_BE_NUMERIC_MUST_BE_1_2_3 = "Input has to be numeric! and you can only type 1, 2 or 3!";

    public static final String CONGRATULATION_MESSAGE = "Congratulations you won!";

    public static final String X_BEATS_X = "%s beats %s";

    public static final String LOST_MESSAGE = "You  lost! :(";

    public static final String DRAW_MESSAGE = "DRAW! :-|";

    public static final String HAND_STATE_MESSAGE = "Your hand is %s,\nComputer's hand is %s";

    private MessageResource() {
    }
}
