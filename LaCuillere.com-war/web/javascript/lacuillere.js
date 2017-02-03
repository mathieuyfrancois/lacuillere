/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function hideElement(elementId){
    var element = document.getElementById(elementId);
    element.disable = true;
    element.style.display = 'none';
}

function showElement(elementId){
    var element = document.getElementById(elementId);
    element.disable = false;
    element.style.display = 'inline';
}

function resetForm(elementId){
    var element = document.getElementById(elementId);
    element.reset();
}

function clickCreerCompte(){
    hideElement('keyAndCreate');
    hideElement('connexionFieldset');
    resetForm("connexionForm");
    showElement('inscriptionFieldset');
}

function clickSeConnecter(){
    hideElement('inscriptionFieldset');
    resetForm('inscriptionForm');
    showElement('connexionFieldset');
    showElement('keyAndCreate');  
}

function clickAjoutRestaurant(){
    resetForm('ajouterRestaurantForm');
    showElement('ajouterRestaurantFieldset');
}