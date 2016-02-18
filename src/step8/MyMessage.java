package step8;

/**
 *
 * @author Bhoomi
 */
class MyMessage implements Comparable<MyMessage>
{
    String msg;
    Boolean status;
    int t; 
    String msg_id;
    int sender;
    int receiver;
    public MyMessage(String msg,int t,String msg_id,int sender,int receiver)
    {
        this.msg=msg;
        status=false;
        this.t=t;
        this.msg_id=msg_id;
        this.sender=sender;
        this.receiver=receiver;
    }
    public MyMessage(String msg,int t,String msg_id,int sender,int receiver,Boolean status)
    {
        this.msg=msg;
        this.status=status;
        this.t=t;
        this.msg_id=msg_id;
        this.sender=sender;
        this.receiver=receiver;
    }
    @Override
    public int compareTo(MyMessage o) {
        //To change body of generated methods, choose Tools | Templates.
    if(t>o.t)
    {
        return 1;
    }
    else if(t<o.t)
    {
        return -1;
    }
    else return t-o.t;
    
    }
    
    
    
    
}