package com.demirel.game.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

@RunWith(PowerMockRunner.class)
@PrepareForTest()
public class UserInteractionTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void shouldPrintWarningOnWrongInput() {
        systemInMock.provideLines("4434"); // wrong input

        UserInteraction askForContinue = UserInteraction.Builder.aChoiceScanner()
                .prompt("test")
                .pattern("1|2")
                .warning("warning")
                .build();
        askForContinue.askUserForInput();
        assertThat(systemOutRule.getLog()).contains("warning");
    }


}
