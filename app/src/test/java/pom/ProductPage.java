package pom;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.Assertions;

public class ProductPage {
    

    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Realizar array para nombres de productos.
    ArrayList<String> productNameArr = new ArrayList<>();
    // Realizar array para precios del producto.
    ArrayList<Integer> productPriceArr = new ArrayList<>();
    // Realizar array para cantidad del producto.
    ArrayList<Integer> productCountArr = new ArrayList<>();

    public void editProducts() throws InterruptedException{
        // Paso 5: Encontrar todos los botones dentro del div con el id "gallery-layout-container"
        Thread.sleep(3000);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(3000);
        js.executeScript("window.scrollTo(0, 0);");
        List<WebElement> buttons = driver.findElements(By.xpath("//div[@id='gallery-layout-container']//span[@class='w-100 h-100']"));

        // Contar el total de botones
        int totalButtons = buttons.size();
        System.out.println("Total de botones: " + totalButtons);

        // Seleccionar 5 botones aleatoriamente
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        List<WebElement> selectedButtons = selectRandomButtons(buttons, 5); 

        // Hacer clic en los botones seleccionados
        for (WebElement button : selectedButtons) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            WebElement resultsFocus = driver.findElement(By.cssSelector(".vtex-search-result-3-x-filterPopupArrowIcon"));
            new Actions(driver).moveToElement(resultsFocus).perform();
            
            // Paso 6: Hacer clic en el botón "Compra rápida", diligenciar información y extraer datos.
            new Actions(driver).moveToElement(button).perform();
            button.click();

            // Encontrar el elemento por su clase para agregar producto.
            Thread.sleep(1000);
            WebElement addButton = driver.findElement(By.className("exito-vtex-components-4-x-buttonText"));
            // Hacer clic en el botón "Agregar"
            addButton.click();
            Thread.sleep(1000);

            // Ciclo para indicar la cantidad de los productos con un numero aleatorio del 1 a 10.
            Random randonButton = new Random();
            int numeroAleatorio = randonButton.nextInt(10)+1;
            for(int i = 1; i < numeroAleatorio; i++){
                System.out.println("Numero cantidad aleatorio:" + numeroAleatorio);
                driver.findElement(By.xpath("//span[@class='product-details-exito-vtex-components-buy-button-manager-more']")).click();
                Thread.sleep(300);
            }                

            // Extraer información para aserciones.
            // a. Extraer nombre de producto:
            WebElement productName = driver.findElement(By.xpath("//div[@role='presentation']//div[1]//h3[1]//span[1]"));

            // Posicionar nombre en array
            productNameArr.add(productName.getText());
            System.out.println(productNameArr);

            // b. Extraer conteo de los productos:
            WebElement productCount = driver.findElement(By.xpath("//div[@class='exito-vtex-components-4-x-stepperContainerQty']"));

            // Posicionar el conteo individual en array
            Integer valCount = Integer.parseInt(productCount.getText().replaceAll(".00 un", ""));
            productCountArr.add(valCount);
            System.out.println(productCountArr);

            // c. Extraer precio de producto:
            WebElement productPrice = driver.findElement(By.xpath("//div[contains(@class,'w-100 center pt2 pb5')]//div[contains(@class,'exito-vtex-components-4-x-PricePDP')]"));

            // Posicionar precio en array y procesar cadena para obtener el valor
            productPriceArr.add(Integer.parseInt(productPrice.getText().replaceAll("[^0-9]", ""))*valCount);
            System.out.println(productPriceArr);

            // Encontrar el elemento por su clase para cerrar ventana
            WebElement closeButton = driver.findElement(By.className("exito-vtex-components-4-x-defaultModalClose"));

            // Hacer clic para cerrar ventana.
            closeButton.click();
            Thread.sleep(2000);


        }
    }

    public void goToCart () throws InterruptedException{
        //Paso 7: Hacer clic en el botón "Carrito de compras" y enviar correo.
        // Clic en botón carrito de compras.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement cartButton = driver.findElement(By.xpath("//a[@class='exito-header-3-x-minicartLink']//*[name()='svg']"));
        try {
            cartButton.click();
        } catch (Exception e) {
            driver.get("https://www.exito.com/checkout-io#/");
        }

        // Ingresar correo "prueba@prueba.com" al elemento.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement inputEmail = driver.findElement(By.cssSelector("input[type='email'][maxlength='100']"));
        inputEmail.sendKeys("prueba@prueba.com");

        // Buscar botón "Confirmar" y dar clic
        WebElement buttonConfirmar = driver.findElement(By.className("exito-checkout-io-0-x-preLoginActiveButton"));
        buttonConfirmar.click();
    }
    
    public void Assertion1 () throws InterruptedException{
        // Aserciones 1: El nombre de los productos agregados deberá ser igual que en el carrito
        // Obtiene el contenido del cuerpo de la página.
        Thread.sleep(3000);
        for(String value2:productNameArr){
            try {
                WebElement fullBody = driver.findElement(By.tagName("body"));
                String bodyText = fullBody.getText();
                Assertions.assertTrue(bodyText.contains(value2));
                System.out.println("Aserción 1 exitosa: " + value2);
            } catch (Exception e) {
                System.out.println("Aserción 1 fallida, no se encontró el texto: "+value2);
            }
            
        }
    }

    public void Assertion2(){
        // Aserción 2: El total de los precios de los productos agregados deberá ser igual que en el carrito
            // Buscar total de compra.
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            WebElement totalPurchaseEle = driver.findElement(By.xpath("//span[@class='exito-checkout-io-0-x-paymentButtonTextBold']"));

            // Obtener el texto del elemento
            System.out.println(totalPurchaseEle.getText());
            Integer totalPurchase = Integer.parseInt(totalPurchaseEle.getText().replaceAll("[Total:.$ ]", ""));
            System.out.println(totalPurchase);
            // Suma de valores en array 
            Integer sumPrice = 0;
            for(int value : productPriceArr){
                sumPrice += value;
            }
            try {
                Assertions.assertEquals(totalPurchase, sumPrice);
                System.out.println("Aserción 2 exitosa.");
                System.out.println("Total de compra elemento:" + totalPurchase);
                System.out.println("Total de compra suma:" + sumPrice);

            } catch (Exception e) {
                System.out.println("Aserción 2 fallida:");
                System.out.println("Total de compra elemento:" + totalPurchase);
                System.out.println("Total de compra suma:" + sumPrice);
            }
    }

    public void Assertion3 (){
        // Aserción 3: Las cantidades de los productos agregados deberá ser igual que en el carrito
            // Localizar todos los elementos con el identificador
            Integer quantityTotal = 0;
            List<WebElement> quantityElements = driver.findElements(By.cssSelector("[data-molecule-quantity-und]"));
            for (WebElement element : quantityElements) {
                // Obtener el texto del elemento
                String quantityText = element.findElement(By.cssSelector("[data-molecule-quantity-und-value]")).getText();
                // Convertir el texto a entero
                Integer quantity = Integer.parseInt(quantityText.trim());
                quantityTotal += quantity;

                System.out.println("Cantidad: " + quantity);
            }
            Integer sumCount = 0;
            for(int value : productCountArr){
                sumCount += value;
            }
            try {
                Assertions.assertEquals(quantityTotal, sumCount);
                System.out.println("Aserción 3 exitosa.");
                System.out.println("Total cantidad de productos en carrito:" + quantityTotal);
                System.out.println("Total cantidad de productos en suma:" + sumCount);
            } catch (Exception e) {
                System.out.println("Aserción 3 fallida:");
                System.out.println("Total cantidad de productos en carrito:" + quantityTotal);
                System.out.println("Total cantidad de productos en suma:" + sumCount);
            }
    }

    public void Assertion4(){
        // Aserción 4: El número de productos agregados debe ser igual que en el carrito.
            // Localizar todos los elementos con el localizador
            List<WebElement> quantityElements2 = driver.findElements(By.cssSelector("div.exito-checkout-io-0-x-itemCartContent"));
            
            try {
                Assertions.assertEquals(quantityElements2.size(), 5);
                System.out.println("Aserción 4 exitosa: " + quantityElements2.size());
            } catch (Exception e) {
                System.out.println("Aserción 4 fallida, la cantidad esperada es 5 y tiene valor de: " + quantityElements2.size());
            }
    }

    // Método para seleccionar botones aleatoriamente
    public static List<WebElement> selectRandomButtons(List<WebElement> buttons, int numToSelect) {
        Random random = new Random();
        List<WebElement> selectedButtons = new ArrayList<>();

        // Para evitar seleccionar más botones de los que hay disponibles
        numToSelect = Math.min(numToSelect, buttons.size());

        while (selectedButtons.size() < numToSelect) {
            int randomIndex = random.nextInt(buttons.size());
            WebElement randomButton = buttons.get(randomIndex);
            if (!selectedButtons.contains(randomButton)) {
                selectedButtons.add(randomButton);
            }
        }

        return selectedButtons;
    }

}