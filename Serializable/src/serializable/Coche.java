package serializable;

import java.io.Serializable;

public class Coche implements Serializable {
	private static final long serialVersionUID = 1L;
	public String nombre;
	public int potencia;
	public double precio;
	
	public Coche(String nombre, int potencia, double precio) {
		this.nombre = nombre;
		this.potencia = potencia;
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "\nNombre: " +nombre+ "\nPotencia: " +potencia+ "\nPrecio:" +precio+ "\n";
	}

}
