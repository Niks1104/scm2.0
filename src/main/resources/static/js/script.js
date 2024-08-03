console.log("Script loaded");

//change theme code

let currentTheme = getTheme();

//initially call listener, when page is loaded
document.addEventListener('DOMContentLoaded', () => {
    changeTheme();
});

//TODO:
function changeTheme() {
    //set theme to webpage and text to button intially
    changePageTheme(currentTheme, currentTheme);

    //set the listener to change theme on button
    const changeThemeButton = document.getElementById("theme_change_button");

    changeThemeButton.addEventListener('click', (event) => {
        console.log("theme change button clicked");

        const oldTheme = currentTheme;

        if(currentTheme === "dark"){
            //set to light
            currentTheme = "light";
        }else{
            //set to dark
            currentTheme ="dark";
        }

        changePageTheme(currentTheme, oldTheme);
    });
}

//set theme to loacal storage (browser local storage)
function setTheme(theme){
    localStorage.setItem("theme", theme);
}   

//get theme from local storage
function getTheme(){
    let theme = localStorage.getItem("theme");
    //to check if theme is null
    return theme ? theme : "light"; //default theme
}

//change current html page theme
function changePageTheme(theme, oldTheme){

    //to update theme in local storge
    setTheme(currentTheme);

    //remove the old theme from html
    document.querySelector('html').classList.remove(oldTheme);
    //set the new theme to html
    document.querySelector('html').classList.add(theme);

    //change the text of button
    document.getElementById("theme_change_button")
    .querySelector('span').textContent = theme == "light" ? "Dark" : "Light";
}

//change theme code end