import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class APP {
    public static void main(String[] args) {
        Workflow workflow = new Workflow("src/workflowSamples/MONTAGE/MONTAGE.n.100.0.dax", 1.3);
        Solution solution = new Solution();
        HashMap<Task, Allocation> revMapping = solution.getRevMapping();
        HashMap<Task, ArrayList<Container>> holdMapping = solution.getHoldMapping();
        List<VM> vms = Initializer.initVMSet();

        UWS uws = new UWS(workflow, solution, revMapping, holdMapping, vms);
        List<Container> newIns = uws.run();

        IFFD iffd = new IFFD(newIns, vms);
        iffd.run();

        System.out.println("makespan: " + solution.calMakespan());
        System.out.println("cost: " + solution.calCost());
    }
}
