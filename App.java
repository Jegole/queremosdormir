package main;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {

	

	private List<String> log; 
	private SimpleDateFormat sdf;
	
	
	public void reservar(Usuario usuario) throws InterruptedException {
		
		

		
		String fechaNextDay = getDiaParaReservar();		
		String horaReserva = getHoraReserva();
		String programaUrl = getProgramaUrl();
		String programaNombre = programaUrl.equals(Utilidades.urlOpenBox) ? "OpenBox" : "Clase normal";
		WebDriver driver = new ChromeDriver();
		
		addLog("Empezando a reservar para: " + usuario.getNombre());
		addLog("Haciendo redirect a la página de login");
		driver.get("https://crosshero.com/athletes/sign_in");
		
		addLog("Se intenta hacer login con " + usuario.getEmail() + " : " + usuario.getPassword());
		driver.findElement(By.id("athlete_email")).sendKeys( usuario.getEmail() );
		driver.findElement(By.id("athlete_password")).sendKeys( usuario.getPassword() );
		driver.findElement(By.name("commit")).submit();
		
		
		addLog("Se va a reservar para el programa: " + programaUrl + " , que corresponde a " + programaNombre);
		driver.get("https://crosshero.com/dashboard/classes?date=".concat( fechaNextDay ).concat("&program_id=").concat( programaUrl ));
		
		Thread.sleep(Utilidades.tiempoEspera);
		
		driver.findElement(By.id("select2-class_reservation_single_class_id-container")).click();
		
		addLog("Se va reservar para las " + horaReserva + " horas");
		driver.findElement(By.className("select2-search__field")).sendKeys( horaReserva );
			
		driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		
		addLog("Haciendo click en botón reserva");
		driver.findElement(By.id("classes-sign-in")).click();
		
		boolean reservado = false;
		
		Thread.sleep(Utilidades.tiempoEspera);
		
		addLog("Finalizando reserva para " + usuario.getNombre());
		driver.quit();
		
	
	}
	
	public void lanzarProcesoReserva() throws InterruptedException, IOException{
		
		Utilidades utilidades = new Utilidades();
		List<Usuario> usuarios = utilidades.getUsuarios();
		
		
		DayOfWeek diaActual = diaActual();
		
		System.setProperty("webdriver.chrome.driver", Utilidades.rutaDriver);
		
		for ( Usuario usuario: usuarios ){
			if ( usuario.esDiaParaReservar( diaActual ) )
			reservar( usuario );
		}
		
		imprimirLog();
	}
	
	private String getDiaParaReservar(){
		String fecha = "";
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, 36);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		fecha = sdf.format(calendar.getTime());
		return fecha;
	}
	
	
	private String getProgramaUrl(){
		
		LocalDate date = LocalDate.now();
		date.getDayOfWeek();
		if ( date.getDayOfWeek() == DayOfWeek.FRIDAY ){
			return Utilidades.urlOpenBox;
		} else {
			return Utilidades.urlClase;
		}
		
	}
	
	private String getHoraReserva(){
		
		LocalDate date = LocalDate.now();
		date.getDayOfWeek();
		if ( date.getDayOfWeek() == DayOfWeek.FRIDAY ){
			return "12:30";
		} else {
			return "18:30";
		}
		
		
	}
	
	private  void addLog(String lineaTexto) {
		log.add(sdf.format( (new Date())) + lineaTexto);
	}
	
	private void imprimirLog() throws IOException{
		FileWriter writer = new FileWriter("C:\\Users\\jgomezl\\Desktop\\selenium\\log.txt"); 
		for(String str: log) {
		  writer.write(str + System.lineSeparator());
		}
		writer.close();
	}
	
	
	public boolean esDiaDiario(){
		return !esFindeSemana();
	}
	
	public boolean esFindeSemana(){
		LocalDate date = LocalDate.now();
		date.getDayOfWeek();
		
		return ( date.getDayOfWeek() == DayOfWeek.SATURDAY|| date.getDayOfWeek() == DayOfWeek.SUNDAY );
			
	}
	
	public DayOfWeek diaActual(){
		LocalDate date = LocalDate.now();
		return date.getDayOfWeek();
	}
	
	public App(){
		log = new ArrayList<String>();
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.FRANCE); 
	}
	
	
	

}
