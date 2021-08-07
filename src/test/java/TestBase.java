import application.ApplicationManager;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase {
    protected static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startLogger(Method m){
        logger.info("Start on method - " + m.getName());
    }
    @AfterMethod
    public void endOfLogger(Method m){
        logger.info("End off method - " + m.getName());
    }


    @BeforeSuite (alwaysRun = true)
    public void start(){
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        app.stop();
    }


}
