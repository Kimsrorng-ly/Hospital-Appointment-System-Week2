import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String name;
    private String specialist;
    private double hourlyRate;
    private List<Schedule> availability;

    public Doctor(String name, String specialist, double hourlyRate, List<Schedule> availability) {
        // Even the constructor should use the setters to ensure validation logic is applied immediately
        setName(name);
        setSpecialist(specialist);
        this.hourlyRate = hourlyRate; // Or use a default password check if needed
        setAvailability(availability);
    }

    // --- SETTERS (Mutators) with Conditions ---

    public void setName(String name) {
        // Condition: Prevents "anonymous" doctors or accidental empty strings
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Name cannot be empty. Setting to 'Unknown Doctor'.");
            this.name = "Unknown Doctor";
        } else {
            this.name = name;
        }
    }

    public void setSpecialist(String specialist) {
        // Condition: Ensures the doctor has a valid department
        if (specialist == null || specialist.trim().isEmpty()) {
            System.out.println("Error: Specialist field required. Setting to 'General Practice'.");
            this.specialist = "General Practice";
        } else {
            this.specialist = specialist;
        }
    }

    public void setHourlyRate(double hourlyRate, String accessCode) {
        // Condition 1: Security check
        if (!accessCode.equals("ADMIN123")) {
            System.out.println("Access Denied: Incorrect password to modify salary.");
            return;
        }
        // Condition 2: Data Integrity (A doctor cannot pay the hospital to work)
        if (hourlyRate < 0) {
            System.out.println("Error: Rate cannot be negative.");
        } else {
            this.hourlyRate = hourlyRate;
        }
    }

    public void setAvailability(List<Schedule> availability) {
        // Condition: A doctor must have at least one day assigned, or the list must not be null
        if (availability == null || availability.isEmpty()) {
            System.out.println("Warning: Doctor has no assigned schedule.");
            this.availability = new ArrayList<>(); // Initialize empty list to avoid NullPointerErrors
        } else {
            this.availability = availability;
        }
    }

    // --- GETTERS (Accessors) with Conditions ---

    public String getName() {
        // Condition: Format the output for professional display
        if (this.name == null) return "No Name Assigned";
        return "Dr. " + this.name;
    }

    public String getSpecialist() {
        // Condition: Logic to return a readable string
        return this.specialist.toUpperCase(); 
    }

    public double getHourlyRate() {
        // Logic: You could add a condition here to check if the user is authorized 
        // to see the salary, otherwise return 0.0
        return hourlyRate;
    }

    public List<Schedule> getAvailability() {
        // Condition: Logic to ensure we don't return a null list that might crash the app
        if (this.availability == null) {
            return new ArrayList<>();
        }
        return availability;
    }
}
