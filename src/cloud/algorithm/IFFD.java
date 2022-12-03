import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class IFFD {
    private List<Container> newIns;
    private List<VM> vms;

    public void run() {
        for (Container container : newIns) {
            Boolean fitSuccess = Fitter.bestFit(vms, container);
            if (fitSuccess) newIns.remove(container);
        }

        newIns.sort((n1, n2) -> (int) (n2.getECU() - n1.getECU()));
        vms.sort((o1, o2) -> (int) (o2.getResidueECU() - o1.getResidueECU()));

        List<VM> newCreatedVMs = new LinkedList<>();

        List<Container> containerList = Fitter.FFD(newCreatedVMs, VM.FASTEST, newIns);
        double cost = CalHandler.calTotalCost();

        for (int i=VM.FASTEST-1; i>=0; i--) {
            containerList = Fitter.FFD(newCreatedVMs, i, containerList);
        }
    }

}