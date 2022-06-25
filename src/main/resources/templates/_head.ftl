<#macro main pageTitle cssFile>
    <head>
        <meta charset="UTF-8"/>
        <meta
                content="width=device-width, initial-scale=1.0"
                name="viewport"
        />
        <title>${pageTitle}</title>
        <link href="/styles/global.css" rel="stylesheet"/>
        <link href="/styles/${cssFile}" rel="stylesheet"/>
    </head>
</#macro>