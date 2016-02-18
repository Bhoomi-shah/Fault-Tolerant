/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package step8;

import java.util.Iterator;
//import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Bhoomi
 */


public class MyQueue {
Boolean lock=false;
    public static PriorityBlockingQueue<MyMessage> pq= new PriorityBlockingQueue<>(1000);
    
    
    synchronized void put_msg(MyMessage mm)
    {
     
    if(lock)
    {
        try {
	//Step8.logger.info("Inside wait for put_msg");
            wait();
		
        } catch (InterruptedException ex) {
            Logger.getLogger(MyQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    pq.add(mm);
        
    }
    synchronized void update_msg(String msgcont,int t,String msg_id,int s,int r)
    {   
        if(lock)
        {
        try {
		//Step8.logger.info("Inside wait for update_msg");

            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(MyQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        MyMessage mq;
        Iterator it = pq.iterator();
               while (it.hasNext())
               {
               mq=(MyMessage)it.next();
               if(((mq.msg_id).equals(msg_id))&& ((mq.sender)==s) && ((mq.receiver)==r))
               {
                   pq.remove(mq);
                   pq.add(new MyMessage(msgcont,t,msg_id,s,r,true));
                   break;
               }
                }
               
               lock=true;
               notifyAll();
    }
    
    synchronized void poll_msg()
    {
        if(!lock)
        {
            try {
		//Step8.logger.info("Inside wait");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MyQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        MyMessage mmsg;
        
        
              
              while(true)
              {
                  try
                  {
              mmsg=pq.peek();
              if(mmsg.status && mmsg!=null)
              {
              //Step8.logger.info("");
              Step8.logger.info("Msg content : " + mmsg.msg);
              Step8.logger.info("Msg_id : " + mmsg.msg_id);
              Step8.logger.info("Timestamp : " + mmsg.t);
              Step8.logger.info("Sender : " + mmsg.sender);
              Step8.logger.info("Receiver : " + mmsg.receiver);
              Step8.logger.info("Status: " + mmsg.status);
              pq.poll();
              }
                
                  
              else{
                  lock=false;
                  notifyAll();
			Step8.logger.info("Inside else");
                  return;}
              
              
              }catch(NullPointerException e)
                  {//Step8.logger.info("Null pointer exception");
			lock=false;
			notifyAll();
			return;
				}
                  
                  
                  }
                  
              }
        
        
        
    }
    

