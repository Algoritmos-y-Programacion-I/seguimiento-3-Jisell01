package model;
import java.time.LocalDate;

public class Incident { 

    private LocalDate Report; 
    private String description; 
    private boolean solution; 
    private int solutionHours;

    public incident (LocalDate Report, String description) {
        this.Report = Report; 
        this.description = description; 
    }

    public LocalDate getDateReport() {
        return dateReport;
    }

    public String getDescription () {
        return description;
    }

    public boolean getSolution () {
        return solution;
    }

    public int getSolutionHours () {
        return solutionHours;
    }

    public void setDateReport(LocalDate Report) {
        this.Report = dateReport; 
    }

    public void setDescription(String description) {
        this.Description = description; 
    }                                  

    public void setSolution(boolean solution){
        this.Solution = solution;
    }                            

    public void setSolutionHours(int solutionHours) {
        this.SolutionHours = solutionHours;
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
}