/*
 * @author Kyle Crossman
 * @file createBoard.js
 */


$(document).ready(function () {
    showNextTab('formProperty', 'propertyTab');
     
    initializeElements();
});

function showNextTab(nextForm, nextTab) {
    $('#forms').find("div[id^='form']").each(function (index, element) {
       $(this).hide();
    });
    
    $('#spans').find("span").each(function (index, element) {
        $(this).addClass("notSelectedTab");
        $(this).removeClass("selectedTab");
    });
    
    $('#' + nextForm).show();
    $('#' + nextTab).removeClass("notSelectedTab");
    $('#' + nextTab).addClass("selectedTab");
}

function initializeElements() {
    $('#propertyNextButton').click(function () {
        showNextTab('formChance', 'chanceTab');
    });
    $('#chanceNextButton').click(function () {
        showNextTab('formCommunityChest', 'communityChestTab');
    });
    $('#communityChestNextButton').click(function () {
        showNextTab('formImage', 'imageTab');
    });
    $('#imageNextButton').click(function () {
        showNextTab('formProperty', 'propertyTab');
    });
}