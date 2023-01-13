package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    //listener$Screenshot

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    String browser;

    //    WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser user;
    HelperCar car;
    HelperSearch search;//new field

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
//        wd = new ChromeDriver();
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
        wd.navigate().to("https://ilcarro.web.app/");
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
}
