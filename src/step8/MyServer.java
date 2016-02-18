package step8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bhoomi
 */
public class MyServer implements Runnable{
    
    public int MyPortNo;
    int MyId;
    ServerSocket serversocket;
    Socket mysocket=null;
    Thread t;
    MyQueue mq;

    public MyServer(int MyId, int MyPortNo,MyQueue mq) {
        this.MyId = MyId;
        this.MyPortNo = MyPortNo;
       this.mq=mq;
        
        t=new Thread(this);
        t.start();
    }
  
        @Override
    public void run()
    {
            try {
                serversocket=new ServerSocket(MyPortNo);
            } catch (IOException ex) {
                Logger.getLogger(Step8.class.getName()).log(Level.SEVERE, null, ex);
            
            }
          
            while(true)
            {
                try
                {
                   // Step8.logger.info("Waiting for client on port " +serversocket.getLocalPort() + "...");
                    mysocket=serversocket.accept();
                    //Step8.logger.info("Just connected to "+ mysocket.getRemoteSocketAddress());
                
                }catch(IOException e)
                {
                    //Step8.logger.info(e.toString());
                }
                if(mysocket != null)
                {
                    HandleConnection hc;
                    hc= new HandleConnection(mysocket,MyId,mq);
                }
            
            }

    }
    }
    

