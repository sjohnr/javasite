<jsp:useBean id="params" type="web.lib.container.Container" scope="request"/>
<div class="sfTMessageContainer sfTMessage">
  <img alt="module created" class="sfTMessageIcon" width="48" height="48" src="${params.parameters.context}/images/icons/ok48.png" />
  <div class="sfTMessageWrap">
      <h1>Module <code>${params.parameters.module}</code> created</h1>
    <h5>Congratulations! You have successfully created a module.</h5>
  </div>
</div>
<dl class="sfTMessageInfo">
  <dt>This is a temporary page</dt>
  <dd>This page is part of the <code>default</code> module. It will disappear as soon as you override the <code>index</code> action in the new module.</dd>

  <dt>What's next</dt>
  <dd>
    <ul class="sfTIconList">
      <li class="sfTDirectoryMessage">Browse to the <code>web.app.action</code> package</li>
      <li class="sfTEditMessage">In the <code>${params.parameters.module}</code> action, edit the <code>executeIndex()</code> method and remove the final <code>forward</code></li>
      <li class="sfTColorMessage">Customize the <code>templates/${params.parameters.module}/indexSuccess.jsp</code> template</li>
    </ul>
  </dd>
</dl>