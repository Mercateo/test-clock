# Release HowTo
1. Merge your changes into the master branch
1. Execute `mvn release:prepare`
    - respond to prompts, most likely just enter the default values
    - push your local changes and remove your local `pom.xml.releaseBackup` and `release.properties`
1. Wait for the Github action to deploy the artifact and for it to be available in Maven Central
    - you can usually directly access the artifact after 10 to 20 minutes, way before it shows up when searching on [Maven Central](https://search.maven.org/)
    - see https://search.maven.org/artifact/com.mercateo/test-clock
