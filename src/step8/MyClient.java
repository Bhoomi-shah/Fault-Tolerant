package step8;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bhoomi
 */
public class MyClient implements Runnable
{
    
    Thread t1;
    int MyId,MyPort;
    ReadFile rf;
    MyClient(int id,int port,ReadFile rf) throws FileNotFoundException
    {
            MyId=id;
            MyPort=port;
            this.rf=rf;
            t1=new Thread(this);
            t1.start();
    }
   @Override
    public void run()
    {
        try {
            Thread.sleep(10000);
            Scanner sc;
            int id;
            Send send = new Send(rf,MyId);
            int count=0;
            String msg_id;
            try {
               
                sc = new Scanner(new File("./Input.txt"));
                while(sc.hasNextLine())
                {
                    String line = sc.nextLine();
                    msg_id="m";
                    count++;
		    //Step8.logger.info(line);	
                    StringTokenizer token = new StringTokenizer(line, ";");
                    while (token.hasMoreTokens())
                    {
                        id = Integer.parseInt(token.nextToken());
                        //Step8.logger.info(id);
                        if(id!=MyId)
                            break;
                        
                        else if(id==MyId)
                        {   //count++;
                            msg_id=msg_id+count;
                            
                            StringTokenizer token1=new StringTokenizer(token.nextToken(),",");
                           
                            ArrayList<Integer> dst_nodes=new ArrayList<>();
                            
                          
                            while (token1.hasMoreTokens())
                            {
                                dst_nodes.add(Integer.parseInt(token1.nextToken()));
				
                             
                            }

				/*for(int d=0;d<dst.size();d++)
                            {
                                Step8.logger.info(dst.get((d)));
                            }*/
                            String msg=token.nextToken();
                            MyClock mc=new MyClock();
                            mc.setClockValue(mc.getClockValue());
                            int clk=mc.getClockValue();                        
                            send.m_send(msg,clk,dst_nodes,msg_id);
                       
                        }
                        
                    }
                }
            } catch (FileNotFoundException ex) {                
                Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        } catch (InterruptedException ex) {
            Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
