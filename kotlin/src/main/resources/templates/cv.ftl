<#-- @ftlvariable name="user" type="dev.markstanden.models.User" -->
<#-- @ftlvariable name="sections" type="kotlin.collections.List<dev.markstanden.models.Section>" -->

<#import  "_layout.ftl" as layout>
<#import  "_sectionTitle.ftl" as sectionTitle>


<@layout.general pageTitle="${user}" cssFile="cv.css">

    <@languageCards.cards languages/>

    <@divider.withButton>
        I'd love to hear more
    </@divider.withButton>

    <@attributeCards.cards attributes/>

    <@divider.withButton>
        Contact me
    </@divider.withButton>

    <@projectCards.cards projects/>

    <@divider.withButton>
        Contact me
    </@divider.withButton>

</@layout.general>