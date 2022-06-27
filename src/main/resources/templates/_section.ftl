<#macro title title>
    <section>
        <h2 class="section-header">${title}</h2>
        <#nested/>
    </section>
</#macro>

<#macro item item>
    <li class="item">
        <h4 class="item-title">${item.title}</h4>
        <p class="item-content">
            <#list item.content as bullets>${bullets}<#sep>, </#sep></#list>.
        </p>
        <#if item.dates != "">
            <p class="dates"><strong>${item.dates}</strong></p>
        </#if>

    </li>
</#macro>