<jsp:useBean id="params" type="web.lib.container.Container" scope="request"/>
<div class="sfTMessageContainer sfTLock"> 
  <img alt="login required" class="sfTMessageIcon" width="48" height="48" src="${params.parameters.context}/images/icons/lock48.png" />
  <div class="sfTMessageWrap">
    <h1>Login Required</h1>
    <h5>This page is not public.</h5>
  </div>
</div>
<dl class="sfTMessageInfo">
  <dt>How to access this page</dt>
  <dd>You must proceed to the login page and enter your id and password.</dd>

  <dt>What's Next</dt>
  <dd>
    <ul class="sfTIconList">
        <li class="sfTLinkMessage"><a href="${params.parameters.context}/index.jsp/security/login">Proceed to login</a></li>
      <li class="sfTLinkMessage"><a href="javascript:history.go(-1)">Back to previous page</a></li>
    </ul>
  </dd>
</dl>