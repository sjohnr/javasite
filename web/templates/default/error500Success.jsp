<jsp:useBean id="params" type="web.lib.container.Container" scope="request"/>
<div class="sfTMessageContainer sfTAlert">
  <img alt="page not found" class="sfTMessageIcon" src="${params.parameters.context}/images/icons/tools48.png" height="48" width="48" />
  <div class="sfTMessageWrap">
    <h1>Oops! An Error Occurred</h1>
    <h5>The server returned a <code>500 Internal Server Error</code> response.</h5>
  </div>
</div>

<dl class="sfTMessageInfo">
  <dt>Something is broken</dt>
  <dd>Please e-mail us at [<a href="mailto:admin@localhost">email</a>] and let us know what you were doing when this error occurred. We will fix it as soon as possible.
  Sorry for any inconvenience caused.</dd>

  <dt>What's next</dt>
  <dd>
    <ul class="sfTIconList">
      <li class="sfTLinkMessage"><a href="javascript:history.go(-1)">Back to previous page</a></li>
      <li class="sfTLinkMessage"><a href="${params.parameters.context}/index.jsp">Go to Homepage</a></li>
    </ul>
  </dd>
</dl>