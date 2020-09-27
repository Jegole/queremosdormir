package main;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	
	private String nombre;
	
	private String email;
	
	private String password;
	
	private List<DayOfWeek> diasReserva;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void addDiaReserva(DayOfWeek dia){
		diasReserva.add(dia);
	}
	
	public boolean esDiaParaReservar(DayOfWeek dia){
		return diasReserva.contains( dia );
	}	
	
	
	public Usuario(){
		diasReserva = new ArrayList<DayOfWeek>();
	}

}
