# Test driven development workshop
This repository contains an example project for practicing test driven development. It was prepared for the INFORMATION AND COMPUTER SCIENCES department of the UNIVERSITY OF HAWAI‘I AT MĀNOA.

This project is java based and uses [gradle](https://gradle.org/) as a build tool. To run the proejct locally, you need a copy of a java runtime environment (JRE). I created this project using java 17, but other versions _may_ work as well.

You can run the tests of this project by doing `./gradlew test`.

# Contents
- [Test driven development workshop](#test-driven-development-workshop)
- [Contents](#contents)
- [Unit testing](#unit-testing)
  - [What is unit testing?](#what-is-unit-testing)
    - [Characteristics of a good test](#characteristics-of-a-good-test)
      - [Either pass or fail](#either-pass-or-fail)
      - [Return the same results every time](#return-the-same-results-every-time)
      - [Exercise a variety of inputs](#exercise-a-variety-of-inputs)
  - [Why unit test?](#why-unit-test)
    - [Safety net for refactoring](#safety-net-for-refactoring)
    - [Documentation](#documentation)
  - [How to run tests](#how-to-run-tests)
  - [Unit testing exercise](#unit-testing-exercise)
- [Test driven development](#test-driven-development)
  - [What is TDD](#what-is-tdd)
  - [Why TDD](#why-tdd)
  - [TDD Exercise](#tdd-exercise)
- [Closing](#closing)

# Unit testing
## What is unit testing?
I asked ChatGPT "*what is unit testing*" and this is what is said:

> Unit testing is a method in software engineering where individual components of a software program are tested to determine if they work correctly. **A 'unit' is the smallest testable part of any software** and can be a **function**, procedure, method, or class in object-oriented programming.
>
> Unit testing is typically **automated**. Tests are written and executed by software developers to ensure that code meets its design and behaves as expected. The main goal of unit testing is to **isolate each part of the program** and **show that these individual parts are correct** in terms of **requirements and functionality**.
>
> Key principles of unit testing include:
> 1. Test Single Behavior: Each unit test should **check a single function of the system in isolation**.
> 2. Self-checking: The test should be fully automated, and the results should be binary, **either pass or fail**.
> 3. Repeatable and Consistent: The test should **return the same results every time**, no matter how many times it is run.
>
> Unit testing provides many benefits, including helping to find and fix bugs early in the development cycle, and facilitating changes and simplifying integration, which leads to a better design of the software and a more maintainable codebase.

This is a pretty verbose response and maybe too dense for someone who is unfamilar with testing. I think Wikipedia's definition is a bit more consumable:

> Unit testing is a software testing method by which individual units of source code ...are tested to determine whether they are fit for use.
>
> Wikipedia https://en.wikipedia.org/wiki/Unit_testing

In essence, a program that checks the output of another program and determines if that output is "correct."

Here is an example of a simple test that tests the behavior of an addition function [(source code)](src/main/java/tdd_workshop/unit_testing/example/Add.java):
```java
public int add(int number1, int number2){
    return number1 + number2;
}
@Test
public void testAdd(){
  int number1 = 1;
  int number2 = 2;
  int expectedSum = 3; // 1 + 2 = 3
  int actualSum = add(number1, number2);
  assertEquals(expectedSum, actualSum);
}
```
The test asserts that the expected sum of the numbers matches the actual sum. If the values are different, the test will fail.

How does this benefit us? Consider this very simple bug:
```java
public int add(int number1, int number2){
    return number1 + number1; // I added "number1" twice on accident
}
```
This bug could be pretty easy to go unnoticed visually, but our unit test catches it.
```
org.opentest4j.AssertionFailedError: expected: <3> but was: <2>
        at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
        at org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
        at org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
        at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
        at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
        at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:531)
        at src.test.java.tdd_workshop.AddTests.testAdd(AddTests.java:15)
```



### Characteristics of a good test
There are several things about ChatGPT's response that I like, because it describes characteristics of a "good" test. This is by no means an "official" definition of what makes a good test. It's just an analysis of the above response along with some commentary.

#### Either pass or fail
Sometimes I'll see people write tests that don't actually have any sort of assertion in them, and they'll rely on print statements to tell them about the state of the application. A good test will either pass or fail, allowing the test runner to automatically determine if the underlying behavior of the application is correct.

For example:
```java
@Test
public void badTest(){
    // this test is bad because it will always "pass."
    // a human needs to manually inspect the log to see
    // if the program behavior is actually correct
    String result = getResult();
    System.out.print(result);
}
@Test
public void goodTest(){
    // this test is good because it will automatically
    // ensure the expected result matches the actual result.
    // no manual human inspection is required for this test.
    String expectedResult = "the result I expect";
    String actualResult = getResult();
    Assert.equals(expectedResult, actualResult);
}
```

#### Return the same results every time
A good test will always behave exactly the same each time you run it. Sometimes it can be tempting to write tests that depend on a certain state existing (such as records in a database). However in my experience this leads to very flaky and complicated tests that are prone to failure from poor environment setup rather than actual code correctness.

#### Exercise a variety of inputs
ChatGPT didn't mention this, but I believe it's a very important characteristic of a good unit test. Let's revist the example before with the addition bug:
```java
public int add(int number1, int number2){
    return number1 + number1; // I added "number1" twice on accident
}
@Test
public void testAdd(){
  int number1 = 1;
  int number2 = 2;
  int expectedSum = 3; // 1 + 2 = 3
  int actualSum = add(number1, number2);
  assertEquals(expectedSum, actualSum);
}
```
We got LUCKY that our test caught the bug. The bug adds the first number twice, which means if the two input numbers are the same, the bug would go undetected. For example, consider this test:
```java
@Test
public void testAddTwoOfTheSameNumber(){
  int number1 = 2;
  int number2 = 2;
  int expectedSum = 4; // 2 + 2 = 4
  int actualSum = add(number1, number2);
  assertEquals(expectedSum, actualSum);
}
```
In the case of the bug where we add the same number twice, the above test would happily pass without noticing the bug. This is why it's important to include a variety of inputs in our tests, to ensure we're covering lots of different possibilities. The more inputs we exercise, the higher chance of our test catching bugs.

## Why unit test?
Hopefully the previous example with the double addition bug was enough to convince you that there's value to unit testing. But if not, I asked ChatGPT *"why should I unit test my code"* and this is what it said:

> Unit testing is crucial in software development for a variety of reasons:
>
> 1. Improve the Quality: By writing tests for individual components, you **ensure that each part of your software works as intended**, leading to an overall increase in the software quality.
> 2. Easy Refactoring: Unit tests provide **a safety net that allows developers to modify and improve code without fear of breaking existing functionality**. This results in cleaner and more efficient code.
> 3. Early Bug Detection: **Bugs and issues are detected** and fixed early during the development stage which saves time and reduces costs.
> 4. Simplify Integration: When each individual component is tested thoroughly, integrating these components becomes less error-prone and simpler.
> 5. **Documentation: Unit tests can serve as a form of documentation. A new developer can look at the unit tests to understand what a particular function is supposed to do and how it behaves.**
> 6. Promote Good Design Principles: Writing testable code often leads to the use of good design principles such as SOLID, leading to better, more maintainable code.
> 7. Reduce Debugging Time: When a test fails, it clearly indicates which part of the code has issues, thus reducing the time spent on debugging.
> 8. Facilitate DevOps practices: Unit tests are fundamental for Continuous Integration and Continuous Deployment, ensuring that newly integrated code does not break the existing codebase.
> 
> By embracing unit testing, you make your development process more efficient, less error-prone, and you create a safety net that will allow you to continue to maintain and improve your software in the long run.

### Safety net for refactoring
There's a varierty of good points in ChatGPT's response. In particular, my favorite point is this one:
> Easy Refactoring: Unit tests provide a safety net that allows developers to modify and improve code without fear of breaking existing functionality. This results in cleaner and more efficient code.

Have you ever written code that works, and then as you continue coding you discover that you did something to break the code? Unit testing and good source control can eliminate this problem. Your unit tests prove that the code works at a particular point in time. As you make changes, it's easy to identify exactly which change broke the code.

This may not seem like a very big deal when *you* are the one writing all the code, but in a professional setting, you are almost certainly working among a team of other engineers. Even with good communication amongst engineers, it's extremely easy to make a mistake and break someone else's code. Unit tests are often the only line of defense against such accidents.

**Real world example: someone else breaks your code**
Consider a situation where your teammate breaks the code you worked hard to make. You're both busy and the teammate doesn't really understand your code in the first place, so it's easier for you to just go fix it yourself rather than trying to explain it to them. This can be super frustrating. But consider the viewpoint that it's not _their fault_ for breaking it; it's ***your fault*** for leaving your code unprotected, allowing them to break it.

### Documentation
Another point that ChatGPT makes is this:
> Documentation: Unit tests can serve as a form of documentation. A new developer can look at the unit tests to understand what a particular function is supposed to do and how it behaves.

Revisiting the scenario of the teammate breaking your code, it's very likely that they broke your code because they didn't understand it. Unit tests are the ultimate form of documentation for your code because they are compliled examples of how to use your code. And because they are unit tests, they are guaranteed to work.

Have you tried copy/pasting the code examples I've included in this document into actual java programs? They **_probably_** work. I tried to make sure they do. But they're ultimately just part of a text document. They might have once worked but as I made the presentation they may have become broken. The actual tests in this project that get compiled and run though, those are **guaranteed** to work, because they are actual runnable code.

## How to run tests
When we're ready to run our tests, we can use either an IDE test runner, or the `gradle` CLI using the `./gradlew clean test` command. If our tests are successful, we should see an output like this:
```
❯ ./gradlew clean test

BUILD SUCCESSFUL in 1s
4 actionable tasks: 4 executed
```
Gradle will also generate a report for the test executions. It will store the report in `build/reports/tests/test/index.html`. The report will explain the tests that were run along with the results of each test.

## Unit testing exercise
Let's do a hands-on collaborative exercise. Let's write unit tests for [this existing function](src/main/java/tdd_workshop/unit_testing/exercise/Factorial.java).
```java
public static int factorial(int n) {
  if (n < 0) {
    throw new IllegalArgumentException("Input must be a non-negative number");
  } else if (n == 0) {
    return 1;
  } else {
    int result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i;
    }
    return result;
  }
}
```
The source code for this function can be found in [src/main/java/tdd_workshop/Factorial.java](src/main/java/tdd_workshop/Factorial.java).

Let's add our unit tests to [src/test/java/tdd_workshop/unit_testing/exercise/FactorialTests.java](src/test/java/tdd_workshop/unit_testing/exercise/FactorialTests.java).

A "solution" file is available in the `solution` subpackage [src/test/java/tdd_workshop/unit_testing/exercise/solution/FactorialTests.java](src/test/java/tdd_workshop/unit_testing/exercise/solution/FactorialTests.java).

See [#how-to-run-tests](#how-to-run-tests) for info on how to run the tests.
# Test driven development
## What is TDD
I asked ChatGPT "what is test driven development" and am pretty satisfied with the answer it gave back:
> Test-Driven Development (TDD) is a software development methodology where **tests are written before writing the bare minimum of code required for the test to be fulfilled**. The goal is to improve the quality of the code and design, ensure correctness and resilience against bugs.
>
> The TDD process comprises of the following steps, commonly known as **Red-Green-Refactor cycle**:
> 
> 1. Write a Test: Start by **writing a very small test for a function that does not yet exist**. At this point, the test will fail because there's no code implemented.
> 2. Make the Test Pass: **Write the minimum amount of code necessary to make the test pass**.
> 3. Refactor Code: Once the test is passing, **you can make changes without worrying about breaking anything**. The scope for refactoring can be improving the code readability, removing redundancies, etc. If anything is broken, it's instantly caught by running the failing test.
> 
> Repeat these steps for every new feature being developed. This way, TDD ensures each part of the functionality is well-tested, working correctly, and integrated continually, resulting in robust and high-quality software.

## Why TDD
**TODO** cite some studies

When asked "why should I use TDD," this is what chatGPT came up with a number of reasons, but the majority of those reasons were really just related to unit testing in general, rather than TDD specifically. It did however say this:
> Better Software Design: TDD encourages modular, flexible, and maintainable code. The process of writing tests often leads to more modular design, which makes the application easier to understand, modify and maintain.

Personally, I wrote unit tests for years in my career without employing TDD. I wrote production code, manually ran it to see if it worked, then went back after the fact and hacked my tests into place. It wasn't until 5 years into my career that I finally embraced TDD. And after using it for the past 3 years, I find myself very much in agreement with ChatGPT's claim.

My personal experience with writing tests after the code is that **a test written after the code is frequently _less_ powerful than a test written before the code**. In my opinion, there's several possibile reasons for this:
- the code might not have been written in a test-friendly way and so the developer has to add a **test-only entrypoint** to the code rather than using the entrypoint that the production code would use
- if the code is tightly coupled with other modules, it's really hard to create meaningful tests without creating a **super complex test**
- honest opinion: it's really hard to be disciplined enough to go back and write really good quality tests when the production code is complete. **It's MUCH easier to write a few quick tests and deliver the code**

TLDR: writing meaningful tests after the code is hard. Both literally and behaviorally.

I personally feel that the quality of my code drastically increased once I started using TDD. I also think that TDD helps to game-ify my coding experience. **TDD makes coding more fun for me**, and more clear what I should focus on when I'm in a sea of code.
## TDD Exercise
Let's do a hands-on TDD exercise.

**Objective**: create a simple calculator function that reads a calculation string and returns the result of the expression

**Requirements:**
- support ADD/SUBTRACT/MULTIPLY/DIVIDE operations
- support parenthesis
- inputs and outputs should be whole numbers
  - for the case of division, round the result down
- support negative numbers

**Examples:**
- `1+2` -> `3`
- `1+2-1` -> `2`
- `2-1` -> `1`
- `2-2+1` -> `1`
- `3-4*4/8+(2*2)` -> `-3`

**Rules:**
1. We are not allowed to write any production code unless it is to make a failing unit test pass.
2. We are not allowed to write any more of a unit test than is sufficient to fail; and compilation failures are failures.
3. We are not allowed to write any more production code than is sufficient to pass the one failing unit test.
# Closing