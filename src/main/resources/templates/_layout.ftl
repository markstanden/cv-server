<#import "_head.ftl" as head>
<#import "_headerSection.ftl" as headerSection>

<#macro general pageTitle cssFile>
    <!DOCTYPE html>
    <html lang="en">

    <@head.main pageTitle "${cssFile}"/>

    <body class="main-wrapper">
    <#nested>
    </body>
    </html>
</#macro>