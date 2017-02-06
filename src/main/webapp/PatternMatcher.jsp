<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sqlknot_question_IndexPage</title>
        <script type="text/javascript" src="common/dojo.js" />
        <script></script>
        <script type="text/javascript" src="common/functions.js" />
        <script></script>
        <script type="text/javascript">

        </script>
    </head>
    <body>
        
        <h1>Pattern Matcher</h1>
        <br/><br/>
        This is an example showing how the regular expressions could be used in DDL questions' correctness checking.
        <br/>
        The Question on this stage is: 
        <br /><font color="teal">Create a table named "students" with a column named "student_id" for 10 characters.</font>
        <br/>
        The answer stored in the question DB is: 
        <br /><font color="olive"> (?i)\s*create\s+table\s+students\s*\(\s*student_id\s+char(acter)?\s*\(\s*10\s*\)\s*\)\s*</font>
        <br/>
        the "Matcher" area could be used to test the correctness of possible input.
        <br/><br/>
        <span id="pm" ></span><br/><br/>
        Pattern:<input type="text" id="pat" value="(?i)\s*create\s+table\s+students\s*\(\s*student_id\s+char(acter)?\s*\(\s*10\s*\)\s*\)\s*" size="150" onkeyup="patternmatch()" />
        <br />
        Matcher:<input type="text" id="mat" value="create table students (student_id char(10))" size="150" onkeyup="patternmatch()" />

        
        
        
        <%--
    This example uses JSTL, uncomment the taglib directive above.
    To test, display the page like this: index.jsp?sayHello=true&name=Murphy
    --%>
        <%--
    <c:if test="${param.sayHello}">
        <!-- Let's welcome the user ${param.name} -->
        Hello ${param.name}!
    </c:if>
    --%>
    
    </body>
</html>
