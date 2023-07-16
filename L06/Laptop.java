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
    String cost;

    public Laptop (String brandN, String type, String modelN, String screenSize, String cpu, String memory, String diskType, String disk, String os, String cost){
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
        this.cost = cost;
    }

    @Override
    public String toString(){
        return id + ". " + type + " " + brandN + " " + modelN + " (" + screenSize + "\"/" + cpu + "/" + memory + "GB/" + diskType + disk + "/" + os + " - " + cost + " руб.)\n";
    }

    public int costNB(){
        return Integer.valueOf(cost);
    }
    
    public String paramBrand(){
        return brandN;
    }

    public String paramCPU(){
        return cpu;
    }

    public String paramMem(){
        return memory;
    }

    public String paramDiskSize(){
        return disk;
    }

    public String paramDiskType(){
        return diskType;
    }

    public String paramScreen(){
        return screenSize;
    }

    public String paramOS(){
        return os;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if (!(obj instanceof Laptop)){
            return false;
        }
        Laptop nb = (Laptop) obj;
        return brandN == nb.brandN && cpu == nb.cpu && memory == nb.memory && disk == nb.disk && screenSize == nb.screenSize && type == nb.type;
    }


}
