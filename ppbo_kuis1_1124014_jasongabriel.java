enum connectable{
    WIFI, BLUETOOTH
}

enum lockable{
    Terbuka, Terkunci
}

enum switchable{
    Menyala, Mati
}


abstract class elektronik {
    private String id;
    private String nama;
    private int daya;
    private String channel;
    private int volume;

    public elektronik (String id, String nama, int daya, String channel, int volume){
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

abstract class SmartTV extends elektronik {
    private final connectable konektifitas;
    private final switchable turning;

    public SmartTV (String id, String nama, int daya, String channel, int volume, connectable, switchable){
        super(id, nama, daya, channel, volume,);
        this.konektifitas = konektifitas;
        this.turning = turning;


    }
}

public class ppbo_kuis1_1124014_jasongabriel {
    public static void main(String[] args){
        
    }
}
