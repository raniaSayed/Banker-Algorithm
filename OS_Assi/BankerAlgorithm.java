import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;



public class BankerAlgorithm {

	static int res=3; // res = resources , pr = processes
	static int pr=5;
	static boolean flag;
	static int[][] maximum;
	static int [] available = new int[res]; 
	static boolean [] finish= new boolean[pr];	
	static int [][] need = maximum ;
	static int [][] allocation = null;
	
			
	public static String readMax (FileInputStream in) throws IOException{
		String s="",result="";
		 try (BufferedReader reader = new BufferedReader(
				 new InputStreamReader(in, Charset.defaultCharset()))) {
		      
			     int i=0;
		         while ((s = reader.readLine()) != null) {
		        	 result+=s;
		             String parts[] = result.split(",");
			         String[] nums =  result.split(",");
			         for(int j=0;j<res;j++){
			            maximum[i][j]= Integer.parseInt(parts[j]);
			         }
			         result ="";
			         i++;
		         }
		 }
		 return (result);
	}
	
	public static String readAvaliable (FileInputStream in) throws IOException{
		String s="",result="";
		 try (BufferedReader reader = new BufferedReader(
				 new InputStreamReader(in, Charset.defaultCharset()))) {
			 	String parts[] = result.split(" ");
			 	for(int j=0;j<res;j++){        
			 		available [j] = Integer.parseInt(parts[j]);
			 	}
			 	result ="";
		 }
		 return (result);
	}
	
	public static boolean safeSystem (){
		boolean enter =false;
		while(true){
			for(int i=0;i<pr;i++){
				for(int j=0;j<res;j++){
					finish[i]=false;
					if(need[i][j]<available[j]){
						enter = true;
						available[j]+=allocation[i][j];
						finish[i]=true;
						enter=true;
					}
				}
		
			}
			
			if(enter==false)
				break;
		}
		for(int i=0;i<pr;i++){
			if(finish[i]){
				flag=true;
			}
			else{
				flag=false;
				break;
			}
		}
		return flag;
	}
	
	
	public boolean checkRequest(int request[],int i ){
		if(request[0]<=need[i][0] && request[0]<=available[0] 
			&& request[1]<=need[i][1] && request[1]<=available[1] 
			&& request[2]<=need[i][2] && request[2]<=available[2] ){
			for (int k=0 ; k < res ;k++){
				available[k]-=request[k];
				allocation[i][k]+=request[k];
				need[i][k]-=request[k];
			}
		}
		if(safeSystem()){
			return true;
		}
		else{
			for (int k=0 ; k < res ;k++){
				available[k]+=request[k];
				allocation[i][k]-=request[k];
				need[i][k]+=request[k];
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {

		
	}
}
	
	

	


