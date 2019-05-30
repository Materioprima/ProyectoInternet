/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$( document ).ready(function() {
    var animasioLogo= anime({
        targets: '.lema',
        translateY: [-250,0],
        duration: 1500
    });
    
    var animasioTop= anime({
        targets: '#top',
        translatex: [-400,0],
        duration: 1800
    });
    
    function iniciar(){
        animasioLogo.play();
    }
    iniciar();
    
});

