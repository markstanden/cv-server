<#import "_layout.ftl" as layout>
<#import "_section.ftl" as section>

<@layout.general "Form" "form.css">
    <@section.title "Form">
        <form name="cv" method="post" action="/submit" enctype="application/x-www-form-urlencoded">
            <label for="data">Add CV JSON Here:</label>
            <textarea id="data" name="data" style="width:50rem; height:50rem;"></textarea>
            <button type="submit">Submit</button>
        </form>
    </@section.title>
</@layout.general>