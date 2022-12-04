import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Configuration {
     private List<VM> vms = new ArrayList<>();
     private double successRatio = -1;
     private Solution solution;
     private Workflow workflow;

     public Configuration(Workflow workflow, Solution solution) {
         this.workflow = workflow;
         this.solution = solution;
     }

     public void addVM(VM vm) {
         if (!vms.contains(vm)) vms.add(vm);
     }

     public void addVMSet(Set<VM> vmSet) {
         for (VM vm : vmSet) addVM(vm);
     }

    public double calTotalCost() {
        double cost = 0.0;
        for (VM vm : vms) cost += vm.getUnitCost();
        return cost;
    }

    public double calSuccessRatio() {
        if (successRatio > 0) return successRatio;
        int success = 0;
        int sum = 0;
        HashMap<Task, Allocation> revMapping = solution.getRevMapping();
        for (Allocation allocation : revMapping.values()) {
            sum++;
            double AFT = allocation.getFinishTime();
            if (AFT < workflow.getDeadline()) success++;
        }
        if (successRatio < 0) setSuccessRatio((double) success / sum);
        return successRatio;
    }

     public void printMsg() {
         System.out.println("total VM's number is: " + vms.size());
         System.out.println("ESMS final cost is: " + calTotalCost());
         System.out.println("ESMS success ratio is: " + calSuccessRatio());
         System.out.println("ESMS Configuration is: \n" + vms);
     }
}
