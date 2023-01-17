package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
   //Command Line :cd C:\QA_Auto_Projects\ILCarro_QA17_web
    //gradlew clean -Ptraget=config2 tests
    //listener$Screenshot

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    String browser;

    //    WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser user;
    HelperCar car;
    HelperSearch search;//new field
    Properties properties;//create Object "Properties" to where we load data of config file (there are data in format: key = value) for
    // var of class that responses for init Browser


    public ApplicationManager(String browser) {
        properties = new Properties();//var initialization is passed to class constructor
        this.browser = browser;
    }

    public void init() throws IOException {
//        wd = new ChromeDriver();
        //create and address to var 'target'
        String target = System.getProperty("target","config");//if in command line we won't pass specific file, this config file will be used by default
       // properties.load(new FileReader(new File("src/test/resources/config.properties")));//method load() read data from file
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));//method load() read data from file via var target - %s - >.properties(extensions)
        if(browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Tests on Chrome started");
        } else if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Tests on FireFox started");
        }
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//implicitly  wait after webDriver
        wd.register(new MyListener());
        wd.manage().window().maximize();
        //wd.navigate().to("https://ilcarro.web.app/");
        wd.navigate().to(properties.getProperty("web.baseUrl"));
        //go to object Properties and with getProperty take out value of key URL
        user = new HelperUser(wd);
        car = new HelperCar(wd);
        search = new HelperSearch(wd);//create the new object search
    }

    public HelperSearch getSearch() {
        return search;
    }//getter of serachObject

    public HelperCar getCar() {
        return car;
    }

    public HelperUser getUser() {
        return user;
    }

//encapsulation: modify getter so that it returns email and password
    public String getEmail() {
        return properties.getProperty("web.email");
        //form which take out the key of email
    }
    public String getPassword() {
        return properties.getProperty("web.password");
        //form which take out the key of password
    }
}
