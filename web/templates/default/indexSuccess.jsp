<jsp:useBean id="params" type="web.lib.container.Container" scope="request"/>
<div class="sfTMessageContainer sfTMessage"> 
  <img alt="OK" class="sfTMessageIcon" width="48" height="48" src="${params.parameters.context}/images/icons/ok48.png" />
  <div class="sfTMessageWrap">
    <h1>Project Created</h1>
    <h5>Congratulations! You have successfully created your <code>Java EE</code> project.</h5>
  </div>
</div>
<dl class="sfTMessageInfo">
  <dt>Project setup successful</dt>
  <dd>This project is a concept deployment of a basic MVC framework on the <code>Java EE</code> platform.</dd>

  <dt>This is a temporary page</dt>
  <dd>This page is part of the <code>default</code> module. It will disappear as soon as you modify the <code>DEFAULT_MODULE</code> and <code>DEFAULT_ACTION</code> constants in <code>web.lib.filter.RequestFilter</code>.</dd>

  <dt>What's next</dt>
  <dd>
    <ul class="sfTIconList">
        <li class="sfTDatabaseMessage">Create a backend application using <code>JavaBeans</code> and <code>EJB 3.0</code> technology</li>
        <li class="sfTColorMessage">Create and customize your frontend using <code>actions</code> and <code>jsp</code> templates</li>
        <li class="sfTLinkMessage"><a href="http://java.sun.com/javaee/5/docs/tutorial/doc/">Learn more from the online documentation</a></li>
    </ul>
  </dd>
</dl>