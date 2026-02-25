## insider-ui-test
UI test automation framework for Insider One Careers website built with Java 21, JUnit 5, Selenium WebDriver, Gradle, and Allure Reports.

The framework validates the QA Careers flow including:
- Home page validation
- Filtering jobs by Location and Department
- Verifying job card details
- Redirecting to Lever application page

---

### Prerequisites

Before running the tests locally, make sure you have:

- **Java 21** installed
- **Git** installed
- **Chrome** browser installed
- **Firefox** browser installed
- **Allure CLI** installed locally (optional but recommended to view reports):

  **MacOS (via Homebrew):**
   ```bash
   brew install allure
   ```
  **Windows (via Scoop or Chocolatey):**
   ```bash
   scoop install allure
   ```
  or
   ```bash
   choco install allure
   ```
  **Verify installation:**
   ```bash
   allure --version
   ```
---

### Quick Start

1. Clone the repo:
```bash
git clone https://github.com/staserbin/insiderone-ui-test.git cd insiderone-ui-test
```
2. Make Gradle wrapper executable (Linux/macOS):
```bash
chmod +x gradlew
```
3. Run all tests (default: Chrome):
```bash
./gradlew clean test
```
4. Run tests in Firefox:
```bash
./gradlew clean test -Dbrowser=firefox
```
5. Generate Allure report:
```bash
./gradlew allureReport
./gradlew allureServe
```