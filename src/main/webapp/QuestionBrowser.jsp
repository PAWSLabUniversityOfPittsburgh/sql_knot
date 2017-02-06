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
        <title>Question Browser</title>
    </head>
    <body>

    <h1>Question Browser</h1>
    
    
    <form action="QuestionGenerator">
        
        
        <ol type="1">
            <li><b>Table Creation</b></li>
            <li><b>Table Deletion & Alteration</b></li>
            <li><b>Attribute and Domain Constraints</b></li>
            <li><b>Key Constraints</b></li>
            <li><b>General Constraints</b></li>
            <li><b>Tuple Insertion</b></li>
            <li><b>Tuple Deletion</b></li>
            <li><b>Tuple Update</b></li>
            <li><b>SELECT-FROM</b></li>
            <br/>Template1: <a href="QuestionGenerator?cid=9&tid=1" target="_blank">select * from TT1</a><font color="teal">(16 Questions)</font>
            <br/>Template2: <a href="QuestionGenerator?cid=9&tid=2" target="_blank">select AT1 from TT1</a><font color="teal">(24 Questions)</font>
            <br/>Template3: <a href="QuestionGenerator?cid=9&tid=2" target="_blank">select AT1,AT2 from TT1</a><font color="teal">(11 Questions)</font>
            <li><b>SELECT-DISTINCT</b></li>
            <br/>Template1: <a href="QuestionGenerator?cid=10&tid=1" target="_blank">select distinct AT1 from TT1</a><font color="teal">(6 Questions)</font>
            <li><b>Arithmetic Expressions</b></li>
            <br/>Template1: <a href="QuestionGenerator?cid=11&tid=1" target="_blank">select AT1*d from TT1</a><font color="teal">(5 Questions)</font>
            <li><b>SELECT-FROM-WHERE</b></li>
            <br/>Template1: <a href="QuestionGenerator?cid=12&tid=1" target="_blank">select AT1 from TT1 where C1</a><font color="teal">(42 Questions)</font>
            <br/>Template2: <a href="QuestionGenerator?cid=12&tid=2" target="_blank">select AT1,AT2 from TT1 where C1</a><font color="teal">(105 Questions)</font>
            <br/>Template3: <a href="QuestionGenerator?cid=12&tid=3" target="_blank">select AT1 from TT1 where C1 and C2</a><font color="teal">(15 Questions)</font>
            <br/>Template4: <a href="QuestionGenerator?cid=12&tid=4" target="_blank">select AT1 from TT1 where C1 or C2</a><font color="teal">(0 Questions)</font>
            <br/>Template5: <a href="QuestionGenerator?cid=12&tid=5" target="_blank">select AT1 from TT1 where not C1</a><font color="teal">(0 Questions)</font>
            <li><b>Pattern Matching</b></li>
            <br/>Template1: <a href="QuestionGenerator?cid=13&tid=1" target="_blank">select * from TT1 where AT1 like P1</a><font color="teal">(0 Questions)</font>
            <br/>Template2: <a href="QuestionGenerator?cid=13&tid=2" target="_blank">select AT1 from TT1 where AT2 like P1</a><font color="teal">(0 Questions)</font>
            <br/>Template3: <a href="QuestionGenerator?cid=13&tid=3" target="_blank">select AT1,AT2 from TT1 where AT3 like P1</a><font color="teal">(46 Questions)</font>
            <li><b>Multiple Table Queries</b></li>
            <br/>Template1: <a href="QuestionGenerator?cid=14&tid=1" target="_blank">select AT1,AT2 from TT1,TT2 where C1</a><font color="teal">(3 Questions)</font>
            <br/>Template2: <a href="QuestionGenerator?cid=14&tid=2" target="_blank">select AT1 from TT1,TT2 where C1</a><font color="teal">(0 Questions)</font>
            <li><b>ORDER-BY</b></li>
            <br/>Template1: <a href="QuestionGenerator?cid=15&tid=1" target="_blank">select AT1 from TT1 order by AT2</a><font color="teal">(0 Questions)</font>
            <br/>Template2: <a href="QuestionGenerator?cid=15&tid=2" target="_blank">select AT1 from TT1 order by AT2 asc</a><font color="teal">(0 Questions)</font>
            <br/>Template3: <a href="QuestionGenerator?cid=15&tid=3" target="_blank">select AT1 from TT1 order by AT2 desc</a><font color="teal">(0 Questions)</font>
            <li><b>Set Operations</b></li>
            <li><b>Aggregate Functions</b></li>
            <br/>Template1: <a href="QuestionGenerator?cid=17&tid=1" target="_blank">select avg(AT1) from TT1 where C1</a><font color="teal">(15 Questions)</font>
            <br/>Template2: <a href="QuestionGenerator?cid=17&tid=2" target="_blank">select min(AT1) from TT1</a><font color="teal">(0 Questions)</font>
            <br/>Template3: <a href="QuestionGenerator?cid=17&tid=3" target="_blank">select max(AT1) from TT1</a><font color="teal">(0 Questions)</font>
            <br/>Template4: <a href="QuestionGenerator?cid=17&tid=4" target="_blank">select sum(AT1) from TT1 where C1</a><font color="teal">(5 Questions)</font>
            <br/>Template5: <a href="QuestionGenerator?cid=17&tid=5" target="_blank">select count(AT1) from TT1 where C1</a><font color="teal">(5 Questions)</font>
            <li><b>GROUP-BY & HAVING</b></li>
            <li><b>Sub-Queries</b></li>
            <li><b>NULL-Values</b></li>
            <li><b>Derived Relations & Views</b></li>
        </ol>
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
