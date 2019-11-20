package com.linhuanjie.thread.multithread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample2 {

	/**
	 * 设想百米赛跑比赛 发令枪发出信号后选手开始跑，全部选手跑到终点后比赛结束
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		int runnerCnt = 10;
		CountDownLatch doneSignal = new CountDownLatch(runnerCnt);

		// create and start threads
		for (int i = 0; i < runnerCnt; ++i) {
			new Thread(new Worker(doneSignal)).start();
		}

		System.out.println("准备工作...");
		System.out.println("准备工作就绪");
		System.out.println("比赛开始");
		doneSignal.await(); // wait for all to finish
		System.out.println("比赛结束");
	}

	static class Worker implements Runnable {
		private final CountDownLatch doneSignal;

		Worker(CountDownLatch doneSignal) {
			this.doneSignal = doneSignal;
		}

		@Override
		public void run() {
			doWork();
			doneSignal.countDown();
		}

		void doWork() {
			System.out.println(Thread.currentThread().getName() + ": 跑完全程");
		}
	}
}
