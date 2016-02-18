package step8;

/**
 *
 * @author Bhoomi
 */
public class MyClock
{
    
    public static int myclock=0;
    int getClockValue()
    {
        return myclock;
    }
     synchronized void setClockValue(int a)
     {
             myclock=a+1;
            
         
     }
    
}
