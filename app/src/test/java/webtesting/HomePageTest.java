// Adición de package actual.
package webtesting;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
// Librerías por defecto de Selenium
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pom.HomePage;
import pom.ProductPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest {
    private static WebDriver driver;
    private static HomePage homePage;
    private static ProductPage productPage;

    @BeforeAll
    public static void setUp() {
        // Configuración del WebDriver para Chrome
        System.setProperty("webdriver.chrome.driver", "C:/CHOUCAIR/app/src/test/resources/chromedriver.exe");

        // Inicialización del WebDriver
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);

    }

    @AfterAll
    public static void tearDown() {
        // Cerrar el navegador
        driver.quit();
    }

    @Test
    @Order(1)
    public void testHome() throws InterruptedException {
        homePage.goToHomePage();
        homePage.clickCategoryMenu();
        homePage.clickSubCategoryMenu();
        homePage.clickOptionsSubMenu();
    }

    @Test
    @Order(2)
    public void selectProducts() throws InterruptedException {
        productPage.editProducts();
        productPage.goToCart();
    }

    @Test 
    @Order(3)
    public void nombreProductos() throws InterruptedException{
        productPage.Assertion1();
    }

    @Test 
    @Order(4)
    public void totalPrecio() throws InterruptedException{
        productPage.Assertion2();
    }

    @Test 
    @Order(5)
    public void totalProductos(){
        productPage.Assertion3();
    }
    
     @Test 
     @Order(6)
    public void cantidadProductos(){
        productPage.Assertion4();
    }
    

}
