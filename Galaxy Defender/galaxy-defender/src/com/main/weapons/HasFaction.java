package weapons;

/**
 * 
 * @author Grupp9
 * Contains functions needed to have multiple targets
 */
public interface HasFaction {
	
	 /**
	 * @return true if the projectile is harmful for the player
	 */
	public TargetTypes[] getFactionTypes();
	public TargetTypes getFaction();
}
