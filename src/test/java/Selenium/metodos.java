package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class metodos {
    private WebDriver driver;

    // Constructo que inicializa el WebDriver
    public metodos(WebDriver driver) {
        this.driver = driver;
    }

    //METODO PARA VALIDAR VISIBILIDAD DE CAMPO
    public WebElement ElementoVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("El elemento no fue visible");
            return null;
        }
    }

    //ACTUALIZA EL DRIVER
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void Login(String userName, String password) {
        WebDriverManager.chromedriver().setup();
        ElementoVisible(By.id("user-name"));
        // Se ingresan credenciales
        driver.findElement(By.id("user-name")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    public void CerrarCeccion (){
        driver.findElement(By.id("react-burger-menu-btn")).click();
        ElementoVisible(By.id("logout_sidebar_link"));
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

}
