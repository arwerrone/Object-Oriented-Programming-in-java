package workshop10;

public class Time implements Comparable<Time>, Cloneable {
	private long elapsedTime;
	
	public Time() {}
	
	public Time(int h, int m, int s) {
		this.elapsedTime = (h * 3600) + (m * 60) + s;
	}

	public Time(long ela) {
		this.elapsedTime = ela;
	}

	public int getHour() {
		return (int) (this.elapsedTime / 3600) % 24;
	}

	public int getMinute() {
		return (int) (this.elapsedTime / 60) % 60;
	}

	public int getSecond() {
		return (int) this.elapsedTime % 60;
	}
	  
	public long getSeconds(){
		return  elapsedTime;
	} 
	   
	@Override
	public int compareTo(Time obj) {
		return (int) (this.elapsedTime - obj.elapsedTime);
	}
	  
	public Time clone(){
		Time newTime = new Time(elapsedTime);
		return newTime;
	}
	  
	   
	public String toString() {
		return String.format(this.getHour()%24 + " hours " + this.getMinute() + " minutes "+ this.getSecond() +" seconds");
	}
	   
}