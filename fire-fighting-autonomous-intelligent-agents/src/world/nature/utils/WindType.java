/**
 * 
 * Fire Fighting - Autonomous Intelligent Agents
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

package world.nature.utils;

/**
 * The enumeration responsible for the type of a Wind.
 */
public enum WindType {
	
	// Enumeration definitions:
	
	/**
	 * The possible enumerations and their parameters.
	 */
	NO_WIND((byte) 0, "No Wind", Config.AIRCRAFT_MOVEMENT_PENALTY_TIME_NO_WIND),
	WEAK_WIND((byte) 1, "Weak Wind", Config.AIRCRAFT_MOVEMENT_PENALTY_TIME_WEAK_WIND),
	NORMAL_WIND((byte) 2, "Normal Wind", Config.AIRCRAFT_MOVEMENT_PENALTY_TIME_NORMAL_WIND),
	STRONG_WIND((byte) 3, "Strong Wind", Config.AIRCRAFT_MOVEMENT_PENALTY_TIME_STRONG_WIND);
	
	
	// Global Instance Variables:
	
	/*
	 * The ID of the type of a Wind.
	 */
    private final byte id;
    
    /**
     * The name of the type of a Wind.
     */
    private final String name;
    
    /**
     * The penalty time that will affect the movement time of all the Vehicle Agents,
     * accordingly with the current type of a Wind assigned to the World.
	 * 
	 * The penalty times associated to each type of wind are the following:
	 * - No Wind - 0ms / 0s
	 * - Weak Wind - 100ms / 0,1 s
	 * - Normal Wind - 250ms / 0,25s
	 * - Strong Wind - 600ms /0,6s
     */
    private final long penaltyAircraftMovementTime;
    
    
    // Constructors:
    
    /**
     * The constructor #1 of the type of a Wind.
     * 
     * @param id the ID of the type of a Wind
     * @param name the name of the type of a Wind
     * @param penaltyAircraftMovementTime the penalty time associated to the type of a Wind
     * 		  that will affect the movement time of all the Vehicle Agents
     */
    private WindType(byte id, String name, long penaltyAircraftMovementTime) {
        this.id = id;
        this.name = name;
        this.penaltyAircraftMovementTime = penaltyAircraftMovementTime;
    }
       
    
    // Methods/Functions:
    
    /**
     * Returns the ID of the type of a Wind.
     * 
     * @return the ID of the type of a Wind
     */
    public byte getID() {
    	return this.id;
    }

    /**
     * Returns the name of the type of a Wind.
     * 
     * @return the name of the type of a Wind
     */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the penalty time that will affect the movement time of all the Vehicle Agents,
	 * accordingly with the current type of a Wind assigned to the World.
	 * 
	 * The penalty times associated to each type of a Wind are the following:
	 * - No Wind - 0ms / 0s
	 * - Weak Wind - 100ms / 0,1 s
	 * - Normal Wind - 250ms / 0,25s
	 * - Strong Wind - 600ms /0,6s
	 * 
	 * @return the penalty time that will affect the movement time of all the Vehicle Agents,
	 * 		   accordingly with the current type of a Wind assigned to the World
	 */
	public long getPenaltyAircraftMovementTime() {
		return this.penaltyAircraftMovementTime;
	}
}