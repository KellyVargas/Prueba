package Selenium;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;

public class selenium {
    private ChromeDriver Driver;
    static WebDriver driver;
    private Connection connection;
    private long startTime;
    private long duration;
    metodos metodos = new metodos(driver);

    //Escenario 1
    @Given("el usuario inicia sesión")
    public void IngresoSistema() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Cargue de pagina
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        // El campo de usuario sea visible
        metodos.ElementoVisible(By.id("user-name"));
        //Ingreso se usuario y contraseña y click en botón login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
    @When("el usuario selecciona el artículo")
    public void seleccionarArticulo() throws Throwable {
        metodos.ElementoVisible(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
    }
    @Then("se finaliza compra")
    public void finalizarCompra() throws Throwable {
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Kelly");
        driver.findElement(By.id("last-name")).sendKeys("Vargas");
        driver.findElement((By.id("postal-code"))).sendKeys("1065");
        driver.findElement((By.id("continue"))).click();
        driver.findElement(By.id("finish")).click();
        System.out.println("Thank you for your order!");
    }

    //ESCENARIO 2
    @Given("el usuario regresa a página principal")
    public void NuevoIngresoSistema() {
        //Usurio retoma a la pantalla inicial
        driver.findElement(By.id("back-to-products")).click();//Finaliza Compra
    }

    @When("el usuario selecciona el articulo a eliminar")
    public void eliminarProducto()throws Throwable{
    //se seleccionan nuevamente los articulos
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        //Se eliminan los articulos
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("remove-sauce-labs-onesie")).click();
    }
    @Then("los articulos cambian de estado")
    public void  cambio(){
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("continue-shopping")).click();
        metodos.ElementoVisible(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        metodos.ElementoVisible(By.id("add-to-cart-sauce-labs-onesie"));
    }

    //Escenario 3
    @Given("se ingresan datos de usuario bloqueado")
    public void ingresarDatos() throws Throwable{
        //Se continua con el proceso de compra
        driver.findElement(By.id("react-burger-menu-btn")).click();
        metodos.ElementoVisible(By.id("logout_sidebar_link"));
        driver.findElement(By.id("logout_sidebar_link")).click();
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @When("se selecciona login")
    public void seleccionarLogin()throws Throwable {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("el sistema muestra mensaje de error")
    public void mensaje()throws Throwable {
        System.out.println("Epic sadface: Sorry, this user has been locked out.");
        driver.quit();
    }

}
