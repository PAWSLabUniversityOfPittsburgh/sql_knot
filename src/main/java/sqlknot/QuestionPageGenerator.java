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

public class QuestionPageGenerator extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_question")
	private DataSource questionDataSource;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String QuestionID = request.getParameter("qid");
		String Qinit = "q" + QuestionID.substring(0, 1) + "_";
		String UserID = request.getParameter("usr");
		String GroupID = request.getParameter("grp");
		String SessionID = request.getParameter("sid");
		String CorrectAnswerSwitch;
		
		if (request.getParameter("cas") != null) {
			CorrectAnswerSwitch = request.getParameter("cas");
		} else {
			CorrectAnswerSwitch = "0";
		}
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<html>");
		out.println("  <head>");
		out.println("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		out.println("    <meta http-equiv=\"cache-control\" content=\"no-cache\" />");
		out.println("    <meta http-equiv=\"pragma\" content=\"no-cache\" />");
		out.println("    <title>Question" + QuestionID.substring(0, 1) + "."
				+ QuestionID.substring(1) + "</title>");
		out.println("    <script type=\"text/javascript\" src=\"common/dojo.js\" />");
		out.println("    <script></script>");
		out.println("    <script type=\"text/javascript\" src=\"common/functions.js\" />");
		out.println("    <script></script>");
		out.println("    <script type=\"text/javascript\"></script>");
		out.println("    </head>");
		out.println("  <body onload=\"getQuestion()\">");
		out.println("    <h1><font color=\"olive\">Question</font><font color=\"teal\">"
				+ QuestionID.substring(0, 1)
				+ "."
				+ QuestionID.substring(1)
				+ "</font></h1>");
		out.println("    <input type=\"hidden\" value=\"" + QuestionID
				+ "\" id=\"question_No\" />");
		out.println("    <input type=\"hidden\" value=\"" + UserID
				+ "\" id=\"user_Id\" />");
		out.println("    <input type=\"hidden\" value=\"" + GroupID
				+ "\" id=\"group_Id\" />");
		out.println("    <input type=\"hidden\" value=\"" + SessionID
				+ "\" id=\"session_Id\" />");
		out.println("    <h3><font color=\"olive\">Based on the tables below, write the required SQL expression.</font></h3>");
		out.println("    <table border=\"1\">");
		out.println("      <thead>");
		out.println("        <tr>");
		out.println("          <th align=\"center\"><font color=\"olive\">Table Name</font></th>");
		out.println("          <th align=\"center\"><font color=\"olive\">Schema & Sample Data</th></font>");
		out.println("        </tr>");
		out.println("      </thead>");
		out.println("      <tbody>");
		String exMessage;
		try {
			Connection connection = questionDataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeQuery("show tables");
			ResultSet rs = statement.getResultSet();
			while (rs.next()) {
				String tableName = rs.getString(1);
				if (tableName.substring(0, 3).equalsIgnoreCase(Qinit)) {
					out.println("        <tr onmousedown=\"getSchema('"
							+ tableName.substring(3)
							+ "')\" onmouseup=\"clearSchema('"
							+ tableName.substring(3) + "')\">");
					out.println("          <td><b><font color=\"teal\"><a title=\"click to view/hide the table schema\">"
							+ tableName + "</a></font></b></td>");

					out.println("          <td>");
					out.println("            <table>");
					out.println("              <tbody>");
					out.println("                <tr>");

					Statement statement1 = connection.createStatement();
					statement1.executeQuery("select * from " + tableName);
					ResultSet rs1 = statement1.getResultSet();
					ResultSetMetaData rsmd1 = rs1.getMetaData();
					int column_count1 = rsmd1.getColumnCount();
					for (int i = 1; i - 1 < column_count1; i++) {
						out.println("<td><b><font color=\"black\"><a title=\"click to view/hide the table schema\">"
								+ rsmd1.getColumnName(i) + "</a>");
						if (i < column_count1) {
							out.println(",");
						}
						out.println("</font></b></td>");
					}
					rs1.close();

					out.println("                </tr>");
					out.println("              </tbody>");
					out.println("            </table>");
					out.println("          </td>");
					out.println("        </tr>");
					out.println("        <tr>");
					out.println("          <td></td>");
					out.println("          <td><div id=\""
							+ tableName.substring(3) + "\"></div></td>");

					out.println("        </tr>");
					System.out.println("sqlknot_question: "
							+ tableName.substring(3));
				}
			}
			rs.close();
			connection.close();
		} catch (Exception exception) {
			exMessage = exception.getMessage();
		}
		out.println("      </tbody>");
		out.println("    </table>");
		out.println("    <br/>");
		out.println("    <h3><font color=\"olive\">Task: </font><div id=\"QuestionBody\"></div></h3>");
		if (CorrectAnswerSwitch.equalsIgnoreCase("1")) {
			out.println("    <h5 onclick=\"getAnswer()\">Correct Answer: <div id=\"AnswerBody\"></div><font color=\"red\">");
			out.println("      you may click to get correct answer, but this action will count no credit.");
			out.println("    </font><br/></h5>");
		}
		out.println("    <textarea id=\"QueryString\" onfocus=\"clearTextArea()\" rows=\"6\" cols=\"40\">Enter your answer here.");
		out.println("</textarea>");
		out.println("    <input type=\"submit\" onclick=\"CheckAnswer()\" value=\"Submit Answer\" name=\"submit\" />");
		out.println("    <a href=\"SQLLab?qid=" + QuestionID
				+ "\" target=\"_blank\">SQL-Lab</a>");
		out.println("    <br/><br/><div id=\"QueryResult\"/>");
		out.println("  </body>");
		out.println("</html>");

		out.close();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public String getServletInfo() {
		return "Short description";
	}
}
