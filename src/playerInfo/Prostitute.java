package playerInfo;

public class Prostitute extends Townie{

	/**
	 * 
	 * @param name, String
	 * @param position, Integer
	 */
	public Prostitute(String name, int position) {
		super(name, position);
		setRole(scan("Prostitute"));
	}
	
	/**
	 * Used for continuing saved games
	 * @param name, String
	 * @param position, integer
	 * @param status, integer
	 * @param isLynched, boolean
	 */
	public Prostitute(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
		setRole(scan("Prostitute"));
	}
	
	/** 
	 * Copy constructor
	 * @param toCopy, Townie toCopy
	 */
	public Prostitute(Townie toCopy){
		super(toCopy);
	}
	
	/**Copy method*/
	public Player copy(){
		return new Prostitute(this);
	}

	/**
	 * Stops the night action of another player
	 */
	@Override
	public int doAction(Player p) {
		if(getStatus()!=0 && getInBar() != 1){
			System.out.print("ACTIVE: ");return 1;
		}
		System.out.print("FAIL: ");return 0;
	}
	
}
