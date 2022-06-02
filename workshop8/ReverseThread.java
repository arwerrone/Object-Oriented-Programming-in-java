package workshop8;

public class ReverseThread extends Thread {
    private static int count;

    public ReverseThread(int c) {
        count = c;
        this.setName(String.valueOf(c));

    }

    public void run() {
        count++;
        if (count <= 51) {
            ReverseThread thread = new ReverseThread(count);
            thread.start();

            try {
                thread.join();
                System.out.println("Hello from Thread! " + this.getName());
                
            } catch (Exception err) {
            	err.printStackTrace();
            }
        }else {
        	return;
        }
    }
    
    
}