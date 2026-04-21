package com.krakedev.financiero.entidades;

public class Cuenta {
	//ATRIBUTOS
	private String id;
	private double saldoActual;
	private String tipo;
	private Cliente propietario;
	//CONSTRUCTORES
	public Cuenta(String id) {
		this.id = id;
		this.saldoActual=0;
		this.tipo="A";
		this.propietario=new Cliente();
	}
	//METODOS GET Y SET
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getSaldoActual() {
		return saldoActual;
	}
	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Cliente getPropietario() {
		return propietario;
	}
	public void setPropietario(Cliente propietario) {
		this.propietario = propietario;
	}
	//METODO IMPRIMIR 
	public void imprimir() {
		System.out.println("id: "+id);
		System.out.println("Saldo actual: "+saldoActual);
		System.out.println("Tipo: "+tipo);
		//METODO IMPRIMIR DE LA CLASE CLIENTE
		propietario.imprimir();		
	}
	
}
