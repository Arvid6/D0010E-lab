package ViewPackage;

import GeneralClasses.View;
import StatePackage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

/** 
 *  Prints the updated results for the specific simulator
 * 
 *  @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */
public class ShopView extends View {
    private ShopState shopState;
    private ArrayList<ArrayList<String>> grid = new ArrayList<ArrayList<String>>();

    public ShopView(ShopState shopState) {
        // sets up first row in our matrix
        setUpArraylist();
        this.shopState = shopState;

        shopState.addObserver(this);



    }

    /**
     * Prints result of Simulation
     */
    public void printResult() {
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < 14; j++) {
                System.out.print(grid.get(i).get(j) + " | ");
            }
            System.out.println("");
        }
    }

    public void update(Observable o, Object arg){
        grid.add(addRow(shopState));
    }
    /**
     * Sets up ArrayList for Result
     */
    private void setUpArraylist() {
        ArrayList<String> firstRow = new ArrayList<String>();
        String[] array = {"Time \t", "Event \t", "ID \t", "O/C \t", "Checkouts", "SumCheckouts ",
                "InShop \t", "FinShoppers \t", "MissedShoppers \t", "QueuedShoppers \t",
                "SumTimeQueued \t", "SumTimeEmpty \t", "QueueSize \t", "Queue"};
        Collections.addAll(firstRow, array);
        grid.add(firstRow);
    }

    /**
     * Creates an Arraylist with all the values from the previous state in StatePackage.ShopState
     *
     * @param oldState, takes the previous state from StatePackage.ShopState
     * @return an ArrayList<String> with all the values from the previous
     */
    private ArrayList<String> addRow(ShopState oldState) {
        ArrayList<String> newRow = new ArrayList<String>();
        newRow.add(String.format("%.2f",(oldState.getTime()))+"\t");
        newRow.add(oldState.getEvent()+ "\t");
        newRow.add(oldState.getID()+"\t");
        newRow.add(trueToOpen(oldState.getShopState())+"\t");
        newRow.add(oldState.getCheckoutEmpty() + " / " +
                (oldState.getCheckoutBusy())+"\t");
        newRow.add(oldState.getOpenCheckout()+"\t"+"\t"+"\t");
        newRow.add(oldState.getCustomerShopping()+"\t" + "\t");
        newRow.add(oldState.getCustomerShopped()+"\t"+"\t" + "\t" );
        newRow.add(oldState.getCustomerMissed()+"\t"+"\t" + "\t" +"\t");
        newRow.add(oldState.getTotalQueue()+"\t" + "\t" + "\t" + "\t");
        newRow.add(String.format("%.2f",(oldState.getCheckoutBusyTime()))+"\t"+"\t" + "\t" + "\t");
        newRow.add(String.format("%.2f",(oldState.getCheckoutEmptyTime()))+"\t"+"\t" + "\t" + "\t");
        newRow.add(oldState.getQueueSize()+"\t" +"\t" + "\t");
        String queue = "[";
        for(int i = 0; i< oldState.getQueueSize(); i++){
            queue += oldState.getQueue().get(i).getID() + ", ";
        }
        queue += "]";
        newRow.add(queue);
        return newRow;

    }

    /**
     * Translate bool to open or close
     * @param bool true or false
     * @return if bool is false return "Clos", if true return "Open"
     */
    private String trueToOpen(boolean bool){
        if(bool) {
            return "Open";
        }else{
            return "Clos";
        }
    }
}
