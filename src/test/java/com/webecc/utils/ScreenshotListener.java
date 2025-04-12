package com.webecc.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        try {
            Field driverField = currentClass.getClass().getDeclaredField("driver"); // statt getSuperclass()
            driverField.setAccessible(true);
            WebDriver driver = (WebDriver) driverField.get(currentClass);

            String methodName = result.getMethod().getMethodName();
            ScreenshotUtil.captureScreenshot(driver, methodName);

        } catch (Exception e) {
            System.err.println("❌ ScreenshotListener-Fehler: " + e.getMessage());
        }
    }

}
