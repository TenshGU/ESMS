import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VM {
    private static int internalId = 0;
    public static final double LAUNCH_TIME = 0;
    public static final int SLOWEST = 0;
    public static final int FASTEST = 7;
    public static final int TYPE_NO = 8;
    public static final long NETWORK_SPEED = 20 * 1024 * 1024;
    public static final double DISCRETIZE_UNIT = 0.5;
    public static final double[] ECU = {6.5, 8, 13, 16, 26, 31, 53.5, 60};
    public static final double[] UNIT_COSTS = {0.1, 0.113, 0.2, 0.226, 0.4, 0.452, 0.8, 0.904};
    public static final double INTERVAL = 3600;	//one hour, billing interval

    private int id;
    private int type;
    private int DISCRETIZE_COUNT = 1;
    private List<Integer> images = new ArrayList<>();

    public static void resetInternalId(){	//called by the constructor of Solution
        internalId = 0;
    }

    public VM(int type){
        this.type = type;
        this.id = internalId++;
    }

    public double getECU(){return ECU[type];}
    public double getUnitCost(){return UNIT_COSTS[type];}

    @Override
    public String toString() {
        return "VM [id=" + id + ", type=" + type + "]";
    }
}
