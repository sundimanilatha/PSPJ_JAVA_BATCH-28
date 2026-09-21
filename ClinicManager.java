import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Comparator;
import java.util.stream.Collectors;
public class ClinicManager {
    static Scanner sc = new Scanner(System.in);
    // =========================================================
    // MODULE 4 - CLASSES AND OBJECTS
    // =========================================================
    // Patient class
    static class Patient {
        private int patientId;
        private String name;
        private int age;
        private String phone;
        // Parameterized constructor
        Patient(int patientId, String name, int age, String phone) {
            this.patientId = patientId;
            this.name = name;
            this.age = age;
            this.phone = phone;
        }
        // Getters
        int getPatientId() {
            return patientId;
        }
        String getName() {
            return name;
        }
        int getAge() {
            return age;
        }
        String getPhone() {
            return phone;
        }
        @Override
        public String toString() {
            return "ID: " + patientId +
                   " | Name: " + name +
                   " | Age: " + age +
                   " | Phone: " + phone;
        }
    }
    // Doctor class
    static class Doctor {
        private int doctorId;
        private String name;
        private String specialization;
        Doctor(int doctorId, String name, String specialization) {
            this.doctorId = doctorId;
            this.name = name;
            this.specialization = specialization;
        }
        int getDoctorId() {
            return doctorId;
        }
        String getName() {
            return name;
        }
        String getSpecialization() {
            return specialization;
        }
        @Override
        public String toString() {
            return "ID: " + doctorId +
                   " | Dr. " + name +
                   " | Specialization: " + specialization;
        }
    }
    // Appointment class
    static class Appointment {
        private int appointmentId;
        private int patientId;
        private int doctorId;
        private String date;
        private String time;
        private String status;
        Appointment(int appointmentId, int patientId, int doctorId,
                    String date, String time) {
            this.appointmentId = appointmentId;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.date = date;
            this.time = time;
            this.status = "Booked";
        }
        int getAppointmentId() {
            return appointmentId;
        }
        int getPatientId() {
            return patientId;
        }
        int getDoctorId() {
            return doctorId;
        }
        String getDate() {
            return date;
        }
        String getTime() {
            return time;
        }
        String getStatus() {
            return status;
        }
        void cancelAppointment() {
            status = "Cancelled";
        }
        @Override
        public String toString() {
            return "Appointment ID: " + appointmentId +
                   " | Patient ID: " + patientId +
                   " | Doctor ID: " + doctorId +
                   " | Date: " + date +
                   " | Time: " + time +
                   " | Status: " + status;
        }
    }
    // Prescription class
    static class Prescription {
        private int prescriptionId;
        private int patientId;
        private int doctorId;
        private String medicines;
        private String instructions;
        Prescription(int prescriptionId, int patientId, int doctorId,
                     String medicines, String instructions) {
            this.prescriptionId = prescriptionId;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.medicines = medicines;
            this.instructions = instructions;
        }
        @Override
        public String toString() {
            return "Prescription ID: " + prescriptionId +
                   " | Patient ID: " + patientId +
                   " | Doctor ID: " + doctorId +
                   " | Medicines: " + medicines +
                   " | Instructions: " + instructions;
        }
    }
    // =========================================================
    // MODULE 6 - COLLECTIONS
    // =========================================================
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static ArrayList<Prescription> prescriptions = new ArrayList<>();
    // HashMap for quick patient lookup
    static HashMap<Integer, Patient> patientMap = new HashMap<>();
    // HashSet for storing unique specializations
    static HashSet<String> specializations = new HashSet<>();
    // =========================================================
    // MODULE 1 - INPUT AND BASIC OPERATIONS
    // =========================================================
    static void addPatient() {
        System.out.println("\n--- ADD PATIENT ---");
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();
        // Module 2 - if condition
        if (age <= 0) {
            System.out.println("Invalid age.");
            return;
        }
        Patient p = new Patient(id, name, age, phone);
        patients.add(p);
        patientMap.put(id, p);
        System.out.println("Patient registered successfully.");
    }
    // =========================================================
    // ADD DOCTOR
    // =========================================================
    static void addDoctor() {
        System.out.println("\n--- ADD DOCTOR ---");
        System.out.print("Enter Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Doctor Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine().trim();
        Doctor d = new Doctor(id, name, specialization);
        doctors.add(d);
        specializations.add(specialization);
        System.out.println("Doctor added successfully.");
    }
    // =========================================================
    // MODULE 3 - METHOD AND SEARCHING
    // =========================================================
    static Patient findPatient(int id) {

        // Module 6 - HashMap lookup
        return patientMap.get(id);
    }
    static Doctor findDoctor(int id) {
        for (Doctor d : doctors) {
            if (d.getDoctorId() == id) {
                return d;
            }
        }
        return null;
    }
    // =========================================================
    // BOOK APPOINTMENT
    // =========================================================
    static void bookAppointment() {
        System.out.println("\n--- BOOK APPOINTMENT ---");
        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();
        Patient p = findPatient(patientId);
        Doctor d = findDoctor(doctorId);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }
        if (d == null) {
            System.out.println("Doctor not found.");
            return;
        }
        System.out.print("Enter Date (DD-MM-YYYY): ");
        String date = sc.nextLine();
        System.out.print("Enter Time: ");
        String time = sc.nextLine();
        // Generate appointment ID
        int appointmentId = appointments.size() + 1;
        Appointment a = new Appointment(
                appointmentId,
                patientId,
                doctorId,
                date,
                time
        );
        appointments.add(a);
        System.out.println("\nAppointment booked successfully!");
        System.out.println(a);
    }
    // =========================================================
    // MODULE 5 - STRING OPERATIONS
    // =========================================================
    static void createPrescription() {
        System.out.println("\n--- CREATE PRESCRIPTION ---");
        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();
        Patient p = findPatient(patientId);
        Doctor d = findDoctor(doctorId);
        if (p == null || d == null) {
            System.out.println("Invalid patient or doctor ID.");
            return;
        }
        System.out.print("Enter medicines separated by comma: ");
        String medicines = sc.nextLine().trim();
        System.out.print("Enter instructions: ");
        String instructions = sc.nextLine().trim();
        // Module 5 - split()
        String[] medicineList = medicines.split(",");
        System.out.println("\nMedicines:");
        for (String medicine : medicineList) {
            System.out.println("- " + medicine.trim());
        }
        int prescriptionId = prescriptions.size() + 1;
        Prescription prescription = new Prescription(
                prescriptionId,
                patientId,
                doctorId,
                medicines,
                instructions
        );
        prescriptions.add(prescription);

        System.out.println("\nPrescription created successfully!");
    }
    // =========================================================
    // DISPLAY PATIENTS
    // =========================================================
    static void displayPatients() {
        System.out.println("\n--- PATIENT LIST ---");
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }
        // Module 3 - enhanced for loop
        for (Patient p : patients) {
            System.out.println(p);
        }
    }
    // =========================================================
    // DISPLAY DOCTORS
    // =========================================================
    static void displayDoctors() {
        System.out.println("\n--- DOCTOR LIST ---");
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }
        for (Doctor d : doctors) {
            System.out.println(d);
        }
        System.out.println("\nAvailable Specializations:");
        // Module 6 - HashSet
        for (String s : specializations) {
            System.out.println("- " + s);
        }
    }
    // =========================================================
    // DISPLAY APPOINTMENTS
    // =========================================================
    static void displayAppointments() {
        System.out.println("\n--- APPOINTMENT LIST ---");
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }
        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }
    // =========================================================
    // DISPLAY PRESCRIPTIONS
    // =========================================================
    static void displayPrescriptions() {
        System.out.println("\n--- PRESCRIPTION LIST ---");
        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions available.");
            return;
        }
        for (Prescription p : prescriptions) {
            System.out.println(p);
        }
    }
    // =========================================================
    // MODULE 3 - SEARCHING
    // =========================================================
    static void searchAppointment() {
        System.out.println("\n--- SEARCH APPOINTMENT ---");
        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        boolean found = false;
        for (Appointment a : appointments) {
            if (a.getPatientId() == patientId) {
                System.out.println(a);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointment found.");
        }
    }
    // =========================================================
    // MODULE 6 - STREAMS
    // =========================================================
    static void appointmentReport() {
        System.out.println("\n--- APPOINTMENT REPORT ---");
        long total = appointments.size();
        long booked = appointments.stream()
                .filter(a -> a.getStatus().equals("Booked"))
                .count();
        long cancelled = appointments.stream()
                .filter(a -> a.getStatus().equals("Cancelled"))
                .count();
        System.out.println("Total Appointments : " + total);
        System.out.println("Booked Appointments: " + booked);
        System.out.println("Cancelled          : " + cancelled);
    }
    // =========================================================
    // MODULE 6 - SORTING USING STREAM
    // =========================================================
    static void sortAppointments() {
        System.out.println("\n--- SORTED APPOINTMENTS ---");
        ArrayList<Appointment> sortedAppointments =
                appointments.stream()
                .sorted(Comparator.comparing(Appointment::getDate))
                .collect(Collectors.toCollection(ArrayList::new));
        for (Appointment a : sortedAppointments) {
            System.out.println(a);
        }
    }
    // =========================================================
    // MODULE 2 - SWITCH AND LOOP
    // =========================================================
    static void menu() {
        int choice;
        // do-while loop
        do {
            System.out.println("\n========================================");
            System.out.println("   CLINIC APPOINTMENT & PRESCRIPTION");
            System.out.println("              MANAGER");
            System.out.println("========================================");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Book Appointment");
            System.out.println("4. Create Prescription");
            System.out.println("5. Display Patients");
            System.out.println("6. Display Doctors");
            System.out.println("7. Display Appointments");
            System.out.println("8. Display Prescriptions");
            System.out.println("9. Search Appointment");
            System.out.println("10. Appointment Report");
            System.out.println("11. Sort Appointments");
            System.out.println("12. Exit");
            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();
            // switch statement
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    addDoctor();
                    break;
                case 3:
                    bookAppointment();
                    break;
                case 4:
                    createPrescription();
                    break;
                case 5:
                    displayPatients();
                    break;
                case 6:
                    displayDoctors();
                    break;
                case 7:
                    displayAppointments();
                    break;
                case 8:
                    displayPrescriptions();
                    break;
                case 9:
                    searchAppointment();
                    break;
                case 10:
                    appointmentReport();
                    break;
                case 11:
                    sortAppointments();
                    break;
                case 12:
                    System.out.println("\nThank you for using Clinic Manager!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 12);
    }
    // =========================================================
    // MAIN METHOD - MODULE 1
    // =========================================================
    public static void main(String[] args) {
        System.out.println("Welcome to Clinic Appointment and Prescription Manager");
        // Sample doctors
        doctors.add(new Doctor(101, "Ravi Kumar", "Cardiology"));
        doctors.add(new Doctor(102, "Anita Sharma", "Dermatology"));
        specializations.add("Cardiology");
        specializations.add("Dermatology");
        menu();
        sc.close();
    }
}