package ui;

import java.time.LocalDate;
import java.util.Scanner;
import model.SchoolController;

public class SchoolApp {

    private SchoolController controller;
    private Scanner input;

    public static void main(String[] args) {
        SchoolApp app = new SchoolApp();
        app.menu();
    }

    public SchoolApp() {
        input = new Scanner(System.in);
        controller = new SchoolController(5, 10);
    }

    /**
     * Muestra el menú principal del sistema.
     * Pre: Ninguna
     * Post: Permite al usuario interactuar con las opciones del sistema.
     */
    public void menu() {
        int option;
        System.out.println("=== Bienvenido al sistema de Computaricemos ===");

        do {
            System.out.println("\nMenú principal");
            System.out.println("1. Registrar computador");
            System.out.println("2. Registrar incidente");
            System.out.println("3. Consultar computador con más incidentes");
            System.out.println("4. Resolver incidente");
            System.out.println("5. Ver lista completa");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1 -> registerComputer();
                case 2 -> registerIncident();
                case 3 -> showMostIncidents();
                case 4 -> resolveIncident();
                case 5 -> listAll();
                case 0 -> System.out.println("Gracias por usar el sistema.");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }

        } while (option != 0);
    }

    /**
     * Permite registrar un nuevo computador.
     * Pre: serial != null y floor válido.
     * Post: Se agrega el computador si hay espacio.
     */
    public void registerComputer() {
        System.out.print("Ingrese el serial del computador: ");
        String serial = input.nextLine();
        System.out.print("Ingrese el piso (0-4): ");
        int floor = input.nextInt();
        input.nextLine();
        System.out.println(controller.addComputer(serial, floor));
    }

    /**
     * Permite agregar un incidente a un computador existente.
     * Pre: serial != null y description != null
     * Post: Se agrega el incidente al computador indicado.
     */
    public void registerIncident() {
        System.out.print("Fecha del reporte (AAAA-MM-DD): ");
        LocalDate date = LocalDate.parse(input.nextLine());
        System.out.print("Serial del computador: ");
        String serial = input.nextLine();
        System.out.print("Descripción del incidente: ");
        String description = input.nextLine();
        System.out.println(controller.addIncident(date, serial, description));
    }

    /**
     * Muestra el computador con más incidentes.
     * Pre: Deben existir computadores.
     * Post: Se muestra el computador con más incidentes.
     */
    public void showMostIncidents() {
        System.out.println(controller.showComputerWithMostIncidents());
    }

    /**
     * Permite resolver un incidente.
     * Pre: serial válido, index >= 0, hours >= 0
     * Post: El incidente pasa a estado resuelto.
     */
    public void resolveIncident() {
        System.out.print("Ingrese el serial del computador: ");
        String serial = input.nextLine();
        System.out.print("Número del incidente: ");
        int index = input.nextInt();
        System.out.print("Horas utilizadas: ");
        int hours = input.nextInt();
        input.nextLine();
        System.out.println(controller.resolveIncident(serial, index, hours));
    }

    /**
     * Muestra la lista completa de computadores e incidentes.
     * Pre: Ninguna
     * Post: Imprime la información por consola.
     */
    public void listAll() {
        System.out.println(controller.listComputers());
    }
}
