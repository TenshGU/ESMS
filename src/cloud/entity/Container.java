import lombok.Getter;
import lombok.Setter;

/**
 * It can be used to represent the configuration
 */
@Getter
@Setter
public class Container {
    public static final double DISCRETIZE_ECU_UNIT = 0.5;
    private int amount = 1; //the amount of ECU
    private double ECU_Unit = 6.5; // basis ECU
    private int VMType; //the type of VM
    private boolean isUsed = false; //whether this container will be used for task

    public Container(int VMType) {
        this.VMType = VMType;
    }

    public Container(int VMType, int amount) {
        this.VMType = VMType;
        this.amount = amount * 2;
    }

    public Container(double maxSpeed) {
        this.VMType = VM.FASTEST;
        this.amount = (int) (maxSpeed / ECU_Unit) + 1;
    }

    public void increaseAmount() {ECU_Unit += 0.5;}

    public void decreaseAmount() {ECU_Unit -= 0.5;}

    public double getECU() {
        return amount * ECU_Unit;
    }

    public double getUnitCost() {
        return (getECU() / VM.ECU[VMType]) * VM.UNIT_COSTS[VMType];
    }
}
