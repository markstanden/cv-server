<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->
<#-- @ftlvariable name="experience" type="dev.markstanden.models.ExperienceSection" -->
<#-- @ftlvariable name="sections" type="kotlin.collections.List<dev.markstanden.models.Section>" -->

<#import "_layout.ftl" as layout>
<#import "_section.ftl" as section>

<@layout.general "Form" "form.css">
    <@section.title "Form">
        <form name="cv" action="/upload" method="post" enctype="multipart/form-data">
            <input type="file" id="file" name="file">
            <button type="submit">Submit</button>
        </form>
    </@section.title>
</@layout.general>