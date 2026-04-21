package com.krakedev.financiero.entidades;

public class Cliente {
	//ATRIBUTOS
	private String cedula;
	private String nombre;
	private String apellido;
	//CONSTRUCTORES
	public Cliente() {
		//vacio
	}
	public Cliente(String cedula, String nombre, String apellido) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	//METODOS GET Y SET 
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	//METODO IMPRIMIR
	public void imprimir() {
		System.out.println("Cedula: "+this.cedula);
		System.out.println("Nombre: "+this.nombre);
		System.out.println("Apellido: "+this.apellido);
	}
	
}
