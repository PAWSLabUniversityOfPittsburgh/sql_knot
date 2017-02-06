package sqlknot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class QuestionGenerator extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_question")
	private DataSource questionDataSource;
	
	@Resource(name = "jdbc/sqlknot_sakila")
	private DataSource sakilaDataSource;
	
	protected void processRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
		paramHttpServletResponse.setContentType("text/html");
		PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();

		String str1 = paramHttpServletRequest.getRequestURL().toString() 
				+ paramHttpServletRequest.getQueryString().toString();

		String str2 = paramHttpServletRequest.getParameter("cid");
		String str3 = paramHttpServletRequest.getParameter("tid");
		String str4 = paramHttpServletRequest.getParameter("usr");
		String str5 = paramHttpServletRequest.getParameter("grp");
		String str6 = paramHttpServletRequest.getParameter("sid");
		String str7 = "";
		String str8 = "";
		String str9 = "";
		String str10 = "";
		String str11 = "";
		String str12 = "";
		String str13 = "";
		
		if (paramHttpServletRequest.getParameter("nav") != null) {
			str8 = paramHttpServletRequest.getParameter("nav");
		}
		if (paramHttpServletRequest.getParameter("srt") != null) {
			str9 = paramHttpServletRequest.getParameter("srt");
		}
		if (paramHttpServletRequest.getParameter("sar") != null) {
			str10 = paramHttpServletRequest.getParameter("sar");
		}
		if (paramHttpServletRequest.getParameter("svc") != null) {
			str11 = paramHttpServletRequest.getParameter("svc");
		}
		if (paramHttpServletRequest.getParameter("exm") != null) {
			str12 = paramHttpServletRequest.getParameter("exm");
		}
		String str14 = "";
		String str15 = "";
		int i = 0;

		Vector localVector = new Vector();
		Object localObject1;
		Statement localStatement;
		ResultSet localResultSet;
		Object localObject2;
		int i1;
		try {
			localObject1 = questionDataSource.getConnection();
			localStatement = ((Connection) localObject1).createStatement();
			localStatement
					.executeQuery("SELECT max(id) from questions where TopicID = " + str2 + " and TemplateID = " + str3);
			localResultSet = localStatement.getResultSet();
			localResultSet.next();
			int j = localResultSet.getInt(1);
			if (paramHttpServletRequest.getParameter("qid") != null) {
				str7 = paramHttpServletRequest.getParameter("qid");
			} else {
				double d = Math.random() * 1.0E8D;
				localObject2 = new Double(d);
				int n = ((Double) localObject2).intValue();
				i1 = n % j + 1;
				Integer localInteger = new Integer(i1);
				str7 = localInteger.toString();
			}
			localStatement = ((Connection) localObject1).createStatement();
			localStatement
					.executeQuery("SELECT body,answer FROM questions where TopicID = "
							+ str2
							+ " and TemplateID = "
							+ str3
							+ " and id = "
							+ str7);
			localResultSet = localStatement.getResultSet();

			localResultSet.next();
			str14 = localResultSet.getString("answer");
			str15 = localResultSet.getString("body");
			localResultSet.close();
		} catch (Exception localException1) {
			localObject1 = localException1.getMessage();
		}
		try {
			localObject1 = sakilaDataSource.getConnection();
			localStatement = ((Connection) localObject1).createStatement();
			localStatement.executeQuery("SHOW FULL TABLES where table_type = 'base table'");
			localResultSet = localStatement.getResultSet();
			while (localResultSet.next()) {
				String str20 = localResultSet.getString(1);
				if ((str14.contains(str20)) || (i < 6)) {
					i++;
					localVector.addElement(str20);
					str13 = str13 + str20 + " ";
				}
			}
			
			int k = str13.length();
			String str21;
			if (paramHttpServletRequest.getParameter("cas") != null) {
				str21 = paramHttpServletRequest.getParameter("cas");
			} else {
				str21 = "0";
			}
			localPrintWriter
					.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			localPrintWriter.println("<html>");
			localPrintWriter.println("  <head>");
			localPrintWriter
					.println("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
			localPrintWriter
					.println("    <meta http-equiv=\"cache-control\" content=\"no-cache\" />");
			localPrintWriter
					.println("    <meta http-equiv=\"pragma\" content=\"no-cache\" />");
			localPrintWriter.println("    <title>sqlknot_question</title>");
			localPrintWriter
					.println("    <script type=\"text/javascript\" src=\"common/dojo.js\" />");
			localPrintWriter.println("    <script></script>");
			localPrintWriter
					.println("    <script type=\"text/javascript\" src=\"common/functions.js\" />");
			localPrintWriter.println("    <script></script>");
			localPrintWriter.println("    </head>");
			localPrintWriter.println("  <body bgcolor=\"white\" >");
			localPrintWriter
					.println("    <h1><font color=\"olive\">Question:</font></h1>");
			localPrintWriter.println("    <input type=\"hidden\" value=\""
					+ str7 + "\" id=\"question_No\" />");
			localPrintWriter.println("    <input type=\"hidden\" value=\""
					+ str4 + "\" id=\"user_Id\" />");
			localPrintWriter.println("    <input type=\"hidden\" value=\""
					+ str5 + "\" id=\"group_Id\" />");
			localPrintWriter.println("    <input type=\"hidden\" value=\""
					+ str6 + "\" id=\"session_Id\" />");
			localPrintWriter.println("    <input type=\"hidden\" value=\""
					+ str2 + "\" id=\"topic_Id\" />");
			localPrintWriter.println("    <input type=\"hidden\" value=\""
					+ str8 + "\" id=\"QuizGuideNav\" />");
			localPrintWriter.println("    <input type=\"hidden\" value=\""
					+ str11 + "\" id=\"svc_information\" />");
			localPrintWriter.println("    <input type=\"hidden\" value=\""
					+ str3 + "\" id=\"template_Id\" />");
			localPrintWriter.println("    <input type=\"hidden\" value=\"Topic"
					+ str2 + "\" id=\"activity_Id\" />");
			localPrintWriter
					.println("    <input type=\"hidden\" value=\"Template"
							+ str3 + "\" id=\"subactivity_Id\" />");
			localPrintWriter
					.println("    <h3><font color=\"olive\">Based on the tables below, write the required SQL expression.</font></h3>");
			localPrintWriter
					.println("    <h3><font color=\"olive\">Task: </font><div id=\"QuestionBody\"><font color=\"teal\">"
							+ str15 + "</font></div></h3>");
			if (str21.equalsIgnoreCase("1")) {
				localPrintWriter
						.println("    <h5 onclick=\"getAnswer()\">Correct Answer: <div id=\"AnswerBody\"></div><font color=\"red\">");
				localPrintWriter
						.println("      you may click to get correct answer, but this action will count no credit.");
				localPrintWriter.println("    </font><br/></h5>");
			}
			localPrintWriter
					.println("<table><tbody><tr valign=\"bottom\"><td>");
			localPrintWriter
					.print("    <textarea id=\"QueryString\" onfocus=\"clearTextArea('Enter your answer here.','clear')\" onblur=\"clearTextArea('Enter your answer here.','reset')\" rows=\"6\" cols=\"40\"");
			if ((str9.equals("1")) || (str9.equals("0"))) {
				localPrintWriter.print(" disabled=\"disabled\"");
			}
			localPrintWriter.print(" >");
			if (str10.length() > 1) {
				localPrintWriter.print(str10);
			} else {
				localPrintWriter.print("Enter your answer here.");
			}
			localPrintWriter.print("</textarea>");

			localPrintWriter.println("    </td><td>");

			localPrintWriter.println("    <table><tbody><tr><td>");
			localPrintWriter.print("        <b><span id=\"CheckResult\" >");
			if (str9.equals("1")) {
				localPrintWriter.print("Well done! The answer is correct.");
				if (str8.length() > 1) {
					localPrintWriter
							.print("<br />Pick another question by clicking on a question icon, or");
				}
			}
			if (str9.equals("0")) {
				localPrintWriter
						.print("Sorry, please revise your answer and try again.");
				if (!str12.equals("")) {
					str12 = str12
							.replaceAll("sqlknot_sakila.", "")
							.replaceAll(
									"the manual that corresponds to your MySQL server version ",
									"");
					localPrintWriter.print("<br />" + str12);
				}
			}
			localPrintWriter.print("</span></b>");
			localPrintWriter.println("    </td></tr><tr><td>");
			if (str9.equals("1")) {
				localPrintWriter
						.println("        <input id=\"SubmitAnswer\" type=\"submit\" onclick=\"window.location = location.href.replace(/&qid=[0-9]+/g, '').replace(/&srt=[01]/g, '').replace(/&sar=.*&/g, '&').replace(/&sar=.*/g, '')\" value=\"Try a similar question\" name=\"submit\" />");
			} else if (str9.equals("0")) {
				localPrintWriter
						.println("        <input id=\"SubmitAnswer\" type=\"submit\" onclick=\"window.location = location.href.replace(/&srt=[01]/g, '').replace(/&exm=.*&/g, '&').replace(/&exm=.*/g, '')\" value=\"Try it again\" name=\"submit\" />");
			} else {
				localPrintWriter
						.println("        <input id=\"SubmitAnswer\" type=\"submit\" onclick=\"CheckAnswer()\" value=\"Submit Answer\" name=\"submit\" />");
			}
			localPrintWriter.println("    </td></tr><tr><td>");
			localPrintWriter
					.println("        <input type=\"button\" onclick=\"window.open('SQLLab?cid="
							+ str2
							+ "&tid="
							+ str3
							+ "&qid="
							+ str7
							+ "&usr="
							+ str4
							+ "&grp="
							+ str5
							+ "&sid="
							+ str6
							+ "&schema="
							+ str13
							+ "')\" value=\"Go to SQL-Lab\"/>");
			localPrintWriter.println("    </td></tr></tbody></table>");
			localPrintWriter.println("</td></tr></tbody></table>");
			localPrintWriter.println("    <br/>");
			localPrintWriter
					.println("    <table cellpadding=\"0\" cellspacing=\"0\" title=\"click +/- next to the table name to show/hide sample data\" border=\".1\">");
			localPrintWriter.println("      <thead>");
			localPrintWriter.println("        <tr>");
			localPrintWriter
					.println("          <th align=\"right\"><font color=\"olive\">Table Name</font></th>");
			localPrintWriter
					.println("          <th align=\"left\"><font color=\"olive\">Schema & Sample Data  (click +/- to show/hide sample data)</font></th>");
			localPrintWriter.println("        </tr>");
			localPrintWriter.println("      </thead>");
			localPrintWriter.println("      <tbody>");
			for (int m = 0; m < localVector.size(); m++) {
				localPrintWriter.println("        <tr>");
				localPrintWriter
						.println("          <td align=\"right\"><b><font color=\"teal\">"
								+ localVector.elementAt(m)
								+ "</font><font style=\"cursor:pointer\" color=\"olive\"><span id=\"show"
								+ localVector.elementAt(m)
								+ "\" onclick=\"getSchema('"
								+ localVector.elementAt(m)
								+ "')\">(+)</span></font></b></td>");

				localPrintWriter.println("          <td>");

				localStatement.executeQuery("select * from "
						+ localVector.elementAt(m));
				localObject2 = localStatement.getResultSet();
				ResultSetMetaData localResultSetMetaData = ((ResultSet) localObject2)
						.getMetaData();
				i1 = localResultSetMetaData.getColumnCount();
				for (int i2 = 1; i2 - 1 < i1; i2++) {
					if (i2 % 2 == 0) {
						localPrintWriter.print("<font color=\"teal\">"
								+ localResultSetMetaData.getColumnName(i2)
								+ "</font>");
					} else {
						localPrintWriter.print("<font color=\"teal\">"
								+ localResultSetMetaData.getColumnName(i2)
								+ "</font>");
					}
					if (i2 < i1) {
						localPrintWriter.print("  ");
					}
				}
				((ResultSet) localObject2).close();

				localPrintWriter.println("          </td>");
				localPrintWriter.println("        </tr>");
				localPrintWriter.println("        <tr>");
				localPrintWriter.println("          <td></td>");
				localPrintWriter.println("          <td><div id=\""
						+ localVector.elementAt(m) + "\"></div></td>");
				localPrintWriter.println("        </tr>");
			}
			localPrintWriter.println("      </tbody>");
			localPrintWriter.println("    </table>");
			localPrintWriter.println("    <br/><br/><div id=\"QueryResult\"/>");
			localPrintWriter.println("  </body>");
			localPrintWriter.println("</html>");
			localPrintWriter.close();

			localResultSet.close();
			((Connection) localObject1).close();
		} catch (Exception localException2) {
			localObject1 = localException2.getMessage();
		}
	}

	protected void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
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
