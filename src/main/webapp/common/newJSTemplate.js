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
    var dest = data.replace("sqlknot_question.", "");
    element = dojo.byId("QueryResult");
    element.innerHTML = dest;
    if (dest.indexOf("Problem") == -1) {
        reportToLab("query", "1", qqss);
    } else {
        reportToLab("query", "0", qqss);
    }
}

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

function CheckAnswer(){
    
    var UserAnswer;
    UserAnswer = dojo.byId("QueryString").value;
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
}
function reportAnswer(type, data, evt) {
    var QuizGuideNav = dojo.byId("QuizGuideNav").value;
    var StudentAnswer = dojo.byId("QueryString").value;
    var app = "23";
    var act = dojo.byId("activity_Id").value;
    var sub = dojo.byId("subactivity_Id").value;
    var usr = dojo.byId("user_Id").value;
    var grp = dojo.byId("group_Id").value;
    var sid = dojo.byId("session_Id").value;
    var res = data;
    var svc = dojo.byId("svc_information").value;
    var cid = dojo.byId("topic_Id").value;
    var tid = dojo.byId("template_Id").value;
    var qid = dojo.byId("question_No").value;
    dojo.io.bind( {
        url: "ReportToUM",
        load: function (type, data, evt) {alert(data)},//alert(data)},
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
    
    if (data == "1") {
        window.location = location.href.replace(/&qid=[0-9]+/g, "").replace(/&srt=[01]/g, "").replace(/&sar=.*&/g, "&").replace(/&sar=.*/g, "") + "&qid=" + qid + "&srt=1&sar=" + StudentAnswer;
    }
    else {
        window.location = location.href.replace(/&qid=[0-9]+/g, "").replace(/&srt=[01]/g, "").replace(/&sar=.*&/g, "&").replace(/&sar=.*/g, "") + "&qid=" + qid + "&srt=0&sar=" + StudentAnswer;
    }
    
}
function getSchema(tableName) {
    element = dojo.byId(tableName);
    var ShowToClear = "show" + tableName;
    ele = dojo.byId(ShowToClear);
    
    if (ele.innerHTML == "(+)"){
        
        var questionNo;
        questionNo = dojo.byId("question_No").value;
        dojo.io.bind( {
            url: "GetSchema",
            load: function (type, data, evt) {
                element.innerHTML = data;
            },
            content: {
                tableName: tableName,
                questionNo: questionNo}
        });
        
        ele.innerHTML = "(-)";
        
    }
    else {
        element.innerHTML = "";
        ele.innerHTML = "(+)";
    }
    
}

function showData(tName) {
    var ShowToClear = "show" + tName;
    ele = dojo.byId(ShowToClear);
    element = dojo.byId(tName);
    var father = dojo.byId("DataShow");
    if (ele.innerHTML == "(+)"){    
        dojo.io.bind( {
            url: "ShowDB",
            load: function (type, data, evt){
                var elem = document.createElement("a");
                elem.id = tName;
                elem.innerHTML = data;
                var son = father.firstChild;
                father.insertBefore(elem, son);
            },
            content: {tableName: tName}
        });
        ele.innerHTML = "(-)";
    }
    else {
        father.removeChild(element);
        ele.innerHTML = "(+)";
    }
}

function listQ() {
    var QtopicID = document.forms["GetQuestionTaskForm"].TopicID.value;
    var QtemplateID = document.forms["GetQuestionTaskForm"].TemplateID.value;
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

function reportToLab(tsub, tres, tsvc) {
    var app = "23";
    var act = "sqllab";
    var sub = tsub;
    var usr = document.getElementById("user_Id").value;
    var grp = document.getElementById("group_Id").value;
    var sid = document.getElementById("session_Id").value;
    var res = tres;
    var svc = tsvc;
    dojo.io.bind( {
        url: "ReportToUM",
        load: function (type, data, evt){},//alert(data)
        content: {
            app: app,
            act: act,
            sub: sub,
            usr: usr,
            grp: grp,
            sid: sid,
            res: res,
            svc: svc
        }
    });
}