<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleSOAPTicTacToeProxyid" scope="session" class="org.thaw.webservices.soaptictactoe.SOAPTicTacToeProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleSOAPTicTacToeProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleSOAPTicTacToeProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleSOAPTicTacToeProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        org.thaw.webservices.soaptictactoe.SOAPTicTacToe getSOAPTicTacToe10mtemp = sampleSOAPTicTacToeProxyid.getSOAPTicTacToe();
if(getSOAPTicTacToe10mtemp == null){
%>
<%=getSOAPTicTacToe10mtemp %>
<%
}else{
        if(getSOAPTicTacToe10mtemp!= null){
        String tempreturnp11 = getSOAPTicTacToe10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String boardDimension_1id=  request.getParameter("boardDimension16");
        int boardDimension_1idTemp  = Integer.parseInt(boardDimension_1id);
        String boardAsString_2id=  request.getParameter("boardAsString18");
            java.lang.String boardAsString_2idTemp = null;
        if(!boardAsString_2id.equals("")){
         boardAsString_2idTemp  = boardAsString_2id;
        }
        String playerAsString_3id=  request.getParameter("playerAsString20");
            java.lang.String playerAsString_3idTemp = null;
        if(!playerAsString_3id.equals("")){
         playerAsString_3idTemp  = playerAsString_3id;
        }
        String playerPly_4id=  request.getParameter("playerPly22");
        int playerPly_4idTemp  = Integer.parseInt(playerPly_4id);
        int findBestMove13mtemp = sampleSOAPTicTacToeProxyid.findBestMove(boardDimension_1idTemp,boardAsString_2idTemp,playerAsString_3idTemp,playerPly_4idTemp);
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(findBestMove13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>