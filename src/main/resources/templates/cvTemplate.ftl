<#-- @ftlvariable name="coverLetter" type="dev.markstanden.models.CoverLetter" -->
<#-- @ftlvariable name="user" type="dev.markstanden.models.CV.User" -->
<#-- @ftlvariable name="experience" type="dev.markstanden.models.CV.ExperienceSection" -->
<#-- @ftlvariable name="sections" type="kotlin.collections.List<dev.markstanden.models.CV.Section>" -->

<#import  "_layout.ftl" as layout>
<#import  "_headerTemplate.ftl" as headerSection>

<#import  "_coverLetter.ftl" as coverLetterTemplate>
<#import  "_sectionTemplate.ftl" as sectionTemplate>
<#import  "_workExperienceTemplate.ftl" as workExp>


<@layout.general "${user.name}'s CV" "cv.css">

<#-- Cover letter if present -->
    <@coverLetterTemplate.showLetter coverLetter=coverLetter user=user/>
    <div class="page-break"></div>

<#-- CV -->
    <@headerSection.titleBlock user/>
    <main>
        <@sectionTemplate.withTitle title=experience.title>
            <#list experience.items as position>
                <@workExp.article position=position/>
            </#list>
        </@sectionTemplate.withTitle>

        <#list sections as eachSection>
            <@sectionTemplate.withTitle eachSection.title>
                <ul class="with-bar">
                    <#list eachSection.items as subSection>
                        <@sectionTemplate.content subSection/>
                    </#list>
                </ul>
            </@sectionTemplate.withTitle>
        </#list>
    </main>
</@layout.general>