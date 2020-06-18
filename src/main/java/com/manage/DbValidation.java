package com.manage;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbValidation extends Property
{
	static Connection con = null;
    private static Statement stmt=null;
    public static String DB_URL = "jdbc:mysql://localhost:3306/orangehrm_mysql";   
    public static int colnum;
   
    //	SoftAssert s = new SoftAssert();
       
    public void test(String sheetname,int i,String empId) throws Throwable
    {
    	String dbClass = "com.mysql.cj.jdbc.Driver";

        Class.forName(dbClass).newInstance();

        Connection con = DriverManager.getConnection(DB_URL, super.getProperty("userid"), super.decode(super.getProperty("pass")));

        List<String> data = new ArrayList<String>();
        stmt = con.createStatement();
        String query = "select  employee_id,emp_firstname,emp_middle_name,emp_lastname,emp_birthday,emp_gender,emp_marital_status,nation_code from hs_hr_employee where employee_id="+empId;
        ResultSet rs1 = stmt.executeQuery(query);
       ResultSetMetaData rsmd = rs1.getMetaData();
      colnum= rsmd.getColumnCount();

      while(rs1.next())
      {
    	  
    	  for (int j = 1; j <=colnum; j++) 
    	  {
			data.add(rs1.getString(j));
		}
      }
      		   if(!super.getcelldata(sheetname, "Employee ID", i).isEmpty())
      		   {
    		   assertEquals(super.getcelldata(sheetname, "Employee ID", i), data.get(0));
      		   }
      		   
      		   if(!super.getcelldata(sheetname, "First Name", i).isEmpty())
      		   {
      			   assertEquals(super.getcelldata(sheetname, "First Name", i), data.get(1));
      		   }
      		   
      		   if(!super.getcelldata(sheetname, "Middle Name", i).isEmpty())
      		   {
      			  assertEquals(super.getcelldata(sheetname, "Middle Name", i), data.get(2));
      		   }
      		   
      		   if(!super.getcelldata(sheetname, "Last Name", i).isEmpty())
      		   {
      			   assertEquals(super.getcelldata(sheetname, "Last Name", i), data.get(3));
      		   }
      		   
    		   if(!super.getcelldata(sheetname, "Date of Birth", i).isEmpty())
    		   {
    		   assertEquals(super.getcelldata(sheetname, "Date of Birth", i), data.get(4));
    		   }
    		   
    		   if(!super.getcelldata(sheetname, "Gender code", i).isEmpty())
    		   {
    		   assertEquals(super.getcelldata(sheetname, "Gender code", i), data.get(5));  
    		   }
    		   
    		   if(!super.getcelldata(sheetname, "Marital Status", i).isEmpty())
    		   {
    		   assertEquals(super.getcelldata(sheetname, "Marital Status", i), data.get(6));
    		   }
    		    
    		   if(!super.getcelldata(sheetname, "Nation code", i).isEmpty())
    		   {
    		   assertEquals(super.getcelldata(sheetname, "Nation code", i), data.get(7));
    		   }

    		       
       if (con != null) 
       {
           con.close();

           }

     //  s.assertAll();

    }


}
