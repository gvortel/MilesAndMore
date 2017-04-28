package milesandmore;


import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * @author GVortelinos
 */
public class Passengers {
    // Map with id,miles
    private static Map<Long,Integer> MembersMap;
    //Sorted Map with miles,number of times where mile is in csv
    private static NavigableMap<Integer,Integer> Values;
    


    /**
     * Constructor
     */
    public Passengers() {
        this.MembersMap = new ConcurrentHashMap<Long,Integer>();
        this.Values = new TreeMap<Integer,Integer>();
    }
    
    
    /**
     * Return members map
     * @return members map
     */
    public Map<Long,Integer> getMembers(){
        if(this.MembersMap==null || this.Values==null) 
            throw new NullPointerException("Data is empty");
        return this.MembersMap;
    }
    
    
    /**
     * Add new member
     * Default miles = 1000
     * @param MemberID 
     */
    public void addMember(long MemberID) {
        if(this.MembersMap==null || this.Values==null) 
            throw new NullPointerException("Data is empty");
        //miles init number = 1000
        addMember(MemberID,1000);
    }
    
    
    
    public boolean containsMember(long MemberID) {
        if(this.MembersMap==null || this.Values==null) 
            throw new NullPointerException("Data is empty");
        return this.MembersMap.containsKey(MemberID);
    }
    
    /**
     * Add new member
     * @param memberID
     * @param miles 
     */
    public void addMember(long memberID,int miles) {
        if(this.MembersMap==null || this.Values==null) 
            throw new NullPointerException("Data is empty");
        //add memberID,miles
        this.MembersMap.put(memberID, miles);
       
        //add miles in sorted Value map
        if(this.Values.containsKey(miles))
        {
            //if miles exist already 
            int milesCounter = this.Values.get(miles);
            milesCounter ++;
            this.Values.replace(miles, milesCounter);
        }else{
            //if miles dont exist
            this.Values.put(miles, 1);
        }
    }
    
    
     
    
    /**
     * Delete existing member
     * @param memberID 
     */
    public void deleteMember(long memberID) {
        
        if(this.MembersMap==null || this.Values==null) 
            throw new NullPointerException("Data is empty");
        
        if( !this.MembersMap.containsKey(memberID) )
            throw new IllegalArgumentException("Provided member do not exists: memberID " + memberID);
            
            
        int miles = this.MembersMap.get(memberID);
        int milesCounter = this.Values.get(miles);
        
        if( milesCounter == 1 ){
            //if miles exist only one time
            this.Values.remove(miles);
        }else{
            //if miles exist more than one times
            this.Values.put(miles, milesCounter - 1);
        }
        
        this.MembersMap.remove(memberID);
    }
    
    
    
    
    /**
     * Update miles for memberID
     * @param memberID
     * @param miles 
     */
    public int updateMiles(long memberID,int miles) {
        if(this.MembersMap==null || this.Values==null) 
            throw new NullPointerException("Data is empty");
        if( !this.MembersMap.containsKey(memberID) )
            throw new IllegalArgumentException("Provided member do not exists: memberID " + memberID);
        
        //first delete old miles in Values Map
        int oldmiles = this.MembersMap.get(memberID);
        int milesCounter = this.Values.get(oldmiles);
        if( milesCounter == 1 ){
            //if miles exist only one time
            this.Values.remove(miles);
        }else{
            //if miles exist more than one times
            this.Values.put(miles, milesCounter - 1);
        }
        
        //new miles = old miles + new miles
        miles = miles + oldmiles;
        //then insert new miles
        //add miles in sorted map
        if(this.Values.containsKey(miles))
        {
            //if miles exist already 
            milesCounter = this.Values.get(miles);
            milesCounter ++;
            this.Values.replace(miles, milesCounter);
        }else{
            //if miles dont exist
            this.Values.put(miles, 1);
        }
        
        //finally insert in Members Map
        this.MembersMap.put(memberID, miles);
        
        return miles;
    }
    
    
    
    
    /**
     * Get member's miles
     * @param MemberID
     * @return miles
     */
    public int getMiles(long MemberID) {
        if(this.MembersMap==null || this.Values==null) 
            throw new NullPointerException("Data is empty");
        return this.MembersMap.get(MemberID);
    }
    
    
    
    /**
     * Passenger count
     * @return Passenger count
     */
    public int getSize() {
        if(this.MembersMap==null || this.Values==null) 
            throw new NullPointerException("Data is empty");
        return this.MembersMap.size();
    }
    
    
 
     /**
     * Calculate percentile rank
     * @param id
     * @return rank percentage
     */
     public int rank(long memberID) {
         if(this.MembersMap==null || this.Values==null) 
            throw new NullPointerException("Data is empty");
         
        int greaterCount = 0;
        int sameCount = 0;
        int n = getSize();
        int value =  this.MembersMap.get(memberID);
        
        if (n == 0) 
            throw new IllegalArgumentException("Data array is empty");    
        
        
        if( ! this.MembersMap.containsKey(memberID))
            throw new IllegalArgumentException("Provided value do not exists in dataset: " + value);
        
        SortedMap<Integer, Integer> treemapincl = new TreeMap<Integer, Integer>();
        treemapincl=this.Values.tailMap(value,false);
        
        for(Map.Entry<Integer, Integer> entry : treemapincl.entrySet()) {
            int val = entry.getValue();
            greaterCount = greaterCount + val;
        }
        
        sameCount =  this.Values.get(value);
        // 95 will be rank's max value
        double rank = (greaterCount + 0.5 * sameCount) / n * 95;
        //round up with ceil so max miles have rank 1
        //now rank is between [1,95]
        rank = Math.ceil(rank);
        
      
        return (int) rank;
    }
     
     //just for testing
     public long[] get150IDs(){
         long[] ids=new long[150];
         
         int i=0;
         for(Map.Entry<Long, Integer> entry : this.MembersMap.entrySet()) {
            ids[i] = entry.getKey();
            i++;
            if(i==150) break;
        }
         return ids;
     }
    
        
}
