package sqlknot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessDB extends HttpServlet {
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>result</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<tr>");
		out.println("<body>");
		out.println("<body>");

		String select = request.getParameter("select");
		String from = request.getParameter("from");
		String where = request.getParameter("where");

		Pattern pattern = Pattern.compile("[a-zA-Z*].*");
		Matcher matcher = pattern.matcher(select);
		if (matcher.matches()) {
			try {
				String userName = "root";
				String password = "Reniorc123456";
				String url = "jdbc:mysql://localhost/ReniorcTestDB";
				Class.forName("com.mysql.jdbc.Driver").newInstance();

				Connection connection = DriverManager.getConnection(url,
						userName, password);
				Statement statement = connection.createStatement();

				String sql = "SELECT " + select + " FROM " + from + " WHERE "
						+ where;

				statement.executeQuery(sql);
				ResultSet rs = statement.getResultSet();

				ResultSetMetaData rsmd = rs.getMetaData();
				int column_count = rsmd.getColumnCount();

				out.println("The result for query: " + sql + "<br/>");
				out.println("is: <br/><br/>");
				out.println("<table border = \"1\">");
				out.println("<thead>");
				out.println("<tr>");
				for (int i = 1; i - 1 < column_count; i++) {
					out.println("<th align = center>" + rsmd.getColumnName(i)
							+ "</th>");
				}
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				while (rs.next()) {
					out.println("<tr>");
					for (int j = 1; j - 1 < column_count; j++) {
						out.println("<td align = center>" + rs.getString(j)
								+ "</td>");
					}
					out.println("<tr>");
				}
				out.println("</tbody>");
				out.println("</table>");
				out.println("<p>Click <a href=\""
						+ request.getHeader("referer")
						+ "\">here</a> to try another query.</p>");

				rs.close();
				connection.close();
			} catch (Exception exception) {
				String exMessage = exception.getMessage();
				out.println("Problem :" + exMessage.substring(0));

				out.println("<br/>Click <a href=\""
						+ request.getHeader("referer")
						+ "\">here</a> to have another try.");
			}
		} else {
			out.println("the parameter of SELECT should starts with a character.");
			out.println("<br/>Click <a href=\"" + request.getHeader("referer")
					+ "\">here</a> to have another try.");
		}
		out.println("</body>");
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
