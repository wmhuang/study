package design.single;


public class SingleThread extends Thread {
	 
	public void run() {
			Emperor emperor = Emperor.getInstance();
			//emperor.emperorInfo();
	}

}
