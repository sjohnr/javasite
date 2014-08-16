<%@page contentType="application/xml"%>
<%@page pageEncoding="UTF-8"%>
<jsp:useBean id="params" type="web.lib.container.Container" scope="request"/>
${params.parameters.content}