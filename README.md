[![Build Status](https://travis-ci.org/AshleyByeUK/tictactoe-java-graphical.svg?branch=master)](https://travis-ci.org/AshleyByeUK/tictactoe-java-graphical)
[![codecov](https://codecov.io/gh/AshleyByeUK/tictactoe-java-graphical/branch/master/graph/badge.svg)](https://codecov.io/gh/AshleyByeUK/tictactoe-java-graphical)

# tictactoe-java-graphical

## Introduction

A game of [TicTacToe](https://github.com/AshleyByeUK/tictactoe-java-graphical) that can be played with a graphical user interface. Features include Human vs Human,
Human vs Computer and Computer vs Computer modes. The computer player can be configured for easy and hard difficulties.

![Graphical Game Play Screenshot](images/graphical-screenshot.png)

## Requirements

This project was written using OpenJDK 11. To run this project, you will need to have a compatible version of the
JDK installed. The JDK can be obtained from the following links:

- [OpenJDK 11](https://jdk.java.net/11/)

You can also use your operating system's native or third-party package managers to obtain the JDK. Whilst this game 
has not been tested with older versions of Java, you may find that versions as old as JDK 8 are sufficient.

## To Play

There are several ways that you can play TicTacToe. First, you'll need to clone this repository:

`git clone git@github.com:AshleyByeUK/tictactoe-java-graphical.git`

You can then use Gradle to run the game or to build a JAR file. The following sections explain how to do this and
assume you are in the root directory of the project.

### Run with Gradle

To run with Gradle, execute the following command:

`./gradlew run`

### Run as a JAR file

To build a JAR file, execute the following command:

`./gradlew jar`

This will build a JAR file in the root directory of the project. You can then run the JAR file as follows:

`java -jar build/tictactoe-java-graphical.jar`


## Testing

The test suite can be run with the `test` task. To run all tests with Gradle:

```
./gradlew test
```

It can also generate code coverage reports:

```
./gradlew test jacocoTestReport
```

Test reports are generated in both XML and HTML format and are located in the following directory, relative to the
project root directory:

```
build/reports/jacoco/test
```

To open the HTML test reports, type the following (on macOS):

```
open build/reports/jacoco/test/html/index.html
```

## Cleaning Up

You can clean up the files and directories created by Gradle with the following command:

`./gradlew clean`
