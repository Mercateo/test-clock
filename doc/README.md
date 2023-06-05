# Release HowTo
1. Merge your changes into the master branch
1. Execute `mvn release:prepare`
    - prompts you for a tag, choose a SemVer conform tag that reflects the changes you made
        - take care of second prompt for SCM. by default it will prepend `sqs-utils-`. you have to set set this manually
        ```
        What is SCM release tag or label for "sqs-utils"? (com.mercateo.sqs:sqs-utils) sqs-utils-0.7.1: : 0.7.1
        ```
    - creates a new commit with the new tag
    - tags the new commit
    - creates another commit, which updates version to the next snapshot
    - commits everything to the Github repo
1. Wait for the Github action to deploy the artifact and for it to be available in Maven Central
    - you can usually directly access the artifact after 10 to 20 minutes, way before it shows up when searching on [Maven Central](https://search.maven.org/)
    - i.e. `https://search.maven.org/artifact/com.mercateo.sqs/sqs-utils/X.Y.Z/jar` where `X.Y.Z` is the version that you just released
