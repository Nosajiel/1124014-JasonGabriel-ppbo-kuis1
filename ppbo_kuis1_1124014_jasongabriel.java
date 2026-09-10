import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum ConnectionType {
    WIFI, BLUETOOTH, NONE
}

enum DeviceStatus {
    MENYALA("Menyala"), 
    MATI("Mati"), 
    TERKUNCI("Terkunci"), 
    TERBUKA("Terbuka");

    private final String label;

    DeviceStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

interface Switchable {
    void turnOn();
    void turnOff();
}

interface Connectable {
    void connect(ConnectionType type);
    void disconnect();
}

interface Lockable {
    void lock();
    void unlock();
}

abstract class SmartDevice {
    private String id;
    private String nama;
    private double daya;
    private String status;

    public SmartDevice(String id, String nama, double daya, String initialStatus) {
        this.id = id;
        this.nama = nama;
        this.daya = daya;
        this.status = initialStatus;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public double getDaya() { return daya; }
    public String getStatus() { return status; }
    
    public void setStatus(String status) { this.status = status; }

    public abstract String getDeviceDetails();
}

class SmartTV extends SmartDevice implements Switchable, Connectable {
    private int channel;
    private int volume;
    private ConnectionType koneksi;

    public SmartTV(String id, String nama, double daya, int channel, int volume) {
        super(id, nama, daya, DeviceStatus.MATI.getLabel());
        this.channel = channel;
        this.volume = volume;
        this.koneksi = ConnectionType.NONE;
    }

    @Override
    public void turnOn() { setStatus(DeviceStatus.MENYALA.getLabel()); }

    @Override
    public void turnOff() { setStatus(DeviceStatus.MATI.getLabel()); }

    @Override
    public void connect(ConnectionType type) { this.koneksi = type; }

    @Override
    public void disconnect() { this.koneksi = ConnectionType.NONE; }

    @Override
    public String getDeviceDetails() {
        return String.format("• Smart TV [%s] (ID: %s) - Daya: %.1fW | Status: %s | Koneksi: %s | Channel: %d | Volume: %d",
                getNama(), getId(), getDaya(), getStatus(), koneksi, channel, volume);
    }
}

class SmartSpeaker extends SmartDevice implements Switchable, Connectable {
    private int volume;
    private ConnectionType koneksi;

    public SmartSpeaker(String id, String nama, double daya, int volume) {
        super(id, nama, daya, DeviceStatus.MATI.getLabel());
        this.volume = volume;
        this.koneksi = ConnectionType.NONE;
    }

    @Override
    public void turnOn() { setStatus(DeviceStatus.MENYALA.getLabel()); }

    @Override
    public void turnOff() { setStatus(DeviceStatus.MATI.getLabel()); }

    @Override
    public void connect(ConnectionType type) { this.koneksi = type; }

    @Override
    public void disconnect() { this.koneksi = ConnectionType.NONE; }

    @Override
    public String getDeviceDetails() {
        return String.format("• Smart Speaker [%s] (ID: %s) - Daya: %.1fW | Status: %s | Koneksi: %s | Volume: %d",
                getNama(), getId(), getDaya(), getStatus(), koneksi, volume);
    }
}

class SmartDoorLock extends SmartDevice implements Lockable {
    private String pin;

    public SmartDoorLock(String id, String nama, double daya, String pin) {
        super(id, nama, daya, DeviceStatus.TERKUNCI.getLabel());
        this.pin = pin;
    }

    @Override
    public void lock() { setStatus(DeviceStatus.TERKUNCI.getLabel()); }

    @Override
    public void unlock() { setStatus(DeviceStatus.TERBUKA.getLabel()); }

    @Override
    public String getDeviceDetails() {
        return String.format("• Smart Door Lock [%s] (ID: %s) - Daya: %.1fW | Status: %s | PIN: ****",
                getNama(), getId(), getDaya(), getStatus());
    }
}

class InputUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Input harus berupa angka!");
            System.out.print(prompt);
            scanner.next(); 
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Input harus berupa desimal/angka!");
            System.out.print(prompt);
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}

public class ppbo_kuis1_1124014_jasongabriel {
    public static void main(String[] args) {
        List<SmartDevice> deviceList = new ArrayList<>();

        while (true) {
            System.out.println("\n=== SMART HOME MENU ===");
            System.out.println("1. Tambah Perangkat");
            System.out.println("2. Print Semua Perangkat");
            System.out.println("3. Keluar");
            int choice = InputUtil.readInt("Pilih menu (1-3): ");

            if (choice == 1) {
                System.out.println("\n--- Tambah Perangkat ---");
                System.out.println("1. Smart TV");
                System.out.println("2. Smart Speaker");
                System.out.println("3. Smart Door Lock");
                int deviceChoice = InputUtil.readInt("Pilih jenis perangkat (1-3): ");

                String id = InputUtil.readString("Masukkan ID: ");
                String nama = InputUtil.readString("Masukkan Nama: ");
                double daya = InputUtil.readDouble("Masukkan Daya (W): ");

                if (deviceChoice == 1) {
                    int channel = InputUtil.readInt("Masukkan Channel Awal: ");
                    int volume = InputUtil.readInt("Masukkan Volume Awal: ");
                    SmartTV tv = new SmartTV(id, nama, daya, channel, volume);
                    tv.turnOn(); 
                    tv.connect(ConnectionType.WIFI); 
                    deviceList.add(tv);
                    System.out.println(" Smart TV berhasil ditambahkan!");

                } else if (deviceChoice == 2) {
                    int volume = InputUtil.readInt("Masukkan Volume Awal: ");
                    SmartSpeaker speaker = new SmartSpeaker(id, nama, daya, volume);
                    speaker.turnOn();
                    speaker.connect(ConnectionType.BLUETOOTH);
                    deviceList.add(speaker);
                    System.out.println(">> Smart Speaker berhasil ditambahkan!");

                } else if (deviceChoice == 3) {
                    String pin = InputUtil.readString("Masukkan PIN: ");
                    SmartDoorLock doorLock = new SmartDoorLock(id, nama, daya, pin);
                    doorLock.lock();
                    deviceList.add(doorLock);
                    System.out.println(" Smart Door Lock berhasil ditambahkan!");

                } else {
                    System.out.println("Pilihan perangkat tidak valid.");
                }

            } else if (choice == 2) {
                System.out.println("\n--- Daftar Semua Perangkat ---");
                if (deviceList.isEmpty()) {
                    System.out.println("Belum ada perangkat yang terdaftar.");
                } else {
                    for (SmartDevice device : deviceList) {
                        System.out.println(device.getDeviceDetails());
                    }
                }
            } else if (choice == 3) {
                System.out.println("Keluar dari program. Terima kasih!");
                break;
            } else {
                System.out.println("Menu tidak tersedia.");
            }
        }
    }
}