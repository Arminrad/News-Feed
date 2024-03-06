package org.feed;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class NewsItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final List<String> wordList = Arrays.asList("up", "down", "rise", "fall", "good", "bad", "success", "failure", "high", "low");
    private String headline;
    private int priority;

    public NewsItem() {
        this.headline = getRandomHeadline();
        this.priority = getBiasedRandomPriority(0, 9, 1);
    }

    private String getRandomHeadline() {
        Random random = ThreadLocalRandom.current();
        return random.ints(0, wordList.size())
                .distinct()
                .limit(3 + random.nextInt(3))
                .mapToObj(wordList::get)
                .collect(Collectors.toList()).toString();
    }

    private int getBiasedRandomPriority(double minValue, double maxValue, double peakValue) {
        double leftArea = (peakValue - minValue) * (peakValue - minValue);
        double rightArea = (maxValue - peakValue) * (maxValue - minValue);
        double totalArea = leftArea + rightArea;

        double randomValue = ThreadLocalRandom.current().nextDouble(totalArea);

        if (randomValue < leftArea) {
            return (int) (Math.sqrt(randomValue) + minValue);
        } else {
            return (int) (maxValue - Math.sqrt(totalArea - randomValue));
        }
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
