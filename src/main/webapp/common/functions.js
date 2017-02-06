//This is the TimeCounter
/*
var second=0;
var minute=0;
var hour=0;
timespent=window.setTimeout("interval();",1000);
function interval(){
    second++;
    if(second==60){second=0;minute+=1;}
    if(minute==60){minute=0;hour+=1;}
    document.stay.costtime.value=hour+":"+minute+":"+second+"s";
    timespent=window.setTimeout("interval();",1000);}
 */


//This is the drag effect
var dragapproved=false
var z,x,y
function move(){
    if (event.button==1&&dragapproved){
        z.style.pixelLeft=temp1+event.clientX-x
        z.style.pixelTop=temp2+event.clientY-y
        return false
    }
}
function drags(){
    if (!document.all)
        return
        if (event.srcElement.className=="drag"){
            dragapproved=true
            z=event.srcElement
            temp1=z.style.pixelLeft
            temp2=z.style.pixelTop
            x=event.clientX
            y=event.clientY
            document.onmousemove=move
        }
}
document.onmousedown=drags
document.onmouseup=new Function("dragapproved=false")

//This is the QueryResult
function makeRequest() {
    var qqss;
    qqss = dojo.byId("QueryString").value;
    
    dojo.io.bind( {
        url: "QueryExecution",
        load: processResponse,
        content: {QueryString: qqss}
    });
}
function processResponse(type, data, evt) {
    
    var qqss;
    qqss = dojo.byId("QueryString").value;
    var dest = data.replace("sqlknot_question.", "").replace("the manual that corresponds to your MySQL server version ", "");
    element = dojo.byId("QueryResult");
    element.innerHTML = dest;
    if (dest.indexOf("Problem") == -1) {
        reportLab("query", "1", qqss);
    } else {
        reportLab("query", "0", qqss);
    }
}
//This is the QueryResult2
function makeRequest2(qqss) {
    
    var qqsss;
    qqsss = dojo.byId(qqss).value;
    
    dojo.io.bind( {
        url: "QueryExecution2",
        load: processResponse2,
        content: {QueryString: qqsss}
    });
}
function processResponse2(type, data, evt) {
    element = dojo.byId("QueryResult");
    element.innerHTML=data;
}


//This is the HintProvider
function HintRequest() {
    var QuestionID;
    QuestionID = dojo.byId("questionNo");
    
    dojo.io.bind( {
        url: "HintProvider",
        load: hintResponse,
        content: {questionNo: QuestionID}
    });
}
function hintResponse(type, data, evt) {
    element = dojo.byId("hintResult");
    element.innerHTML=data;
}


//This is the getQuestion
function getQuestion() {
    //CheckAnswer();
    var questionNumber;
    questionNumber = document.getElementById("question_No").value;
    var topicId;
    topicId = document.getElementById("topic_Id").value;
    var templateId;
    templateId = document.getElementById("template_Id").value;
    dojo.io.bind( {
        url: "GetQuestion",
        load: putQuestion,
        content: {
            QuestionNo: questionNumber,
            cid: topicId,
            tid: templateId}
    });
}
function putQuestion(type, data, evt) {
    element = document.getElementById("QuestionBody");
    element.innerHTML = data;
}


//This is the getAnswer
function getAnswer() {
    var questionNumber2;
    questionNumber2 = dojo.byId("question_No").value;
    var topicId;
    topicId = dojo.byId("topic_Id").value;
    var templateId;
    templateId = dojo.byId("template_Id").value;
    dojo.io.bind( {
        url: "GetAnswer",
        load: putAnswer,
        content: {
            QuestionNo: questionNumber2,
            cid: topicId,
            tid: templateId}
    });
}
function putAnswer(type, data, evt) {
    //alert(data);
    element = dojo.byId("AnswerBody");
    element.innerHTML = data;
    var app = "23";
    
    var act = dojo.byId("activity_Id").value;
    var sub = dojo.byId("subactivity_Id").value;
    var usr = dojo.byId("user_Id").value;
    var grp = dojo.byId("group_Id").value;
    var sid = dojo.byId("session_Id").value;
    var svc = dojo.byId("svc_information").value;
    var cid = dojo.byId("topic_Id").value;
    var tid = dojo.byId("template_Id").value;
    var qid = dojo.byId("question_No").value;
    var res = "-1";
    dojo.io.bind( {
        //url: "http://adapt2.sis.pitt.edu/cbum/um",
        url: "ReportToUM",
        //load: function (type, data, evt) {},//alert(data)},
        load: doNothing,
        mimetype: "text/json", 
        content: {
            app: app,
            act: act,
            sub: sub,
            usr: usr,
            grp: grp,
            sid: sid,
            res: res,
            svc: svc,
            cid: cid,
            tid: tid,
            qid: qid
        }
    });
}


function doNothing(type, data, evt) {
    return false;
}



//This is the answerChecking with the reporting
function CheckAnswer(){
    
    var UserAnswer;
    UserAnswer = dojo.byId("QueryString").value;
    //alert("." + UserAnswer.substring(0, 23) + ".");
    
    //if (UserAnswer.substring(0, 23) != "Enter your answer here.") {
    //var UserAnswer = UA;
    SubmitButton = dojo.byId("SubmitAnswer");
    SubmitButton.disabled = "disabled";
    SubmitButton.value = "Checking....";
    
    var questionNumber;
    questionNumber = dojo.byId("question_No").value;
    var topicId;
    topicId = dojo.byId("topic_Id").value;
    var templateId;
    templateId = dojo.byId("template_Id").value;
    
    dojo.io.bind({
        url: "AnswerChecking",
        load: reportAnswer,
        content: {
            Answer: UserAnswer,
            QuestionNo: questionNumber,
            cid: topicId,
            tid: templateId}
    });
    //alert(Answer);
    
    //}
}
//////////down here is the check answer for DDL
function CheckAnswer1(){
    var UserAnswer;
    UserAnswer = dojo.byId("QueryString").value;
    var questionNumber;
    questionNumber = dojo.byId("question_No").value;
    var topicId;
    topicId = dojo.byId("topic_Id").value;
    var templateId;
    templateId = dojo.byId("template_Id").value;
    
    //location.reload();
    dojo.io.bind({
        url: "AnswerChecking1",
        //url: "AC",
        load: reportAnswer,
        content: {
            Answer: UserAnswer,
            QuestionNo: questionNumber,
            cid: topicId,
            tid: templateId}
    });
    //alert(UserAnswer);
}
//////////up here is the check answer for DDL
function reportAnswer(type, data, evt) {
    //alert(data);
    //var result = data;
    //alert(location.href);
    var QuizGuideNav = dojo.byId("QuizGuideNav").value;
    //alert(QuizGuideNav);
    var StudentAnswer = dojo.byId("QueryString").value;
    //alert(StudentAnswer);
    var app = "23";
    //var qNumber;
    //qNumber = dojo.byId("question_No").value;
    //var act = "test" + qNumber;
    var act = dojo.byId("activity_Id").value;
    var sub = dojo.byId("subactivity_Id").value;
    var usr = dojo.byId("user_Id").value;
    var grp = dojo.byId("group_Id").value;
    var sid = dojo.byId("session_Id").value;
    var res;
    if (data == "1") {
        res = "1";
    } else {
        res = "0";
    }
    var svc = dojo.byId("svc_information").value;
    var cid = dojo.byId("topic_Id").value;
    var tid = dojo.byId("template_Id").value;
    var qid = dojo.byId("question_No").value;
    
    if (parent.vis) {
        parent.vis.actDone(parseInt(res));
    }
    
    /*
    alert("app: " + app);
    alert("act: " + act);
    alert("usr: " + usr);
    alert("grp: " + grp);
    alert("sid: " + sid);
    alert("res: " + res);
    alert("svc: " + svc);
    */
    dojo.io.bind( {
        //url: "http://adapt2.sis.pitt.edu/cbum/um",
        url: "ReportToUM",
        //load: function (type, data, evt) {},//alert(data)},
        load: function(type, data, event) { 
                    
                    if (res == "1") {
                        window.location = location.href.replace(/&qid=[0-9]+/g, "").replace(/&srt=[01]/g, "").replace(/&sar=.*&/g, "&").replace(/&sar=.*/g, "").replace(/&exm=.*&/g, "&").replace(/&exm=.*/g, "") + "&qid=" + qid + "&srt=1&sar=" + StudentAnswer;
                    }
                    else {
                        if (res == "0") {
                            window.location = location.href.replace(/&qid=[0-9]+/g, "").replace(/&srt=[01]/g, "").replace(/&sar=.*&/g, "&").replace(/&sar=.*/g, "").replace(/&exm=.*&/g, "&").replace(/&exm=.*/g, "") + "&qid=" + qid + "&srt=0&sar=" + StudentAnswer;
                        } else {
                            window.location = location.href.replace(/&qid=[0-9]+/g, "").replace(/&srt=[01]/g, "").replace(/&sar=.*&/g, "&").replace(/&sar=.*/g, "").replace(/&exm=.*&/g, "&").replace(/&exm=.*/g, "") + "&qid=" + qid + "&srt=0&sar=" + StudentAnswer + "&exm=" + data;
                        }
                    }
            },
        mimetype: "text/json",
   	    error: function(type, data, event) { 
   	        /* type will be "error", data will be response data and event will be onscriptload event */ 
   	              //alert('error ');
   	            //console.log(JSON.stringify(event));
   	            //console.dir(event);
   	                
   	            },
        content: {
            app: app,
            act: act,
            sub: sub,
            usr: usr,
            grp: grp,
            sid: sid,
            res: res,
            svc: svc,
            cid: cid,
            tid: tid,
            qid: qid
        }
    });
    //alert(data);
    //location.reload();
    //if (confirm("ready?")) {
    //    alert("good");
    //}
    
//    if (data == "1") {
//        window.location = location.href.replace(/&qid=[0-9]+/g, "").replace(/&srt=[01]/g, "").replace(/&sar=.*&/g, "&").replace(/&sar=.*/g, "").replace(/&exm=.*&/g, "&").replace(/&exm=.*/g, "") + "&qid=" + qid + "&srt=1&sar=" + StudentAnswer;
//    }
//    else {
//        if (data == "0") {
//            window.location = location.href.replace(/&qid=[0-9]+/g, "").replace(/&srt=[01]/g, "").replace(/&sar=.*&/g, "&").replace(/&sar=.*/g, "").replace(/&exm=.*&/g, "&").replace(/&exm=.*/g, "") + "&qid=" + qid + "&srt=0&sar=" + StudentAnswer;
//        } else {
//            window.location = location.href.replace(/&qid=[0-9]+/g, "").replace(/&srt=[01]/g, "").replace(/&sar=.*&/g, "&").replace(/&sar=.*/g, "").replace(/&exm=.*&/g, "&").replace(/&exm=.*/g, "") + "&qid=" + qid + "&srt=0&sar=" + StudentAnswer + "&exm=" + data;
//        }
//    }
   
    
}

//redirect to a similar question
function redi() {
    
    var UserAnswer;
    UserAnswer = dojo.byId("QueryString").value;
    
    re = /&qid=[0-9]+/g;
    var dest = location.href.replace(re, "");
    //alert(dest);
    var qid = dojo.byId("question_No").value;
    var loca = dest + "&qid=" + qid;
    //alert(loca);
    location.replace(loca);
    alert(UserAnswer.substring(0, 23));
    if (UserAnswer.substring(0, 23) == "Enter your answer here.") {
        dojo.byId("QueryString").value = UserAnswer;
    }
}








//Here is the getSchema
function getSchema(tableName) {
    element = dojo.byId(tableName);
    var ShowToClear = "show" + tableName;
    ele = dojo.byId(ShowToClear);
    
    if (ele.innerHTML == "(+)"){
        
        var questionNo;
        questionNo = dojo.byId("question_No").value;
        dojo.io.bind( {
            url: "GetSchema",
            //load: putSchema,
            load: function (type, data, evt) {
                element.innerHTML = data;
            },
            content: {
                tableName: tableName,
                questionNo: questionNo}
        });
        
        ele.innerHTML = "(-)";
        
    }
    //function putSchema(type, data, evt){
    //    element.innerHTML = data;
    //} 
    else {
        //alert("1231313");
        element.innerHTML = "";
        ele.innerHTML = "(+)";
    }
    
}










//This is the ShowData
function showData(tName) {
    //var doom;
    //doom = this.value;
    var ShowToClear = "show" + tName;
    ele = dojo.byId(ShowToClear);
    element = dojo.byId(tName);
    var father = dojo.byId("DataShow");
    
    //if (ele.innerHTML == "(+)" && element.innerHTML == ""){
    if (ele.innerHTML == "(+)"){    
        dojo.io.bind( {
            url: "ShowDB",
            load: function (type, data, evt){
                var elem = document.createElement("a");
                elem.id = tName;
                //elem.className = "drag";
                elem.innerHTML = data;
                //var father = dojo.byId("DataShow");
                var son = father.firstChild;
                father.insertBefore(elem, son);
            },
            content: {tableName: tName}
        });
        ele.innerHTML = "(-)";
    }
    else {
        //if (ele.innerHTML == "(-)") {
        //element = dojo.byId(tName);
        //element.innerHTML = "";
       /* var sdf = father.firstChild;
        for (i=0;i<father.length;i++)
{
    if (sdf.getAttribute("id") != tName) {
            sdf = sdf.nextSibling;
        }
}*/
        father.removeChild(element);
        ele.innerHTML = "(+)";
        //element = dojo.byId(doom);
        //element.innerHTML = doom ;
        //}
    }
}









//here is the questions tester
function listQ() {
    var QtopicID = document.forms["GetQuestionTaskForm"].TopicID.value;
    var QtemplateID = document.forms["GetQuestionTaskForm"].TemplateID.value;
    //alert(QtopicID + QtemplateID);
    dojo.io.bind( {
        url: "GetQuestionTask",
        load: showQ,
        content: {TopicID: QtopicID,
        TemplateID: QtemplateID}
    });
    function showQ(type, data, evt){
        var element = dojo.byId("qqhere");
        element.innerHTML = data;
    }
}


//here is the report the SQL-Lab result to UM
function reportLab(tsub, tres, tsvc) {
    var app = "23";
    var act = "sqllab";
    var sub = tsub;
    var usr = dojo.byId("user_Id").value;
    var grp = dojo.byId("group_Id").value;
    var sid = dojo.byId("session_Id").value;
    var res = tres;
    var svc = tsvc;
    var cid = dojo.byId("topic_Id").value;
    var tid = dojo.byId("template_Id").value;
    var qid = dojo.byId("question_No").value;
    //alert(app + act + sub + usr + grp + sid + res + svc + cid + tid + qid);
    dojo.io.bind( {
        //url: "http://adapt2.sis.pitt.edu/cbum/um",
        url: "ReportToUM",
        //load: function (type, data, evt) {},//alert(data)},
        load: doNothing,
        mimetype: "text/json", 
        content: {
            app: app,
            act: act,
            sub: sub,
            usr: usr,
            grp: grp,
            sid: sid,
            res: res,
            svc: svc
            //cid: cid,
            //tid: tid,
            //qid: qid
        }
    });
}










///////////////////////////////////I am spliter/////////////////////////////////////







//here is the test of adding template token element
function addst() {
    var adding = document.forms["st"].st1;
    var cont = document.forms["st"].sts.value;
    var ll = adding.options.length;
    adding.options[ll] = new Option(cont, cont);
    document.forms["st"].sts.value = "";
}
function delst() {
    var deling = document.forms["st"].st1;
    deling.options[deling.selectedIndex] = null;
}

function addTo() {
    var box = document.forms["testform"].test1;
    var box2 = document.forms["testform"].test2;
    var tttt = box.options[box.selectedIndex].text;
    var vvvv = box.options[box.selectedIndex].value;
    var lele = box2.options.length;
    //alert(lele);
    //alert(tttt);
    //alert(vvvv);
    box2.options[lele] = new Option(tttt, vvvv);
    box.options[box.selectedIndex] = null;
}
function delFrom() {
    var box = document.forms["testform"].test2;
    var box2 = document.forms["testform"].test1;
    var tttt = box.options[box.selectedIndex].text;
    var vvvv = box.options[box.selectedIndex].value;
    var lele = box2.options.length;
    //alert(lele);
    //alert(tttt);
    //alert(vvvv);
    box2.options[lele] = new Option(tttt, vvvv);
    box.options[box.selectedIndex] = null;
}
function modify() {
    var tttt = dojo.byId("ee").value;
    //alert(tttt);
    dojo.byId("eee").innerHTML = tttt;
}
function putClause() {
    var Clause = document.forms["MakeTemplate"].clauses;
    var QueryTemplate = document.forms["MakeTemplate"].QueryTemplate;
    var clauseText = Clause.options[Clause.selectedIndex].value;
    QueryTemplate.value = QueryTemplate.value + clauseText;
    QueryTemplate.focus();
}
function putToken() {
    var Token = document.forms["MakeTemplate"].tokens;
    var QueryTemplate = document.forms["MakeTemplate"].QueryTemplate;
    var tokenText = Token.options[Token.selectedIndex].value;
    QueryTemplate.value = QueryTemplate.value + tokenText;
    QueryTemplate.focus();
    var label = tokenText.substring(3);
    var succlabel = parseInt(label) + 1;
    Token.options[Token.selectedIndex].value = tokenText.substring(0,3) + succlabel + ">";
}


function clearTextArea(tt, pp) {
    var asdf = dojo.byId("QueryString");
    
    if (asdf.value == tt && pp == "clear") {
        asdf.value = "";
    } else {
        if (asdf.value == "" && pp == "reset") {
            asdf.value = tt;
        }
    }
}














function patternmatch(){
    var pat = dojo.byId("pat").value;
    var mat = dojo.byId("mat").value;
    var pm = dojo.byId("pm");
    dojo.io.bind( {
        url: "AC",
        load: function (type, data, evt){
            if (data == "1"){
                pm.innerHTML = "<font size=\"5\" color=\"green\">Matched!!!</font>";
            } else {
                pm.innerHTML = "<font size=\"5\" color=\"red\">Unmatched?</font>";
            }
        },
        content: {cid: 1, tid: 1, QuestionNo: 1,
        PAT: pat,
        MAT: mat}
    });
}