package chapter21.concurrency;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.sun.tools.extcheck.Main;

class Sender implements Runnable{
	private Random rand = new Random(47);
	private PipedWriter out = new PipedWriter();
	public PipedWriter getPipedWriter(){
		return out;
	}
	public void run(){
		try{
			while(true)
				for(char c = 'A'; c <='z';c++){
					out.write(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
		}catch(IOException e){
			System.out.println(e + " sender write exception");
		}catch(InterruptedException e){
			System.out.println(e + " sender sleep interrupted");
		}
	}
}

class Receiver implements Runnable{
	private PipedReader in;
	public Receiver (Sender sender) throws IOException{
		in = new PipedReader(sender.getPipedWriter());
	}
	
	public void run(){
		try{
			while(true){
				System.out.print(" Read: " + (char) in.read() + ",");
			}
		}catch(IOException e){
			System.out.println(e + "Receiver read exception");
		}
	}
}

public class PipedIO {
	public static void main(String[] args) throws Exception{
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}
}
