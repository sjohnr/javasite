<jsp:useBean id="params" type="web.lib.container.Container" scope="request"/>
<div class="sfTMessageContainer sfTAlert"> 
  <img alt="module disabled" class="sfTMessageIcon" width="48" height="48" src="${params.parameters.context}/images/icons/disabled48.png" />
  <div class="sfTMessageWrap">
    <h1>This Module is Unavailable</h1>
    <h5>This module has been disabled by a site administrator.</h5>
  </div>
</div>
<dl class="sfTMessageInfo">

  <dt>What's next</dt>
  <dd>
    <ul class="sfTIconList">
      <li class="sfTLinkMessage"><a href="javascript:history.go(-1)">Back to previous page</a></li>
        <li class="sfTLinkMessage"><a href="${params.parameters.context}/index.jsp">Go to Homepage</a></li>
    </ul>
  </dd>
</dl>