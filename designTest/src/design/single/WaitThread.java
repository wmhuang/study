package design.single;

import java.util.concurrent.CountDownLatch;

public class WaitThread extends Thread {
	private static CountDownLatch latch = null;

	public WaitThread(CountDownLatch latch) {
		WaitThread.latch = latch;
	}

	public void run() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			latch.countDown();
		}
	}
}
