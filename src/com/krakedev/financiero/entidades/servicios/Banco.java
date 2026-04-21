package com.krakedev.financiero.entidades.servicios;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class Banco {
	//ATRIBUTOS
	private int ultimoCodigo=1000;
	//CONSTRUCTORES
	public Banco() {
		//VACIO
	}
	//METODOS GEY Y SET
	public int getUltimoCodigo() {
		return ultimoCodigo;
	}
	public void setUltimoCodigo(int ultimoCodigo) {
		this.ultimoCodigo = ultimoCodigo;
	}
	//METODO CREAR CUENTA
	public Cuenta crearCuenta(Cliente cliente) {
		String codigoStr=ultimoCodigo+"";
		ultimoCodigo+=1;
		Cuenta cuentaNueva=new Cuenta(codigoStr);
		cuentaNueva.setPropietario(cliente);
		return cuentaNueva;
	}
	//METODO DEPOSITAR
	public boolean depositar(double monto, Cuenta cuenta) {
		if(monto>0) {
			double saldoActual=cuenta.getSaldoActual();
			cuenta.setSaldoActual(saldoActual+monto);
			return true;
		}
		return false;
	}
	//METODO RETIRAR
	public boolean retirar(double monto, Cuenta cuenta) {
		if(monto>0 && cuenta.getSaldoActual()>=monto) {
			double saldoActual=cuenta.getSaldoActual();
			cuenta.setSaldoActual(saldoActual-monto);
			return true;
		}
		return false;
	}
	//METODO TRASFERIR 
	public boolean transferir(double monto, Cuenta origen, Cuenta destino) {
		if(monto>0 && origen.getSaldoActual()>=monto && origen.getId()!=destino.getId()) {
			return retirar(monto, origen) && depositar(monto, destino);			
		}		
		return false;
	}	
}
