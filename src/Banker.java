import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class Banker {

	static int res=3; // res = resources , pr = processes
	static int pr=5;
	boolean flag;
	int [] available= new int[res]; 
	boolean [] finish= new boolean[pr];
	static int [][] maximum = new int[pr][res];
	static int [][] allocation = new int[pr][res];
	static int [][] need = new int[pr][res]; 
	
	public void checkRequest(int request[],int i ,int j){
		if(request[j]<=need[i][j] && request[j]<=available[j]){
			if(checksafety()==true){
				//when process take resource
			available[j]-=request[j];
			allocation[i][j]+=request[j];
			need[i][j]-=request[j];
			}
		}
	}
	public boolean checksafety(){
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
	}
	public static String read(FileInputStream in) throws IOException{
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
			     

		            
		            return (result);
}
}
	//read Processes , Resources , Instances 
	
	
	
	/*for(int i=0;i<pr;i++)
		finish[i] =false; //all array = false*/
	public static void main(String[]args) throws IOException{
		FileInputStream in =new FileInputStream("infile.txt");
		read(in);
		for(int i=0;i<pr;i++)
			for(int j=0;j<res;j++)
				System.out.println("max["+i+"]["+j+"]"+maximum[i][j]); 
		String choice ="";
		while(true){
			if(choice=="*"){
				System.out.println("Allocation	Max	Available");
				System.out.println(" A B C	A B C 	A B C");
				for(int i=0;i<pr;i++){
					for(int j=0;j<res;j++){
					System.out.print("P"+i+" "+allocation[i][j]+" ");
					}
					System.out.println();
				}
			}
				
		}
}
}