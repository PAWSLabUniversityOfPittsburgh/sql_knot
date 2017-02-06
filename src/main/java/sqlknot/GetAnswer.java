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

public class GetAnswer extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_question")
	private DataSource questionDataSource;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String questionNo = request.getParameter("QuestionNo");
		String TopicID = request.getParameter("cid");
		String TemplateID = request.getParameter("tid");
		try {
			Connection connection = questionDataSource.getConnection();
			Statement statement = connection.createStatement();

			String questionsql = "select answer from Questions where id="
					+ questionNo + " and TopicID = " + TopicID
					+ " and TemplateID = " + TemplateID;

			statement.executeQuery(questionsql);
			ResultSet rs = statement.getResultSet();

			ResultSetMetaData rsmd = rs.getMetaData();
			int column_count = rsmd.getColumnCount();
			while (rs.next()) {
				String CorrectAnswer = rs.getString("answer");
				String FormatedAnswer = CorrectAnswer.replaceAll(
						"[sS][eE][lL][eE][cC][tT]",
						"<br/><font color=red>SELECT</font>");
				FormatedAnswer = FormatedAnswer.replaceAll("[fF][rR][oO][mM]",
						"<br/><font color=green>FROM</font>");
				FormatedAnswer = FormatedAnswer.replaceAll(
						"[wW][hH][eE][rR][eE]",
						"<br/><font color=blue>WHERE</font>");
				FormatedAnswer = FormatedAnswer
						.replaceAll("[aA][nN][dD]",
								"<br/>&nbsp;&nbsp;&nbsp;&nbsp;<font color=cyan>and</font>");
				out.println(FormatedAnswer);
			}
			rs.close();
			connection.close();
		} catch (Exception exception) {
			String exMessage = exception.getMessage();
			out.println("Problem :" + exMessage.substring(0));
			out.println("<br/>Click <a href=\"" + request.getHeader("referer")
					+ "\">here</a> to have another try.");
		}
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
