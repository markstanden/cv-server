<#macro main pageTitle cssFile>
    <head>
        <meta charset="UTF-8"/>
        <meta
                content="width=device-width, initial-scale=1.0"
                name="viewport"
        />
        <title>${pageTitle}</title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@200;300&display=swap" rel="stylesheet">

        <link rel="shortcut icon" href="/favicon.svg">

        <link href="/styles/${cssFile}" rel="stylesheet"/>
    </head>
</#macro>