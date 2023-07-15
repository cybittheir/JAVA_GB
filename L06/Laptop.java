package L06;

public class Laptop {
    static int idc;
    int id;
    String brandN;
    String type;
    String modelN;
    String screenSize;
    String cpu;
    String memory;
    String diskType;
    String disk;
    String os;

    public Laptop (String brandN, String type, String modelN, String screenSize, String cpu, String memory, String diskType, String disk, String os){
        this.id = idc++;
        this.brandN = brandN;
        this.type = type;
        this.modelN = modelN;
        this.screenSize = screenSize;
        this.cpu = cpu;
        this.memory = memory;
        this.diskType = diskType;
        this.disk = disk;
        this.os = os;

    }

    @Override
    public String toString(){
        return id + ". " + type + " " + brandN + " " + modelN + " (" + screenSize + "\"/" + memory + "GB/" + diskType + disk + "/" + os + ")";
    }

}
