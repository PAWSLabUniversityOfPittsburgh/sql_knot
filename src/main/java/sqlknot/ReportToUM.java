package sqlknot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportToUM extends HttpServlet {
	protected void processRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
		paramHttpServletResponse.setContentType("text/html;charset=UTF-8");
		PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();

		String str1 = "http://adapt2.sis.pitt.edu/cbum/um";
		if (paramHttpServletRequest.getParameter("app") != null) {
			str1 = str1 + "?app=" + paramHttpServletRequest.getParameter("app");
		}
		if (paramHttpServletRequest.getParameter("act") != null) {
			str1 = str1 + "&act=" + paramHttpServletRequest.getParameter("act");
		}
		if (paramHttpServletRequest.getParameter("sub") != null) {
			str1 = str1 + "&sub=" + paramHttpServletRequest.getParameter("sub");
		}
		if (paramHttpServletRequest.getParameter("usr") != null) {
			str1 = str1 + "&usr=" + paramHttpServletRequest.getParameter("usr");
		}
		if (paramHttpServletRequest.getParameter("grp") != null) {
			str1 = str1 + "&grp=" + paramHttpServletRequest.getParameter("grp");
		}
		if (paramHttpServletRequest.getParameter("sid") != null) {
			str1 = str1 + "&sid=" + paramHttpServletRequest.getParameter("sid");
		}
		if (paramHttpServletRequest.getParameter("res") != null) {
			str1 = str1 + "&res=" + paramHttpServletRequest.getParameter("res");
		}
		if (paramHttpServletRequest.getParameter("svc") != null) {
			String localObject = paramHttpServletRequest.getParameter("svc");
			localObject = (localObject).replace("'", "\"");
			if (paramHttpServletRequest.getParameter("act").equalsIgnoreCase(
					"sqllab")) {
				str1 = str1 + "&svc="
						+ URLEncoder.encode((String) localObject, "UTF-8");
			} else {
				str1 = str1 + "&svc=" + (String) localObject;
			}
		}
		if (paramHttpServletRequest.getParameter("cid") != null) {
			str1 = str1 + "&cid=" + paramHttpServletRequest.getParameter("cid");
		}
		if (paramHttpServletRequest.getParameter("tid") != null) {
			str1 = str1 + "&tid=" + paramHttpServletRequest.getParameter("tid");
		}
		if (paramHttpServletRequest.getParameter("qid") != null) {
			str1 = str1 + "&qid=" + paramHttpServletRequest.getParameter("qid");
		}
		localPrintWriter.println(str1);

		Object localObject = new URL(str1);

		URLConnection localURLConnection = ((URL) localObject).openConnection();

		BufferedReader localBufferedReader = new BufferedReader(
				new InputStreamReader(localURLConnection.getInputStream()));

		String str2 = "";
		while (localBufferedReader.readLine() != null) {
			str2 = localBufferedReader.readLine();
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
