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

public class GetSchema extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_sakila")
	private DataSource sakilaDataSource;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String tableName = request.getParameter("tableName");
		String questionNo = request.getParameter("questionNo");

		String ActualTableName = tableName;
		String querystring = "select * from " + ActualTableName;
		try {
			Connection connection = sakilaDataSource.getConnection();
			Statement statement = connection.createStatement();

			statement.executeQuery(querystring);
			ResultSet rs = statement.getResultSet();

			ResultSetMetaData rsmd = rs.getMetaData();
			int column_count = rsmd.getColumnCount();

			out.println("<table cellpadding=\"0\" cellspacing=\"0\" border=\"1\">");

			out.println("<thead>");
			out.println("<tr>");
			for (int i = 1; i - 1 < column_count; i++) {
				out.println("<th>");

				out.println(rsmd.getColumnName(i));

				out.println("</th>");
			}
			out.println("</tr>");
			out.println("</thead>");

			out.println("<tbody>");

			int SampleNo = 2;
			while ((rs.next()) && (SampleNo > 0)) {
				out.println("<tr>");
				for (int j = 1; j - 1 < column_count; j++) {
					out.println("<td align = center>" + rs.getString(j)
							+ "</td>");
				}
				out.println("<tr>");
				SampleNo--;
			}
			out.println("</tbody>");
			out.println("</table>");

			rs.close();
			connection.close();
		} catch (Exception exception) {
			String exMessage = exception.getMessage();
			out.println("Problem :" + exMessage.substring(0));
			out.println(querystring);
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
