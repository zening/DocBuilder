import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class DocBuilder {
	public static String[][] schemas = new String[][]{
		
		{"Record_Type",
		"Created_By",
		"Created_Date",
		"Created_Time",
		"Updated_By",
		"Updated_Date",
		"Updated_Time",
		"Originator",
		"Case_Index_Reference",
		"Case_Title",
		"Case_Date",
		"Offence_Category",
		"Offence_Location",
		"Offence_Date",
		"Offence_Time",
		"Crime_Report_Number",
		"Case_Status",
		"Status_date",
		"Narrative",
		"Security_Status",
		"Last_RS_Number",
		"Last_AC_Number",
		"Last_SR_Number",
		"Last_MD_Number",
		"Last_DS_Page_Number",
		"Evaluation_Code",
		"Last_ADDB_Page_Number",
		"Creator_Agency",
		"Last_CP_Number",
		"Transfer_Case"},
		
		{	"Record_Type",
			"Link_Created_By",
			"Link_Created_Date",
			"Link_Created_Time",
			"Link_from_RIN",
			"Link_Type",
			"Link_to_RIN",
			"Current_Doc_Ref",
			"Created_By",
			"Created_Date",
			"Created_Time",
			"Updated_By",
			"Updated_Date",
			"Updated_Time",
			"Person-ISN",
			"Family_Name",
			"Given_Name_1",
			"Given_Name_2",
			"Given_Name_3",
			"ID_Number",
			"POI",
			"Notes_Text",
			"Soundex",
			"Street_Number",
			"Street_Name",
			"Street_Code",
			"Suburb_A",
			"State_A",
			"Post_Code",
			"Home_Phone_STD",
			"Home_Phone_Number",
			"Bus_Phone_STD",
			"Bus_Phone_Number",
			"Bus_phone_Extension",
			"Birth_date",
			"Gender",
			"Mobile_Phone_Number"
		},{
			"Record_Type",
			"Created_By",
			"Created_Date",
			"Created_Time",
			"Updated_By",
			"Updated_Date",
			"Updated_Time",
			"Parent_Document_Type",
			"Parent_Doc_ISN",
			"Document_Type",
			"Document_Number",
			"Document_Date",
			"Document_Time",
			"Source_Name",
			"Source_Addr_St",
			"Source_Addr_Suburb",
			"Source_Home_Phone",
			"Source_Bus_Phone",
			"Keyword_Complete",
			"Action_Issued_Date",
			"Running_Sheet_Filed_date",
			"Action_Last_Results_No",
			"Doc_Case_Index_Ref",
			"Inv_Doc_No",
			"Case_Orig",
			"Sec_Agency",
			"Creator_Case_Agency",
			"Doc_Last_Page_No",
			"Action_Relevant_To",
			"Doc_Current_Location",
			"CrimeStopper_Flag",
			"Call_ID",
			"Target_Crime_Flag",
			"Reward_Flag",
			"Reward_Amnt",
			"IR_Type",
			"Caller_Detail_Flag",
			"Parent_Doc_Type_2",
			"Parent_Doc_Number"
		},{
			"Record_Type",
			"Created_By",
			"Created_Date",
			"Created_Time",
			"Updated_By",
			"Updated_Date",
			"Updated_Time",
			"DRIN_File",
			"DRIN_ISN",
			"PNUM",
			"No_of_Text",
			"Text",
			"Text_Keywording_Complete",
			"Text_ISN",
			"Security_Agency",
			"Keys",
			"Keys",
			"Keys",
			"Security_Status",
			"Creator_Security_Agency",
			"Text_Case_Index_Reference",
			"Text_Case_Agency",
			"IR_Type",
			"Crimestoppers_Flag",
			"Current_Doc_Type",
			"Current_Doc_No"
		}
		};
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File file = new File("C:\\textDocs\\pd72904cmstestc.txt");

		for (int i=0;i<schemas.length;i++){
			buildXML(file, schemas[i], "0"+ (i + 1) );
		}
			
	}
	
	private static void buildXML(File file, String[] headers, String reportType) {
		// TODO Auto-generated method stub

		BufferedReader reader = null;
		
		try {

		    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder domBuilder = domFactory.newDocumentBuilder();

		    Document newDoc = domBuilder.newDocument();
		    // Root element
		    Element rootElement = newDoc.createElement("CMSrecords");
		    newDoc.appendChild(rootElement);

		    reader = new BufferedReader(new FileReader(file));
		    //int line = 0;

		    String text = null;

		    while ((text = reader.readLine()) != null) {
		    	//System.out.println( (text.substring(0, 2) ));
		    	if ( text.indexOf(reportType) == 0){
			        StringTokenizer st = new StringTokenizer(text, "~", false);    
			        String[] rowValues = new String[st.countTokens()];
			        int index = 0;
			        while (st.hasMoreTokens()) {
	
			            String next = st.nextToken();
			            rowValues[index++] = next;
	
			        }

			        //String[] rowValues = text.split(",");
	
			            Element rowElement = newDoc.createElement("records");
			            rootElement.appendChild(rowElement);
			            for (int col = 0; col < headers.length; col++) {
			                String header = headers[col];
			                String value = null;
	
			                if (col < rowValues.length) {
			                    value = rowValues[col];
			                } else {
			                    // ?? Default value
			                    value = "";
			                }
			                
			                //System.out.println(header);
			                
			                Element curElement = newDoc.createElement(header);
			                curElement.appendChild(newDoc.createTextNode(value));
			                rowElement.appendChild(curElement);
			            }
		            
		            
		    		}
		        }


		    ByteArrayOutputStream baos = null;
		    OutputStreamWriter osw = null;

		    try {
		        baos = new ByteArrayOutputStream();
		        osw = new OutputStreamWriter(baos);

		        TransformerFactory tranFactory = TransformerFactory.newInstance();
		        Transformer aTransformer = tranFactory.newTransformer();
		        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
		        aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		        Source src = new DOMSource(newDoc);
		        Result result = new StreamResult(osw);
		        StreamResult outputFile = new StreamResult(new File("C:\\textDocs\\type"+reportType+".txt.xml"));
		        
		        aTransformer.transform(src, result);
		        aTransformer.transform(src, outputFile);

		        osw.flush();
		        System.out.println(new String(baos.toByteArray()));

		        
		    } catch (Exception exp) {
		        exp.printStackTrace();
		    } finally {
		        try {
		            osw.close();
		        } catch (Exception e) {
		        }
		        try {
		            baos.close();
		        } catch (Exception e) {
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
