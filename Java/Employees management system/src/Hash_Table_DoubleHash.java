
public class Hash_Table_DoubleHash {
	
	private double loadfactor;
	private static int tablesize;
	static Employee[] hashtabledoublehash=new Employee[tablesize];
	
	
	
	public Hash_Table_DoubleHash(double d,Employee[] hashtabledoublehash,int tablesize){
		this.loadfactor=d;
		Hash_Table_DoubleHash.hashtabledoublehash=hashtabledoublehash;
		Hash_Table_DoubleHash.tablesize=tablesize;
		
	}

	public static int hash(int key){
		return key%tablesize;
	}
	public static int hash2(int key){
		return 1+(key%(tablesize-1));
	}
	public static void Put(int phone,Employee person){
		if(hashtabledoublehash[hash(phone)]==null){
			hashtabledoublehash[hash(phone)]=person;
		}
		else{
			int b = 1;
			int a=1;
			while(hashtabledoublehash[(hash(phone)+a*hash2(phone))%tablesize]!=null){
				a+=1;
				if(((hash(phone)+a*hash2(phone))%tablesize)>tablesize){
					while(hashtabledoublehash[(b*hash2(phone))%tablesize]!=null){
						b+=1;
						if(((b*hash2(phone))%tablesize)>=(hash(phone)%tablesize)){
							return;
						}
					}
					hashtabledoublehash[b*hash2(phone)%tablesize]=person;
				}
				
			}
			hashtabledoublehash[(hash(phone)+a*hash2(phone))%tablesize]=person;
			}
			
	  }
	
	public static Employee Get(int key){
		int i=1;
		if(hashtabledoublehash[hash(key)]!=null){
		if(hashtabledoublehash[hash(key)].getPhone()==key){
			System.out.printf("Key found with %d comparisons",i);
			return hashtabledoublehash[hash(key)];
		}
		
		else{
			int b = 1;
			int a=1;
			while(hashtabledoublehash[(hash(key)+a*hash2(key))%tablesize]!=null){
				while(hashtabledoublehash[(hash(key)+a*hash2(key))%tablesize].getPhone()!=key){
					a+=1;
				if(((hash(key)+a*hash2(key))%tablesize)>tablesize){
						while(hashtabledoublehash[(b*hash2(key))%tablesize]!=null){
							while(hashtabledoublehash[(b*hash2(key))%tablesize].getPhone()!=key){
								b+=1;
								if(((b*hash2(key))%tablesize)>=(hash(key)%tablesize)){
							
										System.out.printf("Key cannot found");
										return null;
						}
					}
					System.out.printf("Key found with %d comparisons",b+a+1);
					return hashtabledoublehash[b*hash2(key)%tablesize];
				}
					System.out.printf("Key found with %d comparisons",b+a+1);
					return hashtabledoublehash[b*hash2(key)%tablesize];
				
			}
			}
			System.out.printf("Key found with %d comparisons",a+1);
			return hashtabledoublehash[(hash(key)+a*hash2(key))%tablesize];
			
			}
			System.out.printf("Key found with %d comparisons",a+1);
			return hashtabledoublehash[(hash(key)+a*hash2(key))%tablesize];
		}
		}
		else{
			System.out.printf("Key cannot found");
			return null;
		}
	}
	
	public Employee[] getHashtabledoublehash() {
		return hashtabledoublehash;
	}

	public void setHashtabledoublehash(Employee[] hashtabledoublehash) {
		Hash_Table_DoubleHash.hashtabledoublehash = hashtabledoublehash;
	}

	public double getLoadfactor() {
		return loadfactor;
	}

	public void setLoadfactor(double loadfactor) {
		this.loadfactor = loadfactor;
	}
	
	public int gettablesize() {
		return tablesize;
	}

	public void settablesize(int tablesize) {
		Hash_Table_DoubleHash.tablesize=tablesize;
	}

	

	
}
