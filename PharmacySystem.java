import java.util.ArrayList;
import java.util.Scanner;

class Medicine {
    private int id;
    private String name;
    private String manufacturer;
    private double price;
    private int quantity;

    public Medicine(int id, String name, String manufacturer, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Mfr: " + manufacturer + 
               " | Price: $" + price + " | Qty: " + quantity;
    }
}

class PharmacyManager {
    private ArrayList<Medicine> inventory;
    private int nextId;

    public PharmacyManager() {
        inventory = new ArrayList<>();
        nextId = 1;
    }

    public void addMedicine(String name, String manufacturer, double price, int quantity) {
        Medicine newMed = new Medicine(nextId++, name, manufacturer, price, quantity);
        inventory.add(newMed);
        System.out.println("✅ Medicine added successfully! ID: " + newMed.getId());
    }

    public void viewAllMedicines() {
        if (inventory.isEmpty()) {
            System.out.println("⚠️ Inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory ---");
        for (Medicine med : inventory) {
            System.out.println(med);
        }
        System.out.println("-------------------------");
    }

    public void searchMedicine(String name) {
        boolean found = false;
        for (Medicine med : inventory) {
            if (med.getName().equalsIgnoreCase(name)) {
                System.out.println("🔎 Found: " + med);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("❌ Medicine not found.");
        }
    }

    public void updateMedicine(int id, double newPrice, int newQuantity) {
        for (Medicine med : inventory) {
            if (med.getId() == id) {
                med.setPrice(newPrice);
                med.setQuantity(newQuantity);
                System.out.println("✅ Medicine updated successfully.");
                return;
            }
        }
        System.out.println("❌ Medicine ID not found.");
    }

    public void sellMedicine(int id, int qtyToSell) {
        for (Medicine med : inventory) {
            if (med.getId() == id) {
                if (med.getQuantity() >= qtyToSell) {
                    double totalCost = med.getPrice() * qtyToSell;
                    med.setQuantity(med.getQuantity() - qtyToSell);
                    System.out.println("🧾 Sale Successful! Total Cost: $" + totalCost);
                    System.out.println("Remaining Stock: " + med.getQuantity());
                } else {
                    System.out.println("❌ Not enough stock. Available: " + med.getQuantity());
                }
                return;
            }
        }
        System.out.println("❌ Medicine ID not found.");
    }
}

public class PharmacySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PharmacyManager manager = new PharmacyManager();
        
        boolean running = true;

        System.out.println("💊 Welcome to Pharmacy Management System 💊");

        manager.addMedicine("Paracetamol", "ABC Pharma", 5.50, 100);
        manager.addMedicine("Amoxicillin", "HealthCare Inc", 12.00, 50);

        while (running) {
            System.out.println("\n1. Add Medicine");
            System.out.println("2. View All Medicines");
            System.out.println("3. Search Medicine");
            System.out.println("4. Update Medicine");
            System.out.println("5. Sell Medicine");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Manufacturer: ");
                    String mfr = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    manager.addMedicine(name, mfr, price, qty);
                    break;

                case 2:
                    manager.viewAllMedicines();
                    break;

                case 3:
                    System.out.print("Enter Medicine Name to search: ");
                    String searchName = scanner.nextLine();
                    manager.searchMedicine(searchName);
                    break;

                case 4:
                    System.out.print("Enter Medicine ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new Price: ");
                    double newPrice = scanner.nextDouble();
                    System.out.print("Enter new Quantity: ");
                    int newQty = scanner.nextInt();
                    manager.updateMedicine(updateId, newPrice, newQty);
                    break;

                case 5:
                    System.out.print("Enter Medicine ID to sell: ");
                    int sellId = scanner.nextInt();
                    System.out.print("Enter quantity to sell: ");
                    int sellQty = scanner.nextInt();
                    manager.sellMedicine(sellId, sellQty);
                    break;

                case 6:
                    running = false;
                    System.out.println("👋 Exiting System. Goodbye!");
                    break;

                default:
                    System.out.println("⚠️ Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}