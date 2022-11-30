public class APP {
    public static void main(String[] args) {
        Workflow workflow = new Workflow("src/workflowSamples/MONTAGE/MONTAGE.n.50.0.dax", 1.0);
        Solution solution = new Solution();
        CalHandler.calCE(workflow, solution);
    }
}
