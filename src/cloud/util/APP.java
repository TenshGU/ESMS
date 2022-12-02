import java.util.ArrayList;
import java.util.HashMap;

public class APP {
    public static void main(String[] args) {
        Workflow workflow = new Workflow("src/workflowSamples/MONTAGE/MONTAGE.n.100.0.dax", 2.0);
        Solution solution = new Solution();
        HashMap<Task, Allocation> revMapping = solution.getRevMapping();
        HashMap<Task, ArrayList<Container>> holdMapping = solution.getHoldMapping();
        UWS uws = new UWS(workflow, solution, revMapping, holdMapping);
        uws.run();
    }
}
