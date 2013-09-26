package weapons;

/**
 * 
 * @author Grupp9
 * Contains functions needed to have multiple targets
 */
public interface HasTargets {
	
	 /**
	 * @return true if the projectile is harmful for the player
	 */
	public TargetTypes[] getTargetTypes();
	public TargetTypes getFaction();
}
