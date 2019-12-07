# Amazon_Demo

Singleton design pattern is used along with TestNG framework.
The Java jar has been handled using Maven.

This Framework has 3 main components:

```
1. Base/Config -  Base class consists of all methods to be used to perform actions and gestures.(e.g. Click, Scrolls, SendKeys etc).
Also the appium driver initialization methods are added in configs.

2. Pages - Pages package contains page level, locators and page actions.

3. Tests - This package consists of the test class and will have the test scripts to be executed.
```

Other packages in the framework:
```
Utilities - Consists of methods to extract test data from properties file.

App - Contains the application under test
```

![Framework Overview](https://github.com/mayureshshirodkar/Amazon_Demo/blob/master/overview.png)
