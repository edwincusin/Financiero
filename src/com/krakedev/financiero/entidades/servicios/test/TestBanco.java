package com.krakedev.financiero.entidades.servicios.test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.entidades.servicios.Banco;

public class TestBanco {

	public static void main(String[] args) {
		Cliente cliente1= new Cliente();
		Cliente cliente2= new Cliente("175308156", "Edwin", "Cusin");
		Cliente cliente3= new Cliente("175308156", "Edwin", "ANT");
		
		Banco banco=new Banco();
		
		Cuenta cuenta1=banco.crearCuenta(cliente1);
		Cuenta cuenta2=banco.crearCuenta(cliente2);
		Cuenta cuenta3=banco.crearCuenta(cliente3);
		
		boolean resultado=banco.depositar(500, cuenta1);
		cuenta1.imprimir();
		System.out.println("Resultado: "+resultado);
		
		System.out.println("--------------------");
		resultado=banco.depositar(600, cuenta2);
		cuenta2.imprimir();
		System.out.println("Resultado: "+resultado);
		
		System.out.println("--------------------");
		resultado=banco.depositar(700, cuenta3);
		cuenta3.imprimir();
		System.out.println("Resultado: "+resultado);
		
		System.out.println("---retiro----");
		
		resultado=banco.retirar(100, cuenta1);
		cuenta1.imprimir();
		System.out.println("Resultado retiro: "+resultado);
		
		System.out.println("--------------------");
		resultado=banco.retirar(900, cuenta2);
		cuenta2.imprimir();
		System.out.println("Resultado retiro: "+resultado);
		
		System.out.println("--------------------");
		resultado=banco.retirar(7000, cuenta3);
		cuenta3.imprimir();
		System.out.println("Resultado retiro: "+resultado);
		
		System.out.println("----transferencia 1---------------");
		resultado=banco.transferir(200, cuenta2, cuenta3);
		cuenta2.imprimir();
		cuenta3.imprimir();
		System.out.println("Resultado trasfer: "+resultado);
		
		System.out.println("---------trasnferencia 2-----------");
		resultado=banco.transferir(1000, cuenta2, cuenta3);
		cuenta2.imprimir();
		cuenta3.imprimir();
		System.out.println("Resultado trasfer: "+resultado);
		
		
	}

}
