import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// enum connectable{
//     WIFI, BLUETOOTH
// }

// enum lockable{
//     Terbuka, Terkunci
// }

// enum switchable{
//     Menyala, Mati
// }


interface connectable{
    String konek();
}
interface lockable{
    String kunci();
}
interface switchable{
    String turning();
}

abstract class SmartDevice {
    private String id;
    private String nama;
    private int daya;
    private String channel;
    private int volume;

    public SmartDevice (String id, String nama, int daya, String channel, int volume){
        this.id = nama;
        this.nama = nama;
        this.daya = daya;
        this.channel = channel;
        this.volume = volume;
    }


    public String getId(){
        return id;
    }
    public String getName(){
        return nama;
    }
    public int getDaya(){
        return daya;
    }
    public String getChannel(){
        return channel;
    }
}

abstract class SmartTV extends SmartDevice {

    public SmartTV (String id, String nama, int daya, String channel, int volume){
        super(id, nama, daya, channel, volume);
    }

    @Override 
    public String konek(){
        return "Koneksi: WIFI"
    }
    @Override 
    public String switch(){
        return "Status: Menyala"
    }
    @Override 
    public String getPrintDetail(){
        return "Smart TV" + nama + "";
    }
}

abstract class SmartSpeaker extends SmartDevice {
    private final connectable konektifitas;
    private final switchable turning;

    public SmartSpeaker (String id, String nama, int daya, String channel, int volume){
        super(id, nama, daya, channel, volume);

    }


    @Override 
    public String konek(){
        return "Koneksi: BLUETOOTH"
    }
    @Override 
    public String switch(){
        return "Status: Menyala"
    }
    @Override 
    public String getPrintDetail(){
        return "Smart Speaker" + nama + "";
    }
}


abstract class SmartDoor extends SmartDevice {
    private final connectable konektifitas;
    private final switchable turning;

    public SmartDoor (String id, String nama, int daya, String channel, int volume){
        super(id, nama, daya, channel, volume);

    }


    @Override 
    public String konek(){
        return "Koneksi: WIFI"
    }
    @Override 
    public String switch(){
        return "Status: Menyala"
    }
    @Override 
    public String getPrintDetail(){

        return "Smart Door" + nama + "";
    }
}




public class ppbo_kuis1_1124014_jasongabriel {
    public static void main(String[] args){
        
    }
}
