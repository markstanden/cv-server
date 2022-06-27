<#-- @ftlvariable name="position" type="dev.markstanden.models.Position" -->

<#macro article position>
    <article>
        <span class="position-header">
            <h3 class="position-title">${position.title}</h3>
            <h4 class="position-company">${position.business.title}</h4>
            <p class="position-dates"><strong>${position.dates}</strong></p>
        </span>
        <ul>
            <#list position.content as bulletPoint>
                <li class="bulleted-content">${bulletPoint}</li>
            </#list>
        </ul>
    </article>
</#macro>