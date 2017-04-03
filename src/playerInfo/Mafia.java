package playerInfo;
/**
 * The Mafia is an abstract class that
 * signifies a mafia affiliation of a player
 * 
 * Unlike the Town class, Mafia is abstract
 * @author Elvin Limpin 30018832
 *
 */
public abstract class Mafia extends Player {

	/**
	 * Constructor passing values from different mafia
	 * classes to the Player class
	 * @param name
	 * @param position
	 * @param role
	 */
	public Mafia(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Copy constructor
	 * @param m
	 */
	public Mafia(Mafia m){
		super(m);
	}
}
