<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->

<#macro titleBlock user>
    <header>
        <address>
            <p>${user.location}</p>
            <p>${user.contact}</p>
        </address>
        <h1>${user.name}</h1>
        <nav>
            <p>${user.contact}</p>
        </nav>
        <blockquote>
            ${user.summary}
        </blockquote>
    </header>
</#macro>