package step8;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Bhoomi
 */
public class ReadFile {
    
    public HashMap<Integer, String> hostnamehashmap;
    public HashMap<Integer, Integer> porthashmap;
    public HashMap<Integer,String>recieverhashmap; 
    public HashMap<Integer,String>messagehashmap; 
    
    
    
    public ReadFile() throws FileNotFoundException
    { //constructor
        hostnamehashmap = new HashMap<Integer, String>();
        porthashmap = new HashMap<Integer, Integer>();
        recieverhashmap = new HashMap<Integer, String>();
        messagehashmap = new HashMap<Integer, String>();
        
        int id;
        Scanner scanner = new Scanner(new File("./Config2.txt"));
        
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            StringTokenizer token = new StringTokenizer(line, " ");
            while (token.hasMoreTokens()) {
                id = Integer.parseInt(token.nextToken());
                hostnamehashmap.put(id, token.nextToken());
                porthashmap.put(id, Integer.parseInt(token.nextToken()));
            }
        }
       
      
        
    }

    public String getHostName(int routerId) {
       String hostname= hostnamehashmap.get(routerId);
      return hostname;
    }
    public Integer getPortNo(int routerId) {
		int port = porthashmap.get(routerId);
		return port;
	}
    
}
