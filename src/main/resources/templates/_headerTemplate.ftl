<#-- @ftlvariable name="user" type="dev.markstanden.models.CV.User" -->

<#macro titleBlock user>
    <header class="headerBlock">
        <section class="personal">
            <h1 class="applicantName">${user.name}</h1>
            <address class="applicantLocation">
                <ul>
                    <li class="flex">
                        <em class="info_title">Based: </em>
                        <strong>${user.location.city}, ${user.location.country}</strong>
                    </li>
                    <li class="flex">
                        <em class="info_title">Email: </em>
                        <a href="mailto:${user.contact.email}?subject=CV Review">${user.contact.email}</a>
                    </li>
                    <li class="flex">
                        <em class="info_title">Tel: </em>
                        <a href="tel:${user.contact.phone}">${user.contact.phone}</a>
                    </li>
                </ul>
            </address>
        </section>
        <nav style="display: flex; flex-direction: column">
            <ul class="links">
                <#list user.links as link>
                    <li class="link-item">
                        <em class="info_title">
                            <span class="link-desc">${link.title}: </span>
                        </em>
                        <a href="${link.url}">${link.url}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>