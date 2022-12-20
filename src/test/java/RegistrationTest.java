import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RegistrationTest extends TestBase{

    @Test
    public void registrationPositiveTest(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Joe")
                .withLastName("Doe")
                .withEmail("joe" + i + "@mail.com")
                .withPassword("$passWord123");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().pause(3000);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isRegistered());
    }
    @Test
    public void registrationNegativePasswordWithoutSymbol(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Joe")
                .withLastName("Doe")
                .withEmail("joe" + i + "@mail.com")
                .withPassword("sdf1234");
        logger.info("registrationNegativePasswordWithoutSymbol" + " " + user.getEmail()
                +" " + user.getPassword());
        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        //app.getUser().click(By.cssSelector("label[for='password']"));//above field password
        app.getUser().pause(3000);

        Assert.assertTrue(app.getUser().isErrorMessage());

        //Password must contain minimum 8 symbols
        //Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]

    }
    @Test
    public void registrationNegativePasswordWithoutUpperCase(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Joe")
                .withLastName("Doe")
                .withEmail("joe" + i + "@mail.com")
                .withPassword("$passsord123");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        //app.getUser().click(By.cssSelector("label[for='password']"));//above field password
        app.getUser().pause(3000);

        Assert.assertTrue(app.getUser().isErrorMessage());

    }
    @Test
    public void registrationNegativePasswordWithoutLowCase(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Joe")
                .withLastName("Doe")
                .withEmail("joe" + i + "@mail.com")
                .withPassword("$PASSWORD123");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().click(By.cssSelector("label[for='password']"));//above field password
        app.getUser().pause(3000);

        Assert.assertTrue(app.getUser().isErrorMessage());

    }
    @Test
    public void registrationNegativePasswordWithoutNumber(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Joe")
                .withLastName("Doe")
                .withEmail("joe" + i + "@mail.com")
                .withPassword("$passWordddd");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        //app.getUser().click(By.cssSelector("label[for='password']"));//above field password
        app.getUser().pause(3000);

        Assert.assertTrue(app.getUser().isErrorMessage());

    }
    @Test
    public void registrationNegativePasswordOnly7charts(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withName("Joe")
                .withLastName("Doe")
                .withEmail("joe" + i + "@mail.com")
                .withPassword("$passW3");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        //app.getUser().click(By.cssSelector("label[for='password']"));//above field password
        app.getUser().pause(3000);

        Assert.assertTrue(app.getUser().isErrorMessage());

    }
    @AfterMethod
    public void swichToHomePage(){
        app.getUser().clickToLogo();
    }

}
