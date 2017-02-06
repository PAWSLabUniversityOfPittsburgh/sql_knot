package sqlknot;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AC extends HttpServlet {
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String answerMAT = "";
		answerMAT = request.getParameter("MAT");
		String answerPAT = "";
		answerPAT = request.getParameter("PAT");
		String answer = "";
		answer = answerMAT;
		String Canswer = "";
		Canswer = answerPAT;

		Pattern pattern = Pattern.compile(Canswer);
		Matcher matcher = pattern.matcher(answer);
		if (matcher.matches()) {
			out.print("1");
		} else {
			out.print("0");
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
