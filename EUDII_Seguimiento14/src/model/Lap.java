package model;

public class Lap {
	
	private int id;
	
	//Attributes
	private int overallTimeMinutes;
	private int overallTimeSeconds;
	private int overallTimeMiliseconds;
	
	@SuppressWarnings("unused")
	private int lapMinutes;
	@SuppressWarnings("unused")
	private int lapSeconds;
	@SuppressWarnings("unused")
	private int lapMiliseconds;
	
	
	private String lapTime;
	private String overallTime;
	
	
	
	public Lap(int id,int overallTimeMinutes, int overallTimeSeconds, int overallTimeMiliseconds, int lapMinutes,
			int lapSeconds, int lapMiliseconds) {
		super();
		this.id = id;
		this.overallTimeMinutes = overallTimeMinutes;
		this.overallTimeSeconds = overallTimeSeconds;
		this.overallTimeMiliseconds = overallTimeMiliseconds;
		this.lapMinutes = lapMinutes;
		this.lapSeconds = lapSeconds;
		this.lapSeconds = lapMiliseconds;
		

		
		
		setOverallTime(overallTimeMinutes+":"+overallTimeSeconds+":"+overallTimeMiliseconds);
		setLapTime(lapMinutes+":"+lapSeconds+":"+lapMiliseconds);
	}
	
	
	//
	// === GETTERS AND SETTERS===
	//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOverallTime() {
		return overallTime;
	}


	public void setOverallTime(String overallTime) {
		this.overallTime = overallTime;
	}


	public String getLapTime() {
		return lapTime;
	}


	public void setLapTime(String lapTime) {
		this.lapTime = lapTime;
	}


	public int getOverallTimeMinutes() {
		return overallTimeMinutes;
	}


	public void setOverallTimeMinutes(int overallTimeMinutes) {
		this.overallTimeMinutes = overallTimeMinutes;
	}


	public int getOverallTimeSeconds() {
		return overallTimeSeconds;
	}


	public void setOverallTimeSeconds(int overallTimeSeconds) {
		this.overallTimeSeconds = overallTimeSeconds;
	}


	public int getOverallTimeMiliseconds() {
		return overallTimeMiliseconds;
	}


	public void setOverallTimeMiliseconds(int overallTimeMiliseconds) {
		this.overallTimeMiliseconds = overallTimeMiliseconds;
	}
	
	
	
	
	

	
	

}
