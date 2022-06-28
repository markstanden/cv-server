<#-- @ftlvariable name="position" type="dev.markstanden.models.Position" -->

<#macro article position>
    <article>
        <span class="position-header">
            <div class="position-title">
                <h3>${position.title}</h3>
                <h4>${position.business.title}</h4>
            </div>
            <p class="dates"><strong>${position.dates}</strong></p>
        </span>
        <ul class="with-bar">
            <#list position.content as bulletPoint>
                <li class="bulleted-content">${bulletPoint}</li>
            </#list>
        </ul>
    </article>
</#macro>