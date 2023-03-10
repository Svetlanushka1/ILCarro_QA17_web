import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getUser().isLogged() == false) {
            app.getUser()
                    .login(new User()
                            //.withEmail("haifa@gmail.com")
                            .withEmail(app.getEmail())
                           // .withPassword("Haifa082022$")
                            .withPassword(app.getPassword())

                    );
            app.getUser().pause(3000);
            app.getUser().clickOkButton();
        }
    }

    @Test
    public void addNewCarPositive() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        //        logger.info("Method addNewCarPositive started");

        Car car = Car.builder()
                .address("Tel Aviv")
                .make("Toyota Camry2")
                .model("Sedan")
                .year("2023")
                .fuel("Hybrid")
                .seats("4")
                .carClass("luxury")
                .carRegNumber("100-200" + i)
                .price("400")
                .build();

        app.getUser().pause(3000);
        app.getCar().openCarForm();
//        Assert.assertTrue(app.getCar().isCarFormPresent());
        app.getUser().pause(3000);
        app.getCar().fillCarForm(car);
        app.getCar().submitForm();
        //logger
        logger.info("Car added with \n" + car.toString());

    }


//    @AfterMethod
}