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
	
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		File file = new File( inputPath + "AD910024.txt");
	
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
					buildType02(line );	
				}
				else if ( line.indexOf("03") == 0){
					buildType03(line);	
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
				bw.write(":Case_Date: " + Case_Date); bw.newLine();
				bw.write(":Offence Date: " + Offence_Date);		bw.newLine();
				bw.write(":Offence Time: " + Offence_Time);		bw.newLine();
				bw.write(":Offence Location: " + Offence_Location);	bw.newLine();	
				bw.write(":Crime Report: " + Crime_Report);		bw.newLine();
				bw.write(":Narrative: " + Narrative);		bw.newLine();

				bw.close();
				
			}
		
	private static void buildType02(String line) throws IOException{

				String reportType = line.substring(0,2);
				String Originator = line.substring(970, 978).trim();
				String Case_Index_Reference = line.substring(978, 984);
				String Given_Name1 = (line.substring(53,73).trim().length() > 0 ) ? line.substring(53,73).trim() + " " : "" ;
				String Given_Name2 = (line.substring(73,88).trim().length() > 0 ) ? line.substring(73,88).trim() + " " : "" ;
				String Given_Name3 = (line.substring(88,103).trim().length() > 0) ? line.substring(88,103).trim() + " " : "";
				String Family_Name = line.substring(28,53).trim();
				
				String Person_ISN = line.substring(21,28).trim();
				String Gender = line.substring(959,960);
				
				String Address1 = line.substring(853,863).trim() + " " + line.substring(863,883).trim() + " " + line.substring(883,887).trim();
			    String Address2 = line.substring(887,917).trim() + " " + line.substring(917,920).trim() + " " + line.substring(920,924).trim();
			    
			    String Home_Number = line.substring(924,927) + " " +line.substring(927,935);
			    String Bus_Number = line.substring(935,938) + " "+ line.substring(938,946) + " " + line.substring(946,951);
			    String Mobile_Number = line.substring(960,970);
			    
				String Birth_Date = line.substring(951,959);
				String Notes_Text = line.substring(103,853).trim();
				
				BufferedWriter bw = new BufferedWriter(new FileWriter( outputPath + Originator+Case_Index_Reference + " - " + reportType +" - "+ Given_Name1 +  Given_Name2 + Given_Name3 + Family_Name +".txt"));
				
				bw.write(":Record Type: "+ reportType + " - Case Person Record" ); bw.newLine();
				bw.write(":Title: "+ Originator + Case_Index_Reference + " - " + Given_Name1 + Given_Name2 + Given_Name3 + Family_Name); bw.newLine();
				bw.write(":Originator: " + Originator); bw.newLine();
				bw.write(":CIR: " + Case_Index_Reference); bw.newLine();
				bw.write(":Name: " + Given_Name1 + Given_Name2 + Given_Name3 + Family_Name); bw.newLine();
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
	
	private static void buildType03(String line) throws IOException{

		String reportType = line.substring(0,2);
		String Document_Type = line.substring(2, 4);
		String Document_Date = line.substring(12,18);
		String Source_Name = line.substring(18,48);
		
		String Address1 = line.substring(48,68).trim() + " " + line.substring(68,88).trim();

	    String Home_Number = line.substring(88,98);
	    String Bus_Number = line.substring(98,108);
	    String Mobile_Number = "";
	    
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
		String Parent_Doc = line.substring(122,124);
		String Parent_Doc_Number = line.substring(124,132);

		BufferedWriter bw = new BufferedWriter(new FileWriter( outputPath + Originator+Case_Index_Reference + " - " + reportType +" - "+ Parent_Doc + Parent_Doc_Number+".txt"));
		
		bw.write(":Record Type: "+ reportType + " - Case Document Record" ); bw.newLine();
		bw.write(":Title: "+ Originator + Case_Index_Reference + " - " + Parent_Doc + Parent_Doc_Number); bw.newLine();
		bw.write(":Originator: " + Originator); bw.newLine();
		bw.write(":CIR: " + Case_Index_Reference); bw.newLine();
		bw.write(":Type of Document: " + DocTypeMapping ); bw.newLine();
		bw.write(":Document Reference: " + Parent_Doc + Parent_Doc_Number ); bw.newLine();
		bw.write(":Document Date: " + Document_Date ); bw.newLine();
		bw.write(":Source Name: " + Source_Name ); bw.newLine();

		bw.write(":Street Address: " + Address1 ); bw.newLine();
		
		bw.write(":Home Contact Number: " + Home_Number ); bw.newLine();
		bw.write(":Business Phone Number: " + Bus_Number ); bw.newLine();
		bw.write(":Mobile Contact Number: " + Mobile_Number ); bw.newLine();
		
		//get text from type04 here ***************************
		String type04Text = getType04Text(Parent_Doc + Parent_Doc_Number);
		bw.write( type04Text ); bw.newLine();
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
		return result;
	}
	

	
}

	

