<#-- @ftlvariable name="position" type="dev.markstanden.models.Position" -->

<#macro article position>
    <article>
        <h3>${position.title}</h3>
        <h4>${position.business.title}</h4>
        <p><strong>${position.dates}</strong></p>
        <ul>
            <#list position.content as bulletPoint>
                <li class="bulleted-content">${bulletPoint}</li>
            </#list>
        </ul>
    </article>
</#macro>