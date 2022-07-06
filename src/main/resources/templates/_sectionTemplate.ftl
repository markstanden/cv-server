<#macro withTitle title>
    <section class="section-wrapper">
        <h2 class="section-header">${title}</h2>
        <#nested/>
    </section>
</#macro>

<#macro content item>
    <li class="item vertically-spaced">
        <h3 class="item-title">
            <#if item.link != "">
                <a href="${item.link}">${item.title}</a>
            <#else>
                ${item.title}
            </#if>
        </h3>
        <p class="item-content">
            <#list item.content as points><span class="item-point">${points}</span><#sep>, </#sep></#list>.
        </p>
        <#if item.dates != "">
            <p class="dates"><strong>${item.dates}</strong></p>
        </#if>
    </li>
</#macro>