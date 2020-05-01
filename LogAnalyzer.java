
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
        
    }
    /**
     * Create an object to analyze hourly web accesses.
     * @param file takes in a string file name
     */
    public LogAnalyzer(String file)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(file);
        
    }
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    /**
     * Return the number of accesses recorded in the log file.
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int i = 0; i < hourCounts.length; i++)
        {
            total += hourCounts[i];
        }
        
        return total;
    }
    /**
     * returns the busiest hour
     * 
     */
    public int busiestHour()
    {
        int busyHour = 0;
        int busyStorage = 0;
        
        for( int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] > busyStorage)
            {
                busyStorage = hourCounts[i];
                busyHour = i;
            }
            else if(hourCounts[i] == busyStorage)
            {
                busyHour = i;
            }
            
        }
        
        return busyHour;
    }
    /**
     * returns the quiest hour
     */
    public int quiestHour()
    {
        int quietHour = 0;
        int quietStorage = hourCounts[0];
        
        for (int i = 0; i < hourCounts.length; i++)
        {
            if (hourCounts[i] < quietStorage)
            {
                quietStorage = hourCounts[i];
                quietHour = i;
            }
            else if(hourCounts[i] == quietStorage)
            {
                quietHour = i;
            }
        }
        
        return quietHour;
    }
    
    
    
    
    
    
}
