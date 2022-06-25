<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->
<#-- @ftlvariable name="cssFile" type="String" -->

<#import "_head.ftl" as head>
<#import "_headerSection.ftl" as headerSection>

<#macro general user cssFile>
    <!DOCTYPE html>
    <html lang="en">

    <@head.main "${user.name}'s CV" cssFile/>

    <body>

    <@headerSection.titleBlock user/>

    <main class="main-wrapper">

        <#nested/>

    </main>
    </body>
    </html>
</#macro>