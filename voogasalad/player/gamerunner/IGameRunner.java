package player.gamerunner;

import java.util.Collections;
import java.util.List;

/**
 * Main control interface for player runner
 * @author mykuryshev
 *
 */
public interface IGameRunner {
	
	/**
	 * Stop the timeline
	 */
	void stop();
	
	/**
	 * Start the timeline
	 */
	void start();
	
	IGameRunner getSelf();
	
	Collections getKeyEvents();
}
