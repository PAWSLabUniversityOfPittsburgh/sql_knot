<%@page contentType="text/html"%>
<%@page import="java.util.*, java.sql.*"%>
<%
	String JDBC_URL = "jdbc:mysql://localhost/sqlknot_question";
	String JDBC_ID = "Reniorc";
  	String JDBC_PASSWD = "123456";
  	String strQuery = "";
	ResultSet rs = null; 
	String topicID = request.getParameter("topicID");
	String templateID = request.getParameter("templateID");
	Vector v_body = new Vector();
	Vector v_answer = new Vector();
        
try{
	Class.forName("org.gjt.mm.mysql.Driver");
	Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_ID, JDBC_PASSWD);
	Statement stmt = conn.createStatement();
	
	strQuery = "select body, answer from questions where topicID="+topicID+" and templateID= "+templateID; 
	rs = stmt.executeQuery(strQuery);
	while(rs.next()){
		v_body.addElement(rs.getString(1));
		v_answer.addElement(rs.getString(2));
	}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question Browser</title>
<STYLE type="text/css">
<!--
A.b:link {  font-family: ""; color: #003366; text-decoration: none}
a:link {  font-family: ""; color: #003366; text-decoration: none}
a:visited {  font-family: ""; color: #003366; text-decoration: none}
a:hover {  font-family: ""; color: #800080; text-decoration: underline}
-->
</STYLE>
    </head>
    <body>
    <h2><font face="verdana">Topic <%=topicID%> Template<%=templateID%></font></h2>
    <form action="QuestionGenerator">
		<table width = "80%">
			<tr>
				<td colspan="3" height=1 bgcolor="#000000">
			</tr>
<%		for(int i=0; i<v_body.size(); i++){%>
			<tr>
				<td width="5%" align="right">
					<font face="verdana" size=2><%=(i+1)%></font>
				</td>
				<td width="55%">&nbsp;&nbsp;<a href="QuestionGenerator?cid=<%=topicID%>&tid=<%=templateID%>&qid=<%=(i+1)%>" target="_blank"><font face="verdana" size=2 color="teal"><%=v_body.elementAt(i)%></font></a></td>
				<td><font face="verdana" size=2><%=v_answer.elementAt(i)%></font></td>
			</tr>
			<tr>
				<td colspan="3" height=1 bgcolor="#000000">
			</tr>
<%		}%>
			<tr>
				<td colspan="3" align="right">
					<a href="QuestionList.jsp"><font face="verdana" size=2>Go Back</font></a>
				</td>
			</tr>
		</table>

    </form>  
    </body>
</html>
<%
	conn.close();
	stmt.close();
}catch(Exception e){
		System.out.println("9_1.jsp Connect() Error : "+e);
}
%>
