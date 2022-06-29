<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->
<#-- @ftlvariable name="experience" type="dev.markstanden.models.ExperienceSection" -->
<#-- @ftlvariable name="sections" type="kotlin.collections.List<dev.markstanden.models.Section>" -->

<#import  "_layout.ftl" as layout>
<#import  "_headerSection.ftl" as headerSection>

<#import  "_section.ftl" as sectionWrapper>
<#import  "_workExperience.ftl" as workExp>


<@layout.general "${user.name}'s CV" "cv.css">
    <@headerSection.titleBlock user/>
    <main>
        <p class="with-bar">
            ${user.summary}
        </p>

        <@sectionWrapper.title experience.title>
            <#list experience.items as position>
                <@workExp.article position/>
            </#list>
        </@sectionWrapper.title>

        <#list sections as section>
            <@sectionWrapper.title section.title>
                <ul class="with-bar">
                    <#list section.items as subSection>
                        <@sectionWrapper.item subSection/>
                    </#list>
                </ul>
            </@sectionWrapper.title>
        </#list>
    </main>
</@layout.general>