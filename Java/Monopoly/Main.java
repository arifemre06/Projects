



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;














import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException   {
		
        PrintStream outputFile = new PrintStream(new File("output.txt"));
        System.setOut(outputFile);
        
		String lines[];
		String str;
		int bank=100000;
		int chancecount=0;
		int chestcount=0;
		lines =new String[linecounter()];
		ArrayList <Property> landlist=new ArrayList<>();
		ArrayList <Property> railroadlist=new ArrayList<>();
		ArrayList <Property> companylist=new ArrayList<>();
		ArrayList <Chance> changelist=new ArrayList<>();
		ArrayList <CommunityChest> chestlist=new ArrayList<>();
		
		JSONParser parser = new JSONParser();
         JSONParser parser1=new JSONParser();
        try (Reader reader = new FileReader("property.json")) {
          try (Reader reader1 =new FileReader("list.json")){
        	 JSONObject jsonObject1=(JSONObject) parser1.parse(reader1);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            
            
         
            Players player1=new Player1();
            Players player2=new Player2();
            
            JSONArray list1=(JSONArray) jsonObject1.get("chanceList");
            for (int i=0;i<list1.size();i++){
            	String h =list1.get(i).toString();
            	String y =h.replaceAll("\"","");
            	y=y.replaceAll("}","");
            	changelist.add(new Chance(y.split(":")[1]));
            	
            }
            JSONArray list2=(JSONArray) jsonObject1.get("communityChestList");
            for (int i=0;i<list2.size();i++){
            	String h =list2.get(i).toString();
            	String y =h.replaceAll("\"","");
            	y=y.replaceAll("}","");
            	chestlist.add(new CommunityChest(y.split(":")[1]));
            	
            }
           //Tapu nesneleri olusturuldu
            JSONArray msg = (JSONArray) jsonObject.get("1");
            for (int i=0;i<msg.size();i++){
            	String h =msg.get(i).toString();
                String g =h.replaceAll("\"","");
                 g=g.replaceAll("}","");
                landlist.add(new Land(Integer.valueOf(g.split(",")[0].split(":")[1]),g.split(",")[1].split(":")[1],Integer.valueOf(g.split(",")[2].split(":")[1])));
                
            }
            
            
         JSONArray msg2 = (JSONArray) jsonObject.get("2");
            for (int j=0;j<msg2.size();j++){
            	String l =msg2.get(j).toString();
                String z =l.replaceAll("\"","");
                 z=z.replaceAll("}","");
                railroadlist.add(new Railroad(Integer.valueOf(z.split(",")[0].split(":")[1]),z.split(",")[1].split(":")[1],Integer.valueOf(z.split(",")[2].split(":")[1])));
                
            }
         JSONArray msg3 = (JSONArray) jsonObject.get("3");
            for (int k=0;k<msg3.size();k++){
            	String c =msg3.get(k).toString();
                String v =c.replaceAll("\"","");
                 v=v.replaceAll("}","");
                companylist.add(new Company(Integer.valueOf(v.split(",")[0].split(":")[1]),v.split(",")[1].split(":")[1],Integer.valueOf(v.split(",")[2].split(":")[1])));
                
            }
            //dosya okundu
           	 FileInputStream fStream =new FileInputStream(args[0]);
           	 DataInputStream dStream=new DataInputStream(fStream);
           	 BufferedReader bReader=new BufferedReader(new InputStreamReader(dStream));
           	 
           
           
             int linescount=0;
           	 	while ((str =bReader.readLine())!= null){
           	 	 
           	 		lines[linescount]=str;
           	 		linescount++;
           	 		//waittimelar
           	 		if(player1.waittime!=0&&str.split(";")[0].contains("1")){
           	 			player1.waittime-=1;
           	 			if(player1.waittime==3){
           	 			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 in jail (count=1)");
           	 			}
           	 			else if(player1.waittime==2){
           	 			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 in jail (count=2)");
           	 			}
           	 			else if(player1.waittime==1){
           	 			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 in jail (count=3)");
           	 			}
           	 		}
           	 	if(player2.waittime!=0&&str.split(";")[0].contains("2")){
       	 			player2.waittime-=1;
       	 		if(player2.waittime==3){
       	 			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 in jail (count=1)");
       	 			}
       	 			else if(player2.waittime==2){
       	 			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 in jail (count=2)");
       	 			}
       	 			else if(player2.waittime==1){
       	 			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 in jail (count=3)");
       	 			}
       	 		
       	 		}
           	 		//Player 1 hareket ettirildii
           	 	if (str.split(";")[0].contains("1")&&player1.waittime==0){
           	 		if(player1.getCurrentlocation()+Integer.valueOf(str.split(";")[1])>40){
           	 			player1.setCurrentlocation(player1.getCurrentlocation()+Integer.valueOf(str.split(";")[1])-40);
           	 			player1.setMoney(player1.getMoney()+200);
           	 			bank=bank-200;
           	 		}
           	 		else{
           	 			player1.setCurrentlocation(player1.getCurrentlocation()+Integer.valueOf(str.split(";")[1]));
           	 		}
           	 	}
           	 		//Player 2 hareket ettirildi
           	 	else if (str.split(";")[0].contains("2")&&player2.waittime==0){
           	 		if(player2.getCurrentlocation()+Integer.valueOf(str.split(";")[1])>40){
           	 			player2.setCurrentlocation(player2.getCurrentlocation()+Integer.valueOf(str.split(";")[1])-40);
           	 			player2.setMoney(player2.getMoney()+200);
           	 			bank=bank-200;
           	 		
           	 		}
           	 		else{
           	 			player2.setCurrentlocation(player2.getCurrentlocation()+Integer.valueOf(str.split(";")[1]));
           	 		
           	 		}
           	 		
       	 			
           	 			
       	 	}
           	if(player1.waittime!=0||!str.split(";")[0].contains("1")){
           		
           	}
           	else{
           		
       //*****************************************************************PLAYER 1*************************************************************
          if (player1.currentlocation==1){
        	  System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 is in GO square");
          }
       //player 1 land de oynuyor
         for(int i=0;i<landlist.size();i++){
        	 if(player1.getCurrentlocation()==landlist.get(i).getId()){
        		 
        		 //player 1 bu yere sahipse
        		 if(landlist.get(i).haveplayer1){
        			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 has  "+landlist.get(i).name);
        		 }
        		 //player 2 bu yere sahipse
        		 else if(landlist.get(i).haveplayer2){
        			 if(landlist.get(i).getCost()<=2000){
        				 if(player1.money-landlist.get(i).getCost()/100*40<0){
        					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
        					 System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
        			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
        			    	    System.out.println("Banker	"+bank);
        			    	    if(player1.money>player2.money){
        			    	    	System.out.println("Winner Player 1");
        			    	    }
        			    	    else if(player1.money<player2.money){
        			    	    	System.out.println("Winner Player 2");
        			    	    }
        			    	    else{
        			    	    	System.out.println("scoreless");
        			    	    }
        			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	    System.exit(1);
        					 
        				 }
        				 else{
        			 player1.setMoney(player1.getMoney()-landlist.get(i).getCost()/100*40);
        			 player2.setMoney(player2.getMoney()+landlist.get(i).getCost()/100*40);
        			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 paid rent for "+landlist.get(i).name);
        		 }
        			 }
        			 else if(landlist.get(i).getCost()>2000&&landlist.get(i).getCost()<=3000){
        				 if(player1.money-landlist.get(i).getCost()/100*30<0){
        					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
        					 System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
        			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
        			    	    System.out.println("Banker	"+bank);
        			    	    if(player1.money>player2.money){
        			    	    	System.out.println("Winner Player 1");
        			    	    }
        			    	    else if(player1.money<player2.money){
        			    	    	System.out.println("Winner Player 2");
        			    	    }
        			    	    else{
        			    	    	System.out.println("scoreless");
        			    	    }
        			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	    System.exit(1);
        					 
        				 }
        				 else{
            			 player1.setMoney(player1.getMoney()-landlist.get(i).getCost()/100*30);
            			 player2.setMoney(player2.getMoney()+landlist.get(i).getCost()/100*30);
            			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 paid rent for "+landlist.get(i).name);
            		 }
        			 }
        			 else if(landlist.get(i).getCost()>3000&&landlist.get(i).getCost()<=4000){
        				 if(player1.money-landlist.get(i).getCost()/100*35<0){
        					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
        					 System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
        			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
        			    	    System.out.println("Banker	"+bank);
        			    	    if(player1.money>player2.money){
        			    	    	System.out.println("Winner Player 1");
        			    	    }
        			    	    else if(player1.money<player2.money){
        			    	    	System.out.println("Winner Player 2");
        			    	    }
        			    	    else{
        			    	    	System.out.println("scoreless");
        			    	    }
        			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	    System.exit(1);
        					 
        				 }
        				 else{
            			 player1.setMoney(player1.getMoney()-landlist.get(i).getCost()/100*35);
            			 player2.setMoney(player2.getMoney()+landlist.get(i).getCost()/100*35);
            			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 paid rent for "+landlist.get(i).name);
            		 }
        			 }
        		 }
        		 else if(!landlist.get(i).haveplayer2&&!landlist.get(i).haveplayer1){
        			 
        			 player1.setMoney(player1.getMoney()-landlist.get(i).getCost());
        			 bank=bank+landlist.get(i).getCost();
        			 landlist.get(i).haveplayer1=true;
        			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 bought "+landlist.get(i).name);
        			 	if(player1.have.equals("have:")){
        			 			player1.have=player1.have+" "+landlist.get(i).getName();
        			 }
        			 	else{
        			 		player1.have=player1.have+","+landlist.get(i).getName();
        			 	}
        		 }
        	 }
         }
         //player 1 railroadda oynuyor
         for(int i=0;i<railroadlist.size();i++){
        	 if(player1.getCurrentlocation()==railroadlist.get(i).getId()){
        		 //player 1 bu yere sahipse
        		 if(railroadlist.get(i).haveplayer1){
        			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 has  "+railroadlist.get(i).name);
        		 }
        		 //player 2 bu yere sahipse
        		 else if(railroadlist.get(i).haveplayer2){
        			 if(player1.money-player2.haverailroad*25<0){
    					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
        			 else{
           	       player1.setMoney(player1.getMoney()-player2.haverailroad*25);
           	       player2.setMoney(player2.getMoney()+player2.haverailroad*25);
           	    System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 paid rent for "+railroadlist.get(i).name);
           	 	} 
        		 }
        		 else if (!railroadlist.get(i).haveplayer2&&!railroadlist.get(i).haveplayer1){
        			 if(player1.money-railroadlist.get(i).getCost()<0){
    					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
        			 else{
        			 
        			 player1.setMoney(player1.getMoney()-railroadlist.get(i).getCost());
        			 bank=bank+railroadlist.get(i).getCost();
        			 player1.haverailroad+=1;
        			 railroadlist.get(i).haveplayer1=true;
        			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 bought "+railroadlist.get(i).name);
        			 if(player1.have.equals("have:")){
        			 player1.have=player1.have+" "+railroadlist.get(i).getName();
        			 }
        			 else{
        				 player1.have=player1.have+","+railroadlist.get(i).getName();
        			 }
        		 }
        		 }
        	 }
         }
       //player 1 companyde oynuyor
         for(int i=0;i<companylist.size();i++){
        	 if(player1.getCurrentlocation()==companylist.get(i).getId()){
        		 //player 1 bu yere sahipse
        		 if(companylist.get(i).haveplayer1){
        			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 has  "+companylist.get(i).name);
        		 }
        		 //player 2 bu yere sahipse
        		 else if(companylist.get(i).haveplayer2){
        			 if(player1.money-4*Integer.valueOf(str.split(";")[1])<0){
    					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
        			 else{
        			 player1.setMoney(player1.getMoney()-4*Integer.valueOf(str.split(";")[1]));
        			 player2.setMoney(player2.getMoney()+4*Integer.valueOf(str.split(";")[1]));
        			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 paid rent for "+companylist.get(i).name);
        	 }
        		 }
        		 else if(!companylist.get(i).haveplayer2&&!companylist.get(i).haveplayer1){
        			 if(player1.money-companylist.get(i).getCost()<0){
    					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
        			 else{
        			 
        			 player1.setMoney(player1.getMoney()-companylist.get(i).getCost());
        			 bank+=companylist.get(i).getCost();
        			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 bought "+companylist.get(i).name);
        			 if(player1.have.equals("have:")){
        			 player1.have=player1.have+" "+companylist.get(i).getName();
        			 }else{
        				 player1.have=player1.have+","+companylist.get(i).getName();
        			 }
        			 companylist.get(i).haveplayer1=true;
        			 
        		 }
        		 }
         }
           	 	}
         //player1 taxta
         if (player1.currentlocation==5||player1.currentlocation==39){
        	 if(player1.money-100<0){
				 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
				 System.out.println("-----------------------------------------------------------------------------------------------------------");
		    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
		    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
		    	    System.out.println("Banker	"+bank);
		    	    if(player1.money>player2.money){
		    	    	System.out.println("Winner Player 1");
		    	    }
		    	    else if(player1.money<player2.money){
		    	    	System.out.println("Winner Player 2");
		    	    }
		    	    else{
		    	    	System.out.println("scoreless");
		    	    }
		    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
		    	    System.exit(1);
				 
			 }
        	 else{
        	 player1.money-=100;
        	 bank+=100;
        	 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 paid Tax");
        	 
         }
         }
         //playerlar1 free parkingde
         if(player1.currentlocation==21&&player1.waittime==0){
        	 
        	 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 is in free park");
        	 
         }
       
       //player 1 jailda
         if(player1.currentlocation==11&&player1.waittime==0){
        	 player1.waittime=4;
        	 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 went to jail");
        	 
         }
       //playerlar1 go to jailda
         if(player1.currentlocation==31){
        	 player1.currentlocation=11;
        	 player1.waittime=4;
        	 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 went to jail");
         }
       //playerlar1 chancede
         if(player1.currentlocation==8 || player1.currentlocation==23 || player1.currentlocation==37){
        	 if(chancecount==5){
        		 chancecount=0;
        		 player1.money+=100;
        		 bank-=100;
        		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw You have won a crossword competition - collect $100");
        	}
        	 else if(chancecount==4){
        		 player1.money+=150;
        		 bank-=150;
        		 chancecount+=1;
        		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Your building loan matures - collect $150");
        	 }
        	else if(chancecount==3){
        		if(player1.money-15<0){
					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
					 System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
			    	    System.out.println("Banker	"+bank);
			    	    if(player1.money>player2.money){
			    	    	System.out.println("Winner Player 1");
			    	    }
			    	    else if(player1.money<player2.money){
			    	    	System.out.println("Winner Player 2");
			    	    }
			    	    else{
			    	    	System.out.println("scoreless");
			    	    }
			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	    System.exit(1);
					 
				 }
        		else{
        		player1.money-=15;
        		bank+=15;
        		chancecount+=1;
        		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Pay poor tax of $15");
        	}
        	}
        	 //"**********************************3 KARE GERi GiTMEEEEEEE""""""***************************************************
        	else if(chancecount==2){
        		chancecount+=1;
        		
        		if(player1.currentlocation==8){
        			if(player1.money-100<0){
   					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
   					 System.out.println("-----------------------------------------------------------------------------------------------------------");
   			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
   			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
   			    	    System.out.println("Banker	"+bank);
   			    	    if(player1.money>player2.money){
   			    	    	System.out.println("Winner Player 1");
   			    	    }
   			    	    else if(player1.money<player2.money){
   			    	    	System.out.println("Winner Player 2");
   			    	    }
   			    	    else{
   			    	    	System.out.println("scoreless");
   			    	    }
   			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
   			    	    System.exit(1);
   					 
   				 }
        			else{
        			player1.currentlocation-=3;
        			player1.money-=100;
        			bank+=100;
        			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	");
        			}
        		}
        		else if(player1.currentlocation==23){
        			player1.currentlocation-=3;
        			 if(landlist.get(10).haveplayer1){
               			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 has  "+landlist.get(10).name);
               		 }
               		 //player 2 bu yere sahipse
               		 else if(landlist.get(10).haveplayer2){
               			 
               			 if(landlist.get(10).getCost()<=2000){
               				if(player1.money-landlist.get(10).getCost()/100*40<0){
              					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
              					 System.out.println("-----------------------------------------------------------------------------------------------------------");
              			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
              			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
              			    	    System.out.println("Banker	"+bank);
              			    	    if(player1.money>player2.money){
              			    	    	System.out.println("Winner Player 1");
              			    	    }
              			    	    else if(player1.money<player2.money){
              			    	    	System.out.println("Winner Player 2");
              			    	    }
              			    	    else{
              			    	    	System.out.println("scoreless");
              			    	    }
              			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
              			    	    System.exit(1);
              					 
              				 }
               				else{
               			 player1.setMoney(player1.getMoney()-landlist.get(10).getCost()/100*40);
               			 player2.setMoney(player2.getMoney()+landlist.get(10).getCost()/100*40);
               			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 paid rent for "+landlist.get(10).name);
               		 }
               			 }
               			 else if(landlist.get(10).getCost()>2000&&landlist.get(10).getCost()<=3000){
               				if(player1.money-landlist.get(10).getCost()/100*30<0){
             					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
             					 System.out.println("-----------------------------------------------------------------------------------------------------------");
             			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
             			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
             			    	    System.out.println("Banker	"+bank);
             			    	    if(player1.money>player2.money){
             			    	    	System.out.println("Winner Player 1");
             			    	    }
             			    	    else if(player1.money<player2.money){
             			    	    	System.out.println("Winner Player 2");
             			    	    }
             			    	    else{
             			    	    	System.out.println("scoreless");
             			    	    }
             			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
             			    	    System.exit(1);
             					 
             				 }
               				else{
                   			 player1.setMoney(player1.getMoney()-landlist.get(10).getCost()/100*30);
                   			 player2.setMoney(player2.getMoney()+landlist.get(10).getCost()/100*30);
                   			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player1 paid rent for "+landlist.get(10).name);
                   		 }
               			 }
               			 else if(landlist.get(10).getCost()>3000&&landlist.get(10).getCost()<=4000){
               				if(player1.money-landlist.get(10).getCost()/100*35<0){
             					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
             					 System.out.println("-----------------------------------------------------------------------------------------------------------");
             			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
             			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
             			    	    System.out.println("Banker	"+bank);
             			    	    if(player1.money>player2.money){
             			    	    	System.out.println("Winner Player 1");
             			    	    }
             			    	    else if(player1.money<player2.money){
             			    	    	System.out.println("Winner Player 2");
             			    	    }
             			    	    else{
             			    	    	System.out.println("scoreless");
             			    	    }
             			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
             			    	    System.exit(1);
             					 
             				 }
               				else{
                   			 player1.setMoney(player1.getMoney()-landlist.get(10).getCost()/100*35);
                   			 player2.setMoney(player2.getMoney()+landlist.get(10).getCost()/100*35);
                   			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player1 paid rent for "+landlist.get(10).name);
                   		 }
               			 }
               		 }
               		 else if(!landlist.get(10).haveplayer2&&!landlist.get(10).haveplayer1){
               			if(player1.money-landlist.get(10).getCost()<0){
         					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
         					 System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
         			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
         			    	    System.out.println("Banker	"+bank);
         			    	    if(player1.money>player2.money){
         			    	    	System.out.println("Winner Player 1");
         			    	    }
         			    	    else if(player1.money<player2.money){
         			    	    	System.out.println("Winner Player 2");
         			    	    }
         			    	    else{
         			    	    	System.out.println("scoreless");
         			    	    }
         			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	    System.exit(1);
         					 
         				 }
               			else{
               			 
               			 player1.setMoney(player1.getMoney()-landlist.get(10).getCost());
               			 bank=bank+landlist.get(10).getCost();
               			 landlist.get(10).haveplayer1=true;
               			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 bought "+landlist.get(10).name);
               			 	if(player1.have.equals("have:")){
               			 			player1.have=player1.have+" "+landlist.get(10).getName();
               			 }
               			 	else{
               			 		player1.have=player1.have+","+landlist.get(10).getName();
               			 	}
               		 }
               		 }
        		}
        		else if(player1.currentlocation==37){
        			player1.currentlocation-=3;
        			if(chestcount==10){
        	      		 
        	      		
        	      		   chestcount=0;
        	      		   player1.money+=50;
        	      		   bank-=50;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw From sale of stock you get $50");
        	      	   }
        	      	   else if(chestcount==9){
        	      		
        	      		
        	      		   chestcount+=1;
        	      		   player1.money+=100;
        	      		   bank-=100;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw You inherit $100");
        	      	   }
        	      	   else if(chestcount==8){
        	      		
        	      		
        	      		   chestcount+=1;
        	      		 if(player1.money-50<0){
         					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
         					 System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
         			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
         			    	    System.out.println("Banker	"+bank);
         			    	    if(player1.money>player2.money){
         			    	    	System.out.println("Winner Player 1");
         			    	    }
         			    	    else if(player1.money<player2.money){
         			    	    	System.out.println("Winner Player 2");
         			    	    }
         			    	    else{
         			    	    	System.out.println("scoreless");
         			    	    }
         			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	    System.exit(1);
         					 
         				 }
        	      		 else{
        	      		   player1.money-=50;
        	      		   bank+=50;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw Pay School Fees of $50");
        	      	   }
        	      	   }
        	      	   else if(chestcount==7){
        	      		 
        	      		
        	      		   chestcount+=1;
        	      		 if(player1.money-100<0){
         					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
         					 System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
         			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
         			    	    System.out.println("Banker	"+bank);
         			    	    if(player1.money>player2.money){
         			    	    	System.out.println("Winner Player 1");
         			    	    }
         			    	    else if(player1.money<player2.money){
         			    	    	System.out.println("Winner Player 2");
         			    	    }
         			    	    else{
         			    	    	System.out.println("scoreless");
         			    	    }
         			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	    System.exit(1);
         					 
         				 }
        	      		 else{
        	      		   player1.money-=100;
        	      		   bank+=100;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw Pay Hospital Fees of $100");
        	      	   }
        	      	   }
        	      	   else if(chestcount==6){
        	      		
        	      		
        	      		   chestcount+=1;
        	      		   player1.money+=100;
        	      		   bank-=100;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw Life Insurance Matures - collect $100");
        	      	   }
        	      	   else if(chestcount==5){
        	      		 
        	      		   chestcount+=1;
        	      		   player1.money+=20;
        	      		   bank-=20;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw Income Tax refund - collect $20");
        	      	   }
        	      	   else if(chestcount==4){
        	      		
        	      		   chestcount+=1;
        	      		 if(player2.money-50<0){
         					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
         					 System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
         			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
         			    	    System.out.println("Banker	"+bank);
         			    	    if(player1.money>player2.money){
         			    	    	System.out.println("Winner Player 1");
         			    	    }
         			    	    else if(player1.money<player2.money){
         			    	    	System.out.println("Winner Player 2");
         			    	    }
         			    	    else{
         			    	    	System.out.println("scoreless");
         			    	    }
         			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	    System.exit(1);
         					 
         				 }
        	      		 else{
        	      		   player1.money+=50;
        	      		   player2.money-=50;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw Grand Opera Night - collect $50 from every player for opening night seats");
        	      	   }
        	      	   }
        	      	   else if(chestcount==3){
        	      		 
        	      		   chestcount+=1;
        	      		 if(player2.money-10<0){
         					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
         					 System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
         			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
         			    	    System.out.println("Banker	"+bank);
         			    	    if(player1.money>player2.money){
         			    	    	System.out.println("Winner Player 1");
         			    	    }
         			    	    else if(player1.money<player2.money){
         			    	    	System.out.println("Winner Player 2");
         			    	    }
         			    	    else{
         			    	    	System.out.println("scoreless");
         			    	    }
         			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	    System.exit(1);
         					 
         				 }
        	      		 else{
        	      		   player1.money+=10;
        	      		   player2.money-=10;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw It is your birthday Collect $10 from each player");
        	      	   }
        	      	   }
        	      	   else if(chestcount==2){
        	      		 
        	      		   chestcount+=1;
        	      		 if(player1.money-50<0){
         					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
         					 System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
         			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
         			    	    System.out.println("Banker	"+bank);
         			    	    if(player1.money>player2.money){
         			    	    	System.out.println("Winner Player 1");
         			    	    }
         			    	    else if(player1.money<player2.money){
         			    	    	System.out.println("Winner Player 2");
         			    	    }
         			    	    else{
         			    	    	System.out.println("scoreless");
         			    	    }
         			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	    System.exit(1);
         					 
         				 }
        	      		 else{
        	      		   player1.money-=50;
        	      		   bank+=50;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw Doctor's fees - Pay $50");
        	      	   }
        	      	   }
        	      	   else if(chestcount==1){
        	      		
        	      		   chestcount+=1;
        	      		   player1.money+=75;
        	      		   bank-=75;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw Bank error in your favor - collect $75");
        	      	   }
        	      	   else if(chestcount==0){
        	      		  chestcount+=1;
        	      		   player1.currentlocation=1;
        	      		   player1.money+=200;
        	      		   bank-=200;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Go back 3 spaces	"+"Player 1 draw Advance to Go (Collect $200)");
        	      	   }
        		}
        	
        	 
        	}
        	else if (chancecount==1){
        		chancecount+=1;
        		 
        		   player1.currentlocation=27;
        		   if (player1.currentlocation==37){
        			   player1.money+=200;
        			   bank-=200;
        		   }
        		   //player 1 bu yere sahipse
          		 if(landlist.get(14).haveplayer1){
          			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Advance to Leicester Square	"+"Player 1 has  "+landlist.get(14).name);
          		 }
          		 //player 2 bu yere sahipse
          		 else if(landlist.get(14).haveplayer2){
          			 if(landlist.get(14).getCost()<=2000){
          				 if(player1.money-landlist.get(14).getCost()/100*40<0){
         					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
         					 System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
         			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
         			    	    System.out.println("Banker	"+bank);
         			    	    if(player1.money>player2.money){
         			    	    	System.out.println("Winner Player 1");
         			    	    }
         			    	    else if(player1.money<player2.money){
         			    	    	System.out.println("Winner Player 2");
         			    	    }
         			    	    else{
         			    	    	System.out.println("scoreless");
         			    	    }
         			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	    System.exit(1);
         					 
         				 }
          				 else{
          			 player1.setMoney(player1.getMoney()-landlist.get(14).getCost()/100*40);
          			 player2.setMoney(player2.getMoney()+landlist.get(14).getCost()/100*40);
          			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Advance to Leicester Square	"+"Player 1 paid rent for "+landlist.get(14).name);
          		 }
          			 }
          			 else if(landlist.get(14).getCost()>2000&&landlist.get(14).getCost()<=3000){
          				 if(player1.money-landlist.get(14).getCost()/100*30<0){
         					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
         					 System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
         			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
         			    	    System.out.println("Banker	"+bank);
         			    	    if(player1.money>player2.money){
         			    	    	System.out.println("Winner Player 1");
         			    	    }
         			    	    else if(player1.money<player2.money){
         			    	    	System.out.println("Winner Player 2");
         			    	    }
         			    	    else{
         			    	    	System.out.println("scoreless");
         			    	    }
         			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	    System.exit(1);
         					 
         				 }
          				 else{
              			 player1.setMoney(player1.getMoney()-landlist.get(14).getCost()/100*30);
              			 player2.setMoney(player2.getMoney()+landlist.get(14).getCost()/100*30);
              			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Advance to Leicester Square	"+"Player1 paid rent for "+landlist.get(14).name);
              		 }
          			 }
          			 else if(landlist.get(14).getCost()>3000&&landlist.get(14).getCost()<=4000){
          				 if(player1.money-landlist.get(14).getCost()/100*35<0){
         					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
         					 System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
         			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
         			    	    System.out.println("Banker	"+bank);
         			    	    if(player1.money>player2.money){
         			    	    	System.out.println("Winner Player 1");
         			    	    }
         			    	    else if(player1.money<player2.money){
         			    	    	System.out.println("Winner Player 2");
         			    	    }
         			    	    else{
         			    	    	System.out.println("scoreless");
         			    	    }
         			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
         			    	    System.exit(1);
         					 
         				 }
          				 else{
              			 player1.setMoney(player1.getMoney()-landlist.get(14).getCost()/100*35);
              			 player2.setMoney(player2.getMoney()+landlist.get(14).getCost()/100*35);
              			System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Advance to Leicester Square	"+"Player1 paid rent for "+landlist.get(14).name);
              		 }
          			 }
          		 }
          		 else if(!landlist.get(14).haveplayer2&&!landlist.get(14).haveplayer1){
          			 if(player1.money-landlist.get(14).getCost()<0){
     					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
     					 System.out.println("-----------------------------------------------------------------------------------------------------------");
     			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
     			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
     			    	    System.out.println("Banker	"+bank);
     			    	    if(player1.money>player2.money){
     			    	    	System.out.println("Winner Player 1");
     			    	    }
     			    	    else if(player1.money<player2.money){
     			    	    	System.out.println("Winner Player 2");
     			    	    }
     			    	    else{
     			    	    	System.out.println("scoreless");
     			    	    }
     			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
     			    	    System.exit(1);
     					 
     				 }
          			 else{
          			
          			 player1.setMoney(player1.getMoney()-landlist.get(14).getCost());
          			 bank=bank+landlist.get(14).getCost();
          			 landlist.get(14).haveplayer1=true;
          			 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Advance to Leicester Square	"+"Player 1 bought "+landlist.get(14).name);
          			 	if(player1.have.equals("have:")){
          			 			player1.have=player1.have+" "+landlist.get(14).getName();
          			 }
          			 	else{
          			 		player1.have=player1.have+","+landlist.get(14).getName();
          			 	}
          		 }
          		 }
        	}
        	else if(chancecount==0){
        		chancecount+=1;
        		 
        		player1.currentlocation=1;
        		player1.money+=200;
        		bank-=200;
        		System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Advance to Go (Collect $200)");
        	}
         }
       //playerlar1 communitychestte
         else if(player1.currentlocation==3 || player1.currentlocation==18 ||player1.currentlocation==34){
        	 if(chestcount==10){
	      		 
 	      		
	      		   chestcount=0;
	      		   player1.money+=50;
	      		   bank-=50;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw From sale of stock you get $50");
	      	   }
	      	   else if(chestcount==9){
	      		
	      		
	      		   chestcount+=1;
	      		   player1.money+=100;
	      		   bank-=100;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw You inherit $100");
	      	   }
	      	   else if(chestcount==8){
	      		
	      		
	      		   chestcount+=1;
	      		 if(player1.money-50<0){
					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
					 System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
			    	    System.out.println("Banker	"+bank);
			    	    if(player1.money>player2.money){
			    	    	System.out.println("Winner Player 1");
			    	    }
			    	    else if(player1.money<player2.money){
			    	    	System.out.println("Winner Player 2");
			    	    }
			    	    else{
			    	    	System.out.println("scoreless");
			    	    }
			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	    System.exit(1);
					 
				 }
	      		 else{
	      		   player1.money-=50;
	      		   bank+=50;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Pay School Fees of $50");
	      	   }
	      	   }
	      	   else if(chestcount==7){
	      		 
	      		
	      		   chestcount+=1;
	      		 if(player1.money-100<0){
					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
					 System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
			    	    System.out.println("Banker	"+bank);
			    	    if(player1.money>player2.money){
			    	    	System.out.println("Winner Player 1");
			    	    }
			    	    else if(player1.money<player2.money){
			    	    	System.out.println("Winner Player 2");
			    	    }
			    	    else{
			    	    	System.out.println("scoreless");
			    	    }
			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	    System.exit(1);
					 
				 }
	      		 else{
	      		   player1.money-=100;
	      		   bank+=100;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Pay Hospital Fees of $100");
	      	   }
	      	   }
	      	   else if(chestcount==6){
	      		
	      		
	      		   chestcount+=1;
	      		   player1.money+=100;
	      		   bank-=100;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Life Insurance Matures - collect $100");
	      	   }
	      	   else if(chestcount==5){
	      		 
	      		   chestcount+=1;
	      		   player1.money+=20;
	      		   bank-=20;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Income Tax refund - collect $20");
	      	   }
	      	   else if(chestcount==4){
	      		
	      		   chestcount+=1;
	      		 if(player2.money-50<0){
					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
					 System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
			    	    System.out.println("Banker	"+bank);
			    	    if(player1.money>player2.money){
			    	    	System.out.println("Winner Player 1");
			    	    }
			    	    else if(player1.money<player2.money){
			    	    	System.out.println("Winner Player 2");
			    	    }
			    	    else{
			    	    	System.out.println("scoreless");
			    	    }
			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	    System.exit(1);
					 
				 }
	      		 else{
	      		   player1.money+=50;
	      		   player2.money-=50;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Grand Opera Night - collect $50 from every player for opening night seats");
	      	   }
	      	   }
	      	   else if(chestcount==3){
	      		 
	      		   chestcount+=1;
	      		 if(player2.money-10<0){
					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
					 System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
			    	    System.out.println("Banker	"+bank);
			    	    if(player1.money>player2.money){
			    	    	System.out.println("Winner Player 1");
			    	    }
			    	    else if(player1.money<player2.money){
			    	    	System.out.println("Winner Player 2");
			    	    }
			    	    else{
			    	    	System.out.println("scoreless");
			    	    }
			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	    System.exit(1);
					 
				 }
	      		 else{
	      		   player1.money+=10;
	      		   player2.money-=10;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw It is your birthday Collect $10 from each player");
	      	   }
	      	   }
	      	   else if(chestcount==2){
	      		 
	      		   chestcount+=1;
	      		 if(player1.money-50<0){
					 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
					 System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
			    	    System.out.println("Banker	"+bank);
			    	    if(player1.money>player2.money){
			    	    	System.out.println("Winner Player 1");
			    	    }
			    	    else if(player1.money<player2.money){
			    	    	System.out.println("Winner Player 2");
			    	    }
			    	    else{
			    	    	System.out.println("scoreless");
			    	    }
			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	    System.exit(1);
					 
				 }
	      		 else{
	      		   player1.money-=50;
	      		   bank+=50;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Doctor's fees - Pay $50");
	      	   }
	      	   }
	      	   else if(chestcount==1){
	      		
	      		   chestcount+=1;
	      		   player1.money+=75;
	      		   bank-=75;
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Bank error in your favor - collect $75");
	      	   }
	      	   else if(chestcount==0){
	      		  chestcount+=1;
	      		   player1.currentlocation=1;
	      		   player1.money+=200;
	      		   bank-=200;
	      		   
	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player1.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 draw Advance to Go (Collect $200)");
	      	   }
         }
           	}    
         //************************************************PLAYER 2*****************************************
           	if(player2.waittime!=0||!str.split(";")[0].contains("2")){
           		
           	}
           	else{
           		if (player2.currentlocation==1){
              	  System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 is in GO square");
                }
         //player 2 land de oynuyor
           	
         for(int i=0;i<landlist.size();i++){
        	 
        	 if(player2.getCurrentlocation()==landlist.get(i).getId()){
        		 //player 2 bu yere sahipse
        		 if(landlist.get(i).haveplayer2){
        			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 has  "+landlist.get(i).name);
        		 }
        		 //player 1 bu yere sahipse
        		 else if(landlist.get(i).haveplayer1){
        			 if(landlist.get(i).getCost()<=2000){
        				 
        				 if(player2.money-landlist.get(i).getCost()/100*40<0){
        					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
        					 System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
        			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
        			    	    System.out.println("Banker	"+bank);
        			    	    if(player1.money>player2.money){
        			    	    	System.out.println("Winner Player 1");
        			    	    }
        			    	    else if(player1.money<player2.money){
        			    	    	System.out.println("Winner Player 2");
        			    	    }
        			    	    else{
        			    	    	System.out.println("scoreless");
        			    	    }
        			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	    System.exit(1);
        					 
        				 }
        				 else{
        			 player2.setMoney(player2.getMoney()-landlist.get(i).getCost()/100*40);
        			 player1.setMoney(player1.getMoney()+landlist.get(i).getCost()/100*40);
        			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 paid rent for "+landlist.get(i).name);
        		 }
        			 }
        			 else if(landlist.get(i).getCost()>2000&&landlist.get(i).getCost()<=3000){
        				 
            				 if(player2.money-landlist.get(i).getCost()/100*30<0){
            					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
            					 System.out.println("-----------------------------------------------------------------------------------------------------------");
            			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
            			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
            			    	    System.out.println("Banker	"+bank);
            			    	    if(player1.money>player2.money){
            			    	    	System.out.println("Winner Player 1");
            			    	    }
            			    	    else if(player1.money<player2.money){
            			    	    	System.out.println("Winner Player 2");
            			    	    }
            			    	    else{
            			    	    	System.out.println("scoreless");
            			    	    }
            			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
            			    	    System.exit(1);
            					 
            				 
        				 }
            				 else{
            			 player2.setMoney(player2.getMoney()-landlist.get(i).getCost()/100*30);
            			 player1.setMoney(player1.getMoney()+landlist.get(i).getCost()/100*30);
            			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 paid rent for "+landlist.get(i).name);
            		 }
        			 }
        			 else if(landlist.get(i).getCost()>3000&&landlist.get(i).getCost()<=4000){
        				 if(player2.money-landlist.get(i).getCost()/100*35<0){
        					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
        					 System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
        			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
        			    	    System.out.println("Banker	"+bank);
        			    	    if(player1.money>player2.money){
        			    	    	System.out.println("Winner Player 1");
        			    	    }
        			    	    else if(player1.money<player2.money){
        			    	    	System.out.println("Winner Player 2");
        			    	    }
        			    	    else{
        			    	    	System.out.println("scoreless");
        			    	    }
        			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
        			    	    System.exit(1);
        					 
        				 
    				 }
        				 else{
            			 player2.setMoney(player2.getMoney()-landlist.get(i).getCost()/100*35);
            			 player1.setMoney(player1.getMoney()+landlist.get(i).getCost()/100*30);
            			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 paid rent for "+landlist.get(i).name);
            		 }
        			 }
        		 }
        		 else if(!landlist.get(i).haveplayer2&&!landlist.get(i).haveplayer1){
        			 if(player2.money-landlist.get(i).getCost()<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 
				 }
        			 else{
        			 
        			 player2.setMoney(player2.getMoney()-landlist.get(i).getCost());
        			 bank=bank+landlist.get(i).getCost();
        			 landlist.get(i).haveplayer2=true;
        			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 bought "+landlist.get(i).name);
        			 if(player2.have.equals("have:")){
        			 player2.have=player2.have+" "+landlist.get(i).getName();
        			 }
        			 else{
        				 player2.have=player2.have+","+landlist.get(i).getName();
        			 }
        		 }
        	 }
         }
         }
         //player 2 railroadda oynuyor
         for(int i=0;i<railroadlist.size();i++){
        	 if(player2.getCurrentlocation()==railroadlist.get(i).getId()){
        		 //player 2 bu yere sahipse
        		 if(railroadlist.get(i).haveplayer2){
        			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 has  "+railroadlist.get(i).name);
        		 }
        		 //player 1 bu yere sahipse
        		 else if(railroadlist.get(i).haveplayer1){
        			 if(player2.money-player1.haverailroad*25<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
        			 else{
           	       player2.setMoney(player2.getMoney()-player1.haverailroad*25);
           	       player1.setMoney(player1.getMoney()+player1.haverailroad*25);
           	    System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 paid rent for "+railroadlist.get(i).name);
           	 	} 
        		 }
        		 else if(!railroadlist.get(i).haveplayer1&&!railroadlist.get(i).haveplayer2){
        			 if(player2.money-railroadlist.get(i).getCost()<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
        			 else{
        			 
        			 player2.setMoney(player2.getMoney()-railroadlist.get(i).getCost());
        			 bank=bank+railroadlist.get(i).getCost();
        			 player2.haverailroad+=1;
        			 railroadlist.get(i).haveplayer2=true;
        			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 bought "+railroadlist.get(i).name);
        			 if(player2.have.equals("have:")){
        			 player2.have=player2.have+" "+railroadlist.get(i).getName();
        			 }
        			 else{
        				 player2.have=player2.have+","+railroadlist.get(i).getName();
        			 }
        		 }
        		 }
        	 }
         }
         
         //player 2 companyde oynuyor
         for(int i=0;i<companylist.size();i++){
        	 if(player2.getCurrentlocation()==companylist.get(i).getId()){
        		 //player 2 bu yere sahipse
        		 if(companylist.get(i).haveplayer2){
        			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 has  "+companylist.get(i).name);
        		 }
        		 //player 1 bu yere sahipse
        		 else if(companylist.get(i).haveplayer1){
        			 if(player2.money-4*Integer.valueOf(str.split(";")[1])<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
        			 else{
        			 player2.setMoney(player2.getMoney()-4*Integer.valueOf(str.split(";")[1]));
        			 player1.setMoney(player1.getMoney()+4*Integer.valueOf(str.split(";")[1]));
        			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 paid rent for "+companylist.get(i).name);
        	 }
        		 }
        		 else if(!companylist.get(i).haveplayer1&&!companylist.get(i).haveplayer2){
        			 if(player2.money-companylist.get(i).getCost()<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
        			 else{
        			 
        			 player2.setMoney(player2.getMoney()-companylist.get(i).getCost());
        			 bank+=companylist.get(i).getCost();
        			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 bought "+companylist.get(i).name);
        			 if(player2.have.equals("have:")){
        			 player2.have=player2.have+" "+companylist.get(i).getName();
        			 }
        			 else{
        				 player2.have=player2.have+","+companylist.get(i).getName();
        			 }
        			 companylist.get(i).haveplayer2=true;
        			 }
        		 }
         }
           	 	}
        
         //player2 taxta
         if (player2.currentlocation==5||player2.currentlocation==39){
        	 if(player2.money-100<0){
				 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
				 System.out.println("-----------------------------------------------------------------------------------------------------------");
		    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
		    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
		    	    System.out.println("Banker	"+bank);
		    	    if(player1.money>player2.money){
		    	    	System.out.println("Winner Player 1");
		    	    }
		    	    else if(player1.money<player2.money){
		    	    	System.out.println("Winner Player 2");
		    	    }
		    	    else{
		    	    	System.out.println("scoreless");
		    	    }
		    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
		    	    System.exit(1);
				 
			 }
        	 else{
        	 player2.money-=100;
        	 bank+=100;
        	 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 paid Tax");
        	 //
         }
         }
         
         
        
         //player 2 free parking
         if(player2.currentlocation==21&&player2.waittime==0){
        	
        	 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 is in free park");
         }
         
        
         
         //player 2 jailda
         if(player2.currentlocation==11&&player2.waittime==0){
        	 player2.waittime=4;
        	 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 went to jail");
         }
         //player 2 go tojailda
         
         if(player2.currentlocation==31){
        	 player2.currentlocation=11;
        	 player2.waittime=4;
        	 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 went to jail");
         }
         
         //player 2chance da
         if(player2.currentlocation==8 || player2.currentlocation==23 || player2.currentlocation==37){
        	 if(chancecount==5){
        		 chancecount=0;
        		 player2.money+=100;
        		 bank-=100;
        		 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw You have won a crossword competition - collect $100 ");
        	}
        	 else if(chancecount==4){
        		 player2.money+=150;
        		 bank-=150;
        		 chancecount+=1;
        		 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Your building loan matures - collect $150");
        	 }
        	else if(chancecount==3){
        		chancecount+=1;
        		if(player2.money-15<0){
					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
					 System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
			    	    System.out.println("Banker	"+bank);
			    	    if(player1.money>player2.money){
			    	    	System.out.println("Winner Player 1");
			    	    }
			    	    else if(player1.money<player2.money){
			    	    	System.out.println("Winner Player 2");
			    	    }
			    	    else{
			    	    	System.out.println("scoreless");
			    	    }
			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
			    	    System.exit(1);
					 
				 }
        		else{
        		player2.money-=15;
        		bank+=15;
        		
        		System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Pay poor tax of $15");
        	}
        	}
        	 //3 KARE GERi GiTME *********************************************************************************3KAREEE****************************
        	else if(chancecount==2){
        		chancecount+=1;
        		
        		if(player2.currentlocation==8){
        			player2.currentlocation-=3;
        			if(player2.money-100<0){
   					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
   					 System.out.println("-----------------------------------------------------------------------------------------------------------");
   			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
   			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
   			    	    System.out.println("Banker	"+bank);
   			    	    if(player1.money>player2.money){
   			    	    	System.out.println("Winner Player 1");
   			    	    }
   			    	    else if(player1.money<player2.money){
   			    	    	System.out.println("Winner Player 2");
   			    	    }
   			    	    else{
   			    	    	System.out.println("scoreless");
   			    	    }
   			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
   			    	    System.exit(1);
   					 
   				 }
        			else{
        			player2.money-=100;
        			bank+=100;
        			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	");
        			}
        		}
        		else if(player2.currentlocation==23){
        			player2.currentlocation-=3;
        			 if(landlist.get(10).haveplayer2){
               			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 has  "+landlist.get(10).name);
               		 }
               		 //player 2 bu yere sahipse
               		 else if(landlist.get(10).haveplayer1){
               			 if(landlist.get(10).getCost()<=2000){
               				if(player2.money-landlist.get(10).getCost()/100*40<0){
           					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
           					 System.out.println("-----------------------------------------------------------------------------------------------------------");
           			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
           			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
           			    	    System.out.println("Banker	"+bank);
           			    	    if(player1.money>player2.money){
           			    	    	System.out.println("Winner Player 1");
           			    	    }
           			    	    else if(player1.money<player2.money){
           			    	    	System.out.println("Winner Player 2");
           			    	    }
           			    	    else{
           			    	    	System.out.println("scoreless");
           			    	    }
           			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
           			    	    System.exit(1);
           					 
           				 }
               				else{
               			 player2.setMoney(player2.getMoney()-landlist.get(10).getCost()/100*40);
               			 player1.setMoney(player1.getMoney()+landlist.get(10).getCost()/100*40);
               			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 paid rent for "+landlist.get(10).name);
               		 }
               			 }
               			 else if(landlist.get(10).getCost()>2000&&landlist.get(10).getCost()<=3000){
               				if(player2.money-landlist.get(10).getCost()/100*30<0){
              					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
              					 System.out.println("-----------------------------------------------------------------------------------------------------------");
              			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
              			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
              			    	    System.out.println("Banker	"+bank);
              			    	    if(player1.money>player2.money){
              			    	    	System.out.println("Winner Player 1");
              			    	    }
              			    	    else if(player1.money<player2.money){
              			    	    	System.out.println("Winner Player 2");
              			    	    }
              			    	    else{
              			    	    	System.out.println("scoreless");
              			    	    }
              			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
              			    	    System.exit(1);
              					 
              				 }
               				else{
                   			 player2.setMoney(player2.getMoney()-landlist.get(10).getCost()/100*30);
                   			 player1.setMoney(player1.getMoney()+landlist.get(10).getCost()/100*30);
                   			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 paid rent for "+landlist.get(10).name);
                   		 }
               			 }
               			 else if(landlist.get(10).getCost()>3000&&landlist.get(10).getCost()<=4000){
               				if(player2.money-landlist.get(10).getCost()/100*35<0){
              					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
              					 System.out.println("-----------------------------------------------------------------------------------------------------------");
              			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
              			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
              			    	    System.out.println("Banker	"+bank);
              			    	    if(player1.money>player2.money){
              			    	    	System.out.println("Winner Player 1");
              			    	    }
              			    	    else if(player1.money<player2.money){
              			    	    	System.out.println("Winner Player 2");
              			    	    }
              			    	    else{
              			    	    	System.out.println("scoreless");
              			    	    }
              			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
              			    	    System.exit(1);
              					 
              				 }
               				else{
                   			 player2.setMoney(player2.getMoney()-landlist.get(10).getCost()/100*35);
                   			 player1.setMoney(player1.getMoney()+landlist.get(10).getCost()/100*35);
                   			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 paid rent for "+landlist.get(10).name);
                   		 }
               			 }
               		 }
               		 else if(!landlist.get(10).haveplayer2&&!landlist.get(10).haveplayer1){
               			if(player2.money-landlist.get(10).getCost()<0){
          					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
          					 System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
          			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
          			    	    System.out.println("Banker	"+bank);
          			    	    if(player1.money>player2.money){
          			    	    	System.out.println("Winner Player 1");
          			    	    }
          			    	    else if(player1.money<player2.money){
          			    	    	System.out.println("Winner Player 2");
          			    	    }
          			    	    else{
          			    	    	System.out.println("scoreless");
          			    	    }
          			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	    System.exit(1);
          					 
          				 }
               			else{
               			 
               			 player2.setMoney(player2.getMoney()-landlist.get(14).getCost());
               			 bank=bank+landlist.get(10).getCost();
               			 landlist.get(10).haveplayer1=true;
               			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 bought "+landlist.get(10).name);
               			 	if(player2.have.equals("have:")){
               			 			player2.have=player2.have+" "+landlist.get(10).getName();
               			 }
               			 	else{
               			 		player2.have=player2.have+","+landlist.get(10).getName();
               			 	}
               		 }
               		 }
        		}
        		else if(player2.currentlocation==37){
        			player2.currentlocation-=3;
        			if(chestcount==10){
        	      		 
        	      		 
        	      		   chestcount=0;
        	      		   player1.money+=50;
        	      		   bank-=50;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw From sale of stock you get $50");
        	      	   }
        	      	   else if(chestcount==9){
        	      		
        	      		
        	      		   chestcount+=1;
        	      		   player2.money+=100;
        	      		   bank-=100;
        	      		 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw You inherit $100");
        	      	   }
        	      	   else if(chestcount==8){
        	      		
        	      		
        	      		   chestcount+=1;
        	      		 if(player2.money-50<0){
          					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
          					 System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
          			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
          			    	    System.out.println("Banker	"+bank);
          			    	    if(player1.money>player2.money){
          			    	    	System.out.println("Winner Player 1");
          			    	    }
          			    	    else if(player1.money<player2.money){
          			    	    	System.out.println("Winner Player 2");
          			    	    }
          			    	    else{
          			    	    	System.out.println("scoreless");
          			    	    }
          			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	    System.exit(1);
          					 
          				 }
        	      		 else{
        	      		   player2.money-=50;
        	      		   bank+=50;
        	      		 }
        	      		System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw Pay School Fees of $50");
        	      	   }
        	      	   else if(chestcount==7){
        	      		 
        	      		
        	      		   chestcount+=1;
        	      		 if(player2.money-100<0){
          					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
          					 System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
          			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
          			    	    System.out.println("Banker	"+bank);
          			    	    if(player1.money>player2.money){
          			    	    	System.out.println("Winner Player 1");
          			    	    }
          			    	    else if(player1.money<player2.money){
          			    	    	System.out.println("Winner Player 2");
          			    	    }
          			    	    else{
          			    	    	System.out.println("scoreless");
          			    	    }
          			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	    System.exit(1);
          					 
          				 }
        	      		 else{
        	      		   player2.money-=100;
        	      		   bank+=100;
        	      		 }
        	      		System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw Pay Hospital Fees of $100");
        	      	   }
        	      	   else if(chestcount==6){
        	      		
        	      		 
        	      		   chestcount+=1;
        	      		   player2.money+=100;
        	      		   bank-=100;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw Life Insurance Matures - collect $100");
        	      	   }
        	      	   else if(chestcount==5){
        	      		
        	      		   chestcount+=1;
        	      		   player2.money+=20;
        	      		   bank-=20;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw Income Tax refund - collect $20");
        	      	   }
        	      	   else if(chestcount==4){
        	      		
        	      		   chestcount+=1;
        	      		 if(player1.money-50<0){
          					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
          					 System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
          			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
          			    	    System.out.println("Banker	"+bank);
          			    	    if(player1.money>player2.money){
          			    	    	System.out.println("Winner Player 1");
          			    	    }
          			    	    else if(player1.money<player2.money){
          			    	    	System.out.println("Winner Player 2");
          			    	    }
          			    	    else{
          			    	    	System.out.println("scoreless");
          			    	    }
          			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	    System.exit(1);
          					 
          				 }
        	      		 else{
        	      		   player2.money+=50;
        	      		   player1.money-=50;
        	      		 }
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw Grand Opera Night - collect $50 from every player for opening night seats");
        	      	   }
        	      	   else if(chestcount==3){
        	      		 
        	      		   chestcount+=1;
        	      		 if(player1.money-10<0){
          					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
          					 System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
          			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
          			    	    System.out.println("Banker	"+bank);
          			    	    if(player1.money>player2.money){
          			    	    	System.out.println("Winner Player 1");
          			    	    }
          			    	    else if(player1.money<player2.money){
          			    	    	System.out.println("Winner Player 2");
          			    	    }
          			    	    else{
          			    	    	System.out.println("scoreless");
          			    	    }
          			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	    System.exit(1);
          					 
          				 }
        	      		 else{
        	      		   player2.money+=10;
        	      		   player1.money-=10;
        	      		 }
        	      		System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw It is your birthday Collect $10 from each player");
        	      	   }
        	      	   else if(chestcount==2){
        	      		
        	      		   chestcount+=1;
        	      		 if(player2.money-50<0){
          					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
          					 System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
          			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
          			    	    System.out.println("Banker	"+bank);
          			    	    if(player1.money>player2.money){
          			    	    	System.out.println("Winner Player 1");
          			    	    }
          			    	    else if(player1.money<player2.money){
          			    	    	System.out.println("Winner Player 2");
          			    	    }
          			    	    else{
          			    	    	System.out.println("scoreless");
          			    	    }
          			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	    System.exit(1);
          					 
          				 }else{
        	      		   player2.money-=50;
        	      		   bank+=50;
          				 }
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw Doctor's fees - Pay $50");
        	      	   }
        	      	   else if(chestcount==1){
        	      		 
        	      		   chestcount+=1;
        	      		   player2.money+=75;
        	      		   bank-=75;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw Bank error in your favor - collect $75");
        	      	   }
        	      	   else if(chestcount==0){
        	      		  chestcount+=1;
        	      		   player2.currentlocation=1;
        	      		   player2.money+=200;
        	      		   bank-=200;
        	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Go back 3 spaces	"+"Player 2 draw Advance to Go (Collect $200)");
        	      	   }
        		}
        		}
        	else if (chancecount==1){
        		chancecount+=1;
        		 if (player2.currentlocation==37){
      			   player2.money+=200;
      			   bank-=200;
      		   }
        		   player2.currentlocation=27;
        		   //player 2 bu yere sahipse
        		   if(landlist.get(14).haveplayer2){
        			   System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Advance to Leicester Square "+"Player 2 has  "+landlist.get(14).name);
          		 }
          		 //player 1 bu yere sahipse
          		 else if(landlist.get(14).haveplayer1){
          			 if(landlist.get(14).getCost()<=2000){
          				 if(player2.money-landlist.get(14).getCost()/100*40<0){
          					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
          					 System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
          			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
          			    	    System.out.println("Banker	"+bank);
          			    	    if(player1.money>player2.money){
          			    	    	System.out.println("Winner Player 1");
          			    	    }
          			    	    else if(player1.money<player2.money){
          			    	    	System.out.println("Winner Player 2");
          			    	    }
          			    	    else{
          			    	    	System.out.println("scoreless");
          			    	    }
          			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	    System.exit(1);
          					 
          				 }
          				 else{
          			 player2.setMoney(player2.getMoney()-landlist.get(14).getCost()/100*40);
          			 player1.setMoney(player1.getMoney()+landlist.get(14).getCost()/100*40);
          			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Advance to Leicester Square "+"Player 2 paid rent for "+landlist.get(14).name);
          		 }
          			 }
          			 else if(landlist.get(14).getCost()>2000&&landlist.get(14).getCost()<=3000){
          				 if(player2.money-landlist.get(14).getCost()/100*30<0){
          					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
          					 System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
          			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
          			    	    System.out.println("Banker	"+bank);
          			    	    if(player1.money>player2.money){
          			    	    	System.out.println("Winner Player 1");
          			    	    }
          			    	    else if(player1.money<player2.money){
          			    	    	System.out.println("Winner Player 2");
          			    	    }
          			    	    else{
          			    	    	System.out.println("scoreless");
          			    	    }
          			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	    System.exit(1);
          					 
          				 }
          				 else{
              			 player2.setMoney(player2.getMoney()-landlist.get(14).getCost()/100*30);
              			 player1.setMoney(player1.getMoney()+landlist.get(14).getCost()/100*30);
              			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Advance to Leicester Square "+"Player 2 paid rent for "+landlist.get(14).name);
              		 }
          			 }
          			 else if(landlist.get(14).getCost()>3000&&landlist.get(14).getCost()<=4000){
          				 if(player2.money-landlist.get(14).getCost()/100*35<0){
          					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
          					 System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
          			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
          			    	    System.out.println("Banker	"+bank);
          			    	    if(player1.money>player2.money){
          			    	    	System.out.println("Winner Player 1");
          			    	    }
          			    	    else if(player1.money<player2.money){
          			    	    	System.out.println("Winner Player 2");
          			    	    }
          			    	    else{
          			    	    	System.out.println("scoreless");
          			    	    }
          			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
          			    	    System.exit(1);
          					 
          				 }
          				 else{
              			 player2.setMoney(player2.getMoney()-landlist.get(14).getCost()/100*35);
              			 player1.setMoney(player1.getMoney()+landlist.get(14).getCost()/100*35);
              			 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Advance to Leicester Square "+"Player 2 paid rent for "+landlist.get(14).name);
              		 }
          			 }
          		 }
          		 else if(!landlist.get(14).haveplayer2&&!landlist.get(14).haveplayer1){
          			 if(player2.money-landlist.get(14).getCost()<0){
      					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
      					 System.out.println("-----------------------------------------------------------------------------------------------------------");
      			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
      			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
      			    	    System.out.println("Banker	"+bank);
      			    	    if(player1.money>player2.money){
      			    	    	System.out.println("Winner Player 1");
      			    	    }
      			    	    else if(player1.money<player2.money){
      			    	    	System.out.println("Winner Player 2");
      			    	    }
      			    	    else{
      			    	    	System.out.println("scoreless");
      			    	    }
      			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
      			    	    System.exit(1);
      					 
      				 }
          			 else{
          			 
          			 player2.setMoney(player2.getMoney()-landlist.get(14).getCost());
          			 bank=bank+landlist.get(14).getCost();
          			 landlist.get(14).haveplayer2=true;
          			System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Advance to Leicester Square "+"Player 2 bought "+landlist.get(14).name);
          			 if(player2.have.equals("have:")){
          			 player2.have=player2.have+" "+landlist.get(14).getName();
          			 }
          			 else{
          				 player2.have=player2.have+","+landlist.get(14).getName();
          			 }
          			 }
          		 }
          		 
        	}
        	else if(chancecount==0){
        		chancecount+=1;
        		player2.currentlocation=1;
        		player2.money+=200;
        		bank-=200;
        		System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Advance to Go (Collect $200)");
        	}
         }
         
           //player2 chestte
         else if(player2.currentlocation==3 || player2.currentlocation==18 ||player2.currentlocation==34){
        	   if(chestcount==10){
  	      		 
  	      		
  	      		   chestcount=0;
  	      		   player2.money+=50;
  	      		   bank-=50;
  	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw From sale of stock you get $50");
  	      	   }
  	      	   else if(chestcount==9){
  	      		
  	      		
  	      		   chestcount+=1;
  	      		   player2.money+=100;
  	      		   bank-=100;
  	      		System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw You inherit $100");
  	      	   }
  	      	   else if(chestcount==8){
  	      		
  	      		
  	      		   chestcount+=1;
  	      		   
  	      		 if(player2.money-50<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
    					 System.out.println("---------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("---------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
  	      		 else{
  	      		   player2.money-=50;
  	      		   bank+=50;
  	      		   
  	      		 }
  	      		System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Pay School Fees of $50");
  	      	   }
  	      	   else if(chestcount==7){
  	      		 
  	      		
  	      		   chestcount+=1;
  	      		 if(player2.money-100<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
    					 System.out.println("---------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("---------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
  	      		 else{
  	      		   player2.money-=100;
  	      		   bank+=100;
  	      		 }
  	      		System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Pay Hospital Fees of $100");
  	      	   }
  	      	   else if(chestcount==6){
  	      		
  	      		
  	      		   chestcount+=1;
  	      		   player2.money+=100;
  	      		   bank-=100;
  	      		 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Life Insurance Matures - collect $100");
  	      	   }
  	      	   else if(chestcount==5){
  	      		 
  	      		   chestcount+=1;
  	      		   player2.money+=20;
  	      		   bank-=20;
  	      		System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Income Tax refund - collect $20");
  	      	   }
  	      	   else if(chestcount==4){
  	      		
  	      		   chestcount+=1;
  	      		 if(player1.money-50<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
  	      		 else{
  	      		   player2.money+=50;
  	      		   player1.money-=50;
  	      		 }
  	      	 System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Grand Opera Night - collect $50 from every player for opening night seats");
  	      	   }
  	      	   else if(chestcount==3){
  	      		 
  	      		   chestcount+=1;
  	      		 if(player1.money-10<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 1 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }
  	      		 else{
  	      		   player2.money+=10;
  	      		   player1.money-=10;
  	      		 }
  	      		System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw It is your birthday Collect $10 from each player");
  	      	   }
  	      	   else if(chestcount==2){
  	      		 
  	      		   chestcount+=1;
  	      		 if(player2.money-50<0){
    					 System.out.println("Player 2"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 goes bankrupt");
    					 System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have+"  "+player1.waittime);
    			    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have+"  "+player2.waittime);
    			    	    System.out.println("Banker	"+bank);
    			    	    if(player1.money>player2.money){
    			    	    	System.out.println("Winner Player 1");
    			    	    }
    			    	    else if(player1.money<player2.money){
    			    	    	System.out.println("Winner Player 2");
    			    	    }
    			    	    else{
    			    	    	System.out.println("scoreless");
    			    	    }
    			    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
    			    	    System.exit(1);
    					 
    				 }else{
  	      		   player2.money-=50;
  	      		   bank+=50;
    				 }
  	      		System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Doctor's fees - Pay $50");
  	      	   }
  	      	   else if(chestcount==1){
  	      		 
  	      		   chestcount+=1;
  	      		   player2.money+=75;
  	      		   bank-=75;
  	      		System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Bank error in your favor - collect $75");
  	      	   }
  	      	   else if(chestcount==0){
  	      		  chestcount+=1;
  	      		   player2.currentlocation=1;
  	      		   player2.money+=200;
  	      		   bank-=200;
  	      		System.out.println("Player 1"+"	"+str.split(";")[1]+"	"+player2.currentlocation+"	"+player1.money+"	"+player2.money+"	"+"Player 2 draw Advance to Go (Collect $200)");
  	      	   }
           }
           	}    	 
           	 
           	 
           	 
           	 
           	 
           	 
           	 
           	 
           	 
           	 
           	 
           	 
         
           	if(str.contains("show")){
           	System.out.println("-----------------------------------------------------------------------------------------------------------");
    	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
    	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
    	    System.out.println("Banker	"+bank);
    	    if(player1.money>player2.money){
    	    	System.out.println("Winner Player 1");
    	    }
    	    else if(player1.money<player2.money){
    	    	System.out.println("Winner Player 2");
    	    }
    	    else{
    	    	System.out.println("scoreless");
    	    }
    	    System.out.println("-----------------------------------------------------------------------------------------------------------");
           	 
           	 
           	}
           	 	}         	 
           	 	if (player1.money>4000&&player2.money>4000){
           	 	System.out.println("-----------------------------------------------------------------------------------------------------------");
        	 	System.out.println("Player 1	"+player1.getMoney()+"	"+player1.have);
        	    System.out.println("Player 2	"+player2.getMoney()+"	"+player2.have);
        	    System.out.println("Banker	"+bank);
        	    if(player1.money>player2.money){
        	    	System.out.println("Winner Player 1");
        	    }
        	    else if(player1.money<player2.money){
        	    	System.out.println("Winner Player 2");
        	    }
        	    else{
        	    	System.out.println("scoreless");
        	    }
        	    System.out.println("-----------------------------------------------------------------------------------------------------------");
               	 
               	 
               	}
           	 	
        
           	 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        
        
	}
	
        public  static int linecounter(){
        	int linecount=0;
        	
            try{
           	 FileInputStream fStream =new FileInputStream("command.txt");
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
        
}
