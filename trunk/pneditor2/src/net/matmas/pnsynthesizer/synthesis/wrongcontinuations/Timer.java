package net.matmas.pnsynthesizer.synthesis.wrongcontinuations;

import java.util.Date;

/**
 *
 * @author matmas
 */
public class Timer {
	private String operationName;
	private Date timeStarted;
	
	public Timer(String operationName) {
		this.operationName = operationName;
		timeStarted = new Date();
	}

	public void end() {
		Date timeEnded = new Date();
		System.out.println(operationName + " done in " + (timeEnded.getTime() - timeStarted.getTime()) + " ms");
		timeStarted = new Date();
	}
}
