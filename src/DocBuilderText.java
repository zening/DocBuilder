import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DocBuilderText {

	
	public static void main(String[] args) {

		
		File file = new File("C:\\textDocs\\AD910024.txt");

			buildType01(file );
			buildType02(file );
			
	}
	
	private static void buildType01(File file){

		BufferedReader br = null;
		String line = "";
	 
		try {
	 
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if ( line.indexOf("01") == 0){
			        // use comma as separator
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
				
				BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\textDocs-output\\"+Originator+Case_Index_Reference + " - " + reportType +" - "+ Case_Title +".txt"));
				
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
	 
		System.out.println("Report Type 01 Done");
	  }
		
		
	private static void buildType02(File file){

		BufferedReader br = null;
		String line = "";
	 
		try {
	 
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if ( line.indexOf("02") == 0){
			        // use comma as separator
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
				
				BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\textDocs-output\\"+Originator+Case_Index_Reference + " - " + reportType +" - "+ Given_Name1 +  Given_Name2 + Given_Name3 + Family_Name +".txt"));
				
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
	 
		System.out.println("Report Type 02 Done");
	  }
	

	

}
