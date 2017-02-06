package sqlknot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class QuestionGenerator1 extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_question")
	private DataSource questionDataSource;
	
	@Resource(name = "jdbc/sqlknot_sakila")
	private DataSource sakilaDataSource;
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String TopicID = request.getParameter("cid");
		String TemplateID = request.getParameter("tid");

		String UserID = request.getParameter("usr");
		String GroupID = request.getParameter("grp");
		String SessionID = request.getParameter("sid");
		String QuestionID = "";

		String exMessage;
		try {
			Connection connection = questionDataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeQuery("SELECT max(id) from questions where TopicID = " + TopicID + " and TemplateID = " + TemplateID);
			ResultSet rs = statement.getResultSet();
			rs.next();
			int maxID = rs.getInt(1);
			if (request.getParameter("qid") != null) {
				QuestionID = request.getParameter("qid");
			} else {
				double RandomValue = Math.random() * 1.0E8D;
				Double DRandomValue = new Double(RandomValue);
				int seed = DRandomValue.intValue();
				int QID = seed % maxID + 1;
				Integer IQID = new Integer(QID);
				QuestionID = IQID.toString();
			}
			rs.close();
		} catch (Exception exception) {
			exMessage = exception.getMessage();
		}
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
		out.println("    <title>sqlknot_question</title>");

		out.println("    <script type=\"text/javascript\" src=\"common/dojo.js\" />");
		out.println("    <script></script>");
		out.println("    <script type=\"text/javascript\" src=\"common/functions.js\" />");
		out.println("    <script></script>");
		out.println("    <script type=\"text/javascript\"></script>");
		out.println("    </head>");
		out.println("  <body onload=\"getQuestion()\">");
		out.println("    <h1><font color=\"olive\">Question:</font></h1>");

		out.println("    <input type=\"hidden\" value=\"" + QuestionID
				+ "\" id=\"question_No\" />");
		out.println("    <input type=\"hidden\" value=\"" + UserID
				+ "\" id=\"user_Id\" />");
		out.println("    <input type=\"hidden\" value=\"" + GroupID
				+ "\" id=\"group_Id\" />");
		out.println("    <input type=\"hidden\" value=\"" + SessionID
				+ "\" id=\"session_Id\" />");
		out.println("    <input type=\"hidden\" value=\"" + TopicID
				+ "\" id=\"topic_Id\" />");
		out.println("    <input type=\"hidden\" value=\"" + TemplateID
				+ "\" id=\"template_Id\" />");
		out.println("    <input type=\"hidden\" value=\"Topic" + TopicID
				+ "\" id=\"activity_Id\" />");
		out.println("    <input type=\"hidden\" value=\"Template" + TemplateID
				+ "\" id=\"subactivity_Id\" />");

		out.println("    <h3><font color=\"olive\">Task: </font><div id=\"QuestionBody\"></div></h3>");
		if (CorrectAnswerSwitch.equalsIgnoreCase("1")) {
			out.println("    <h5 onclick=\"getAnswer()\">Correct Answer: <div id=\"AnswerBody\"></div><font color=\"red\">");
			out.println("      you may click to get correct answer, but this action will count no credit.");
			out.println("    </font><br/></h5>");
		}
		out.println("<table><tbody><tr><td>");
		out.println("    <textarea id=\"QueryString\" onfocus=\"clearTextArea()\" rows=\"6\" cols=\"40\">Enter your answer here.");
		out.println("</textarea>");

		out.println("</td><td>");

		out.println("<table><tbody><tr><td>");
		out.println("    <input type=\"submit\" onclick=\"CheckAnswer1()\" value=\"Submit Answer\" name=\"submit\" />");
		out.println("</td></tr><tr><td>");
		out.println("    <a href=\"SQLLab?cid="
				+ TopicID
				+ "&tid="
				+ TemplateID
				+ "&qid="
				+ QuestionID
				+ "\" target=\"_blank\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SQL-Lab&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>");

		out.println("</td></tr></tbody></table>");

		out.println("</td></tr></tbody></table>");

		out.println("    <br/><br/><br/><br/>");

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
