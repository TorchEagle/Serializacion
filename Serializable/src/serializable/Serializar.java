package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Serializar {
	static ArrayList <Coche> listaCoches = new ArrayList<Coche>();
    static Scanner sc = new Scanner(System.in);
    static File archivo = new File("Coches.bin");
    
    public static void cambiarArchivo() {
    	System.out.println("Que archivo quieres usar para serializar?: ");
    	archivo = new File(sc.nextLine());
    	existencia();
    }
    
   //Metodo que realiza un exists() con el archivo y llama al metodo confirmacion con el valor del exists como entrada
  	private static void existencia() {
  		boolean existencia = archivo.exists();
  		confirmacion(existencia);
  	}
  	
  	//Metodo que, segun si el valor booleano de entrada es verdadero o falso, hara que el programa vuelva al menu principal o que vuelva al metodo selectorArchivo() respectivamente
  	private static void confirmacion(boolean existencia) {
  		if (existencia == true)
  			System.out.println("OK");
  		else {
  			System.out.println("El archivo que la ruta especifica no existe. Por favor, asegúrese de asignar la ruta de un archivo que exista");
  			cambiarArchivo();
  		}
  	}
    
    public static void anyadirCoche() {
    	String nombre;
    	int potencia;
    	double precio;
    	
    	System.out.print("Nombre del coche: ");
    	nombre = sc.nextLine();
    	
    	System.out.print("Potencia: ");
    	potencia = sc.nextInt();
    	
    	System.out.print("Precio: ");
    	precio = sc.nextDouble();
    	
    	listaCoches.add(new Coche(nombre, potencia, precio));
    }
    
    public static void mostrarTodo() {
        if (listaCoches.size() != 0)
            System.out.println(listaCoches);
        else
            System.out.println("La lista está vacia. Por favor, introduzca datos.");
    }
	
    public static void Deserializar() {
    	try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(archivo))) {
    		listaCoches = (ArrayList)input.readObject();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void Serializar() {
    	try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaCoches);
            oos.close();
            fos.close();
            System.out.println("Guardado realizado con exito");
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	existencia();
    	
    	int op = 0;
    	
    	do {
    		System.out.println("Que quieres hacer?\n**********************************************");
    		System.out.println("1. Cambiar archivo de serializado");
            System.out.println("2. Anyadir un objeto a la lista");
            System.out.println("3. Importar de archivo");
            System.out.println("4. Exportar a archivo");
            System.out.println("5. Mostrar lista");
            System.out.println("6. Salir");
            
            try {
                op = sc.nextInt();
                sc.nextLine();
                
                switch (op) {
                	case 1:
                		cambiarArchivo();
                		break;
                    case 2:
                        anyadirCoche();
                        break;
                    case 3:
                        Deserializar();
                        break;
                    case 4:
                        Serializar();
                        break;
                    case 5:
                        mostrarTodo();
                        break;
                    case 6:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch(InputMismatchException e) {
                System.out.println("Introduce un numero por favor.");
                sc.nextLine();
            }
    	} while (op != 6);
    }
	
}
