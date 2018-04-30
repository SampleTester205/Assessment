# Assessment
Two Different test frame works have been uploaded. A JS Protractor framework is located in the SampleTest folder and a Java Selenium framework to the SampleJavaTests folder. For both frameworks, I used the Chrome dev tools to inspect page elements to look at accessor options to use in the tests. The main reasons I have seen UI automation be unstable are asynchronous page loading and constant changes to the page element locators. Test frameworks should make changing these easier to maintain by using a page object type model and use specific locators like ids or very specific xpath when no other locator is usable. I have found that the best way to debug is using a good reporting library to tie errors with screenshots and wrapping bad errors with helper methods to log usable errors messages.   

The sample TestCase I wrote is in the TestCase.txt file and the sample BugReport is in the BugReport.txt.

SQL Test questions are located in the SQLTest.txt.

Sample Postman collection is uploaded as SWAPI API.postman_collection.json.

# JS Protractor Tests
I chose the Protractor flavor of Selenium because it looked like the main app at https://shop.shipt.com/ was an angular based site so I would have more accessor options using Protractor. Also, a JS based framework will bundle nicely in the web app project repo. I used the protractor-jasmine2-html-reporter as the reporting library to tie the screenshots with assertion errors. Report is being generated at path 'target/screenshots'. Currently one test is designed to fail with a bad assertion to show what the errors look like.

To run tests, run the command below from the project folder:
npm test


# Java Selenium Tests
I also did a sample framework in Java because that is what we use at my current position. We chose Java because the tests were not initially intended to be bundled with the web app project and also needed the data to be set up in Excel so the tests could be ran by Business Analysts on client build outs. We were much more familiar with how to do that in Java at the time so it made sense at the time. For Java, we used Extent Reports as our logger and test report tool. Tests can be found at 'ExtentReport' inside the project folder. One test is designed to fail so a screen shot and error can be seen.

To run tests, run the command below in the project folder:
./gradlew clean test

