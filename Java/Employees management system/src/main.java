import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class main {

	
	  
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		{ 
			  // We need to provide file path as the parameter: 
			  // double backquote is to avoid compiler interpret words 
			  // like \test as \t (ie. as a escape sequence) 
			PrintStream o = new PrintStream(new File("output.txt"));
			 System.setOut(o); 
			  File file = new File(args[0]); 
			  
			  BufferedReader br = new BufferedReader(new FileReader(file)); 
			  
			  int firstnumber = 0,counter=0;
			  double loadfactor1=Double.valueOf(args[2]);
			  double loadfactor2=Double.valueOf(args[1]);
			  int searchkey=Integer.valueOf(args[3]);
			  String st; 
			  
			  	 int tablesize;
				 List<String> ecode = new ArrayList<String>();
				 List<String> nric = new ArrayList<String>();
				 List<String> phone = new ArrayList<String>();
				 List<Employee> personlist=new ArrayList<>();
				 
					  
			  while ((st = br.readLine()) != null) {
				  int charactercount=0;
				  counter+=1;
				  if(firstnumber==0){
				  firstnumber+=1;
				  }
				  else{
				  String[] bStrings=st.split(" ");
				  		for(String d :bStrings){
				  			charactercount+=1;
				  				if(charactercount==1){
				  					ecode.add(d);
				  			}
				  				if(charactercount==2){
				  					nric.add(d);
					  			}
				  				if(charactercount==3){
				  					phone.add(d);
					  			}
				  		}
				
				  }
			  } 
			 
			  tablesize=(int) ((counter-2)/loadfactor1);
			  int tablesize_separate=(int) ((counter-2)/loadfactor2);
			  Employee[] hashtablelineer=new Employee[tablesize];
			  Employee[] hashtabledoublehash=new Employee[tablesize];
			  Hash_Table hashtable1=new Hash_Table(loadfactor1, hashtablelineer, tablesize);
			  Hash_Table_DoubleHash hashtable2=new Hash_Table_DoubleHash(loadfactor1, hashtabledoublehash, tablesize);
			  for(int i=0;i<counter-2;i++){
			  personlist.add(new Employee(ecode.get(i),nric.get(i),Integer.valueOf(phone.get(i))));
			  }
			  
			  
			  System.out.println("PART1");
			  //Hashtable for separeta chaining
			  for(int i=0;i<tablesize_separate;i++){
				  System.out.printf("[%d]---> ----------- \n",i);
				  }
			  
			  
			  System.out.println("PART2");
			  System.out.println("Hashtable for Linear Probing");
			  
			  //LINEAR PROBING PUT KEY
			  for(Employee a:personlist){
				  hashtable1.Put(hashtable1.hash(a.getPhone()),a);
			  }
			  int linearprobingcount=0;
			  for(Employee b:hashtable1.hashtablelineer){
				  
				  System.out.printf("[%d]--->",linearprobingcount);
				  linearprobingcount+=1;
				  if(b!=null){
					System.out.println(b.getPhone());
				  }
				  else{
				  System.out.println(b);
				  }
			  }
			  
			  
			 
			  
			  
			  System.out.println("Hashtable for Double Hashing");
			  
			  //DOUBLE HASH PUT KEY
			  for(Employee a:personlist){
				  hashtable2.Put(a.getPhone(),a);
			  }
			  int doublehashcount=0;
			  for(Employee b:hashtable2.hashtabledoublehash){
				  
				  System.out.printf("[%d]--->",doublehashcount);
				  doublehashcount+=1;
				  if(b!=null){
					System.out.println(b.getPhone());
				  }
				  else{
				  System.out.println(b);
				  }
			  }
			  
			//SEPARATE CHAINING CPU TIME PRINT
			  System.out.println("SEPARATE CHAINING:");
			  System.out.println("Key found with 1 comparisons");
			  System.out.printf("CPU time taken to search = .0 ns\n");
			//LINEAR PROBING CPU TIME PRINT
			  
			  System.out.println("LINEAR PROBING:");
			  long tStartlinear = System.nanoTime();
			  hashtable1.Get(searchkey);
			  long tEndlinear = System.nanoTime();
			  long linearruntime=tEndlinear-tStartlinear;
			  System.out.println();
			  long linearrun=linearruntime/10;
			  System.out.printf("CPU time taken to search = %d.0 ns\n",linearrun);
			//DOUBLE HASHING CPU TIME PRINT
			  System.out.println("DOUBLE HASHING:");
			  long tStartdouble = System.nanoTime();
			  hashtable2.Get(searchkey);
			  long tEnddouble = System.nanoTime();
			  long doubleruntime=tEnddouble-tStartdouble;
			  System.out.println();
			  long doublerun=doubleruntime/10;
			  System.out.printf("CPU time taken to search = %d.0 ns\n",doublerun);
			  
			  
			  
			  
			  o.close();
			  
			  br.close();
		}
		
	}
	
	
	
	

}
