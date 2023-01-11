import manager.NGListener;
import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NGListener.class)
public class LoginTest extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }
    // Data Transfer Object
    @Test(dataProvider = "loginModelDto", dataProviderClass = ProviderData.class)
    public void loginSuccessModel(User user){
        logger.info("User: " + user.toString());
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
    }

    @Test
    public void loginSuccess(){
        User data = new User()
                .withEmail("haifa@gmail.com")
                .withEmail("haifagmail.com")//w/o @
                .withPassword("Haifa082022$")
                .withPassword("Haifa082022");//w/o $

        app.getUser().openLoginForm();
//        app.getUser().fillLoginForm("asd@fgh.com", "$Asdf1234");
        app.getUser().fillLoginForm(data);
        app.getUser().submitLogin();
//        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }
 /*   @Test
    public void emailWithoutDog(){
        User data = new User()
                .withEmail("haifagmail.com")
                .withPassword("Haifa082022$");

        app.getUser().openLoginForm();

        app.getUser().fillLoginForm(data);
        app.getUser().submitLogin();
        Assert.assertFalse(app.getUser().isLoggedSuccess());
    }
    @Test
    public void emailWithoutDot(){
        User data = new User()
                .withEmail("haifa@gmailcom")
                .withPassword("Haifa082022$");

        app.getUser().openLoginForm();

        app.getUser().fillLoginForm(data);
        app.getUser().submitLogin();
        Assert.assertFalse(app.getUser().isLoggedSuccess());
    }*/

    @AfterMethod
    public void postCondition(){
        app.getUser().pause(3000);
        app.getUser().clickOkButton();
    }
}