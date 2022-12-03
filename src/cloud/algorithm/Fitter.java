import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Fitter {
    public static Boolean bestFit(List<VM> vms, Container container) {
        double needECU = container.getECU();
        List<VM> vmList = vms.stream().filter(vm -> vm.getImages().contains(container))
                .sorted((o1, o2) -> (int) (o1.getResidueECU() - o2.getResidueECU())).collect(Collectors.toList());
        int low = 0;
        int high = vmList.size()-1;
        while (low < high) {
            int mid = (high+low)/2;
            double residueECU = vmList.get(mid).getResidueECU();
            if (Math.abs(needECU - residueECU) < 0.0001) {
                break;
            }
            else if (needECU < residueECU) high = mid - 1;
            else if (needECU > residueECU) low = mid + 1;
        }
        if (low >= vmList.size()) return false;
        VM vm = vmList.get(low);
        container.setVMType(vm.getType());
        vm.setResidueECU(vm.getResidueECU() - container.getECU());
        return true;
    }

    /**
     *
     * @param vms the new created VM
     * @param VMType the next VMType need to fit
     * @param newIns the Container should be fit
     * @return the List of container fitted to the latest VM
     */
    public static List<Container> FFD(List<VM> vms, int VMType, List<Container> newIns) {
        HashMap<VM, List<Container>> fitMapping = new HashMap<>();
        VM latestVM = null;
        for (Container container : newIns) {
            boolean fitted = false;
            VM fittedVM = null;
            for (VM vm : vms) {
                if (container.getECU() < vm.getResidueECU()) {
                    fittedVM = vm;
                    container.setVMType(vm.getType());
                    vm.setResidueECU(vm.getResidueECU() - container.getECU());
                    fitted = true;
                    break;
                }
            }
            //need to create a new VM to fit this
            if (!fitted) {
                VM newCreateVM = new VM(VMType);
                vms.add(newCreateVM);
                container.setVMType(VMType);
                newCreateVM.setResidueECU(newCreateVM.getResidueECU() - container.getECU());
                fittedVM = newCreateVM;
                latestVM = newCreateVM;
            }

            List<Container> containerList = fitMapping.computeIfAbsent(fittedVM, k -> new ArrayList<>());
            containerList.add(container);
        }
        return fitMapping.get(latestVM);
    }
}
