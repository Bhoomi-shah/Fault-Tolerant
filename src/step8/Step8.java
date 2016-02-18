package step8;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Bhoomi
 */
public class Step8
{
    
public static Logger logger;
        FileHandler fh;  
    
    public static void main(String[] args) throws FileNotFoundException
    {
      
       int MyId = Integer.parseInt(args[0]);
       MyMessage mmsg;
       ReadFile Rf = new ReadFile();
       int MyPortNo = Rf.getPortNo(MyId);
       
       logger = Logger.getLogger("Node"+MyId);  
        FileHandler fh;  
       
        
        try {  
              
             
            fh = new FileHandler("NODE"+MyId+".log");  
            logger.addHandler(fh);  
            
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  
              
            
            //logger.info("My first log");  
              
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException ex) {
        Logger.getLogger(Step8.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    MyQueue mq=new MyQueue();
       MyServer myserver = new MyServer(MyId,MyPortNo,mq);
       MyClient myclient = new MyClient(MyId,MyPortNo,Rf);
       while(true)
       {
        //logger.info("Fetching Messages");  
	mq.poll_msg();
          
                  
       }
    
    }}