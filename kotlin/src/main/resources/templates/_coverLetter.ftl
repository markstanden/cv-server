<#-- @ftlvariable name="coverLetter" type="dev.markstanden.models.CoverLetter" -->
<#-- @ftlvariable name="user" type="dev.markstanden.models.CV.User" -->

<#macro showLetter coverLetter user>
    <article class="section-wrapper">
        <div class="addressBlock">
            <address class="cover-sender">
                <p>${user.name},</p>
                <p>${user.location.city},</p>
                <p>${user.location.country}.</p>
                <p>
                    <a href="mailto:${user.contact.email}?subject=CV Review">${user.contact.email}</a>
                </p>
                <p>
                    <a href="tel:${user.contact.phone}">${user.contact.phone}</a>
                </p>
            </address>
        </div>
        <p>${coverLetter.greeting}</p>
        <div class="cover-text">
            <#list coverLetter.text as paragraph>
                <p>
                    ${paragraph}
                </p>
            </#list>
        </div>
        <p>${coverLetter.signOff}</p>
        <p class="signature">${user.name}</p>
    </article>
</#macro>