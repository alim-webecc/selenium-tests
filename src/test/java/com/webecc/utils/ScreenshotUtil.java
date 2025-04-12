package com.webecc.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static void captureScreenshot(WebDriver driver, String testName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Path destination = Path.of("target", "screenshots", testName + "_" + timestamp + ".png");
            Files.createDirectories(destination.getParent());
            Files.copy(screenshot.toPath(), destination);
            System.out.println("📸 Screenshot gespeichert unter: " + destination.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Screenshot konnte nicht erstellt werden: " + e.getMessage());
        }


    }
}
