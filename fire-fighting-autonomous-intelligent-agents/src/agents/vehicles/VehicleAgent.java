/**
 * 
 * Fire Fighting
 * - Autonomous and Intelligent Agents
 *   in the Fight against Forest Fires
 * 
 * Agents and Distributed Artificial Intelligence
 * 
 * Integrated Master of Informatics and Computing
 * Faculty of Engineering of University of Porto
 * 
 * Authors:
 * @author Bernardo Jose Leite - up201404464@fe.up.pt
 * @author Bruno Miguel Pinto - up201502960@fe.up.pt
 * @author Ruben Andre Barreiro - up201808917@fe.up.pt
 *
 */

package agents.vehicles;

import agents.vehicles.behaviours.DetectEnoughFuelQuantityBehaviour;
import agents.vehicles.behaviours.DetectEnoughWaterQuantityBehaviour;
import agents.vehicles.behaviours.DetectedFireAlarmListenerBehaviour;
import agents.vehicles.messages.templates.AlarmDetectedFireACLMessageTemplate;
import jade.core.Agent;
import world.map.WorldObject;

/**
 * The class responsible for a Vehicle Agent.
 */
public abstract class VehicleAgent extends Agent {

	// Constants/Invariants:
	
	/**
	 * The default UID of the Vehicle Agent.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The maximum water tank's capacity of the Vehicle Agent.
	 */
	protected static int maxWaterTankCapacity;
	
	/**
	 * The maximum fuel tank's capacity of the Vehicle Agent.
	 */
	protected static int maxFuelTankCapacity;
	
	
	// Global Instance Variables:
	
	/**
	 * The ID of the Vehicle Agent.
	 */
	protected byte id;
	
	/**
	 * The world's object of the Vehicle Agent.
	 */
	protected WorldObject worldObject;

	/**
	 * The water tank's quantity of the Vehicle Agent.
	 */
	protected int waterTankQuantity;

	/**
	 * The fuel tank's quantity of the Vehicle Agent.
	 */
	protected int fuelTankQuantity;

	/**
	 * The boolean value to keep the information about if
	 * the Vehicle Agent it's already attending a Fire.
	 */
	protected boolean attendingFire = false;
	
	/**
	 * The boolean value to keep the information about if
	 * the Vehicle Agent it's currently busy because is
	 * refuelling its water or fuel tank.
	 */
	protected boolean refuellingWaterOrFuel = false;
	
	/**
	 * The boolean value that keeps the information about if
	 * the Vehicle Agent crashed or not.
	 */
	protected boolean crashed;

	/**
	 * The execution metrics stats of the Vehicle Agent.
	 */
	//protected VehicleMetricsStats vehicleMetricsStats;

	
	// Methods/Functions:
	
	/**
	 * Returns the ID of the Vehicle Agent.
	 * 
	 * @return the ID of the Vehicle Agent
	 */
	public byte getID() {
		return this.id;
	}
	
	/**
	 * Returns the World's object of the Vehicle Agent.
	 * 
	 * @return the World's object of the Vehicle Agent
	 */
	public WorldObject getWorldObject() {
		return this.worldObject;
	}
	
	/**
	 * Returns the water tank's quantity of the Vehicle Agent.
	 * 
	 * @return the water tank's quantity of the Vehicle Agent
	 */
	public int getWaterTankQuantity() {
		return this.waterTankQuantity;
	}
	
	/**
	 * Increases the water tank's quantity of the Vehicle Agent.
	 */
	public void increaseWaterQuantity() {
		this.waterTankQuantity++;
	}
	
	/**
	 * Decreases the water tank's quantity of the Vehicle Agent
	 */
	public void decreaseWaterQuantity() {
		this.waterTankQuantity--;
	}
	
	/**
	 * Returns true if the Vehicle Agent have its water tank empty and false, otherwise.
	 * 
	 * @return true if the Vehicle Agent have its water tank empty and false, otherwise
	 */
	public boolean haveEmptyWaterTank() {
		return this.getWaterTankQuantity() == 0;
	}
	
	/**
	 * Returns true if the Vehicle Agent have its water tank full and false, otherwise.
	 * 
	 * @return true if the Vehicle Agent have its water tank full and false, otherwise
	 */
	public abstract boolean haveFullWaterTank();
	
	/**
	 * Returns the maximum water tank's capacity of the Vehicle Agent.
	 * 
	 * @return the maximum water tank's capacity of the Vehicle Agent
	 */
	public abstract int getMaxWaterTankCapacity();
	
	/**
	 * Returns the fuel tank's quantity of the Vehicle Agent.
	 * 
	 * @return the fuel tank's quantity of the Vehicle Agent
	 */
	public int getFuelTankQuantity() {
		return this.fuelTankQuantity;
	}
	
	/**
	 * Returns true if the Vehicle Agent have its fuel tank empty and false, otherwise.
	 * 
	 * @return true if the Vehicle Agent have its fuel tank empty and false, otherwise
	 */
	public boolean haveEmptyFuelTank() {
		return this.getFuelTankQuantity() == 0;
	}
	
	/**
	 * Returns true if the Vehicle Agent have its fuel tank full and false, otherwise.
	 * 
	 * @return true if the Vehicle Agent have its fuel tank full and false, otherwise
	 */
	public abstract boolean haveFullFuelTank();
	
	/**
	 * Returns the maximum fuel tank's capacity of the Vehicle Agent.
	 * 
	 * @return the maximum fuel tank's capacity of the Vehicle Agent
	 */
	public abstract int getMaxFuelTankCapacity();
	
	/**
	 * Returns true if the Vehicle Agent have enough fuel in the tank to fly to
	 * some destination and false, otherwise.
	 * 
	 * @return true if the Vehicle Agent have enough fuel in the tank to fly to
	 * 		   some destination and false, otherwise
	 */
	public boolean haveEnoughFuelToDest(int numPositions) {
		return this.getFuelTankQuantity() > numPositions;
	}
	
	/**
	 * Returns true if the Vehicle Agent is crashed and false, otherwise.
	 * 
	 * @return true if the Vehicle Agent is crashed and false, otherwise
	 */
	public boolean isCrashed() {
		return this.crashed;
	}
	
	/**
	 * If the Vehicle Agent, at some moment, have its fuel tank empty,
	 * it suffer an accident crash and become indefinitely inactive.
	 */
	public void accidentCrash() {
		
		// The Vehicle Agent have its water tank empty
		if(this.haveEmptyFuelTank()) {
			this.crashed = true;
			
			// Take down this Vehicle Agent, after its crash
			this.takeDown();
		}
	}
	
	/**
	 * Returns true if the Vehicle Agent is currently attending a Fire and false, otherwise.
	 * 
	 * @return true if the Vehicle Agent is currently attending a Fire and false, otherwise
	 */
	public boolean isCurrentlyAttendingFire() {
		return this.attendingFire;
	}
	
	/**
	 * Sets a "busy" state for this Vehicle Agent, because it will be attending a Fire.
	 */
	public void attendFire() {
		
		// This Vehicle Agent only attends a Fire, if isn't currently "busy"
		if(!this.isBusy())
			this.attendingFire = true;
	}
	
	/**
	 * Returns true if the Vehicle Agent is currently refuelling its Water or Fuel tank and false, otherwise.
	 * 
	 * @return true if the Vehicle Agent is currently refuelling its Water or Fuel tank and false, otherwise
	 */
	public boolean isCurrentlyRefuellingWaterOrFuel() {
		return this.refuellingWaterOrFuel;
	}
	
	/**
	 * Sets a "busy" state for this Vehicle Agent, because it will be refuelling its Water or Fuel tank.
	 */
	public void refuelWaterOrFuelTank() {
		
		// This Vehicle Agent only attends a Fire, if isn't currently "busy"
		if(!this.isBusy())
			this.refuellingWaterOrFuel = true;
	}
	
	/**
	 * Returns true if the Vehicle Agent is currently attending a Fire or,
	 * refuelling its Water or Fuel tank and false, otherwise.
	 * 
	 * @return true if the Vehicle Agent is currently attending a Fire or,
	 * 		   refuelling its Water or Fuel tank and false, otherwise
	 */
	public boolean isBusy() {
		return this.isCurrentlyAttendingFire() || this.isCurrentlyRefuellingWaterOrFuel();
	}
	
	/**
	 * 
	 */
	protected void setup() {
		
		// Add the Vehicle Agent's Behaviours related to the detection of enough water and fuel
		// in its respectively tanks and the necessary actions to perform in these situations
		addBehaviour(new DetectEnoughWaterQuantityBehaviour(this, 1000));
		addBehaviour(new DetectEnoughFuelQuantityBehaviour(this, 1000));
		
		// TODO
		addBehaviour(new DetectedFireAlarmListenerBehaviour(this, new AlarmDetectedFireACLMessageTemplate(), null));
	}
}