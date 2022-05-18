package com.test.somosmakers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.test.utils.utils.takeSnapShot;
import static java.lang.Thread.sleep;

public class StepDefinitions {

    private WebDriver webDriver;
    private String numCelular;
    List<Map<String, String>> rows;
    private ArrayList<String> listInsert = new ArrayList<String>();
    List<Float> OBL = new LinkedList<>();

    @Given("la siguiente url {string}")
    public void laSiguienteUrl(String url) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        if (webDriver == null) {
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get(url);

    }

    @When("hacemos clic en el enlace de {string}")
    public void hacemosClicEnElEnlaceDe(String contacto) {
        webDriver.findElement(By.partialLinkText(contacto)).click();
    }

    @Then("capturar el numero de celular de servicio al cliente")
    public void capturarElNumeroDeCelularDeServicioAlCliente() {
        numCelular = webDriver.findElement(By.xpath("//a[contains(@title, 'celular')]")).getText();
        //System.out.println(webDriver.findElement(By.xpath("//*[contains(@title, 'celular')]")).getText());
    }

    @And("llenar el formulario {string} y en el campo mensaje adjuntar el numero de celular capturado")
    public void llenarElFormularioYEnElCampoMensajeAdjuntarElNumeroDeCelularCapturado(String mensaje) throws InterruptedException {
        // nombre
        webDriver.findElement(By.id("et_pb_contact_first_0")).sendKeys("Yendry");
        // apellido
        webDriver.findElement(By.id("et_pb_contact_last_0")).sendKeys("Viloria");
        // emsil
        webDriver.findElement(By.id("et_pb_contact_email_0")).sendKeys("jendry0017@gmail.com");
        // telefono
        webDriver.findElement(By.id("et_pb_contact_phone_0")).sendKeys(numCelular.replaceAll("\\D+",""));
        // Mensaje
        webDriver.findElement(By.id("et_pb_contact_message_0")).sendKeys(mensaje);
        //sleep(2000);

    }

    @And("antes de hacer clic en el boton {string} tomar un pantallazo con la informacion diligenciada")
    public void antesDeHacerClicEnElBotonTomarUnPantallazoConLaInformacionDiligenciada(String arg0) throws InterruptedException {
        takeSnapShot(webDriver, "snapShot/contacto/deja-tu-mensaje.png");
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        webDriver.findElement(By.name("et_builder_submit_button")).click();
        Assert.assertEquals("¡Mensaje enviado!", webDriver.findElement(By.xpath("//p[text()='¡Mensaje enviado!']")).getText());

        sleep(2000);
        takeSnapShot(webDriver, "snapShot/contacto/Mensaje-Enviado.png");
        webDriver.close();
        webDriver.quit();
    }

    @Given("los siguientes parametros de entrada")
    public void losSiguientesParametrosDeEntrada(DataTable table) throws ParseException {
        rows = table.asMaps(String.class, String.class);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Map<String, String> columns : rows) {
            java.util.Date fecha = formato.parse(columns.get("fecha"));
            listInsert.add("INSERT INTO NewTable (fecha, portafolio, nominal, precio, total) " +
                    "VALUES (\\\""+ formato.format(fecha) +"\\\", \\\"" +
                    columns.get("portafolio")+"\\\","+ columns.get("nominal")+","+
                    columns.get("precio")+","+ columns.get("total") +");");
        }
    }

    @Then("debo insertar los registros en la base datos")
    public void deboInsertarLosRegistrosEnLaBaseDatos() {
        executeQuery("DELETE FROM NewTable;");
        for(int i=0; i<listInsert.size(); i++){
            executeQuery(listInsert.get(i).replace("\\",""));
        }
    }

    @Given("los parametros de entrada previos")
    public void losParametrosDeEntradaPrevios() {
        // No se que hacer aca.
    }

    @Then("calcular el valor total asi [nominal * precio]")
    public void calcularElValorTotalAsiNominalPrecio() {
        for (Map<String, String> columns : rows) {
            if(columns.get("portafolio").equals("OBL-MODER")){
                    float f_nominal = new Float(columns.get("nominal"));
                    float f_precio = new Float(columns.get("precio"));
                    OBL.add(f_nominal*f_precio);
            }
        }
    }

    @But("si el portafolio es {string} se debe calcular el valor total asi [\\({string} \\* {string}) + {int}]")
    public void siElPortafolioEsSeDebeCalcularElValorTotalAsi(String arg0, String arg1, String arg2, int arg3) {
        for (Map<String, String> columns : rows) {
            if(columns.get("portafolio").equals(arg0)){
                    float f_nominal = new Float(columns.get("nominal"));
                    float f_precio = new Float(columns.get("precio"));
                    OBL.add((f_nominal*f_precio)+arg3);
            }
        }
    }

    @And("validar que el valor total calculado sea igual al campo total de la base de datos")
    public void validarQueElValorTotalCalculadoSeaIgualAlCampoTotalDeLaBaseDeDatos() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://ry0eyrwe6c0z.us-east-2.psdb.cloud/testdatabase?sslMode=VERIFY_IDENTITY",
                    "uz2tu7nkqmuh",
                    "pscale_pw_9Xo_1Fevjr8hEZOYZApfSXWAlIW8oJ3UP4qiHFv3leM");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NewTable;");
            int i = 0;
            while (rs.next()) {
                int total = (int) rs.getObject("total");
                String portafolio = (String) rs.getObject("portafolio");

                if(portafolio.equals("OBL-MODER")){
                    //System.out.println((int) OBL.get(i).intValue()+"=="+total);
                    Assert.assertEquals((int) OBL.get(i).intValue(),total);
                }
                if(portafolio.equals("OBL-RIESGO")){
                    //System.out.println((int) OBL.get(i).intValue()+"=="+total);
                    Assert.assertEquals((int) OBL.get(i).intValue(),total);
                }
                i++;
            }
            conn.close();
        }
        catch(Exception e) {System.out.println(e); }
    }

    private void executeQuery(String query){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://ry0eyrwe6c0z.us-east-2.psdb.cloud/testdatabase?sslMode=VERIFY_IDENTITY",
                    "uz2tu7nkqmuh",
                    "pscale_pw_9Xo_1Fevjr8hEZOYZApfSXWAlIW8oJ3UP4qiHFv3leM");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
        }
        catch(Exception e) { System.out.println(e); }
    }


}
