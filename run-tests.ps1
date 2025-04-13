mvn clean test -Dheadless=true

if (Test-Path "target\allure-report\history") {
    Copy-Item -Recurse -Force "target\allure-report\history" "target\allure-results\history"
}

allure generate target/allure-results --clean -o target/allure-report
allure serve target/allure-report
