/**
 * 
 */

 // Email Validity on Signin and Client profile pages
window.onload = function () {
    const email = document.getElementById("username");

    email.addEventListener("blur", (event) => {
        if (email.validity.typeMismatch) {
            email.setCustomValidity("Please enter a valid email address.");
        } else {
            email.setCustomValidity("");
        }
        email.reportValidity();
    });

    // Password validity on Signin page
    let myInput = document.getElementById("password");
    let letter = document.getElementById("letter");
    let capital = document.getElementById("capital");
    let number = document.getElementById("number");
    let length = document.getElementById("length");

    // When the user clicks on the password field, show the message box
    myInput.onfocus = function () {
        document.getElementById("password_error").style.display = "block";
    }

    // When the user clicks outside of the password field, hide the message box
    myInput.onblur = function () {
        document.getElementById("password_error").style.display = "none";
    }

    // When the user starts to type something inside the password field
    myInput.onkeyup = function () {
        // Validate lowercase letters
        let lowerCaseLetters = /[a-z]/g;
        if (myInput.value.match(lowerCaseLetters)) {
            letter.classList.remove("invalid_password");
            letter.classList.add("valid_password");
        } else {
            letter.classList.remove("valid_password");
            letter.classList.add("invalid_password");
        }

        // Validate capital letters
        let upperCaseLetters = /[A-Z]/g;
        if (myInput.value.match(upperCaseLetters)) {
            capital.classList.remove("invalid_password");
            capital.classList.add("valid_password");
        } else {
            capital.classList.remove("valid_password");
            capital.classList.add("invalid_password");
        }

        // Validate numbers
        let numbers = /[0-9]/g;
        if (myInput.value.match(numbers)) {
            number.classList.remove("invalid_password");
            number.classList.add("valid_password");
        } else {
            number.classList.remove("valid_password");
            number.classList.add("invalid_password");
        }

        // Validate length
        if (myInput.value.length >= 8 && myInput.value.length <= 12) {
            length.classList.remove("invalid_password");
            length.classList.add("valid_password");
        } else {
            length.classList.remove("valid_password");
            length.classList.add("invalid_password");
        }
    }

}
