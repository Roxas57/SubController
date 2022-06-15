package casgim.juanma.ProyectoTercerTrimestre.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDateTime;

public class Methods {
	/**
	 * El método generaAleatorio sirve para generar números aleatorios que posteriormente 
	 * guardaremos en un array.
	 * @param upper: Sirve para indicar el numero entero más alto que queremos generar.
	 * @param lower: Sirve para indicar el numero entero más bajo que queremos generar.
	 * @return: Devuelve un valor aleatorio entre "upper" y "lower".
	 */
	public static int generaAleatorio(int upper, int lower) {
		int aux = 0;
		int valor = 0;
		if (lower > upper) {
			aux = lower;
			lower = upper;
			upper = aux;
		}
		valor = (int) (Math.random() * (upper - lower)) + lower;
		return valor;
	}
	
	public static String getSHA256(String input) {
		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%64x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public static LocalDateTime calculaFecha(LocalDateTime f, String t) {
		LocalDateTime fecha = null;
		if (t.equals("Diaria")) {
			fecha = f.plusDays(1);
		} else if (t.equals("Semanal")){
			fecha = f.plusWeeks(1);
		} else if (t.equals("Mensual")) {
			fecha = f.plusMonths(1);
		} else if (t.equals("Anual")) {
			fecha = f.plusYears(1);
		}
		return fecha;
	}
}
