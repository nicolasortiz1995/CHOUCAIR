package mobiletesting;

import java.net.URL;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class HomeAppTesting {
    
    private static RemoteWebDriver driver;

    @BeforeAll
    public void setUp(){
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            //caps.setCapability("platformName", "Android");
            //caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"11");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Mobile1");
            caps.setCapability(MobileCapabilityType.UDID,"emulator-5554");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"");
            //caps.setCapability(MobileCapabilityType.APP,"");
            caps.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
            caps.setCapability(MobileCapabilityType.APP,"C:/CHOUCAIR/app/src/test/resources/Apk/Exito_3.5.1.apk");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2");
            caps.setCapability("chromedriverExecutable","C:/CHOUCAIR/app/src/test/resources/chromedriver.exe");

            URL url = new URL("http://127.0.0.1:4723/wd/hub"); 
            driver = new AppiumDriver(url, caps);


        } catch (Exception e) {
            System.out.println("Excepcion: "+e.getCause());
            System.out.println("Mensaje: "+e.getMessage());
        }
    }

    @Test
        public void sampleTest(){
            System.out.println("Ingreso prueba");
            
        }
}
