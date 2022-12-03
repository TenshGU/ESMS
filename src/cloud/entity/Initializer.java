import java.util.ArrayList;
import java.util.List;

public class Initializer {
    public static List<VM> initVMSet() {
        List<VM> vms = new ArrayList<>();
        int type = 0;
        while(type < VM.TYPE_NO) {
            for (int j=0; j<2; j++) {
                VM vm = new VM(type);
                vms.add(vm);
            }
            type++;
        }
        return vms;
    }
}
