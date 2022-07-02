<#-- @ftlvariable name="position" type="dev.markstanden.models.Cv.Position" -->

<#macro article position>
    <article>
        <div class="position-header">
            <div class="position-title">
                <h3>${position.title}</h3>
                <h4>${position.business.title}</h4>
            </div>
            <p class="dates"><strong>${position.dates}</strong></p>
        </div>
        <ul class="with-bar">
            <#list position.content as bulletPoint>
                <li class="bulleted-content">${bulletPoint}</li>
            </#list>
        </ul>
    </article>
</#macro>