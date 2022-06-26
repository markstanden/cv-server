<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->

<#macro titleBlock user>
    <header style="display: flex">
        <h1>${user.name}</h1>
        <address>
            <p>${user.location}</p>
            <p>${user.contact}</p>
        </address>
        <nav>
            <p>${user.contact}</p>
        </nav>
    </header>
</#macro>