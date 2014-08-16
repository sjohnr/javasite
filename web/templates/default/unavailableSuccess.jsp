<jsp:useBean id="params" type="web.lib.container.Container" scope="request"/>
<div class="sfTMessageContainer sfTAlert"> 
  <img alt="website unavailable" class="sfTMessageIcon" width="48" height="48" src="${params.parameters.context}/images/icons/tools48.png" />
  <div class="sfTMessageWrap">
    <h1>Website Currently Unavailable</h1>
    <h5>This website has been temporarily disabled. Please try again later.</h5>
  </div>
</div>