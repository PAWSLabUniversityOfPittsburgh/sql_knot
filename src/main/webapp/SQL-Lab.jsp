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
        <title>SQL-Lab</title>
        <script type="text/javascript" src="common/dojo.js" />
        <script></script>
        <script type="text/javascript" src="common/functions.js" />
        <script></script>
        <script type="text/javascript">

        </script>
    </head>
    <body>
        <style>
            <!--.drag{position:relative;cursor:hand}-->
        </style>
    <h1>Universal SQL-Lab</h1>
    <table class="drag" border="6" id="DataShow"></table>
    <table class="drag" border="6">
        <tbody>
            <tr>
                <td>
                    <table border="6">
                        <thead>
                            <tr>
                                <th>table</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td onclick="ShowData('Q1_Employee')">Q1_Employee</td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                    
                </td>
                <td>
                    <table class="drag" border="6">
                        <tbody>
                            <tr>
                                <td>
                                    
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    
                </td>
            </tr>
        </tbody>
    </table>
    
    <a onclick="testtt()">kkkk</a>
    <div id="kktt">
        
    <table border="6" class="drag">
            <tbody>
                <tr>
                    <td>
                        asdf
                    </td>
                    <td>
                        fdsa
                    </td>
                </tr>
            </tbody>
        </table>    
    </div>
    
    
    
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
