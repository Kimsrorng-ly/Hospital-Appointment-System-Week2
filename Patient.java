public class Patient {
    private String name;
    private int age;
    private String gender;
    private String date_of_birth; // Format: YYYY-MM-DD
    private String phone;

    public Patient(String name, int age, String gender, String date_of_birth, String phone) {
        // Always call setters in the constructor to trigger the validation logic
        setName(name);
        setAge(age);
        setGender(gender);
        setDate_of_birth(date_of_birth);
        setPhone(phone);
    }

    // --- SETTERS (Mutators) ---

    public void setName(String name) {
        // Logic: A patient name cannot be a number or empty.
        if (name == null || name.trim().length() < 2) {
            this.name = "Invalid Name";
        } else {
            this.name = name;
        }
    }

    public void setAge(int age) {
        // Logic: Age cannot be negative, and logically in a hospital, 
        // someone over 150 is likely a data entry error.
        if (age < 0 || age > 150) {
            System.out.println("Error: Age is invalid. Setting to 0.");
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    public void setGender(String gender) {
        // Logic: Restrict to specific medical categories
        String g = gender.toLowerCase();
        if (g.equals("male") || g.equals("female") || g.equals("other")) {
            this.gender = gender;
        } else {
            this.gender = "Unknown";
        }
    }

    public void setDate_of_birth(String date_of_birth) {
        // Logic: Basic check to ensure it's not an empty string
        // (In a real app, you would check if the date is in the future)
        if (date_of_birth == null || !date_of_birth.contains("-")) {
            this.date_of_birth = "0000-00-00";
        } else {
            this.date_of_birth = date_of_birth;
        }
    }

    public void setPhone(String phone) {
        // Logic: A phone number should usually have at least 10 digits
        if (phone != null && phone.replaceAll("[^0-9]", "").length() >= 10) {
            this.phone = phone;
        } else {
            this.phone = "Invalid Phone Number";
        }
    }

    // --- GETTERS (Accessors) ---

    public String getName() {
        // Logic: Standardize medical records to uppercase for clarity
        return name.toUpperCase();
    }

    public int getAge() {
        // Logic: If the patient is a minor (under 18), we could trigger a warning
        if (age < 18) {
            System.out.println("[Note: Patient is a minor]");
        }
        return age;
    }

    public String getGender() {
        // Logic: Return a standardized format
        return gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase();
    }

    public String getDate_of_birth() {
        return "DOB: " + date_of_birth;
    }

    public String getPhone() {
        // Logic: Mask the phone number for privacy (Security logic)
        // Show only the last 4 digits: *******1234
        if (phone.length() > 4) {
            return "*******" + phone.substring(phone.length() - 4);
        }
        return phone;
    }
}