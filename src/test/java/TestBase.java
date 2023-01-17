import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

    static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    @BeforeMethod
    public void startTest(Method m){
        logger.info("Start test: " + m.getName());
    }
    @AfterMethod
    public void stopTest(Method m){
        logger.info("The end of test: " + m.getName());
    }

    @AfterSuite
    public void tearDown(){
//        app.stop();
    }

}