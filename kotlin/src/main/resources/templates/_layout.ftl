<#import "_head.ftl" as head>
<#import "_headerSection.ftl" as headerSection>

<#macro general user cssFile>
    <!DOCTYPE html>
    <html lang="en">

    <@head.main "${user.name}'s CV" "${cssFile}"/>

    <body class="main-wrapper">

    <@headerSection.titleBlock user/>

    <main>

        <#nested/>

    </main>
    </body>
    </html>
</#macro>