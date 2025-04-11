package com.webecc;

import org.testng.TestNG;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class LoopTest {
//    @Test(priority = 1)
    public void wiederholeTestSuite() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Durchlauf: " + i);

            TestNG testng = new TestNG();
            List<String> suites = new ArrayList<>();
            suites.add("testng.xml");
            testng.setTestSuites(suites);
            testng.run();
        }
    }
}
