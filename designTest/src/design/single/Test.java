package design.single;

import java.util.concurrent.CountDownLatch;

public class Test {
	public static void main(String[] args) {
		CountDownLatch count = new CountDownLatch(1);
		WaitThread t1 = new WaitThread(count);
		Thread threads[] = new Thread[1000];
		for (int i = 0; i < 1000; i++) {
			SingleThread t = new SingleThread();
			threads[i] = t;
		}

		try {
			Long  start= System.currentTimeMillis();
			System.out.println(start);
			t1.start();
			count.await();
			Long b = System.currentTimeMillis();
			System.out.println(b-start);
			for (int i = 0; i < 1000; i++) {
				threads[i].start();
			}
			Long c=  System.currentTimeMillis();
			System.out.println(c);
			System.out.println(c-start);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
