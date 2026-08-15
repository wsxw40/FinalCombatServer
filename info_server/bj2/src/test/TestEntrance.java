package test;

import java.util.Timer;
import java.util.TimerTask;

public class TestEntrance {
	public static void main(String[] args) {
		Runnable runnable=new Runnable(){
			@Override
			public void run() {
				SyncClient client=new SyncClient();
				client.start();
				Timer timer=new Timer();
				TimerTask timerTask=new TestTask.LoginTestTask();
				TestTask.LoginTestTask.setClient(client);
				timer.schedule(timerTask, 1000,5000);
			}
		};
		runnable.run();
	}
	
}
