import models.Car;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewCarTest extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (app.userHelper().isLogined()) {
            app.userHelper().openLoginForm();
            app.userHelper().fillLoginForm("skelon222@bk.ru", "Qwerty$4");
            app.userHelper().submitForm();
            app.userHelper().pause(3000);
            app.userHelper().clickOK();
        }
    }

    @Test
    public void createNewCar() throws InterruptedException {
        int x = (int) ((System.currentTimeMillis() / 1000) % 36);
        System.out.println(x);
        Car car = Car.builder()
                .address("Tel Aviv, Israel")
                .make("BMW")
                .model("M5")
                .year("2020")
                .engine("2.0")
                .fuel("Petrol")
                .gear("MT")
                .wD("AWD")
                .doors("5")
                .seats("4")
                .clasS("C")
                .fuelConsumption("6.5")
                .carRegNumber("102-67-" + x)
                .price("65")
                .distanceIncluded("500")
                .typeFeature("type of")
                .about("Very good car")
                .build();

        app.carHelper().openCarForm();
        app.carHelper().fillCarForm(car);
        app.carHelper().pause(1000);
        app.carHelper().attachPhoto();
        app.carHelper().clickButtonSubmit();
        Thread.sleep(2000);
          Assert.assertTrue(app.carHelper().isCarAdded());
        System.out.println("Car number - " + car.getCarRegNumber());

    }
    @AfterMethod
    public void postCondition(){
        app.carHelper().submitCar();
    }
}
