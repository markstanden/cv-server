<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->
<#-- @ftlvariable name="experience" type="dev.markstanden.models.ExperienceSection" -->
<#-- @ftlvariable name="sections" type="kotlin.collections.List<dev.markstanden.models.Section>" -->

<#import  "_layout.ftl" as layout>
<#import  "_section.ftl" as sectionWrapper>
<#import  "_workExperience.ftl" as workExp>


<@layout.general user "cv.css">
    <@sectionWrapper.title "Summary">
        <blockquote>
            ${user.summary}
        </blockquote>
    </@sectionWrapper.title>

    <@sectionWrapper.title experience.title>
        <#list experience.items as position>
            <@workExp.article position/>
        </#list>
    </@sectionWrapper.title>

    <#list sections as section>
        <@sectionWrapper.title section.title>
            <#list section.items as subSection>
                <p>${subSection}</p>
            </#list>
        </@sectionWrapper.title>
    </#list>

</@layout.general>