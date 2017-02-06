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

public class QueryExecution extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_sakila")
	private DataSource sakilaDataSource;
	
	protected void processRequest(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		paramHttpServletResponse.setContentType("text/html;charset=UTF-8");
		PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();

		String str1 = paramHttpServletRequest.getParameter("QueryString");
		try {
			Connection localConnection = sakilaDataSource.getConnection();
			Statement localStatement = localConnection.createStatement();

			localStatement.executeQuery(str1);
			ResultSet localResultSet = localStatement.getResultSet();

			ResultSetMetaData localResultSetMetaData = localResultSet.getMetaData();
			int i = localResultSetMetaData.getColumnCount();

			localPrintWriter.println("The result for query: <font color=\"teal\">" + str1 + "</font><br/>");
			localPrintWriter.println("is: <br/>");
			localPrintWriter.println("<table border = \"1\">");
			localPrintWriter.println("<thead>");
			localPrintWriter.println("<tr>");
			for (int j = 1; j - 1 < i; j++) {
				localPrintWriter.println("<th align = center>"
						+ localResultSetMetaData.getColumnName(j) + "</th>");
			}
			localPrintWriter.println("</tr>");
			localPrintWriter.println("</thead>");
			localPrintWriter.println("<tbody>");
			while (localResultSet.next()) {
				localPrintWriter.println("<tr>");
				for (int j = 1; j - 1 < i; j++) {
					localPrintWriter.println("<td align = center>"
							+ localResultSet.getString(j) + "</td>");
				}
				localPrintWriter.println("<tr>");
			}
			localPrintWriter.println("</tbody>");
			localPrintWriter.println("</table>");

			localResultSet.close();
			localConnection.close();
		} catch (Exception localException) {
			String str3 = localException.getMessage();
			localPrintWriter.println("Problem :" + str3.substring(0));
		}
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
