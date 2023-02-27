## Welcome to Trustbadge contributing guide

Thank you for investing your time in contributing in our project! Any contribution to Trustbadge library is welcome.


## Testing
The project has Gradle Managed Devices set for running instrumented tests.
All the instrumented tests can be run from the commandline:
```
./gradlew pixel2api30DebugAndroidTest
```
This uses an Android API-30 (google_api) managed device to run the tests.
The device will be automatically created and managed by gradle.
The instrumented test results will be available in the build folder under the following path:
```
library/build/outputs/managed_device_code_coverage/pixel2api30/coverage.ec
```

### Unified coverage reports
The project uses jacoco to generate coverage reports. There following command generates a unified coverage report:
```
./gradlew jacocoTestReoprt
```
The same Gradle managed device mentioned in previous section will be used for runnnig the instrumented tests.
The `.XML` and `.HTML` results will be available under the following path:
```
library/build/mergedReportDir/jacocoTestReport
```

### Screenshot tests
For generating new screenshots, the `generateFreshGolden` automates creating the screenshots and copying the under the assets folder.
```
./gradlew generateFreshGolden
```
Fresh screenshots will be copied under the following path:
```
library/src/androidTest/assets/screenshots
```