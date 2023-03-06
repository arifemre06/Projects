import java.io.*;
import java.util.ArrayList;
import java.util.Locale;


public class Assignment2 {
 
	public static void main(String[] args) {
	 final int  maxwaiter=5;
	 final int  maxemployer=5;
	 final int  maxtableservices=3;
	 int employercount=0;
	 int waitercount=0;
	 int tableservicescount=0;
     int noproblemforcrate=0;
     int tableids=0;
	 int tablecount=0;
	 boolean addorderpossible=false;;
	 int employerexist = 1;
	boolean waiterexist =false;
	 int itemmiktari=0;
	 
	 boolean itemexist=false;
	 boolean uygunmasavarmý=false;
	 String check_out[] = null;
	 String str;
     String lines[];
     String str2;
     String lines2[];
     String lines2count[]=null;
     lines =new String[linecounter()];
     lines2 =new String[linecounter2()];
     String itemProperty[]=null;
     String employerProperty[]=null;
     String waiterProperty[]=null;
     String tableproperty[]=null;
     String orderproperty[]=null;
     String ordereditemproperty[]=null;
     String ordereditempropertyplus[]=null;
     String addorderproperty[]=null;
     String addordereditemproperty[]=null;
     String addordereditempropertyplus[]=null;
     String checkoutproperty[]=null;
     ArrayList <Items> itemlist=new ArrayList<>();
     ArrayList <Employer> employerlist=new ArrayList<>();
     ArrayList <Waiter> waiterlist=new ArrayList<>();
     ArrayList <Table> tablelist=new ArrayList<>();
     ArrayList <String> checkoutlist=new ArrayList <String>();
     
     try{
    	 FileInputStream fStream =new FileInputStream("setup.dat");
    	 DataInputStream dStream=new DataInputStream(fStream);
    	 BufferedReader bReader=new BufferedReader(new InputStreamReader(dStream));
    	 FileInputStream f2Stream =new FileInputStream("commands.dat");
    	 DataInputStream d2Stream=new DataInputStream(f2Stream);
    	 BufferedReader b2Reader=new BufferedReader(new InputStreamReader(d2Stream));
    	 int linescount2=0;
    	 int linescount=0;
    	 
    	 
    	 while ((str =bReader.readLine())!= null){
    		 
    		 lines[linescount]=str;
    		 linescount++;
    	   
    	 if (str.split(" ")[0].equals("add_item")){
    		 itemProperty=str.split(" ")[1].split(";");
    		 
    		 
    		 itemlist.add(new Items(itemProperty[0],Float.valueOf(itemProperty[1]),Integer.valueOf(itemProperty[2])));
    	   }
    	 else if  (str.split(" ")[0].equals("add_employer")){
    		 
    		 if(employercount<=maxemployer){
    			 
    		 employercount++;
    		 employerProperty=str.split(" ")[1].split(";");
    		 employerlist.add(new Employer(employerProperty[0],Integer.valueOf(employerProperty[1])));
    	 }
    	 
    	 else{
    		 System.out.println("Not allowed to exceed max. number of employer, MAX EMPLOYER");
    	 }
    	 }
    	 else if  (str.split(" ")[0].equals("add_waiter")){
    		 if(waitercount<=maxwaiter){
    		 waiterProperty=str.split(" ")[1].split(";");
    		 waiterlist.add(new Waiter(waiterProperty[0],Integer.valueOf(waiterProperty[1])));
    	 }
    		 else{
    			 System.out.println("Not allowed to exceed max. number of waiter, MAX WAITER");
    		 }
    	 }
    	 }
    	 dStream.close();
    	 
while ((str2 =b2Reader.readLine())!= null){
    		 
    		 lines2[linescount2]=str2;
    		 
    		 linescount2++;
    		 
    		 

if (str2.split(" ")[0].equals("create_table")){
    		 employerexist=0;
    		 noproblemforcrate=0;
    		 System.out.println("***********************************");
    		 System.out.println("PROGRESSING COMMAND: create_table");
    		 tableproperty=str2.split(" ")[1].split(";");
    		 
    		
    		for (int i=0;i<employerlist.size();i++){
    			
     	    	if (employerlist.get(i).getEmployername().equals(tableproperty[0])){
     	    		employerlist.get(i).cratedtablecount+=1; 
     	    		
     	    	}
     	    	if(employerlist.get(i).cratedtablecount>2){
      	    		   System.out.println(tableproperty[0]+"has already created ALLOWED_MAX_TABLES tables!");
      	    		   noproblemforcrate=1;
      	    		   
      	    	   }
     	    	
     	    	 if (employerlist.get(i).getEmployername().equals(tableproperty[0])){
     	    		employerexist=1;
     	    	}
     	    }               
                        
    	    if (employerexist==0){
    	    	employerexist=1;
    	    	System.out.println("There is no employer named "+tableproperty[0]);
    	    }
    	    
    	    else if (tablecount>5){
    	    	System.out.println("Not allowed to exceed max. number of tables, MAX_TABLES");
    	    }
    	    else if(noproblemforcrate!=0){
    	    	noproblemforcrate=0;
    	    }
    	    else{
    	    	System.out.println("A new table has successfully been added");
    	    	tablelist.add(new Table(tableproperty[0],Integer.valueOf(tableproperty[1])));
    	    	tablelist.get(tablecount).setId(tablecount);
    	    	tablecount++;
    	    	
    	    	
    	    }
    	    
    	    	}   
    	    
              	
    	 

  



else if(str2.split(" ")[0].equals("new_order")){
	boolean noproblemfororder=true;
    		  uygunmasavarmý=false;
    		  waiterexist=false;
    		  
    		 int ordereditemcount=0;
    		 int maxitem=0;
    		  int releativetableid=9999;
    		  System.out.println("***********************************");
    		  System.out.println("PROGRESSING COMMAND: new_order");
    		  orderproperty=str2.split(" ")[1].split(";");
    		  for(int h6=0;h6<waiterlist.size();h6++){
    		  for(int d1=0;d1<tablelist.size();d1++){
    			 
    			  
    		    if(tablelist.get(d1).getCapacity()>Integer.valueOf(orderproperty[1])&&tablelist.get(d1).getInservice()==false&&waiterlist.get(h6).getWaitername().equals(orderproperty[0])){
    		    uygunmasavarmý=true;
    		    
    		    tablelist.get(d1).setId(tableids);
    		    tableids++;
    		    break;
    		   
    		    }
    		    }
    		  }
    		  for(int d=0;d<tablelist.size();d++){
    		 if(tablelist.get(d).getCapacity()>Integer.valueOf(orderproperty[1])&&tablelist.get(d).getInservice()==false){
    			
    		      
    			      
    		    	  releativetableid=tablelist.get(d).getId();
    		    	 
    		    	  tablelist.get(d).setInservice(true);
    		    	  
    		    	  break;
    		      }
    		  }
    			if (!uygunmasavarmý){
    				System.out.println("There is no appropriate table for this order!");
    				
    			}
    		    
    		  
    		
    		else{ 
    			for (int f1=0;f1<waiterlist.size();f1++){
          			
           	    	if (waiterlist.get(f1).getWaitername().equals(orderproperty[0])){
           	    		waiterexist=true;
           	    	}
           	    	}
    			
    				
    					
    				
    			
    		  for (int f=0;f<waiterlist.size();f++){
      			
       	    	if (waiterlist.get(f).getWaitername().equals(orderproperty[0])){
       	    		waiterlist.get(f).tableservicecount+=1; 
       	    		
       	    		
       	    	}
       	    	if(waiterlist.get(f).tableservicecount>3){
        	    		   System.out.println("Not allowed to service max. number of tables, MAX_TABLE_SERVICES");
        	    		   noproblemfororder=false;
        	    		   
        	    	   }
       	    	
       	    	 
       	    	 
       	    }               
                          
      	    if (!waiterexist){
      	    	waiterexist=true;
      	    	System.out.println("There is no waiter named "+orderproperty[0]);
      	    }
      	
      	   
  	    else {
  	    	System.out.println("Table (= ID "+releativetableid+") has been taken into service");
  	    	tablelist.get(releativetableid).setTablewaiter(orderproperty[0]);
  	    	
  	    }	
  	    }
      	 if(uygunmasavarmý==false ||noproblemfororder==false){
      		 
      	 } 
      	  
      	 else{
      	tablelist.get(releativetableid).setMaxorder(tablelist.get(releativetableid).getMaxorder()+1);
      	
      	ordereditemproperty=orderproperty[2].split(":");
      	for (int g=0;g<ordereditemproperty.length;g++){
      		ordereditempropertyplus=ordereditemproperty[g].split("-");
      		maxitem=Integer.valueOf(ordereditempropertyplus[1])+maxitem;
      		
      		itemexist=false;
      	}
      	for (int g=0;g<ordereditemproperty.length;g++){
      		ordereditempropertyplus=ordereditemproperty[g].split("-");
      		
      	  if(maxitem<=10){
      		for(int h1=0;h1<itemlist.size();h1++){
      			if(ordereditempropertyplus[0].equals(itemlist.get(h1).getName())){
      			itemexist=true;
      		}
      		}
      		for(int h=0;h<itemlist.size();h++){
      			if(ordereditempropertyplus[0].equals(itemlist.get(h).getName())){
      				
      					
      			for (int j=0;j<Integer.valueOf(ordereditempropertyplus[1]);j++){
      				if(itemlist.get(h).getAmount()!=0){
      			    
      				itemlist.get(h).setAmount(itemlist.get(h).getAmount()-1);
      				
      				itemlist.get(h).setAlinanmiktar(itemlist.get(h).getAlinanmiktar()+1);
      				System.out.println("Item "+ordereditempropertyplus[0]+" added into order");
      				ordereditemcount++;
      				}
      			
      				else{
      					System.out.println("Sorry! No "+ordereditempropertyplus[0]+" in the stock!");
      				}
      				}
      				
      				
      				
      					
      				
      				tablelist.get(releativetableid).setPrice(tablelist.get(releativetableid).getPrice()+itemlist.get(h).getCost()*itemlist.get(h).getAlinanmiktar());
      				tablelist.get(releativetableid).setInservice(true);
      					
      				
      			
      			
      			}
      			else if(!itemexist) {
      				System.out.println("Unknown item "+ordereditempropertyplus[0]);
      			}
      		}
      	}
      	  else{
      		  System.out.println("You cannot want more than 10 items in one order MAX ITEMS!");
      		 tablelist.get(releativetableid).setInservice(false);
      		  break;
      		  
      	  }
      	}	  
    		  
      	 for(int h2=0;h2<itemlist.size();h2++){
      		 if(itemlist.get(h2).getAlinanmiktar()!=0){
      			String numberAsString = String.format (Locale.US ,"%.3f", itemlist.get(h2).getCost());
      			String numberAsString2 = String.format (Locale.US ,"%.3f",itemlist.get(h2).getCost()*itemlist.get(h2).getAlinanmiktar());
      		tablelist.get(releativetableid).setCheckoutl(tablelist.get(releativetableid).getCheckoutl()+"\n"+itemlist.get(h2).getName()+":	"+numberAsString+" (x "+itemlist.get(h2).getAlinanmiktar()+") "+numberAsString2+ " $");
      		 } 
      	 }
      	for(int h3=0;h3<itemlist.size();h3++){
      		itemlist.get(h3).setAlinanmiktar(0);
      	}
      	
      	
      	tablelist.get(releativetableid).setOrder(tablelist.get(releativetableid).getOrder()+"\n"+"		"+String.valueOf(ordereditemcount)+" item(s)");
      	 }
      	
	}	


else if(str2.split(" ")[0].equals("add_order")){
int	addordereditemcount=0;
int istableexist=0;
int maxitem1=0;
System.out.println("***********************************");
	System.out.println("PROGRESSING COMMAND: add_order");
	addorderproperty=str2.split(" ")[1].split(";");

		  
		  if(String.valueOf(tablelist.get(Integer.valueOf(addorderproperty[1])).getTablewaiter()).equals(addorderproperty[0])){
			  addorderpossible=true;
		  
	  
	  }
for(int n=0;n<tablelist.size();n++){
	if(Integer.valueOf(addorderproperty[1])==tablelist.get(n).getId()){
		istableexist=1;
	}
}
		for(int n=0;n<tablelist.size();n++){
			if(Integer.valueOf(addorderproperty[1])==tablelist.get(n).getId()){
				
				if(tablelist.get(n).getMaxorder()>=5){
					System.out.println("Not allowed to exceed max number of orders!");
					addorderpossible=false;
				}
			
			}
		}
	if(istableexist==0 || !addorderpossible){
			System.out.println("This table is either not in service now or "+addorderproperty[0]+" cannot be assigned this table!");
		}
	else{
		
		
		for(int y7=0;y7<waiterlist.size();y7++){
			if(waiterlist.get(y7).getWaitername().equals(addorderproperty[0])){
				waiterlist.get(y7).setTableservicecount(waiterlist.get(y7).getTableservicecount()+1);
			}
		}
		addordereditemproperty=addorderproperty[2].split(":");
      	for (int g=0;g<addordereditemproperty.length;g++){
      		
      		addordereditempropertyplus=addordereditemproperty[g].split("-");
      		maxitem1=Integer.valueOf(addordereditempropertyplus[1])+maxitem1;
      		
      		itemexist=false;
      	}
 for (int g=0;g<addordereditemproperty.length;g++){
      		
      		addordereditempropertyplus=addordereditemproperty[g].split("-");
      		
      	  if(maxitem1<=10){
      		for(int h1=0;h1<itemlist.size();h1++){
      			if(addordereditempropertyplus[0].equals(itemlist.get(h1).getName())){
      			itemexist=true;
      		}
      		}
      		for(int h=0;h<itemlist.size();h++){
      			if(addordereditempropertyplus[0].equals(itemlist.get(h).getName())){
      				
      					
      			for (int j=0;j<Integer.valueOf(addordereditempropertyplus[1]);j++){
      				if(itemlist.get(h).getAmount()!=0){
      			    
      				itemlist.get(h).setAmount(itemlist.get(h).getAmount()-1);
      				
      				itemlist.get(h).setAlinanmiktar(itemlist.get(h).getAlinanmiktar()+1);
      				System.out.println("Item "+addordereditempropertyplus[0]+" added into order");
      				addordereditemcount++;
      				}
      			
      				else{
      					System.out.println("Sorry! No "+addordereditempropertyplus[0]+" in the stock!");
      				}
      				}
      				
      			for(int l=0;l<tablecount;l++){
  					if(tablelist.get(l).getId()==Integer.valueOf(addorderproperty[1])){
  					
  				
  				
  				tablelist.get(l).setPrice(tablelist.get(l).getPrice()+itemlist.get(h).getCost()*itemlist.get(h).getAlinanmiktar());
  				tablelist.get(l).setInservice(true);
  					}
  				}
      			
      			
      			}
      			else if(!itemexist) {
      				System.out.println("Unknown item "+addordereditempropertyplus[0]);
      			}
      		}
      	}
      	  else{
      		  System.out.println("You cannot want more than 10 items in one order MAX ITEMS");
      		  break;
      	  }
      	}	 
      	for(int h2=0;h2<itemlist.size();h2++){
     		 if(itemlist.get(h2).getAlinanmiktar()!=0){
     			 if (tablelist.get(Integer.valueOf(addorderproperty[1])).getCheckoutl().equals(null)){
     				
     				
     				tablelist.get(Integer.valueOf(addorderproperty[1])).setCheckoutl(itemlist.get(h2).getName()+":	"+itemlist.get(h2).getCost()+" (x "+itemlist.get(h2).getAlinanmiktar()+" ) "+itemlist.get(h2).getCost()*itemlist.get(h2).getAlinanmiktar()+ " $");
     			 }
     			 else{
     				 
     				String numberAsString = String.format (Locale.US ,"%.3f", itemlist.get(h2).getCost());
     				String numberAsString2 = String.format (Locale.US ,"%.3f",itemlist.get(h2).getCost()*itemlist.get(h2).getAlinanmiktar());
     		tablelist.get(Integer.valueOf(addorderproperty[1])).setCheckoutl(tablelist.get(Integer.valueOf(addorderproperty[1])).getCheckoutl()+"\n"+itemlist.get(h2).getName()+":	"+numberAsString+" (x "+itemlist.get(h2).getAlinanmiktar()+") "+numberAsString2+ " $");
     		 } 
     		 }
      	}
      	for(int h3=0;h3<itemlist.size();h3++){
      		itemlist.get(h3).setAlinanmiktar(0);
      	}
      	tablelist.get(Integer.valueOf(addorderproperty[1])).setMaxorder(tablelist.get(Integer.valueOf(addorderproperty[1])).getMaxorder()+1);
      	tablelist.get(Integer.valueOf(addorderproperty[1])).setOrder(tablelist.get(Integer.valueOf(addorderproperty[1])).getOrder()+"\n"+"		"+String.valueOf(addordereditemcount)+" item(s)");
    	 }
	
	
}
    	 
else if(str2.split(" ")[0].equals("check_out")){
	boolean checkwaitertable=false;
	boolean checkwaiterexist=false;
	System.out.println("***********************************");
System.out.println("PROGRESSING COMMAND: check_out");
checkoutproperty=str2.split(" ")[1].split(";");
	boolean tableexist=false;
	
	for(int m1=0;m1<waiterlist.size();m1++){
		  if(waiterlist.get(m1).getWaitername().equals(checkoutproperty[0])){
			  checkwaiterexist=true;
		  }
		  if(String.valueOf(tablelist.get(Integer.valueOf(checkoutproperty[1])).getTablewaiter()).equals(checkoutproperty[0])&&waiterlist.get(m1).getWaitername().equals(checkoutproperty[0])){
			  checkwaitertable=true;
		  }
	  
	  }
for(int n=0;n<tablelist.size();n++){
	if(Integer.valueOf(checkoutproperty[1])==tablelist.get(n).getId()){
		tableexist=true;
	}
}
if(!checkwaiterexist){
	System.out.println("There is no waiter named "+checkoutproperty[0]);
}
else{
if(!tablelist.get(Integer.valueOf(checkoutproperty[1])).getInservice() || !checkwaitertable){
	System.out.println("This table is either not in service now or "+addorderproperty[0]+" cannot be assigned this table!");
}
else{
	tablelist.get(Integer.valueOf(checkoutproperty[1])).setInservice(false);
	System.out.println(tablelist.get(Integer.valueOf(checkoutproperty[1])).getCheckoutl());
	String asstring=String.format(Locale.US ,"%.3f",tablelist.get(Integer.valueOf(checkoutproperty[1])).getPrice());
	System.out.println("Total:	"+asstring+ " $");
	tablelist.get(Integer.valueOf(checkoutproperty[1])).setOrder(null);
	tablelist.get(Integer.valueOf(checkoutproperty[1])).setMaxorder(0);
}
    		
}

}

else if(str2.split(" ")[0].equals("stock_status")){
	System.out.println("***********************************");
	System.out.println("PROGRESSING COMMAND: stock_status");
	for (int z2=0;z2<itemlist.size();z2++){
		System.out.println(itemlist.get(z2).getName()+":	"+itemlist.get(z2).getAmount());
	}
	
    		 
    	 }
else if(str2.split(" ")[0].equals("get_table_status")){
	System.out.println("***********************************");
	System.out.println("PROGRESSING COMMAND: get_table_status");
	for(int i=0;i<tablelist.size();i++){
		if(!tablelist.get(i).getInservice()){
			
		
		System.out.println("Table "+tablelist.get(i).getId()+": Free");
	}
		else{
			System.out.println("Table "+tablelist.get(i).getId()+": Reserved ("+tablelist.get(i).getTablewaiter()+")");
		}
	}

}
 else if(str2.split(" ")[0].equals("get_order_status")){
	 System.out.println("***********************************");
	 System.out.println("PROGRESSING COMMAND: get_order_status");
   for(int i=0;i<waiterlist.size();i++){
    			 
	        System.out.println("Table: "+tablelist.get(i).getId());
	        System.out.println("	"+tablelist.get(i).getMaxorder()+" order(s)");
    	     System.out.println(""+tablelist.get(i).getOrder());
    	 }
    	 } 
    	 else if(str2.split(" ")[0].equals("get_employer_salary")){
    		 
    		 System.out.println("***********************************");
    		 System.out.println("PROGRESSING COMMAND: get_employer_salary");
    		 for(int i=0;i<employerlist.size();i++){
    			 
    			 System.out.println("Salary for "+employerlist.get(i).getEmployername()+": "+ (employerlist.get(i).getCratedtablecount()*employerlist.get(i).getEsalary()*(0.1)+employerlist.get(i).getEsalary()));
    		 }
    	 }
    	 else if(str2.split(" ")[0].equals("get_waiter_salary")){
    		 System.out.println("***********************************");
    		 System.out.println("PROGRESSING COMMAND: get_waiter_salary");
    		 for(int i=0;i<waiterlist.size();i++){
    			 
    			 System.out.println("Salary for "+waiterlist.get(i).getWaitername()+": "+ (waiterlist.get(i).getTableservicecount()*waiterlist.get(i).getWsalary()*(0.05)+waiterlist.get(i).getWsalary()));
    		 }
    	 }

    	 
	
    	 

	
     
}
d2Stream.close();
     }
     
     catch(Exception e){
    	 System.err.println("Hata : "+e.getMessage());
     }
    
    
    
	 
	 
     }
		

  public  static int linecounter(){
	int linecount=0;
	
    try{
   	 FileInputStream fStream =new FileInputStream("setup.dat");
   	 DataInputStream dStream=new DataInputStream(fStream);
   	 BufferedReader bReader=new BufferedReader(new InputStreamReader(dStream));
   	
   	 
   	 while ( bReader.readLine()!= null){
   		 
   		 
   		 linecount++;
   	 }
   	
    }
    catch(Exception e){
   	 System.err.println("Hata : "+e.getMessage());
    }
    return linecount;
}
  public  static int linecounter2(){
	  int linecount2=0;
	  try{
	  FileInputStream f2Stream =new FileInputStream("commands.dat");
		 DataInputStream d2Stream=new DataInputStream(f2Stream);
		 BufferedReader b2Reader=new BufferedReader(new InputStreamReader(d2Stream));
		 while ( b2Reader.readLine()!= null){
	   		 
	   		 
	   		 linecount2++;
	   	 }
	   	
	    }
	    catch(Exception e){
	   	 System.err.println("Hata : "+e.getMessage());
	    }
	    return linecount2;
	}
  }
  
