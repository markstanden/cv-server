<#-- @ftlvariable name="position" type="dev.markstanden.models.CV.Position" -->

<#macro article position>
    <article>
        <div class="position-header">
            <div class="position-title">
                <h3><strong>${position.title}</strong></h3>
                <h4 class="position-company"><em>${position.business.title}, ${position.business.location.city}</em>
                </h4>
            </div>
            <p class="dates"><strong>${position.dates}</strong></p>
        </div>
        <ul class="position-bullets">
            <#list position.content as bulletPoint>
                <li class="bulleted-content">${bulletPoint}</li>
            </#list>
        </ul>
    </article>
</#macro>