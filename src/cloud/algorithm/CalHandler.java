import java.util.HashMap;

public class CalHandler {
    private static void firstAllocateTask(Workflow workflow, Solution solution) {
        int wfLen = workflow.size();
        for (int j = 0; j < wfLen; j++) {
            Task task = workflow.get(j);
            Container container = new Container(VM.SLOWEST);
            double startTime = solution.calEST(task, container);
            solution.addTaskToContainer(container, task, startTime, j == wfLen - 1);
        }
    }

    private static void updateTaskConfig(Workflow workflow, Solution solution, Task task) {
        int index = workflow.indexOf(task);
        int size = workflow.size();
        for (int j = index; j < size; j++) {
            Task task4update = workflow.get(j);
            solution.updateTaskConfig(task4update);
        }
    }

    public static void calCE(Workflow workflow, Solution solution) {
        firstAllocateTask(workflow, solution);
        double deadline = workflow.getDeadline();
        HashMap<Task, Allocation> revMapping = solution.getRevMapping();

        double cost = solution.calCost();
        double makespan = solution.calMakespan();

        System.out.println("Workflow's deadline: " + deadline);

        while (makespan > deadline) {
            double gain = 0.0; //current biggest gain
            Container c4update = null; //the container should be updated by increasing
            Task t4update = null;
            for (Task task : workflow) {
                Allocation alloc = revMapping.get(task);
                Container container = alloc.getContainer();

                container.increaseAmount();
                updateTaskConfig(workflow, solution, task);

                double gainj = 0.0;
                double costj = solution.calCost();
                double makespanj = solution.calMakespan();

                if (Math.abs(cost - costj) < 1e-6) {
                    gainj = makespan - makespanj;
                } else if (costj < cost) {
                    gainj = cost - costj;
                } else {
                    gainj = (makespan - makespanj) / (costj - cost);
                }
                if (gainj > gain) { //select the biggest gain
                    c4update = container;
                    t4update = task;
                    gain = gainj;
                }

                container.decreaseAmount();
                updateTaskConfig(workflow, solution, task);
            }
            //It finds a CEj to update the container(the gainj is the biggest)
            if (c4update != null) {
                c4update.increaseAmount();
                updateTaskConfig(workflow, solution, t4update);
            }
            cost = solution.calCost();
            makespan = solution.calMakespan();

            System.out.println("cost:" + cost);
            System.out.println("makespan:" + makespan);
        }
    }
}
