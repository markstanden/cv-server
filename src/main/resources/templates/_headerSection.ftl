<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->

<#macro titleBlock user>
    <header class="headerBlock">
        <section class="personal">
            <h1 class="applicantName">${user.name}</h1>
            <address class="applicantLocation">
                <ul>
                    <li>${user.location.city}, ${user.location.country}</li>
                    <li>${user.contact.email}</li>
                    <li>${user.contact.phone}</li>
                </ul>
            </address>
        </section>
        <nav style="display: flex; flex-direction: column">
            <ul class="links">
                <#list user.links as link>
                    <li>${link.title}: <a href="${link.url}">${link.url}</a></li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>