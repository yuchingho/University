import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Sample	{
	
	public static void main(String[] paramArrayOfString) throws IOException, CloneNotSupportedException	{
		
		Queue localQueue1 = new Queue();
		Queue localQueue2 = new Queue();
		int[] arrayOfInt = new int[2];
    
		buildinputQ(localQueue1, paramArrayOfString);
		processJobs(localQueue1, localQueue2, arrayOfInt);
		summary(localQueue2, arrayOfInt[0], arrayOfInt[1]);
	}
  
	public static void buildinputQ(Queue paramQueue, String[] paramArrayOfString) throws IOException	{
		if (paramArrayOfString.length == 0)	{
			System.err.println("Error : Filename Not Provided");
			System.exit(-1);
		}
		
		FileReader localFileReader = new FileReader(paramArrayOfString[0]);
		BufferedReader localBufferedReader = new BufferedReader(localFileReader);
		Job localJob = null;
		for (;;)	{
			String str = localBufferedReader.readLine();
			if (str == null)	{
				break;
			}
			localJob = inputJob(str);
			paramQueue.enqueue(localJob);
		}
		localBufferedReader.close();
	}
  
	public static Job inputJob(String paramString) throws IOException	{
		StringTokenizer localStringTokenizer = new StringTokenizer(paramString);
		String str = localStringTokenizer.nextToken();
		
		int i = Integer.parseInt(localStringTokenizer.nextToken());
		int j = Integer.parseInt(localStringTokenizer.nextToken());
		
		return new Job(str, i, j);
	}
	
	private static void processJobs(Queue paramQueue1, Queue paramQueue2, int[] paramArrayOfInt) throws CloneNotSupportedException	{
		int i = 1;
		int j = 0;
		int k = 0;
		Queue localQueue = new Queue();
		do	{
			if (!paramQueue1.isEmpty())	{
				Job localJob1 = (Job)paramQueue1.front();
				if (localJob1.arrivalTime == i)	{
					localQueue.enqueue(localJob1);
					paramQueue1.dequeue();
				}
			}
			if (!localQueue.isEmpty())	{
				Job localJob2 = (Job)localQueue.front();
				if ((localJob2.startTime != -1) && (i - localJob2.startTime == localJob2.runTime))	{
					localJob2.turnTime = (i - localJob2.arrivalTime);
					paramQueue2.enqueue(localJob2);
					localQueue.dequeue();
				}
			}
			if (!localQueue.isEmpty())	{
				Job localJob3 = (Job)localQueue.front();
				if ((localJob3.arrivalTime <= i) && (!Started(localQueue, localJob3)))	{
					((Job)localQueue.front()).startTime = i;
					((Job)localQueue.front()).waitTime = (i - localJob3.arrivalTime);
				}
			}
			if ((localQueue.isEmpty()) && (!paramQueue1.isEmpty()))	{
				j++;
			} 
			else if (!localQueue.isEmpty())	{
				k++;
			}
			i++;
		} 
		while ((!paramQueue1.isEmpty()) || (!localQueue.isEmpty()));
		paramArrayOfInt[0] = j;
		paramArrayOfInt[1] = k;
	}
  
	private static void summary(Queue paramQueue, int paramInt1, int paramInt2) throws CloneNotSupportedException	{
		int i = 0;
		int j = 0;
    
		System.out.println("\tJob Control Analysis : Summary Report");
		System.out.println("\t-------------------------------------\n");
		System.out.print("\t");
		System.out.println("job id\tarrival\tstart\trun\twait\tturnaround");
    
		System.out.print("\t");
		System.out.println("      \ttime   \ttime \ttime\ttime\ttime      ");
    
		System.out.print("\t");
		System.out.println("------\t-------\t-----\t----\t----\t----------");
    
		printQ(paramQueue);
		Queue localQueue = (Queue)paramQueue.clone();
		
		while (!localQueue.isEmpty())	{
			Job localJob = (Job)localQueue.front();
			j += localJob.waitTime;
			i++;
			localQueue.dequeue();
		}
		
	    System.out.println("\n");
	    System.out.println("\t\tAverage Wait Time => " + Math.round(j / i * 100.0D) / 100.0D);
	    
	    System.out.println("\t\tCPU Usage => " + paramInt2);
	    System.out.println("\t\tCPU Idle => " + paramInt1);
	    System.out.println("\t\tCPU Usage (%) => " + Math.round(paramInt2 / (paramInt2 + paramInt1) * 100.0D * 100.0D) / 100.0D + "%\n");
	    
	    System.out.println("\t--------------------------------------------------------------");
	}

	private static boolean Started(Queue paramQueue, Job paramJob) throws CloneNotSupportedException	{
		Queue localQueue = (Queue)paramQueue.clone();
		while (!localQueue.isEmpty())	{
			String str = ((Job)localQueue.front()).jobName;
			if ((str.equals(paramJob.jobName)) && (paramJob.startTime != -1)) {
				return true;
			}
			localQueue.dequeue();
		}
		return false;
	}
  
	public static void printQ(Queue paramQueue) throws CloneNotSupportedException	{
		Queue localQueue = (Queue)paramQueue.clone();
		while (!localQueue.isEmpty())	{
			Job localJob = (Job)localQueue.front();
			System.out.println("\t" + localJob);
			localQueue.dequeue();
		}
	}
}