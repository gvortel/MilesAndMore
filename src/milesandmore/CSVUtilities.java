package milesandmore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
 

/**
 *
 * @author GVortelinos
 */
public class CSVUtilities {
    
    private static final char DEFAULT_SEPARATOR = ',';
    private static Passengers passengers;
    private static FileWriter  writer = null;
    /**
     * 
     * @return 
     */
    public static Passengers ReadCSV( ) {
                //init
                passengers = new Passengers();
                String fileName = "miles_per_existing_member.csv";
                
                BufferedReader Buffer = null;
		try {
                        
			String Line;
			Buffer = new BufferedReader(new FileReader(fileName));
			
                        
                        int min=0;
                        int max=0;
                        
                        //read first dumb line
                        Buffer.readLine();
			//read file line by line
                        System.out.println("Loading CSV file ...");
			while ((Line = Buffer.readLine()) != null) 
                        {
                                ArrayList<String>  Result = CSVtoArrayList(Line);
                                
                                long MemberID = Long.parseLong( Result.get(0) );
                                int miles = Integer.parseInt(Result.get(1));
                                
                                passengers.addMember(MemberID,miles);
                                
                                if(min==0 && max==0) {min=miles; max=miles;}
                                if(miles<min) min=miles;
                                if(miles>max) max=miles;
			}
                        System.out.println("Done!\t" + passengers.getSize()+ " Members loaded successfully." );
			System.out.println("min: "+min +" max: "+max );
                        
		} catch (IOException e) {
			e.printStackTrace();
                        System.out.println("File not Found"+ fileName);
		} finally {
			try 
                        {
				if (Buffer != null) Buffer.close();
			} catch (IOException crunchifyException) {
				crunchifyException.printStackTrace();
			}
		}
                 
                return passengers;
                
	}

    
	
	/**
         * Converts CSV to ArrayList using Split Operation
         * @param Line
         * @return 
         */
	public static ArrayList<String> CSVtoArrayList(String Line) {
		ArrayList<String>  Result = new ArrayList<String>();
		
		if (Line != null) 
                {
			String[] splitData = Line.split("\\s*,\\s*");
			for (int i = 0; i < splitData.length; i++) 
                        {
				if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
					Result.add(splitData[i].trim());
				}
			}
		}
		
		return Result;
	}
        
        
        /**
         * Export all passengers to CSV File
         * @param passengers 
         */
        public static void createCSV(Passengers passengers)  {
            String csvFile = "miles_per_existing_member_NEW.csv";
        try {
            writer = new FileWriter(csvFile);
        
            
            
            Map<Long,Integer> Members = passengers.getMembers();
            
            writeLine(Arrays.asList("memberID","miles"));
            for(Map.Entry<Long, Integer> entry : Members.entrySet()) {
                writeLine(Arrays.asList( entry.getKey().toString() , entry.getValue().toString() ));
            }

            writer.flush();
            writer.close();
        } 
            catch (IOException ex) {
                Logger.getLogger(CSVUtilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        public static void writeLine(List<String> values) throws IOException {

            boolean first = true;
            char separators = DEFAULT_SEPARATOR;
            
            StringBuilder sb = new StringBuilder();
            for (String value : values) {
                if (!first) {
                    sb.append(separators);
                }
                sb.append(followCVSformat(value));
                first = false;
            }
            sb.append("\n");
            writer.append(sb.toString());

    }
            
        private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }
	
}