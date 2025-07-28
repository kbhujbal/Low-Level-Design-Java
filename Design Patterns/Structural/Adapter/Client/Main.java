public class Main {
    public static void main(String[] args) {

        WeightMachineAdapter weightMachineAdapter = new WeightMachineAdapterImpl(new WeightMachineForBabie());
        
        System.out.println(weightMachineAdapter.getWeightInKg());
    }    
}
