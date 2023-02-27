# Contributing

Welcome to Trustbadge contributing guide, and thank you for investing your time in contributing to our project! Any contribution to the Trustbadge library is welcome.
We would love your input! We also want to make contributing to this project as easy and transparent as possible, whether it's:

- Reporting a bug
- Discussing the current state of the code
- Submitting a fix
- Proposing new features
- Becoming a maintainer

## We develop with GitHub
We use GitHub to host code, to track issues and feature requests, as well as accepting pull requests.

## Any contributions you make will be under the project licence
In short, when you submit code changes, your submissions are understood to be under the same licence that covers the project. You are welcome to contact the maintainers if that's a concern.

## Report bugs using GitHub's issues
We use GitHub issues to track public bugs. Report a bug by opening a new issue, it's that easy!

## Write bug reports with detail, background, and sample code

**Great Bug Reports** tend to have:

- A quick summary and/or background
- Steps to reproduce
    - Be specific.
    - Give sample code if you can.
- What you expected would happen
- What actually happens
- Notes (possibly including why you think this might be happening, or stuff you tried that didn't work)

People *love* thorough bug reports. We are not even kidding.

To make your life easier there is also a handy template available so feel free to use it.

> ATTENTION: If you DONT provide steps to reproduce the ticket will be closed WITHOUT FURTHER NOTICE!

## Licence
By contributing, you agree that your contributions will be licensed under the project's licence.

## Developer Certificate of Origin
Every external contributor needs to sign commits with a valid DCO.

This is done by adding a Signed-off-by line to commit messages.

```
This is my commit message
Signed-off-by: Random J Developer <random@developer.example.org>
```

Git even has a -s command line option to append this automatically to your commit message:

```
git commit -s -m 'This is my commit message'
```

## Testing
The project has Gradle Managed Devices set for running instrumented tests.
All the instrumented tests can be run from the command line:
```
./gradlew pixel2api30DebugAndroidTest
```
This task uses an Android API-30 (google_api) managed device to run the tests.
The device will be automatically created and managed by Gradle.
The instrumented test results will be available in the build folder under the following path:
```
library/build/outputs/managed_device_code_coverage/pixel2api30/coverage.ec
```

### Unified coverage reports
The project uses Jacoco to generate coverage reports. There the following command generates a unified coverage report:
```
./gradlew jacocoTestReoprt
```
The same Gradle managed device mentioned in previous section will be used for runnnig the instrumented tests.
The `.XML` and `.HTML` results will be available under the following path:
```
library/build/mergedReportDir/jacocoTestReport
```

### Screenshot tests
For generating new screenshots, the `generateFreshGolden` automates creating the screenshots and copying them under the assets folder.
```
./gradlew generateFreshGolden
```
The fresh screenshots will be copied under the following path:
```
library/src/androidTest/assets/screenshots
```