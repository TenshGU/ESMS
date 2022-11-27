import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VM {
    private static int internalId = 0;
    public static final double LAUNCH_TIME = 0;
    public static final long NETWORK_SPEED = 20 * 1024 * 1024;
    public static final int TYPE_NO = 8;
    public static final double DISCRETIZE_UNIT = 0.5;
    public static final double[] ECU = {6.5, 8, 13, 16, 26, 31, 53.5, 60};
    public static final double[] PRICE = {0.1, 0.113, 0.2, 0.226, 0.4, 0.452, 0.8, 0.904};
    public static final double INTERVAL = 3600;	//one hour, billing interval

    private int id;
    private int type;
    private int DISCRETIZE_COUNT = 1;
    private List<Integer> images = new ArrayList<>();

    public VM(int type){
        this.type = type;
        this.id = internalId++;
    }

    @Override
    public String toString() {
        return "VM [id=" + id + ", type=" + type + "]";
    }
}
