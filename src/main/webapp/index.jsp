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
    <input type="submit" value="" />
    <body>
        
        <p><h1><a href="QuestionTester.jsp" target="_blank">Question Testser</a></h1></p>

        <a href="PatternMatcher.jsp" >PatternMatcher</a>
       <br /><br /><br /><br /><br />
        <a href="QuestionPageGenerator?usr=Xin&grp=Zhou&sid=00000&qid=11" target="_blank">QuestionPageGenerator</a><br/>
        
        <
        <form target="_blank" action="QuestionPageGenerator?usr=Xin&grp=Zhou&sid=00000" method="POST">
            <input type="text" name="qid" value="11" />
            <input type="submit" value="Generate" />
        </form>
        
        
          <p><h1><a href="QuestionBrowser.jsp" target="_blank">Question Browser</a></h1></p>
      
        <table border="4" class="drag">
            <tbody>
                <tr>
                    <td>
                        <form name="stay">
                            <font color=blue>it tooks you:</font>
                            <input type=text name=costtime size=9>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
        
        <style>
            <!--.drag{position:relative;cursor:hand}-->
        </style>

        <input type="button" onclick="window.location.href = 'index.jsp'" value="des"/> 
        <input type="button" onclick="window.open('index.jsp')" value="zxc"/> 
        <table border="1">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td width=""></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
        
        
        <input type="submit" value="ttttttttt" />
        
        
        
        <table border="4" class="drag">
            <tbody>
                <tr>
                    <td>
                        
                        <div id="QuestionBody"></div>
                        <br>
                        Insert the missing line:<br>
                        <font color="red">MISSING_LINE:</font>=
                        <input type="text" id="Answer">
                        <input type="submit" onmousedown="answerRequest()" onclick="Report()" value="Answer" name="answer" />
                        <input type="submit" onmouseup="HintRequest()" value="Hint"/>
                        <input type="submit" onmouseup="testt()" value="testt"/>
                        <font color="red"><div id="ReportResult"/></font>
                        <font color="green"><div id="hintResult"/></font>
                    </td>
                    <td>
                        <div id="answerResult"/>
                    </td>
                </tr>
            </tbody>
        </table>
        <table border="4" class="drag">
            <tbody>
                <tr>
                    <td>
                        <table border="0">
                            <tbody>
                                <tr>
                                    <textarea id="QueryString" rows="6" cols="40">SELECT *
FROM Book
WHERE Price>2</textarea>
                                </tr>
                                <tr>
                                    <td></td>
                                    <input TYPE="hidden" NAME="username" VALUE="usersname">
                                    <input TYPE="hidden" NAME="lasttime" VALUE="costtime">
                                    <input TYPE="hidden" NAME="whatever" VALUE="valueofwhatever">
                                    
                                    <td><input type="submit" onmouseup="makeRequest()" value="Submit" name="submit" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>
        <table border="5" class="drag">
            <tbody>
                <tr>
                    <td>
                        <div id="QueryResult"/>
                    </td>
                </tr>
            </tbody>
        </table>
        
        <table border="6" class="drag">
            <tbody>
                <tr><input id="question_No" type="text" size="2" value="01" ></textarea></tr>
                <tr>
                    <td><a onclick="getQuestion()">getQuestion</a></td>
                </tr>
                <tr>
                    <td><a onclick="getAnswer()">getAnswer</a></td>
                </tr>
                <tr>
                    <td><div id="AnswerBody"></div></td>
                </tr>
            </tbody>
        </table>
        
        <div onmouseover="beeepp()">beeeeeep</div>
        
        <input type="submit" onclick="trytry()" value="tttt" />
        
        
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
