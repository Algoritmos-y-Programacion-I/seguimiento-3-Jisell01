package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SchoolController {

    private Computer[][] matrix;
    private ArrayList<Computer> computers;
    private int totalHoursUsed;

    // Constantes
    public static final int FLOORS = 5;
    public static final int COLUMNS = 10;
    public static final int MAX_HOURS = 100;

    public SchoolController(int floors, int columns) {
        matrix = new Computer[floors][columns];
        computers = new ArrayList<>();
        totalHoursUsed = 0;
    }

    /**
     * Registra un computador nuevo en un piso disponible.
     * Pre: serial != null && 0 <= floor < número de pisos
     * Post: El computador se agrega si el serial no existe y hay espacio.
     */
    public String addComputer(String serial, int floor) {
        if (floor < 0 || floor >= matrix.length) {
            return "El número de piso no es válido. (0 - " + (matrix.length - 1) + ")";
        }

        for (Computer c : computers) {
            if (c.getSerial().equals(serial)) {
                return "Ya existe un computador con ese serial.";
            }
        }

        for (int col = 0; col < matrix[floor].length; col++) {
            if (matrix[floor][col] == null) {
                Computer comp = new Computer(serial, floor, col);
                matrix[floor][col] = comp;
                computers.add(comp);
                return "Computador agregado en el piso " + floor + ", columna " + col;
            }
        }

        return "No hay espacio disponible en este piso.";
    }

    /**
     * Registra un incidente para un computador.
     * Pre: serial != null && description != null
     * Post: Se agrega el incidente al computador indicado si existe.
     */
    public String addIncident(LocalDate date, String serial, String description) {
        Computer comp = findComputer(serial);
        if (comp == null) {
            return "No se encontró un computador con ese serial.";
        }
        comp.addIncident(new Incident(description));
        return "Incidente registrado en el computador con serial " + serial;
    }

    /**
     * Resuelve un incidente asignándole horas.
     * Pre: serial != null && index >= 0 && hours >= 0
     * Post: El incidente cambia a resuelto si existe y no supera las horas máximas.
     */
    public String resolveIncident(String serial, int index, int hours) {
        Computer comp = findComputer(serial);
        if (comp == null) {
            return "El computador no existe.";
        }
        if (index < 0 || index >= comp.getIncidents().size()) {
            return "El número de incidente no es válido.";
        }
        if (totalHoursUsed + hours > MAX_HOURS) {
            return "No se puede registrar, se superarían las " + MAX_HOURS + " horas totales.";
        }

        comp.getIncidents().get(index).resolve(hours);
        totalHoursUsed += hours;
        return "Incidente resuelto. Horas usadas: " + totalHoursUsed + "/" + MAX_HOURS;
    }

    /**
     * Busca el computador con más incidentes.
     * Pre: Ninguna
     * Post: Retorna la información del computador con más incidentes.
     */
    public String showComputerWithMostIncidents() {
        if (computers.isEmpty()) {
            return "No hay computadores registrados.";
        }

        Computer max = computers.get(0);
        int maxCount = max.getIncidents().size();

        for (Computer c : computers) {
            if (c.getIncidents().size() > maxCount) {
                maxCount = c.getIncidents().size();
                max = c;
            }
        }

        return "Computador con más incidentes:\n" +
               "Serial: " + max.getSerial() + "\n" +
               "Piso: " + max.getFloor() + "\n" +
               "Columna: " + max.getColumn() + "\n" +
               "Cantidad de incidentes: " + maxCount;
    }

    /**
     * Muestra todos los computadores y sus incidentes.
     * Pre: Ninguna
     * Post: Devuelve la lista completa de computadores con sus incidentes.
     */
    public String listComputers() {
        if (computers.isEmpty()) {
            return "No hay computadores registrados todavía.";
        }

        String text = "";
        for (Computer c : computers) {
            text += "Computador [Serial: " + c.getSerial() +
                    ", Piso: " + c.getFloor() +
                    ", Columna: " + c.getColumn() + "]\n";
            if (c.getIncidents().isEmpty()) {
                text += "   No tiene incidentes.\n";
            } else {
                for (int i = 0; i < c.getIncidents().size(); i++) {
                    text += "   " + i + ") " + c.getIncidents().get(i).toString() + "\n";
                }
            }
        }
        return text;
    }

    /**
     * Busca un computador por su serial.
     * Pre: serial != null
     * Post: Retorna el computador si se encuentra, o null si no.
     */
    private Computer findComputer(String serial) {
        for (Computer c : computers) {
            if (c.getSerial().equals(serial)) {
                return c;
            }
        }
        return null;
    }
}
