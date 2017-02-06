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
        <title>Question Tester</title>
        <script type="text/javascript" src="common/dojo.js" />
        <script></script>
        <script type="text/javascript" src="common/functions.js" />
        <script></script>
        <script type="text/javascript">

        </script>
    </head>
    
    <body>
        
        <h1>Question Tester</h1>
        
        
        <table border="1">
            <tbody>
                <tr>
                    <td valign="top">
                        
                        <form name="GetQuestionTaskForm" id="GetQuestionTaskForm" action="GetQuestionTask">
                            
                            <select name="TopicID" id="TopicID" size="9">
                             <!--   <option value="1" onclick="listQ()">1. Table Creation</option>
                                <option value="2" onclick="listQ()">2. Table Deletion & Alteration</option>
                                <option value="3" onclick="listQ()">3. Attribute and Domain Constraints</option>
                                <option value="4" onclick="listQ()">4. Key Constraints</option>
                                <option value="5" onclick="listQ()">5. General Constraints</option>
                                <option value="6" onclick="listQ()">6. Tuple Insertion</option>
                                <option value="7" onclick="listQ()">7. Tuple Deletion</option>
                                <option value="8" onclick="listQ()">8. Tuple Update</option>  -->
                                <option value="9" onclick="listQ()">9. SELECT-FROM(DISTINCT)</option>
                                <option value="10" onclick="listQ()">10. Arithmetic Expressions</option>
                                <option value="11" onclick="listQ()">11. SELECT-FROM-WHERE</option>
                                <option value="12" onclick="listQ()">12. Pattern Matching</option>
                                <option value="13" onclick="listQ()">13. Multiple Table Queries</option>
                                <option value="14" onclick="listQ()">14. ORDER-BY</option>
                                <option value="15" onclick="listQ()">15. Set Operations</option>
                                <option value="16" onclick="listQ()">16. Aggregate Functions</option>
                                <option value="17" onclick="listQ()">17. GROUP-BY & HAVING</option>
                                <option value="18" onclick="listQ()">18. Sub-Queries</option>
                                <option value="19" onclick="listQ()">19. NULL-Values</option>
                                <option value="20" onclick="listQ()">20. Derived Relations & Views</option>
                            </select>
                            
                            <select name="TemplateID" id="TemplateID" size="6">
                                <option value="1" onclick="listQ()">Template1</option>
                                <option value="2" onclick="listQ()">Template2</option>
                                <option value="3" onclick="listQ()">Template3</option>
                                <option value="4" onclick="listQ()">Template4</option>
                                <option value="5" onclick="listQ()">Template5</option>
                                <option value="6" onclick="listQ()">Template6</option>
                            </select>    
                            
                            <input type="button" value="GetQuestions" onclick="listQ()" />
                        </form>
                        
                        <div id="qqhere"></div>
                    </td>
                    <td valign="top">
                        <div id="QueryResult"></div>
                    </td>
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
