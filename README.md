# Collections
_[Majksa Commons](https://github.com/majksa-commons)_

Java library improving your experience with working with collections in java.

## Summary
1. [Installation](#installation)

## Installation
Make sure to replace `%version%` with the latest version number or a commit hash, e.g. `1.0.0`.
More info [HERE](https://jitpack.io/#majksa-commons/collections)

###  Maven
Register the repository
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
Now add the dependency itself
```xml
<dependency>
    <groupId>com.github.majksa-commons</groupId>
    <artifactId>collections</artifactId>
    <version>%version%</version>
</dependency>
```
###  Gradle
Register the repository
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```
Now add the dependency itself
```gradle
dependencies {
    implementation 'com.github.majksa-commons:collections:%version%'
}
```