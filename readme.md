What is this Framework?
=============================
This is a TestNG driven frameworked equipped to handle API and UI tests.
For UI we have used PAGE OBJECT MODELLING for a better maintainenece
FOR API we have used RestAssured.
JSON data is pumped to the tests automatically via TestBase

Tools Used
=============================
- TestNG 
- RestAssured
- Selenium
- Maven 
- Gson

Features Implemented
=============================
- RestAssured syntax abstraction
- Test runner auto generator
- Auto test data pump to test cases


How to execute the test ?
=============================
mvn test -Denv=dev -Dbrowser=chrome -Dgroups=ui
mvn test -Denv=dev -Dbrowser=firefox -Dgroups=api