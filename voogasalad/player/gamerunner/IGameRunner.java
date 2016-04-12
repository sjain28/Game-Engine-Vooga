package player.gamerunner;

/**
 * Main control interface for player runner
 * @author mykuryshev
 *
 */
interface IGameRunner {
	
	/**
	 * Stop the timeline
	 */
	void stop();
	
	/**
	 * Start the timeline
	 */
	void start();
}
