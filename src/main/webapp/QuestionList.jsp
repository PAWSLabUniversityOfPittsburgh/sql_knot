<%@page contentType="text/html"%>
<%@page import="java.util.*, java.sql.*"%>
<%
	String JDBC_URL = "jdbc:mysql://localhost/sqlknot_question";
	String JDBC_ID = "Reniorc";
  	String JDBC_PASSWD = "123456";
  	String strQuery = "";
  	ResultSet rs = null;
  	Vector v_topicid = new Vector();
  	Vector v_templateid = new Vector();
  	Vector v_no = new Vector();
  	Vector v_name = new Vector();
  	
try{
	Class.forName("org.gjt.mm.mysql.Driver");
	Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_ID, JDBC_PASSWD);
	Statement stmt = conn.createStatement();
	
	strQuery = "select q.topicid, q.templateid, count(*), t.name from questions q, topics t where q.topicid = t.id and topicid <> 1 and topicid <> 99  group by topicid, templateid";
	rs = stmt.executeQuery(strQuery);
	while(rs.next()){
		v_topicid.addElement(rs.getString(1));
		v_templateid.addElement(rs.getString(2));
		v_no.addElement(rs.getString(3));
		v_name.addElement(rs.getString(4));
	}
%>
 	
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
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

    <h1><font face="verdana">Question Browser</font></h1>

    
    <form action="QuestionGenerator">
        
        
        <ol type="1"><font face="verdana" size=2>
            <li><b>Table Creation</b></li>
            <li><b>Table Deletion & Alteration</b></li>
            <li><b>Attribute and Domain Constraints</b></li>
            <li><b>Key Constraints</b></li>
            <li><b>General Constraints</b></li>
            <li><b>Tuple Insertion</b></li>
            <li><b>Tuple Deletion</b></li>
            <li><b>Tuple Update</b></li>
            <li><b>SELECT-FROM</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 9 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=9&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>Arithmetic Expressions</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 10 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=10&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>SELECT-FROM-WHERE</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 11 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=11&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>Pattern Matching</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 12 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=12&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>Multiple Table Queries</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 13 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=13&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>ORDER-BY</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 14 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=14&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>Set Operation</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 15 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=15&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>Aggregate Functions</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 16 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=16&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>GROUP-BY & HAVING</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 17 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=17&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>Sub-Queries</b></li>
<%
			strQuery = "select templateid, count(*) from questions where topicid = 18 group by templateid";
			rs = stmt.executeQuery(strQuery);
			while(rs.next()){
%>
            <br/><a href="subTemplate.jsp?topicID=18&templateID=<%=rs.getString(1)%>"><font color="teal">Template<%=rs.getString(1)%>: <%=rs.getString(2)%>
<%
				if(Integer.parseInt(rs.getString(2)) > 1){
					out.println("Questions</font></a>");
				}else{
					out.println("Question</font></a>");	
				}
			}
%>
            <li><b>NULL-Values</b></li>
            <li><b>Derived Relations & Views</b></li>
        </font></ol>
    </form>
    <table border="1">
        <thead>
            <tr style="cursor:pointer">
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td onmouseover=""></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </tbody>
    </table>    
    </body>
</html>
<%
	conn.close();
	stmt.close();
}catch(Exception e){
		System.out.println("9_1.jsp Connect() Error : "+e);
}
%>