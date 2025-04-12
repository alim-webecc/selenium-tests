package com.webecc.utils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHtmlReport implements ITestListener {

    private final StringBuilder html = new StringBuilder();

    @Override
    public void onStart(ITestContext context) {
        html.append("<html><head><title>Fehler-Screenshots</title></head><body>");
        html.append("<h1>📸 Fehlgeschlagene Tests mit Screenshots</h1><ul>");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotFile = "screenshots/" + testName + "_" + timestamp + ".png";

        ScreenshotUtil.captureScreenshot((WebDriver) result.getTestContext().getAttribute("driver"), testName);

        html.append("<li>")
                .append("<b>").append(testName).append("</b> → ")
                .append("<a href='").append(screenshotFile).append("'>Screenshot ansehen</a>")
                .append("</li>");
    }

    @Override
    public void onFinish(ITestContext context) {
        html.append("</ul></body></html>");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/screenshots-report.html"))) {
            writer.write(html.toString());
        } catch (IOException e) {
            System.err.println("❌ Fehler beim Schreiben des Screenshot-HTML-Reports: " + e.getMessage());
        }
    }
}

