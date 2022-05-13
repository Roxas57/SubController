package casgim.juanma.ProyectoTercerTrimestre.utils;

public class methods {
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
}
