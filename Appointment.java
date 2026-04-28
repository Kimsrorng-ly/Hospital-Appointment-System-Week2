public class Appointment {
    private String id;
    private Patient patient;
    private Doctor doctor;
    private String day;
    private String time;
    private double fee;

    public Appointment(String id, Patient patient, Doctor doctor, String day, String time) {
        this.id = id; // Set once
        this.patient = patient;
        this.doctor = doctor;
        setDay(day);   // Use setter for logic
        setTime(time); // Use setter for logic
        // Fee logic: Captured at the moment of booking
        this.fee = (doctor != null) ? doctor.getHourlyRate() : 0.0;
    }

    // --- GETTERS (Accessors) ---

    public String getId() {
        // Logic: Standardize how IDs look to the user
        return "REF-" + id.toUpperCase();
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        // Logic: Adds "H" for Hours or "Clock" symbol for UI
        return time + " (Scheduled)";
    }

    public double getFee() {
        // Logic: Automatically adds a 10% hospital service tax to the doctor's rate
        return fee * 1.10; 
    }

    // --- SETTERS (Mutators) ---

    public void setDay(String day) {
        // Logic: Hospital is closed on Sundays. 
        // If someone tries to book Sunday, it defaults to Monday.
        if (day.equalsIgnoreCase("Sunday")) {
            this.day = "Monday (Defaulted)";
        } else {
            this.day = day;
        }
    }

    public void setTime(String time) {
        // Logic: Simple check to ensure time isn't an empty string
        // Real-world: You could parse the string to ensure it's between 09:00 and 17:00
        if (time == null || time.isEmpty()) {
            this.time = "09:00 AM";
        } else {
            this.time = time;
        }
    }

    // Note: We do NOT provide setFee because the fee is tied to the Doctor's rate.
    // Note: We do NOT provide setPatient because you shouldn't swap patients on a receipt.

    @Override
    public String toString() {
        return "Receipt " + getId() + " | Patient: " + patient.getName() + 
               " | Doctor: " + doctor.getName() + " | Fee: $" + getFee();
    }
}
