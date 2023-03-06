import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Hash_Table {
	
		private double loadfactor;
		private static int tablesize;
		static Employee[] hashtablelineer=new Employee[getTablesize()];
		
		
		
		public Hash_Table(double d,Employee[] hashtablelineer,int tablesize){
			this.loadfactor=d;
			Hash_Table.hashtablelineer=hashtablelineer;
			Hash_Table.setTablesize(tablesize);
			
		}

		public static int hash(int key){
			return key%getTablesize();
		}
		public static void Put(int key,Employee person){
			if(hashtablelineer[key]==null){
			hashtablelineer[key]=person;
			}
			else{
				int a=key;
				while (hashtablelineer[a]!=null) {
					a+=1;
					if(a==getTablesize()){
						int b=0;
						while(hashtablelineer[b]!=null){
							b+=1;
						     if(b==key){
						    	 //yer yok
						    	 return;
						     }
						}
						hashtablelineer[b]=person;
					}
				}
				
				hashtablelineer[a]=person;
			}
		}
		
		public static Employee Get(int key){
			int i=1;
			int a=0;
			if(hashtablelineer[hash(key)]!=null){
			if(hashtablelineer[hash(key)].getPhone()==key){
				System.out.printf("Key found with %d comparisons",i);
				return hashtablelineer[hash(key)];
			}
			
			else{
				while(hashtablelineer[hash(key)+a]!=null){
					while(hashtablelineer[hash(key)+a].getPhone()!=key){
					a+=1;
						if(hash(key)+a==tablesize){
							int b=0;
							while(hashtablelineer[b]!=null){
							while(hashtablelineer[b].getPhone()!=key){
								b+=1;
								if(b==hash(key)){
									System.out.printf("Key cannot found");
									return null;
								}
							}
							System.out.printf("Key found with %d comparisons",b+a+1);
							return hashtablelineer[b];
							}
							System.out.printf("Key found with %d comparisons",b+a+1);
							return hashtablelineer[b];
						}
				}
					System.out.printf("Key found with %d comparisons",a+1);
					return hashtablelineer[hash(key)+a];
				}
				System.out.printf("Key found with %d comparisons",a+1);
				return hashtablelineer[hash(key)+a];
			}
			}
			else{
				System.out.printf("Key cannot found");
				return null;
			}
		}
		
		public Employee[] getHashtablelineer() {
			return hashtablelineer;
		}

		public void setHashtablelineer(Employee[] hashtablelineer) {
			Hash_Table.hashtablelineer = hashtablelineer;
		}

		public double getLoadfactor() {
			return loadfactor;
		}

		public void setLoadfactor(double loadfactor) {
			this.loadfactor = loadfactor;
		}
		
		public int gettablesize() {
			return getTablesize();
		}

		public void settablesize(int tablesize) {
			Hash_Table.setTablesize(tablesize);
		}

		public static int getTablesize() {
			return tablesize;
		}

		public static void setTablesize(int tablesize) {
			Hash_Table.tablesize = tablesize;
		}

		
	
		
}
