package com.krakedev.financiero.entidades.servicios.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.entidades.servicios.Banco;

public class TestServiciosBancoJUnitIA {

	private Banco banco;
	private Cliente cliente1;
	private Cliente cliente2;
	private Cuenta cuenta1;
	private Cuenta cuenta2;

	private final double DELTA = 0.0001; // Tolerancia para comparar valores double

	@BeforeEach
	public void setUp() {
		// Se inicializa el banco y los clientes antes de cada prueba
		banco = new Banco();
		cliente1 = new Cliente("0101", "Juan", "Perez");
		cliente2 = new Cliente("0202", "Maria", "Lopez");

		// Se crean dos cuentas asociadas a los clientes
		cuenta1 = banco.crearCuenta(cliente1);
		cuenta2 = banco.crearCuenta(cliente2);
	}

	// =========================
	// PRUEBAS CREAR CUENTA
	// =========================

	@Test
	public void testCrearCuenta_correctaCreacion() {
		// Se crea una nueva cuenta
		Cuenta cuenta = banco.crearCuenta(cliente1);

		// Se valida que la cuenta tenga asignado correctamente el cliente recibido
		assertEquals(cliente1, cuenta.getPropietario());
	}

	@Test
	public void testCrearCuenta_codigoUnico() {
		// Se crean dos cuentas consecutivas
		Cuenta c1 = banco.crearCuenta(cliente1);
		Cuenta c2 = banco.crearCuenta(cliente1);

		// Se valida que cada cuenta tenga un identificador diferente
		// lo cual garantiza que el código es único
		assertNotEquals(c1.getId(), c2.getId());
	}

	// =========================
	// PRUEBAS DEPOSITAR
	// =========================

	@Test
	public void testDepositar_valido() {
		// Se realiza un depósito válido
		boolean resultado = banco.depositar(100.0, cuenta1);

		// Se valida que el método retorne true indicando éxito
		assertEquals(true, resultado);

		// Se valida que el saldo de la cuenta aumentó correctamente
		assertEquals(100.0, cuenta1.getSaldoActual(), DELTA);
	}

	@Test
	public void testDepositar_montoCero() {
		// Se intenta depositar un monto igual a cero
		boolean resultado = banco.depositar(0, cuenta1);

		// Se valida que la operación falle
		assertEquals(false, resultado);

		// Se valida que el saldo no cambie
		assertEquals(0.0, cuenta1.getSaldoActual(), DELTA);
	}

	@Test
	public void testDepositar_montoNegativo() {
		// Se intenta depositar un monto negativo
		boolean resultado = banco.depositar(-50, cuenta1);

		// Se valida que la operación falle
		assertEquals(false, resultado);

		// Se valida que el saldo permanezca igual
		assertEquals(0.0, cuenta1.getSaldoActual(), DELTA);
	}

	// =========================
	// PRUEBAS RETIRAR
	// =========================

	@Test
	public void testRetirar_valido() {
		// Primero se deposita dinero para poder retirar
		banco.depositar(200, cuenta1);

		// Se realiza un retiro válido
		boolean resultado = banco.retirar(100, cuenta1);

		// Se valida que el retiro fue exitoso
		assertEquals(true, resultado);

		// Se valida que el saldo disminuyó correctamente
		assertEquals(100.0, cuenta1.getSaldoActual(), DELTA);
	}

	@Test
	public void testRetirar_montoMayorSaldo() {
		// Se deposita un monto menor al que se intentará retirar
		banco.depositar(100, cuenta1);

		// Se intenta retirar más dinero del disponible
		boolean resultado = banco.retirar(200, cuenta1);

		// Se valida que la operación falle
		assertEquals(false, resultado);

		// Se valida que el saldo no cambie
		assertEquals(100.0, cuenta1.getSaldoActual(), DELTA);
	}

	@Test
	public void testRetirar_montoCeroONegativo() {
		// Se prueban dos casos inválidos: monto cero y monto negativo
		boolean r1 = banco.retirar(0, cuenta1);
		boolean r2 = banco.retirar(-10, cuenta1);

		// Se valida que ambos casos fallen porque no cumplen la condición de monto > 0
		assertEquals(false, r1);
		assertEquals(false, r2);
	}

	// =========================
	// PRUEBAS TRANSFERIR
	// =========================

	@Test
	public void testTransferir_valido() {
		// Se deposita dinero en la cuenta origen
		banco.depositar(200, cuenta1);

		// Se realiza una transferencia válida
		boolean resultado = banco.transferir(100, cuenta1, cuenta2);

		// Se valida que la operación fue exitosa
		assertEquals(true, resultado);

		// Se valida que el saldo se descontó correctamente en origen
		assertEquals(100.0, cuenta1.getSaldoActual(), DELTA);

		// Se valida que el saldo se incrementó correctamente en destino
		assertEquals(100.0, cuenta2.getSaldoActual(), DELTA);
	}

	@Test
	public void testTransferir_saldoInsuficiente() {
		// Se deposita menos dinero del que se desea transferir
		banco.depositar(50, cuenta1);

		// Se intenta transferir un monto mayor al saldo
		boolean resultado = banco.transferir(100, cuenta1, cuenta2);

		// Se valida que la transferencia falle
		assertEquals(false, resultado);

		// Se valida que ningún saldo cambie
		assertEquals(50.0, cuenta1.getSaldoActual(), DELTA);
		assertEquals(0.0, cuenta2.getSaldoActual(), DELTA);
	}

	@Test
	public void testTransferir_montoInvalido() {
		// Se prueban montos inválidos: cero y negativo
		boolean r1 = banco.transferir(0, cuenta1, cuenta2);
		boolean r2 = banco.transferir(-20, cuenta1, cuenta2);

		// Se valida que ambos casos fallen
		assertEquals(false, r1);
		assertEquals(false, r2);
	}

	@Test
	public void testTransferir_mismaCuenta() {
		// Se deposita dinero en la cuenta
		banco.depositar(100, cuenta1);

		// Se intenta transferir a la misma cuenta (caso inválido)
		boolean resultado = banco.transferir(50, cuenta1, cuenta1);

		// Se valida que la operación falle
		assertEquals(false, resultado);

		// Se valida que el saldo no cambie
		assertEquals(100.0, cuenta1.getSaldoActual(), DELTA);
	}
}