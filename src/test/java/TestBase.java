import application.ApplicationManager;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager();

    @BeforeClass
    public void start(){
        app.init();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        app.stop();
    }


}
