package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        click(By.id("1"));
    }

    public boolean isCarFormPresent(){
        return new WebDriverWait(wd, 10)
                .until(ExpectedConditions.textToBePresentInElement(
                        wd.findElement(By.xpath("//div[@class='car-card']//h1[.' Let the car work ']"))
                        ," Let the car work "));
    }

    public void typeLocation(String address){
        type(By.id("pickUpPlace"),address);
        pause(3000);
        click(By.cssSelector("div.pac-item"));
        pause(3000);
    }

    public void select(By locator, String option){
        new Select(wd.findElement(locator)).selectByValue(option);
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getAddress());

        type(By.id("make"), car.getMake());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), car.getSeats());
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        type(By.id("price"), car.getPrice());
    }

    public void submitForm() {
        click(By.xpath("//button[@type='submit']"));

    }
}
