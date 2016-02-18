package step8;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bhoomi
 */
public class HandleConnection implements Runnable
{
    Socket mysocket;
    Thread t1;
    MyClock mc;
    int MyId;
    String msg_id;
    int sender;
    int receiver;
    MyQueue mq;
    HandleConnection(Socket mysocket,int MyId,MyQueue mq)
    {
            this.MyId=MyId;
            this.mysocket=mysocket;
            mc=new MyClock();
            this.mq=mq;
            t1=new Thread(this);
            t1.start();
    }
   @Override
    public void run()
    {
        
        
       // String msg=msg_content+"@"+c+"@"+msg_id+"@"+MyId+"@"+j;
       
            try
            {
               DataInputStream in = new DataInputStream(mysocket.getInputStream());
               String inmsg=in.readUTF();
               
               
               StringTokenizer intoken=new StringTokenizer(inmsg,"@");
               String msgcontent=intoken.nextToken();
               int inclock=Integer.parseInt(intoken.nextToken());
               
               //Step8.logger.info(inmsg);
               //Step8.logger.info("Incoming clk value : " +inclock );
               msg_id=intoken.nextToken();
               sender=Integer.parseInt(intoken.nextToken());
               receiver=Integer.parseInt(intoken.nextToken());
               int my=mc.getClockValue();
               //Step8.logger.info("my : " +my );
               mc.setClockValue(inclock>=my ? inclock:my);
               int pro_time=mc.getClockValue();
               //Step8.logger.info("Because of rec : "+pro_time);
                
               
               
               
               mq.put_msg(new MyMessage(msgcontent,pro_time,msg_id,sender,receiver)); 
		//Step8.logger.info("Message added");    
               DataOutputStream pro_out=new DataOutputStream(mysocket.getOutputStream());
               
             pro_out.writeUTF("proposed_time" +"@"+ pro_time+"@"+ MyId);
             //Step8.logger.info("Message sent");
               
               String final_time=in.readUTF();
               StringTokenizer stfinal=new StringTokenizer(final_time,"#");
        String temp=stfinal.nextToken();     
	  //Step8.logger.info("Final_time : "+stfinal.nextToken() );
               int final_t=Integer.parseInt(stfinal.nextToken());
               
               
               mq.update_msg(msgcontent,final_t,msg_id,sender,receiver);
             /*  Iterator it = Step6.pq.iterator();
               while (it.hasNext())
               {
               mq=(MyMessage)it.next();
               if(((mq.msg_id).equals(msg_id))&& ((mq.sender)==sender) && ((mq.receiver)==receiver))
               {
                   Step6.pq.remove(mq);
                   Step6.pq.add(new MyMessage(msgcontent,final_t,msg_id,sender,receiver,true));
                   break;
               }
                }
               */
              /* Iterator it1 = Step6.pq.iterator();
               while (it1.hasNext())
               {
               mq=(MyMessage)it1.next();
              Step8.logger.info("node :  " );
              Step8.logger.info("Msg content : " + mq.msg);
              Step8.logger.info("Msg_id : " + mq.msg_id);
              Step8.logger.info("Timestamp : " + mq.t);
              Step8.logger.info("Sender : " + mq.sender);
              Step8.logger.info("Receiver" + mq.receiver);
              
              
             }*/
            } catch (IOException ex) {
                Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            
            
            
    }
    
}
