package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

   //WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser user;
    HelperCar car;

    public void init(){
       // wd = new ChromeDriver();
        wd= new EventFiringWebDriver(new ChromeDriver());
        wd.register(new MyListener());
        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro.web.app/");
        user = new HelperUser(wd);
        car = new HelperCar(wd);

    }

    public HelperCar getCar() {
        return car;
    }

    public HelperUser getUser() {
        return user;
    }
}
