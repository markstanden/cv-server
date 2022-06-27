<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->
<#-- @ftlvariable name="experience" type="dev.markstanden.models.ExperienceSection" -->
<#-- @ftlvariable name="sections" type="kotlin.collections.List<dev.markstanden.models.Section>" -->

<#import  "_layout.ftl" as layout>
<#import  "_section.ftl" as sectionWrapper>
<#import  "_workExperience.ftl" as workExp>


<@layout.general user "cv.css">
    <@sectionWrapper.title "Summary">
        <p class="summary">
            ${user.summary}
        </p>
    </@sectionWrapper.title>

    <@sectionWrapper.title experience.title>
        <#list experience.items as position>
            <@workExp.article position/>
        </#list>
    </@sectionWrapper.title>

    <#list sections as section>
        <@sectionWrapper.title section.title>
            <ul>
            <#list section.items as subSection>
                <@sectionWrapper.item subSection/>
            </#list>
            </ul>
        </@sectionWrapper.title>
    </#list>

</@layout.general>