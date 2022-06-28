<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->

<#macro titleBlock user>
    <header class="headerBlock">
        <section class="personal">
            <h1 class="applicantName">${user.name}</h1>
            <address class="applicantLocation">
                <ul>
                    <li><em>Based: </em> <strong>${user.location.city}, ${user.location.country}</strong></li>
                    <li><em>Email: </em> ${user.contact.email}</li>
                    <li><em>Tel: </em> ${user.contact.phone}</li>
                </ul>
            </address>
        </section>
        <nav style="display: flex; flex-direction: column">
            <ul class="links">
                <#list user.links as link>
                    <li class="link-item"><span class="link-desc">${link.title}: </span><a
                                href="${link.url}">${link.url}</a></li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>