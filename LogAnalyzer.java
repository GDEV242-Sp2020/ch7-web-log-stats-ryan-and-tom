

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
    //where to calculate daily access counts
    private int[] dayCounts;
    //where to calculate monthly counts
    private int[] monthCounts;
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
        dayCounts = new int[28];
        monthCounts = new int[12];
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
        
        dayCounts = new int[28];
        monthCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader(file);
        
    }
    /**
     * Analyze the data from the log file
     */
    public void analyzeData()
    {
        //analyzeHourlyData();
        //analyzeDailyData();
        //analyzeMonthlyData();
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
            int day = entry.getDay();
            dayCounts[day-1]++;
            int month = entry.getMonth();
            monthCounts[month-1]++;
        }
    }
    /**
     * Analyze the hourly access data from the log file.
     */
    private void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    /**
     * Analyze the daily access data from the log file.
     */
    private void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    /**
     * Analyze the monthly access data from the log file.
     */
    private void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }    
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hour: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printDailyCounts()
    {
        System.out.println("Day: Count");
        for(int day = 0; day < dayCounts.length; day++) {
            System.out.println((day+1) + ": " + dayCounts[day]);
        }
    }    
    
     /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printMonthlyCounts()
    {
        System.out.println("Month: Count");
        for(int month = 0; month < monthCounts.length; month++) {
            System.out.println((month+1) + ": " + monthCounts[month]);
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
    /**
     * busiestTwoHhour method
     */
    public int busiestTwoHour()
    {
        int busyHour = 0;
        int busyStorage = 0;
        int tempCount;
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(i + 1 < hourCounts.length) {
                tempCount = hourCounts[i] + hourCounts[i + 1];
            }
            else {
                tempCount = hourCounts[i] + hourCounts[0];
            }
            if(tempCount > busyStorage)
            {
                busyHour = i;
                busyStorage = tempCount;
            }
            
        }
        
        return busyHour;
    }
    /**
     * quietestDay will fint what day was the 
     */
    public int quietestDay()
    {
        int quietDay = 0;
        int quietStorage = dayCounts[0];
        
        for (int i = 0; i < dayCounts.length; i++)
        {
            if(dayCounts[i] < quietStorage)
            {
                quietStorage = dayCounts[i];
                quietDay = i;
            }
            else if (dayCounts[i] == quietStorage)
            {
                quietDay = i;
            }
            
            
        }
        
        return quietDay + 1; //the plus one is for the correct index
    }
    /**
     * this returns the busiest day out of a month as an int
     */
    public int busiestDay()
    {
        int busyDay = 0;
        int busyStorage = 0;
         
        
        for (int i = 0; i < dayCounts.length; i++)
        {
            if(dayCounts[i] > busyStorage)
            {
                busyStorage = dayCounts[i];
                busyDay = i;
            }
            else if (dayCounts[i] == busyStorage)
            {
                busyDay = i;
            }
        }
        
        return busyDay + 1;
    }
    /**
     * this returns the total count for the month with the heighest count as an int
     */
    public int totalAccessesPerMonth()
    {
        int totalMonth = 0;
        int monthStorage = 0;
         
        
        for (int i = 0; i < monthCounts.length; i++)
        {
            if(monthCounts[i] > monthStorage)
            {
                monthStorage = monthCounts[i];
                totalMonth = i;
            }
            else if (monthCounts[i] == monthStorage)
            {
                totalMonth = i;
            }
        }
        
        return monthCounts[totalMonth];
    }
    /**
     * this returns the quietest month as an int
     */
    public int quietestMonth()
    {
        int quietMonth = 0;
        int monthStorage = monthCounts[0];
        
        for (int i = 0; i < monthCounts.length; i++)
        {
            if(monthCounts[i] < monthStorage)
            {
                monthStorage = monthCounts[i];
                quietMonth = i;
            }
            else if (monthCounts[i] == monthStorage)
            {
                quietMonth = i;
            }
        }
        
        return quietMonth + 1;
    }
    /**
     * this returns the busiest month as an int
     */
    public int busyMonth()
    {
        int busyMonth = 0;
        int monthStorage = 0;
        
        for (int i = 0; i < monthCounts.length; i++)
        {
            if(monthCounts[i] > monthStorage)
            {
                monthStorage = monthCounts[i];
                busyMonth = i;
            }
            else if (monthCounts[i] == monthStorage)
            {
                busyMonth = i;
            }
        }
        
        return busyMonth + 1;
    }  
    /**
     * this return the average accesses per month as an int
     */
    public int averageAccessesPerMonth()
    {
        int averageMonth = 0;
        int monthStorage = 0;
        
        for (int i = 0; i < monthCounts.length; i++)
        {
            monthStorage += monthCounts[i];
        }
        
        averageMonth = monthStorage / monthCounts.length;
        return averageMonth;
    }
    
}
