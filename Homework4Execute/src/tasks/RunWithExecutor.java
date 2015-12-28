/**
 * 
 */
package tasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import utils.ExecuteShellComand;

/**
 * @author pzoli
 *
 */
public class RunWithExecutor {
    public Boolean isAESEnabled = null;
    
    public void execCommand() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String getCPUInfo = "cat /proc/cpuinfo | grep aes | uniq";            
            ExecuteShellComand obj = new ExecuteShellComand();
            String output = obj.executeCommand(getCPUInfo);
            isAESEnabled = ((output != null) && (output.contains("aes")));             
        });
        
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }

    }
}
