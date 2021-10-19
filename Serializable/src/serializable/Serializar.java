package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Serializar {
	static ArrayList <Coche> listaCoches = new ArrayList<Coche>();
    static Scanner sc = new Scanner(System.in);
    static File archivo = new File("D:\\Acceso a Datos\\Coches.bin");
    
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
    	int op = 0;
    	
    	do {
    		System.out.println("Que quieres hacer?\n**********************************************");
            System.out.println("1. Anyadir un objeto a la lista");
            System.out.println("2. Importar de archivo");
            System.out.println("3. Exportar a archivo");
            System.out.println("4. Mostrar lista");
            System.out.println("5. Salir");
            
            try {
                op = sc.nextInt();
                sc.nextLine();
                
                switch (op) {
                    case 1:
                        anyadirCoche();
                        break;
                    case 2:
                        Deserializar();
                        break;
                    case 3:
                        Serializar();
                        break;
                    case 4:
                        mostrarTodo();
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch(InputMismatchException e) {
                System.out.println("Introduce un numero por favor.");
                sc.nextLine();
            }
    	} while (op != 5);
    }
	
}
