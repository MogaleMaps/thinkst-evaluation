package com.thinkst.common;

import java.util.Random;

public class TestData {

    public String emailaddress = "testMo@thinkst.com" + getRandomAlphanumeric();
    public String username = "AutoTester" + getRandomAlphanumeric();
    public String password = "AutoTest@123!!";

    public String existingUser = "m.mapaela@gmail.com";
    public String existingPassword = "Test@123!!";
    public String articleTitle = "MoAutomationTest" + getRandomAlphanumeric();
    public String articleAbout = "This article is about Mo writing his automated tests";
    public String articleBody = "Automation testing is like hiring a robot that never sleeps, never complains, and clicks buttons faster than you can say “bug.” It’s the only coworker that actually enjoys doing the same thing over and over again — and somehow still makes you look like a genius.";

    public String articleTag = "Test Automation " + getRandomAlphanumeric();
    public String comment = "I don't have much to say here";

    public static String getRandomAlphanumeric() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            result.append(chars.charAt(rand.nextInt(chars.length())));
        }

        return result.toString();
    }
}
