import GeneralClasses.Event;
import GeneralClasses.EventQueue;
import GeneralClasses.Simulator;
import StatePackage.*;
import ViewPackage.ShopView;

public class SpecSimulator extends Simulator {
	private String type;
	private ShopView sv;

	/**
	 * Initiates the simulation
	 *
	 * @param eq EventQueue object
	 * @param shopState ShopState object
	 */
    public SpecSimulator(EventQueue eq, ShopState shopState){
		super(eq, shopState);
    	run(shopState, eq);
    }
	/**
	 * Runs the simulator
	 *
	 * @param shopState shopstate object
	 * @param eventQueue eventqueue object
	 */
    public void run(ShopState shopState, EventQueue eventQueue){
		sv = new ShopView(shopState);
        while(!shopState.getBreak()){
        	Event event = eventQueue.first();
			shopState.setQueueTime(shopState.getQueueSize() * (event.getTime()-shopState.getTime()));
			for(int i = 0; i < eventQueue.size(); i++){
				if(shopState.getShopState() || eventQueue.getEvent(i).getType() == "PayEvent"){
					shopState.setCheckoutEmptyTime(shopState.getCheckoutEmpty() * (event.getTime()-shopState.getTime()));
					break;
				}
			}
        	event.run(shopState);
			shopState.setEvent(event.getType());
			if(!shopState.getShopState() && (shopState.getEvent() !=  "Arrival" && shopState.getEvent() != "StopEv")){
				shopState.updateTime(event.getTime());
				shopState.updateHiddenTime(shopState.getTime());
			}
			shopState.updateTime(event.getTime());
			shopState.act();
			eventQueue.remove();
        }

		if (!shopState.getOptimize()){
			System.out.println("PARAMETRAR \n" +
					"========== \n" +
					"Antal kassor, N..........: "+ shopState.getOpenCheckout() +"\n" +
					"Max som ryms, M..........: "+ shopState.getMaxCustomer() +"\n" +
					"Ankomshastighet, lambda..: "+ shopState.getLambda() +"\n" +
					"Plocktider, [P_min..Pmax]: ["+ shopState.getPickupLim()[0] + ".." + shopState.getPickupLim()[1] +"] \n" +
					"Betaltider, [K_min..Kmax]: ["+ shopState.getPayLim()[0] + ".." +shopState.getPayLim()[1] + "] \n" +
					"Frö, f...................: "+ shopState.getSeed() + "\n" );
			sv.printResult();
			System.out.println("\n RESULTAT \n =======\n1) Av " + shopState.getTotCustomer()  + " kunder handlade " + shopState.getCustomerShopped()+ " medan " + shopState.getCustomerMissed() + " missades. " +
					"\n" + "2) Total tid "+ shopState.getOpenCheckout()+ " kassor varit lediga: " + String.format("%.2f",(shopState.getCheckoutEmptyTime())) +" te. \n   Genomsnittlig ledig kassatid: " +String.format("%.2f",(shopState.getCheckoutEmptyTime() / shopState.getOpenCheckout()))  + " te (dvs " +String.format("%.2f",(((shopState.getCheckoutEmptyTime() / shopState.getOpenCheckout()) / shopState.getHiddenTime()) * 100)) +"% av tiden från öppning tills sista kunden betalat). " +
					"\n3) Total tid " + shopState.getTotalQueue() +  " kunder tvingats köa " + String.format("%.2f",(shopState.getCheckoutBusyTime())) + " te. \n   Genomsnittlig kötid: "+ String.format("%.2f",((shopState.getCheckoutBusyTime() / shopState.getTotalQueue()))) + " te." );
		}

    }
}
