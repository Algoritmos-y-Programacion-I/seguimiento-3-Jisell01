package model;

public class Incident {

    private String description;
    private boolean solved;
    private int hoursUsed;

    public Incident(String description) {
        this.description = description;
        this.solved = false;
        this.hoursUsed = 0;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public boolean isSolved() {
        return solved;
    }

    public int getHoursUsed() {
        return hoursUsed;
    }

    /**
     * Marca el incidente como resuelto.
     * Pre: hours >= 0
     * Post: El incidente cambia a resuelto y guarda las horas utilizadas.
     */
    public void resolve(int hours) {
        this.solved = true;
        this.hoursUsed = hours;
    }

    @Override
    public String toString() {
        return "Incidente: " + description +
               " | Estado: " + (solved ? "Resuelto" : "Pendiente") +
               " | Horas usadas: " + hoursUsed;
    }
}
