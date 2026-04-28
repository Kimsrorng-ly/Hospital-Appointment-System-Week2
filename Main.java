import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        // 1. CREATE A PATIENT
        // Logic Test: Try to set a negative age and an empty name
        Patient patient1 = new Patient("", -5, "Male", "2000-01-01", "12345");
        
        // Let's fix it with valid data
        patient1.setName("John Doe");
        patient1.setAge(25);
        patient1.setPhone("01234567890"); // Valid 11 digit number

        // 2. CREATE SCHEDULE & SLOTS
        // Logic Test: Creating a list of available hours
        List<TimeSlot> mondaySlots = new ArrayList<>();
        mondaySlots.add(new TimeSlot("09:00", "10:00"));
        mondaySlots.add(new TimeSlot("10:00", "11:00"));

        Schedule mondaySchedule = new Schedule("monday", mondaySlots, "09:00", "17:00", true);

        // 3. CREATE A DOCTOR
        List<Schedule> docAvailability = new ArrayList<>();
        docAvailability.add(mondaySchedule);
        
        Doctor doctor1 = new Doctor("Gregory House", "Diagnostic Medicine", 150.0, docAvailability);

        // Logic Test: Try to change hourly rate with WRONG password
        doctor1.setHourlyRate(400.0, "Hello123"); 
        
        // Logic Test: Change hourly rate with CORRECT password
        doctor1.setHourlyRate(200.0, "ADMIN123");

        // 4. CREATE AN APPOINTMENT (The Bridge)
        // This links Patient1 and Doctor1 together
        Appointment app1 = new Appointment("101", patient1, doctor1, "Monday", "09:00");

        // 5. DISPLAY THE RESULTS
        System.out.println("--- HOSPITAL SYSTEM REPORT ---");
        
        // Check Patient (Should be Uppercase due to Getter logic)
        System.out.println("Patient Name: " + patient1.getName()); 
        System.out.println("Patient Phone (Masked): " + patient1.getPhone());
        
        // Check Doctor
        System.out.println("Doctor: " + doctor1.getName() + " | Rate: $" + doctor1.getHourlyRate());

        // Check Appointment & Fee (Fee should include the 10% tax from our Getter logic)
        System.out.println("\n--- APPOINTMENT RECEIPT ---");
        System.out.println(app1.toString());
        System.out.println("Final Fee (incl. tax): $" + app1.getFee());
    }
}

// Simple TimeSlot class so the code can compile
class TimeSlot {
    private String start;
    private String end;

    public TimeSlot(String start, String end) {
        setStart(start);
        setEnd(end);
    }

    // --- GETTER with Logic ---
    public String getStart() {
        // Logic: Format for the UI
        return start + " hrs";
    }

    public String getEnd() {
        return end + " hrs";
    }

    // --- SETTER with Logic ---
    public void setStart(String start) {
        // Logic: Basic validation to ensure time isn't empty
        if (start == null || start.isEmpty()) {
            this.start = "00:00";
        } else {
            this.start = start;
        }
    }

    public void setEnd(String end) {
        // Logic: Basic validation
        if (end == null || end.isEmpty()) {
            this.end = "00:00";
        } else {
            this.end = end;
        }
    }
}

