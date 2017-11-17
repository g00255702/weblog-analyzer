/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Emeka Okonkwo
 * @version 2017.11.12
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Where to calculate the day access counts
    private int[] dayCounts;
    // Where to calculate the month access counts
    private int[] monthCounts;
    // Where to calculate the year access counts
    private int[] yearCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[28];
        monthCounts = new int[12];
        yearCounts = new int[4];
        
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }
    
    /**
     * Return the number of accesses recorded in the log file
     */
    public int numberOfAccesses()
    {
        int total = 0;
        // Add the value in each element of hourCounts to total
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            total = total + hourCounts[hour];
        }
        return total;
        
    }
    
    /**
     * Return the number of busietHour over a year
     */
    public int busiestHour()
    {
        int busiest = 0;
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            if(busiest < hourCounts[hour])
            {
                busiest = hour;
            }
        }
        return busiest;
    }
    
    /**
     * Return the number of quietestHour over a year
     */
    public int quietestHour()
    {
        int quietest = 0;
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            if(hourCounts[hour] < quietest)
            {
                quietest = hour;
            }
        }
        return quietest;
    }
    
    /**
     * Return the busiest two-hour period
     */
    public int busiestTwoHour()
    {
        int total = 0;
        int totalHour = 0;
        for(int hour = 0; hour < hourCounts.length -1; hour++)
        {
            if(total < (hourCounts[hour] + hourCounts[hour+1]))
            {
                total = hour;
                totalHour = hourCounts[hour] + hourCounts[hour+1];
            }
        }
        return totalHour;
    }
    
    /**
     * Return the quiestest Day
     */
    public int quietestDay()
    {
        int quietest = 0;
        int quietestDay = 0;
        for(int index = 0; index < dayCounts.length; index++)
        {
            if(dayCounts[index] < quietest)
            {
                quietest = dayCounts[index];
                quietestDay = index;
            }
        }
        return quietestDay;
    }
    
    /**
     * Return the busiest Day
     */
    public int busiestDay()
    {
        int busiest = 0;
        int busiestDay = 0;
        for(int index = 0; index < dayCounts.length; index++)
        {
            if(busiestDay < dayCounts[index])
            {
                busiest = dayCounts[index];
                busiestDay = index;
            }
        }
        return busiestDay;
    }
    
    /**
     * Return the total accesses per month
     */
    public int totalAccessesPerMonth()
    {
        int total = 0;
        for(int index = 0; index < monthCounts.length; index++)
        {
            total = total + monthCounts[index];
        }
        return total;
    }
    
    /**
     * Return the quietest Month
     */
    public int quietestMonth()
    {
        int quietest = 0;
        int quietestMonth = 0;
        for(int index = 0; index < monthCounts.length; index++)
        {
            if(monthCounts[index] < quietest)
            {
                quietest = monthCounts[index];
                quietestMonth = index;
            }
        }
        return quietestMonth;
    }
    
    /**
     * Return the busiest Month
     */
    public int busiestMonth()
    {
        int busiest = 0;
        int busiestMonth = 0;
        for(int index = 0; index < monthCounts.length; index++)
        {
            if(busiest < monthCounts[index])
            {
                busiest = monthCounts[index];
                busiestMonth = index;
            }
        }
        return busiestMonth;
    }
    
    /**
     * Return the average Accesses Per Month
     */
    public int averageAccessesPerMonth()
    {
        int total = 0;
        int average = 0;
        for(int index = 0; index < monthCounts.length; index++)
        {
            total = monthCounts[index];
            average = total/12;
        }
        return average;
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
    
}
