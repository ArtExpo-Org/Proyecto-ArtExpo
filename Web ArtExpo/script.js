document.addEventListener('DOMContentLoaded', function() {
    // Menú hamburguesa
    const menuToggle = document.querySelector('.menu-toggle');
    const navMenu = document.querySelector('.nav-menu');
    const navAuth = document.querySelector('.nav-auth');
    
    menuToggle.addEventListener('click', function() {
        navMenu.classList.toggle('active');
        navAuth.classList.toggle('active');
    });
});