package com.demirel.game.util;

import com.demirel.game.domain.HandType;

public final class RandomHandTypeSelector {

    private static RandomHandTypeSelector randomHandTypeSelector;

    private RandomHandTypeSelector() {
    }

    public static synchronized RandomHandTypeSelector getInstance() {
        if (randomHandTypeSelector != null) {
            return randomHandTypeSelector;
        }
        return randomHandTypeSelector = new RandomHandTypeSelector();
    }

    public HandType getHandType() {
        int randomInt = (int) (Math.random() * 3) + 1;
        return HandType.find(randomInt);
    }
}
