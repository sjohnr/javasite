<jsp:useBean id="params" type="web.lib.container.Container" scope="request"/>
<div class="sfTMessageContainer sfTLock"> 
  <img alt="credentials required" class="sfTMessageIcon" width="48" height="48" src="${params.parameters.context}/images/icons/lock48.png" />
  <div class="sfTMessageWrap">
    <h1>Credentials Required</h1>
    <h5>This page is in a restricted area.</h5>
  </div>
</div>
<dl class="sfTMessageInfo">
  <dt>You do not have the proper credentials to access this page</dt>
  <dd>Even though you are already logged in, this page requires special credentials that you currently don't have. </dd>

  <dt>How to access this page</dt>
  <dd>You must ask a site administrator to grant you some special credentials.</dd>

  <dt>What's next</dt>
  <dd>
    <ul class="sfTIconList">
      <li class="sfTLinkMessage"><a href="javascript:history.go(-1)">Back to previous page</a></li>
    </ul>
  </dd>
</dl>