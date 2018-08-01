import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TimeShare {
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException	{
		
		Queue queueOne = new Queue();
		Queue queueTwo = new Queue();
		int[] array = new int[2];
		
		buildInputQueue(queueOne, args);
		processJobs(queueOne, queueTwo, array);
		summary(queueTwo, array[0], array[1]);
	}
	
	public static void buildInputQueue(Queue queue, String[] string) throws IOException	{
			FileReader fileReader = new FileReader(string[0]);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Job job = null;
			if (string.length == 0)	{
				System.out.println("Error : Filename Not Provided");
				System.exit(-1);
			}
			while(true)	{
				String file = bufferedReader.readLine();
				if (file == null)	{
					break;
				}
				job = inputJob(file);
				queue.enqueue(job);
			}
			bufferedReader.close();
		}
	
	public static Job inputJob(String name)	{
		StringTokenizer stringTokenizer = new StringTokenizer(name);
		String string = stringTokenizer.nextToken();
		
		int i = Integer.parseInt(stringTokenizer.nextToken());
		int j = Integer.parseInt(stringTokenizer.nextToken());
		
		return new Job(string, i, j);
	}
	
	public static void processJobs(Queue queueOne, Queue queueTwo, int[] array) throws CloneNotSupportedException	{
		int i = 1;
		int j = 0;
		int k = 0;
		Queue queueJobs = new Queue();
		
		do	{
			if(!queueOne.isEmpty())	{
				Job jobOne = (Job)queueOne.front();
				if(jobOne.arrivalTime == i)	{
					queueJobs.enqueue(jobOne);
					queueOne.dequeue();
				}
			}
			
			if(!queueJobs.isEmpty())	{
				Job jobTwo = (Job)queueJobs.front();
				if((jobTwo.startTime != -1) && (i - jobTwo.startTime == jobTwo.runTime))	{
					jobTwo.turnTime = (i - jobTwo.arrivalTime);
					queueTwo.enqueue(jobTwo);
					queueJobs.dequeue();
				}
			}
			
			if(!queueJobs.isEmpty())	{
				Job jobThree = (Job)queueJobs.front();
				if((jobThree.arrivalTime <= i) && (!start(queueJobs, jobThree)))	{
					((Job)queueJobs.front()).startTime = i;
					((Job)queueJobs.front()).waitTime = (i - jobThree.arrivalTime);
				}
			}
			
			if ((queueJobs.isEmpty()) && (!queueOne.isEmpty()))	{
				j++;
			} 
			else if (!queueJobs.isEmpty())	{
				k++;
			}
			i++;
		}
		while((!queueOne.isEmpty()) || (!queueJobs.isEmpty()));	
		array[0] = j;
		array[1] = k;
	}
	
	public static void summary(Queue queue, int CPUIdle, int CPUUsage) throws QueueException, CloneNotSupportedException	{
		int i = 0;
		int j = 0;
		
		System.out.println("\nJob Control Analysis: Summary Report...\n");
		System.out.println("\t Job ID \t Arrival Time \t Start Time \t Run Time \t Wait Time \t Turnaround Time");
		System.out.println("**********************************************************************************************************");
		
		print(queue);
		Queue queueJobs = (Queue)queue.clone();
		while (!queueJobs.isEmpty())	{
			Job job = (Job)queueJobs.front();
			
			j+= job.waitTime;
			i++;
			queueJobs.dequeue();
		}
		
		System.out.println("\nAverage Wait Time - " + Math.round((double)j / i * 100.0)/100.0);
		System.out.println("CPU Usage - " + (double)CPUUsage);
		System.out.println("CPU Idle - " + (double)CPUIdle);
		System.out.println("CPU Usage (%) - " + Math.round((double)CPUUsage / (double)(CPUUsage + CPUIdle) * 100.0D * 100.0D) / 100.0D + "%\n");
	}
	
	public static boolean start(Queue queue, Job job) throws CloneNotSupportedException	{
		Queue queueJobs = (Queue)queue.clone();
		while(!queueJobs.isEmpty())	{
			String string = ((Job)queueJobs.front()).jobName;
			if((string.equals(job.jobName)) && (job.startTime != -1))	{
				return true;
			}
			queueJobs.dequeue();
		}
		return false;
	}
	
	public static void print(Queue queue) throws CloneNotSupportedException	{
		Queue queueJobs = (Queue)queue.clone();
		while (!queueJobs.isEmpty())	{
			Job job = (Job)queueJobs.front();
			System.out.println(job);
			queueJobs.dequeue();
		}
	}
}