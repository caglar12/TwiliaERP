package com.twiliaerp.StepDefs;

import com.twiliaerp.utilities.DBUtils;
import com.twiliaerp.utilities.Driver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before("@db")
    public void setUpDatabase(){

        DBUtils.createConnection();
    }

    @Before
    public void setUp(){
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
    }

    @After("@db")
    public void tearDownDatabase(){

        DBUtils.destroyConnection();
    }

    @After
    public void tearDown(Scenario scenario){
        //if the scenario fails take the screenshot
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot,"image/png");
        }

        //Driver.closeDriver();
    }




}
