import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static final String FILE_NAME = "coches.dat";

	public static void main(String[] args) {
		ArrayList<Coche> coches = new ArrayList<>();

		leerArchivo(coches);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            printMenu();
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            exit = handleOption(option, coches);
        }
        
        scanner.close();
	}
	
	// Metodo para comprobar que existe el archivo y leerlo. Le pasamos el ArrayList coches
    private static void leerArchivo(ArrayList<Coche> coches) {    	
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
   			 ObjectInputStream ois = new ObjectInputStream(fis);) {
        	
            coches = (ArrayList<Coche>)ois.readObject();

			System.out.println("Imprimiendo stock almacen");
			
			for (Coche coche: coches) {
				System.out.println(coche.toString());
			}
            
        } catch (FileNotFoundException e) {
            // file does not exist, do nothing
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
	// Metodo para escribir en el archivo
    private static void escribirArchivo(ArrayList<Coche> coches) {
		File file = new File(FILE_NAME);
		
    	try(FileOutputStream fos = new FileOutputStream(file);
    		ObjectOutputStream oos = new ObjectOutputStream(fos)) {
    		oos.writeObject(coches);
    		System.out.println("Stock actualizado");
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	// Add un coche al stock
    private static void addCoche(ArrayList<Coche> coches) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el id del coche: ");
        String id = scanner.next();
        System.out.print("Introduce la matrícula del coche: ");
        String matricula = scanner.next();
        System.out.print("Introduce la marca del coche: ");
        String marca = scanner.next();
        System.out.print("Introduce el modelo del coche: ");
        String modelo = scanner.next();
        System.out.print("Introduce el color del coche: ");
        String color = scanner.next();

        Coche coche = new Coche(id, matricula, marca, modelo, color);
        coches.add(coche);        
    }
    
    // borra por id
    private static void deleteById(ArrayList<Coche> coches) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Introduce el id del coche a eliminar");
    	String id = scanner.next();
    	
    	// loopeamos el arrayList y borramos el coche con id indicado
    	for (int i = 0; i < coches.size(); i++) {
    		if (coches.get(i).getId().equals(id)) {
    			coches.remove(i);
    			System.out.println("Coche eliminado con éxito");
    			return;
    		}
    	}
    	
    	System.out.println("No se ha encontrado ningún coche con ese id");
    }
    
    // busca por id
    private static void busquedaId(ArrayList<Coche> coches) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el id del coche a buscar: ");
        String id = scanner.next();
        for (Coche coche : coches) {
            if (coche.getId().equals(id)) {
                System.out.println(coche.toString());
                return;
            }
        }
        System.out.println("No se ha encontrado ningún coche con ese id");
    }

    // lista todo el arrayList
    private static void listarCoches(ArrayList<Coche> coches) {
        if (coches.isEmpty()) {
            System.out.println("No hay coches en el almacén");
        } else {
            for (Coche coche : coches) {
                System.out.println(coche.toString());
            }
        }
    }
    
    private static void crearCSV(ArrayList<Coche> coches) {
        try (PrintWriter writer = new PrintWriter(new File("coches.csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("id,matricula,marca,modelo,color");
            sb.append(System.lineSeparator());
            for (Coche coche : coches) {
                sb.append(coche.getId());
                sb.append(",");
                sb.append(coche.getmatricula());
                sb.append(",");
                sb.append(coche.getmarca());
                sb.append(",");
                sb.append(coche.getmodelo());
                sb.append(",");
                sb.append(coche.getColor());
                sb.append(System.lineSeparator());
            }
            writer.write(sb.toString());
            System.out.println("Archivo 'coches.csv' ha sido creado con exito");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // imprimimos el menu
	private static void printMenu() {
		System.out.println("###########################################################################");
        System.out.println("#                                                                         #");
        System.out.println("#  > 1. Añadir nuevo coche                                                #");
        System.out.println("#  > 2. Borrar coche por id                                               #");
        System.out.println("#  > 3. Consulta coche por id                                             #");
        System.out.println("#  > 4. Listado de coches                                                 #");
        System.out.println("#  > 5. Terminar el programa                                              #");
        System.out.println("#  > 6. Popular stock                                                     #");
        System.out.println("#  > 7. Imprimir a CSV                                                    #");
        System.out.println("#                                                                         #");
        System.out.println("###########################################################################");
    }
	
    // switch para el menu
    public static boolean handleOption(int option,ArrayList<Coche> coches) {
        switch (option) {
            case 1:
            	addCoche(coches);
                break;
            case 2:
            	deleteById(coches);
                break;
            case 3:
            	busquedaId(coches);
                break;
            case 4:
            	listarCoches(coches);
                break;
            case 5:
            	escribirArchivo(coches);
            	return true;
            case 6:
            	llenarAlmacen(coches);
            	break;
            case 7:
            	crearCSV(coches);
            	break;
            default:
                System.out.println("Opción no válida.");
        }
        return false;
    }
    
    private static void llenarAlmacen(ArrayList<Coche> coches) {
        coches.add(new Coche("1", "ABC-123", "Honda", "Accord", "Red"));
        coches.add(new Coche("2", "DEF-456", "Toyota", "Camry", "Blue"));
        coches.add(new Coche("3", "GHI-789", "Ford", "F150", "White"));
        coches.add(new Coche("4", "JKL-012", "Chevrolet", "Silverado", "Black"));
        coches.add(new Coche("5", "MNO-345", "Tesla", "Model S", "Silver"));
    }

}
