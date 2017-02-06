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
        <title>TemplateMaker</title>
        <script type="text/javascript" src="common/dojo.js" />
        <script></script>
        <script type="text/javascript" src="common/functions.js" />
        <script></script>
        <script type="text/javascript">

        </script>
        
        
    </head>
    <body>
    
    
    </head> 
    <body> 
        <h1>TemplateMaker</h1>
        
        
        <br/>
        
        <form name="MakeTemplate" method="post" action=""> 
            <select name="clauses" size="6" onclick="putClause()">
                <option value="SELECT ">SELECT</option>
                <option value="FROM ">FROM</option>
                <option value="WHERE ">WHERE</option>
                <option value="GROUP BY ">GROUP BY</option>
                <option value="HAVING ">HAVING</option>
                <option value="ORDER BY ">ORDER BY</option>
            </select>
            
            <select name="tokens" size="6" onclick="putToken()">
                <option value="<TT1>">&lt;TableToken&gt;</option>
                <option value="<AT1>">&lt;AttributeToken&gt;</option>
                <option value="<ST1>">&lt;StringToken&gt;</option>
                <option value="<VT1>">&lt;ValueToken&gt;</option>
            </select>
            
            <textarea name="QueryTemplate" rows="6" cols="40"></textarea>
        </form>
        
        <form name="testform">
            <table border="3">Table Token 1
                <thead>
                    <tr>
                        <th>Table List</th>
                        <th>Allow Values</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <select name="test1" size="6" onchange="addTo()">
                                <option>q1_company</option>
                                <option>q1_employee</option>
                                <option>q1_manages</option>
                                <option>q1_works</option>
                                <option>q2_accident</option>
                                <option>q2_car</option>
                                <option>q2_owns</option>
                                <option>q2_participated</option>
                                <option>q2_person</option>
                            </select>
                        </td>
                        <td>
                            <select name="test2" size="6" onchange="delFrom()">
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        
        <form name="st">
            <table border="3">Value Token 1
                <thead>
                    <tr>
                        <th>Value</th>
                        <th>Allow Values</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <input type="text" name="sts" />
                            <a onclick="addst()" >==></a>
                            
                        </td>
                        <td>
                            <select name="st1" size="6" onclick="delst()"></select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        
        
        
        
        
        
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
