import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class APP {
    public static void main(String[] args) {
        double factor = 1.3;
        System.out.println("factor: " + factor);
        Workflow workflow = new Workflow("src/workflowSamples/Montage/Montage.n.100.0.dax", factor);
        Solution solution = new Solution();
        HashMap<Task, Allocation> revMapping = solution.getRevMapping();
        HashMap<Task, ArrayList<Container>> holdMapping = solution.getHoldMapping();
        List<VM> vms = Initializer.initVMSet();
        Configuration conf = new Configuration(workflow, solution);

        UWS uws = new UWS(workflow, solution, revMapping, holdMapping, vms, conf);
        List<Container> newIns = uws.execute();
        System.out.println("After UWS, the number of instances that have not been allocated is: " + newIns.size());
        System.out.println("---------------------------------------------------------------");

        IFFD iffd = new IFFD(newIns, vms, conf);
        iffd.execute();

        conf.printMsg();
    }
}
