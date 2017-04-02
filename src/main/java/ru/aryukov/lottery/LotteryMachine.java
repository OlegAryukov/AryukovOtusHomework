package ru.aryukov.lottery;

import java.util.*;

/**
 * Lottery machine class.
 * Created by tully.
 */
class LotteryMachine {
    private final List<String> emails;
    private final int winnersCount;
    private long seed = 0;

    /**
     * Method for variable initializing.
     * @param emails this emails.
     * @param winnersCount count of winners.
     */
    LotteryMachine(List<String> emails, int winnersCount) {
        this.emails = emails;
        this.winnersCount = Math.min(winnersCount, emails.size());
    }

    /**
     * Method for getting list of winners.
     * @return List<String> list of winners.
     */
    List<String> draw() {
        System.out.println("Draw for the seed: " + seed);
        Random rnd = new Random(seed);
        Set<String> winners = new HashSet<>();
        while (winners.size() < winnersCount) {
            int index = rnd.nextInt(emails.size());
            System.out.println("Ball: " + index);
            winners.add(emails.get(index));
        }
        return new ArrayList<>(winners);
    }

    /**
     * Method for setting seed.
     * @param seed random long seed.
     * @return seed.
     */
    LotteryMachine setSeed(long seed) {
        this.seed = seed;
        return this;
    }

    /**
     * Method for setting seed.
     * @param str random String seed.
     * @return seed.
     */
    LotteryMachine setSeed(String str) {
        this.seed = str.hashCode();
        return this;
    }
}
