<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:useBean id="params" type="web.lib.container.Container" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${params.parameters.title}</title>
    <link href="${params.parameters.context}/css/screen.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 7.]>
    <link href="${params.parameters.context}/css/ie.css" rel="stylesheet" type="text/css" media="screen" />
    <![endif]-->
</head>
<body>
    <div class="sfTContainer">
        <img alt="Symfony PHP5 Framework" width="186" height="39" class="sfTLogo" src="${params.parameters.context}/images/sfTLogo.png" />
        
        ${params.parameters.content}
        
    </div>
</body>
</html>