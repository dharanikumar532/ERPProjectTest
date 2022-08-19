package driverfactory;

import org.testng.annotations.Test;

import commonfunctions.functionlibrary;
import constant.apputil;
import utilities.excelfile;

public class driverscript extends apputil 
{
	public static String inputpath="D:\\hybridframework\\ERP_project\\ERPinputpath\\liveprojectfile.xlsx";
	public static String outputpath="D:\\hybridframework\\ERP_project\\ERPoutputpath\\results.xlsx";
	public static String tcsheet ="testcase";
	public static String tssheet = "teststeps";
	static boolean res=false;
	static String tcres="";
	@Test
	public static void starttest() throws Throwable
	{
		excelfile xl=new excelfile(inputpath);
		int tccount=xl.rowcount(tcsheet);
		int tscount=xl.rowcount(tssheet);
		System.out.println("no of rows in testcase sheet   "+tccount+"    "+"no of rows in teststep sheet  "+tscount);
		
		for(int i=1;i<=tccount;i++)
		{
	String excutionmode=xl.getcelldata(tcsheet, i, 2);
	String modulename=xl.getcelldata(tcsheet, i, 1);
	System.out.println(excutionmode+i+"  "+modulename);
	
	if(excutionmode.equalsIgnoreCase("y"))
	{
		String tcid=xl.getcelldata(tcsheet,i, 0);
		for(int j=1;j<=tscount;j++)
		{
			String tsid=xl.getcelldata(tssheet, j, 0);
			if(tcid.equalsIgnoreCase(tsid))
			{
				String key=xl.getcelldata(tssheet, j, 3);
				if(key.equalsIgnoreCase("login"))
				{
					String para1=xl.getcelldata(tssheet, j, 5);
					String para2=xl.getcelldata(tssheet, j, 6);
					res=functionlibrary.verifylogin(para1, para2);
				}
				else if(key.equalsIgnoreCase("logout"))
				{
					res=functionlibrary.logout();
				}
				String tsres="";
				if(res)
				{
					tsres="pass";
					xl.setcelldata(tssheet, j, 4, tsres, outputpath);
				}
				else
				{
					tsres="fail";
					xl.setcelldata(tssheet, j, 4, tsres, outputpath);
				}
				tcres=tsres;
			}
			
		}
		
		xl.setcelldata(tcsheet, i, 3, tcres, outputpath);
	}
	else if(excutionmode.equalsIgnoreCase("n"))
	{
		xl.setcelldata(tcsheet, i, 3, "Blocked", outputpath);
	}
			
		}
		
	}
	

}
