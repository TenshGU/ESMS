import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class Task {
    private int id;
    private String name;
    private double taskSize;
    private int topoCount;
    private double bLevel; 	//blevel
    private double tLevel;	//tLevel
    private double sLevel;
    private double ALAP;

    private List<Edge> outEdges = new ArrayList<>();
    private List<Edge> inEdges = new ArrayList<>();

    public Task(String name, double taskSize) {
        this.name = name;
        this.taskSize = taskSize;
    }

    static class BLevelComparator implements Comparator<Task> {
        public int compare(Task o1, Task o2) {
            // to keep entry node ranking last, and exit node first
            if(o1.getName().equals("entry") || o2.getName().equals("exit"))
                return 1;
            if(o1.getName().equals("exit") || o2.getName().equals("entry"))
                return -1;
            if(o1.getbLevel()>o2.getbLevel())
                return 1;
            else if(o1.getbLevel()<o2.getbLevel())
                return -1;
            else{
                return 0;
            }
        }
    }

}
