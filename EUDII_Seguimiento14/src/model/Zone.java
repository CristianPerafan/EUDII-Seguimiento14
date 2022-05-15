package model;

import java.time.LocalTime;
import java.time.ZoneId;

public class Zone {
	
	private String timeZone;
	private ZoneId zoneId;


	private LocalTime time;
	
	public Zone(String timeZone) {
		
		this.timeZone = timeZone;
		
		zoneId = ZoneId.of(timeZone);

		time = LocalTime.now(zoneId);
		
		
	}
	
	@Override
	public String toString() {
		return "Zone [timeZone=" + timeZone + ", zoneId=" + zoneId + ", time=" + time + "]";
	}
	
	

	public ZoneId getZoneId() {
		return zoneId;
	}

	public void setZoneId(ZoneId zoneId) {
		this.zoneId = zoneId;
	}

	public String getTimeZone() {
		
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public LocalTime getTime() {
		
		return time = LocalTime.now(zoneId);
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
	
	
	
	
	
	

}
