package org.feed;

import java.util.Scanner;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sending news frequency in seconds: ");
        long frequency = scanner.nextLong();
        Timer timer = new Timer();
        timer.schedule(new Feeder.SendTask(), 0, frequency * 1000);
    }
}