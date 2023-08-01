package pom;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage() {
        // Paso 1: Abrir la URL "https://www.exito.com/"
        driver.get("https://www.exito.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    public void clickCategoryMenu() throws InterruptedException {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        WebElement categoryMenu = driver.findElement(By.xpath("//div[@id='category-menu']"));
        Thread.sleep(5000);
        categoryMenu.click();
    }

    public void clickSubCategoryMenu(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        // Paso 3: Obtener opciones de la lista
        List<WebElement> opciones = driver.findElements(By.cssSelector("li.exito-category-menu-3-x-itemCategory"));
        // Seleccionar la "Hogar y muebles" dado que los resultados cuentan con botón "Compra rapida".
        opciones.get(7).click();
    }

    public void clickOptionsSubMenu(){
        // Paso 4: Contar elementos que contienen etiqueta "<a" para seleccionar aleatorio.
        List<WebElement> sub_opciones = driver.findElements(By.cssSelector("div.exito-category-menu-3-x-itemSideMenu a"));

        // Genera un índice aleatorio entre 0 y la cantidad de opciones disponibles.
        int indiceAleatorio_2 = new Random().nextInt(4); //new Random().nextInt(sub_opciones.size());
        System.out.println("_____Sub opciones_____");
        System.out.println(sub_opciones.size());

        // Hacer clic en la opción aleatoria seleccionada.
        sub_opciones.get(indiceAleatorio_2).click();
        System.out.println("_____Opcion seleccionada_____");
        System.out.println(indiceAleatorio_2);
    }

}
