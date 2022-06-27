<#macro title title>
    <section>
        <h2 class="section-header">${title}</h2>
        <#nested/>
    </section>
</#macro>

<#macro item item>
    <li>
        <span class="item">
            <div class="item-title">
                <h3>${item.title}</h3>
                <h4><#list item.content as bullets><span>${bullets}<#nt>, </span></#list></h4>
            </div>
            <p class="item-dates"><strong>${item.dates}</strong></p>
        </span>
    </li>
</#macro>