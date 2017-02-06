package sqlknot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class SQLLab extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_sakila")
	private DataSource sakilaDataSource;
	
	protected void processRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
		paramHttpServletResponse.setContentType("text/html");
		PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();

		String str1 = paramHttpServletRequest.getParameter("cid");
		String str2 = paramHttpServletRequest.getParameter("tid");
		String str3 = paramHttpServletRequest.getParameter("qid");
		String str4;
		if (paramHttpServletRequest.getParameter("cas") != null) {
			str4 = paramHttpServletRequest.getParameter("cas");
		} else {
			str4 = "0";
		}
		String str5 = paramHttpServletRequest.getParameter("usr");
		String str6 = paramHttpServletRequest.getParameter("grp");
		String str7 = paramHttpServletRequest.getParameter("sid");
		String str8 = "";
		if (paramHttpServletRequest.getParameter("svc") != null) {
			str8 = paramHttpServletRequest.getParameter("svc");
		}
		String str9 = paramHttpServletRequest.getParameter("schema");
		String[] arrayOfString = str9.split(" ");

		localPrintWriter
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		localPrintWriter.println("<html>");
		localPrintWriter.println("  <head>");
		localPrintWriter
				.println("    <meta http-equiv=\"Content-Type\" content=\"text/html\">");
		localPrintWriter
				.println("    <meta http-equiv=\"cache-control\" content=\"no-cache\" />");
		localPrintWriter
				.println("    <meta http-equiv=\"pragma\" content=\"no-cache\" />");
		localPrintWriter
				.println("    <title>SQLLab for Single Question</title>");
		localPrintWriter
				.println("    <script type=\"text/javascript\" src=\"common/dojo.js\" />");
		localPrintWriter.println("    <script></script>");
		localPrintWriter
				.println("    <script type=\"text/javascript\" src=\"common/functions.js\" />");
		localPrintWriter.println("    <script></script>");
		localPrintWriter
				.println("    <script type=\"text/javascript\"></script>");
		localPrintWriter.println("    </head>");
		localPrintWriter
				.println("  <body bgcolor=\"white\" onload=\"getQuestion();reportLab('open','1','');\">");

		localPrintWriter.println("  <style>");
		localPrintWriter
				.println("    <!--.drag{position:relative;cursor:hand}-->");
		localPrintWriter.println("  </style>");

		localPrintWriter.println("    <input type=\"hidden\" value=\"" + str3
				+ "\" id=\"question_No\" />");
		localPrintWriter.println("    <input type=\"hidden\" value=\"" + str5
				+ "\" id=\"user_Id\" />");
		localPrintWriter.println("    <input type=\"hidden\" value=\"" + str6
				+ "\" id=\"group_Id\" />");
		localPrintWriter.println("    <input type=\"hidden\" value=\"" + str7
				+ "\" id=\"session_Id\" />");
		localPrintWriter.println("    <input type=\"hidden\" value=\"" + str1
				+ "\" id=\"topic_Id\" />");
		localPrintWriter.println("    <input type=\"hidden\" value=\"" + str2
				+ "\" id=\"template_Id\" />");
		localPrintWriter.println("    <input type=\"hidden\" value=\"Topic"
				+ str1 + "\" id=\"activity_Id\" />");
		localPrintWriter.println("    <input type=\"hidden\" value=\"Template"
				+ str2 + "\" id=\"subactivity_Id\" />");
		localPrintWriter.println("    <input type=\"hidden\" value=\"" + str8
				+ "\" id=\"svc_information\" />");
		localPrintWriter.println("    <font face=\"Times New Roman\">");
		localPrintWriter.println("    <h1>SQLLab for question similar to</h1>");
		localPrintWriter
				.println("    <h3>Task: <div id=\"QuestionBody\"></div></h3>");
		localPrintWriter
				.println("    <h4><font color=\"#C0C0C0\">Here is a field that you can work with a sample DB to find out the solution to the question, you can access to the full tables here and check the result of a query immediately.</font></h4>");
		if (str4.equalsIgnoreCase("1")) {
			localPrintWriter
					.println("    <h5 onclick=\"getAnswer()\">Correct Answer: <div id=\"AnswerBody\"></div><font color=\"red\">");
			localPrintWriter
					.println("      you may click to get correct answer, but this action will count no credit.");
			localPrintWriter.println("    </font><br/></h5>");
		}
		localPrintWriter.println("<table><tbody><tr valign=\"bottom\"><td>");

		localPrintWriter
				.println("    <textarea id=\"QueryString\" onfocus=\"clearTextArea('Enter the test query here.','clear')\" onblur=\"clearTextArea('Enter the test query here.','reset')\" rows=\"6\" cols=\"40\">Enter the test query here.</textarea>");

		localPrintWriter.println("</td><td>");

		localPrintWriter.println("<table><tbody><tr><td>");
		localPrintWriter
				.println("    <input type=\"submit\" onclick=\"makeRequest()\" value=\"Run the Query\" name=\"submit\" />");
		localPrintWriter.println("</td></tr><tr><td>");
		localPrintWriter
				.println("    <input type=\"submit\" onclick=\"reportLab('close','1','');window.close();\" value=\"Close the Lab\" name=\"submit\" />");
		localPrintWriter.println("</td></tr></tbody></table>");

		localPrintWriter.println("</td></tr></tbody></table>");

		localPrintWriter.println("  <table>");
		localPrintWriter.println("  <tbody>");
		localPrintWriter.println("  <tr valign=\"top\">");
		localPrintWriter.println("  <td width=\"50%\">");

		localPrintWriter.println("    <input type=\"hidden\" value=\"" + str3
				+ "\" id=\"question_No\" />");
		localPrintWriter
				.println("    <table cellpadding=\"0\" cellspacing=\"0\" title=\"click +/- next to the table name to show/hide data\" border=\"1\">");
		localPrintWriter.println("      <thead>");
		localPrintWriter.println("        <tr>");
		localPrintWriter
				.println("          <th align=\"right\"><font color=\"olive\">Table Name</font></th>");
		localPrintWriter
				.println("          <th align=\"left\"><font color=\"olive\">Schema & Data  (click +/- to show/hide data)</th></font>");
		localPrintWriter.println("        </tr>");
		localPrintWriter.println("      </thead>");
		localPrintWriter.println("      <tbody>");
		try {
			Connection localConnection = sakilaDataSource.getConnection();
			Statement localStatement1 = localConnection.createStatement();
			for (int i = 0; i < arrayOfString.length; i++) {
				String str13 = arrayOfString[i];

				localPrintWriter.println("        <tr>");
				localPrintWriter
						.println("          <td align=\"right\"><b><font color=\"teal\">"
								+ str13
								+ "<font style=\"cursor:pointer\" color=\"olive\"><span id=\"show"
								+ str13
								+ "\" onclick=\"showData('"
								+ str13
								+ "')\">(+)</span></font></a></font></b></td>");

				localPrintWriter.println("          <td>");

				Statement localStatement2 = localConnection.createStatement();
				localStatement2.executeQuery("select * from " + str13);
				ResultSet localResultSet = localStatement2.getResultSet();
				ResultSetMetaData localResultSetMetaData = localResultSet
						.getMetaData();
				int j = localResultSetMetaData.getColumnCount();
				for (int k = 1; k - 1 < j; k++) {
					if (k % 2 == 0) {
						localPrintWriter.print("<font color=\"teal\">"
								+ localResultSetMetaData.getColumnName(k)
								+ " </font>");
					} else {
						localPrintWriter.print("<font color=\"teal\">"
								+ localResultSetMetaData.getColumnName(k)
								+ " </font>");
					}
					if (k < j) {
						localPrintWriter.print("  ");
					}
				}
				localResultSet.close();

				localPrintWriter.println("          </td>");
				localPrintWriter.println("        </tr>");
				localPrintWriter.println("        <tr>");
				localPrintWriter.println("          <td></td>");
				localPrintWriter.println("          <td><div id=\"" + str13
						+ "\"></div></td>");

				localPrintWriter.println("        </tr>");
			}
			localConnection.close();
		} catch (Exception localException) {
			String str11 = localException.getMessage();
		}
		localPrintWriter.println("      </tbody>");
		localPrintWriter.println("    </table>");
		localPrintWriter.println("    <br/>");
		localPrintWriter.println("  </td>");
		localPrintWriter.println("  <td width=\"50%\">");

		localPrintWriter.println("    <div id=\"QueryResult\"/>");

		localPrintWriter.println("  </td>");
		localPrintWriter.println("  </tr>");
		localPrintWriter.println("  </tbody>");
		localPrintWriter.println("  </table>");

		localPrintWriter.println("  <div id = \"DataShow\"></div>");

		localPrintWriter.println("  </font></body>");
		localPrintWriter.println("</html>");

		localPrintWriter.close();
	}

	protected void doGet(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		processRequest(paramHttpServletRequest, paramHttpServletResponse);
	}

	protected void doPost(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		processRequest(paramHttpServletRequest, paramHttpServletResponse);
	}

	public String getServletInfo() {
		return "Short description";
	}
}
