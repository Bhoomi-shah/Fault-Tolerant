package step8;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bhoomi
 */
public class Send {
    ReadFile rf;    
    ArrayList<Socket> dst_sckt;
    ArrayList<Integer> pro_time;
    int MyId;
    //Thread t1;
    //int MyId,MyPort;
   
    Send(ReadFile rf,int MyId) 
    {
      //      MyId=id;
      //     MyPort=port;
            this.rf=rf;
		dst_sckt=new ArrayList<>();
            pro_time=new ArrayList<>();
            this.MyId=MyId;
      //      t1=new Thread(this);
      //      t1.start();
    }
    
   
    void m_send(String msg_content,int c,ArrayList<Integer> dst_nodes,String msg_id)
    {   
        int j;
  
        dst_sckt=new ArrayList<>();
      //  Step8.logger.info("Inside m_send");
         try
        {
            for (int i=0;i<dst_nodes.size();i++)
            {
                
		
		j = dst_nodes.get(i);
              
                
		//Step8.logger.info("dst.get =" + j);
                Socket st=new Socket(rf.getHostName(j),rf.getPortNo(j));
                dst_sckt.add(st);
                OutputStream outtosend=st.getOutputStream();
                DataOutputStream out =new DataOutputStream(outtosend);
                String msg=msg_content+"@"+c+"@"+msg_id+"@"+MyId+"@"+j;
                out.writeUTF(msg);
               // Step8.logger.info("Sending: "+msg);
            }   
        }catch (IOException ex)
        {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
          try
        {       
                
            for (int m=0;m<dst_sckt.size();m++)
            {
           // Step8.logger.info("Inside for loop");
            Socket s1=dst_sckt.get(m);
            
            //Step8.logger.info(s1.toString());
           // Step8.logger.info("after getting first socket");
            DataInputStream in = new DataInputStream(s1.getInputStream());
            String inmsg=in.readUTF();
  	    //Step8.logger.info(inmsg);
            StringTokenizer stzr=new StringTokenizer(inmsg,"@");
            String msg_type=stzr.nextToken();
            if(msg_type.equals("proposed_time"))
            {
		//Step8.logger.info("Inside If");
                pro_time.add(Integer.parseInt(stzr.nextToken()));
              //  Step8.logger.info("Proposed Time from " + (stzr.nextToken())+ "is " + pro_time.get(m));
            }
            }
            
            
        }catch (IOException ex)
        {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }    
         
          
          int final_time=Collections.max(pro_time);
          //Step8.logger.info("Maximum is : "+final_time);   
            
          try
        {   
         //if(MyId==1)
	//Thread.sleep(30000); 

             for (Socket s1 : dst_sckt)
             {
                OutputStream outtosend=s1.getOutputStream();
                DataOutputStream out =new DataOutputStream(outtosend);
                out.writeUTF(msg_content+"#"+final_time);
		//Step8.logger.info("Final msg sent");
              }
            
               
     }
catch (IOException ex)
        {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        } 
//catch (InterruptedException ex) {
  //          Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
    //    }   
    
    }
}
