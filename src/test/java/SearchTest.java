import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTest extends TestBase {

    @Test(groups = {"web"})
    public void positiveTestSendKey() {
        //with concatinate String from 1 date to 2 date
        logger.info("\"Haifa\", \"08/13/2021\", \"08/31/2021\"");
        app.search().typeSearchCurrentMonth("Haifa", "08/21/2021", "08/31/2021");

        app.userHelper().submitForm();
        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarsAppeared());
    }

    @Test(groups = {"a", "web"})
    public void negativeTestSendKey() throws InterruptedException {

        //with concatinate String from 1 date to 2 date
        app.search().typeSearchCurrentMonth("Haifa", "08/11/2021", "08/30/2021");

        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isDateInPath());
        Assert.assertTrue(app.search().isButtonYallaInactive());
    }

    @Test

    public void selectPeriodCurrentMonth() throws InterruptedException {
        app.userHelper().pause(1000);
        app.search().fillSearchForCurrentMonth("Haifa", "08/30/2021", "08/31/2021");
        app.userHelper().submitForm();
        app.carHelper().pause(2000);
        Assert.assertTrue(app.search().isListOfCarsAppeared());
    }

    @AfterMethod
    public void postConditions() {
        app.search().backToHome();
    }
}



