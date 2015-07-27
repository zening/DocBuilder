import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DocBuilderText {

	public static String inputPath = "\\thoughtweb\\textDocs\\";
	public static String outputPath = "\\thoughtweb\\textDocs-output\\";
	
	public static List<String> type04records = new ArrayList<String>(); // create type 04 record set for type03 to lookup 
	public static int counter02 = 0;
	public static int counter03 = 0;
	
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		File file = new File( inputPath + "pd72904cmstest5 layout v6.txt");
	
		//get type 4 records for later lookup
		getAllType04(file);
		
		//start processing here
		BufferedReader br = null;
		String line = "";
		try {
			 
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if ( line.indexOf("01") == 0){
					buildType01(line );	
				}
				else if ( line.indexOf("02") == 0){
					counter02++;
					buildType02(line, counter02 );	
					
				}
				else if ( line.indexOf("03") == 0){
					counter03++;
					buildType03(line, counter03);	
				}
				
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("Type02 :"+counter02);
			System.out.println("Type03 :"+counter03);
			
			System.out.println(totalTime + "ms");
	}
	
	private static void getAllType04(File file) {
		BufferedReader br04 = null;
		String line04 = "";
		try {
			 
			br04 = new BufferedReader(new FileReader(file));

			while ((line04 = br04.readLine()) != null) {
				if ( line04.indexOf("04") == 0){
					type04records.add( line04 );
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br04 != null) {
				try {
					br04.close();
					
					System.out.println("Total of Type 04: " + type04records.size());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private static void buildType01(String line) throws IOException{

				String reportType = line.substring(0,2);
				String Originator = line.substring(2, 10).trim();
				String Case_Index_Reference = line.substring(10, 16);
				String Case_Title = line.substring(16, 46).trim();
				String Case_Date = line.substring(46,52);
				String Offence_Location = line.substring(52,82);
				String Offence_Date = line.substring(82,88);
				String Offence_Time = line.substring(88, 92);
				String Crime_Report = line.substring(92,101);
				String Narrative = line.substring(101, line.length());
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath +Originator+Case_Index_Reference + " - " + reportType +" - "+ Case_Title +".txt"));
				
				bw.write(":Record Type: "+ reportType + " - Case File Record" ); bw.newLine();
				bw.write(":Title: "+ Originator + Case_Index_Reference + " - " + Case_Title); bw.newLine();
				bw.write(":Originator: " + Originator); bw.newLine();
				bw.write(":CIR: " + Case_Index_Reference); bw.newLine();
				bw.write(":Case Title: " + Case_Title); bw.newLine();
				bw.write(":Case Date: " + Case_Date); bw.newLine();
				bw.write(":Offence Date: " + Offence_Date);		bw.newLine();
				bw.write(":Offence Time: " + Offence_Time);		bw.newLine();
				bw.write(":Offence Location: " + Offence_Location);	bw.newLine();	
				bw.write(":Crime Report: " + Crime_Report);		bw.newLine();
				bw.write(":Narrative: " + Narrative);		bw.newLine();

				bw.close();
				
			}
		
	private static void buildType02(String line, int counter) throws IOException{

				String reportType = line.substring(0,2);
				String Date_Created = line.substring(2,8);
				String Link_Type = line.substring(8, 17).trim();
				String Current_Doc_Ref = line.substring(17, 27).trim(); 
				
				String Originator = line.substring(976, 984).trim();
				String Case_Index_Reference = line.substring(984,990);
				
				String Given_Name1 = (line.substring(59,79).trim().length() > 0 ) ? line.substring(59,79).trim() + " " : "" ;
				String Given_Name2 = (line.substring(79,94).trim().length() > 0 ) ? line.substring(79,94).trim() + " " : "" ;
				String Given_Name3 = (line.substring(94,109).trim().length() > 0) ? line.substring(94,109).trim() + " " : "";
				String Family_Name = line.substring(34,59).trim();
				
				String Person_ISN = line.substring(27,34).trim();
				String Gender = line.substring(965,966);
				
				String Address1 = line.substring(859,869).trim() + " " + line.substring(869,889).trim() + " " + line.substring(889,893).trim();
			    String Address2 = line.substring(893,923).trim() + " " + line.substring(923,926).trim() + " " + line.substring(926,930).trim();
			    
			    String Home_Number = line.substring(930,933) + " " +line.substring(933,941);
			    String Bus_Number = line.substring(941,944) + " "+ line.substring(944,952) + " " + line.substring(952,957);
			    String Mobile_Number = line.substring(966,976);
			    
				String Birth_Date = line.substring(957,965);
				String Notes_Text = line.substring(109,859).trim();
				
				BufferedWriter bw = new BufferedWriter(new FileWriter( outputPath + Originator+Case_Index_Reference + " - " + reportType +" - "+ Given_Name1 +  Given_Name2 + Given_Name3 + Family_Name +"."+counter+".txt"));
				
				bw.write(":Record Type: "+ reportType + " - Case Person Record" ); bw.newLine();
				bw.write(":Title: "+ Originator + Case_Index_Reference + " - " + Given_Name1 + Given_Name2 + Given_Name3 + Family_Name); bw.newLine();
				bw.write(":Originator: " + Originator); bw.newLine();
				bw.write(":Date Created: " + Date_Created); bw.newLine();
				bw.write(":CIR: " + Case_Index_Reference); bw.newLine();
				bw.write(":Name: " + Given_Name1 + Given_Name2 + Given_Name3 + Family_Name); bw.newLine();
				bw.write(":Link Type: " + Link_Type );	bw.newLine();
				bw.write(":Current Document Reference: " + Current_Doc_Ref );	bw.newLine();
				bw.write(":ISN: " + Person_ISN ); bw.newLine();
				bw.write(":D.O.B: " + Birth_Date ); bw.newLine();
				bw.write(":Gender: " + Gender ); bw.newLine();
				bw.write(":Street Address: " + Address1 + ", " + Address2 ); bw.newLine();
				bw.write(":Home Contact Number: " + Home_Number ); bw.newLine();
				bw.write(":Business Phone Number: " + Bus_Number ); bw.newLine();
				bw.write(":Mobile Contact Number: " + Mobile_Number ); bw.newLine();
				bw.write(":Text: " + Notes_Text ); bw.newLine();

				bw.close();
				
				}
	
	private static void buildType03(String line, int counter) throws IOException{

		String reportType = line.substring(0,2);
		String Document_Type = line.substring(2, 4);
		String Document_Number = line.substring(4, 12);
		
		String Document_Date = line.substring(12,18);
	    
		String Case_Index_Reference = line.substring(108, 114);

		String DocTypeMapping = "";
		switch (Document_Type){
		case "RU":
			DocTypeMapping = "Running Sheet";
			break;
		case "AC":
			DocTypeMapping = "Actions";
			break;
		case "RE":
			DocTypeMapping = "Result Sheet";
			break;
		case "MD":
			DocTypeMapping = "Miscellaneous Documents";
			break;
		case "SR":
			DocTypeMapping = "Statement Resumes";
			break;
		case "CP":
			DocTypeMapping = "Crime Progress Report";
			break;
		}
		
		String Originator = line.substring(114, 122).trim();

		BufferedWriter bw = new BufferedWriter(new FileWriter( outputPath + Originator+Case_Index_Reference + " - " + reportType +" - "+ Document_Type + Document_Number +"."+counter+".txt"));
		
		bw.write(":Record Type: "+ reportType + " - Case Document Record" ); bw.newLine();
		bw.write(":Title: "+ Originator + Case_Index_Reference + " - " + Document_Type + Document_Number); bw.newLine();
		bw.write(":Originator: " + Originator); bw.newLine();
		bw.write(":CIR: " + Case_Index_Reference); bw.newLine();
		bw.write(":Type of Document: " +  DocTypeMapping); bw.newLine();
		bw.write(":Document Reference: " + Document_Type + Document_Number ); bw.newLine();

		bw.write(":Document Date: " + Document_Date ); bw.newLine();

		//get text from type04 here ***************************
		String type04Text = getType04Text(Document_Type + Document_Number);
		//if (type04Text.length() > 0 ){
			bw.write( type04Text ); bw.newLine();
		//}
		bw.close();
		}

	private static String getType04Text(String docKey) {
		String result  = "";
		String currentKey = "";
		String line = "";	
		
		for(int i=0;i<type04records.size();i++){
			line = type04records.get(i);

			currentKey = (line.substring(769,771)+line.substring(771,779));

			if ( currentKey.equalsIgnoreCase(docKey)){
				String PNUM = line.substring(2,5);
				String Text = line.substring(5,755);
				result += ":Text " + PNUM +": \r\n" + Text ;		
			}

		}
		//System.out.println("type04: "+result);
		return result;
	}
	

	
}

	

