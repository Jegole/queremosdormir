package main;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Utilidades {
	
		
	public final static String urlOpenBox = "5ef84040d6fdc10042672d5f";
	public final static String urlClase = "5e714db1f59a340042a2ef02";
	public final static String rutaDriver = "C:\\Users\\jgomezl\\Desktop\\selenium\\chromedriver.exe";
	
	public final static int tiempoEspera = 5000;
	
	
	public List<Usuario> getUsuarios(){
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		Usuario jesus = new Usuario();
		jesus.setEmail("xxxxx");
		jesus.setNombre("xxxxx");
		jesus.setPassword("xxxxx");
		
		addTodaSemana(jesus);
		usuarios.add(jesus);
		
		Usuario raulCalderon = new Usuario();
		raulCalderon.setEmail("xxxxx");
		raulCalderon.setNombre("xxxxx");
		raulCalderon.setPassword("xxxxx");
		
		addTodaSemana(raulCalderon);
		usuarios.add(raulCalderon);
		
		
		Usuario raulTallero = new Usuario();
		raulTallero.setEmail("xxxxx");
		raulTallero.setNombre("xxxxx");
		raulTallero.setPassword("xxxxx");

		addTodaSemana(raulTallero);
		usuarios.add(raulTallero);
		
		
		Usuario felisa = new Usuario();
		felisa.setEmail("xxxxx");
		felisa.setNombre("xxxxx");
		felisa.setPassword("xxxxx");
		
		addTodaSemana(felisa);
		usuarios.add(felisa);
		
		Usuario sergio = new Usuario();
		sergio.setEmail("xxxxx");
		sergio.setNombre("xxxxx");
		sergio.setPassword("xxxxx");
		
		addTodaSemana(sergio);
		usuarios.add(sergio);
		
		Usuario ana = new Usuario();
		ana.setEmail("xxxxx");
		ana.setNombre("xxxxx");
		ana.setPassword("xxxxx");
		
		ana.addDiaReserva(DayOfWeek.MONDAY);
		ana.addDiaReserva(DayOfWeek.WEDNESDAY);
		ana.addDiaReserva(DayOfWeek.FRIDAY);
		usuarios.add(ana);
		
		return usuarios;
	}
	
	
	private void addTodaSemana(Usuario usuario){
		usuario.addDiaReserva(DayOfWeek.MONDAY);
		usuario.addDiaReserva(DayOfWeek.TUESDAY);
		usuario.addDiaReserva(DayOfWeek.WEDNESDAY);
		usuario.addDiaReserva(DayOfWeek.THURSDAY);
		usuario.addDiaReserva(DayOfWeek.FRIDAY);
		usuario.addDiaReserva(DayOfWeek.SUNDAY);
	}
	

}
