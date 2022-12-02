import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Urgency-Based WorkFlow Scheduling
 */
@Getter
@Setter
@AllArgsConstructor
public class UWS {
    private Workflow workflow; //inited
    private Solution solution;
    private HashMap<Task, Allocation> revMapping;
    private HashMap<Task, ArrayList<Container>> holdMapping;

    public void run() {
        CalHandler.setCondition(workflow, solution);
        CalHandler.calCE();
        //cal rank and subDDL
        workflow.calTaskRankBasedCE(revMapping);
        workflow.calSubDDL();
        CalHandler.calUrgency();

        List<Task> unassigned = CalHandler.getSortedTaskList();// ordered by urgency
        for (Task task : unassigned) {
            for (Container container : holdMapping.get(task)) {
                double laxity = CalHandler.calLaxity(task, container);
            }
            System.out.println(laxity);
            if (laxity < 0) {
                double minSpeed = CalHandler.calMinSpeed(task);
                if (minSpeed < 0 || minSpeed > VM.MAX_SPEED) {


                } else if () {

                } else {

                }
            }
        }


    }
}
