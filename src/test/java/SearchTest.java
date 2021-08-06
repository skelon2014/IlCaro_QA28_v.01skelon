import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTest extends TestBase {

    @Test
    public void positiveTestSendKey() throws InterruptedException {
        //with concatinate String from 1 date to 2 date
        app.search().typeSearchCurrentMonth("Haifa", "08/10/2021", "08/31/2021");

        app.userHelper().submitForm();
        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarsAppeared());
    }

    @Test
    public void negativeTestSendKey() throws InterruptedException {

        //with concatinate String from 1 date to 2 date
        app.search().typeSearchCurrentMonth("Haifa", "08/10/2021", "08/30/2021");

        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isDateInPath());
        Assert.assertTrue(app.search().isButtonYallaInactive());
    }

    @Test

    public void selectPeriodCurrentMonth() throws InterruptedException {

        app.search().fillSearchForCurrentMonth("Haifa", "07/30/2021", "07/31/2021");
        app.userHelper().submitForm();
        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarsAppeared());
    }

    @AfterMethod
    public void postConditions() {
        app.search().backToHome();
    }
}



