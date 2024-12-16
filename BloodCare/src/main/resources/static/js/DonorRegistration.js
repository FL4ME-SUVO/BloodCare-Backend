// Select elements and form sections
const form = document.querySelector("#registrationForm"),
    nextBtn = form.querySelector(".nextBtn"),
    backBtn = form.querySelector(".backBtn"),
    allInputFirst = form.querySelectorAll(".first input, .first select"),
    allInputSecond = form.querySelectorAll(".second input, .second select");

// Function to clear error messages
function clearErrorMessages(inputs) {
    inputs.forEach(input => {
        const errorMessage = input.nextElementSibling;
        errorMessage.textContent = ''; // Clear error message
        input.classList.remove("error"); // Remove error class
    });
}

// Validation functions
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function isValidName(name) {
    const nameRegex = /^[A-Za-z\s]+$/;
    return nameRegex.test(name);
}

function isValidAadhaar(aadhaar) {
    const aadhaarRegex = /^\d{12}$/;
    return aadhaarRegex.test(aadhaar) && !isAllSameDigits(aadhaar);
}

function isValidMobile(mobile) {
    const mobileRegex = /^\d{10}$/;
    return mobileRegex.test(mobile) && mobile.charAt(0) !== '0';
}

function isValidAge(age) {
    return age >= 18 && age <= 65;
}

function isValidPinCode(pin) {
    const pinRegex = /^\d{6}$/;
    return pinRegex.test(pin);
}

function isAllSameDigits(value) {
    return value.split('').every(digit => digit === value[0]);
}

// Event listener for moving to the second form section
nextBtn.addEventListener("click", () => {
    let isValid = true;
    clearErrorMessages(allInputFirst);

    // Validate fields in the first section
    allInputFirst.forEach(input => {
        const errorMessage = input.nextElementSibling;
        if (input.value === "" && input.hasAttribute("required")) {
            errorMessage.textContent = "This field is required.";
            input.classList.add("error");
            isValid = false;
        } else if (input.name === "email" && !isValidEmail(input.value)) {
            errorMessage.textContent = "Please enter a valid email address.";
            input.classList.add("error");
            isValid = false;
        } else if ((input.name === "firstName" || input.name === "lastName") && !isValidName(input.value)) {
            errorMessage.textContent = "Please enter a valid name (letters only).";
            input.classList.add("error");
            isValid = false;
        } else if (input.name === "aadhaar" && !isValidAadhaar(input.value)) {
            errorMessage.textContent = "Aadhaar number must be 12 digits and cannot contain all same digits.";
            input.classList.add("error");
            isValid = false;
        } else if (input.name === "mobile" && !isValidMobile(input.value)) {
            errorMessage.textContent = "Mobile number must be exactly 10 digits and cannot start with '0'.";
            input.classList.add("error");
            isValid = false;
        } else if (input.name === "age" && !isValidAge(input.value)) {
            errorMessage.textContent = "Age must be between 18 and 65.";
            input.classList.add("error");
            isValid = false;
        }
    });

    // If valid, proceed to the second section
    if (isValid) {
        form.classList.add('secActive');
    }
});

// Event listener for submitting the form
form.querySelector(".submit").addEventListener("click", (e) => {
    let isValid = true;
    clearErrorMessages(allInputSecond);

    // Validate fields in the second section
    allInputSecond.forEach(input => {
        const errorMessage = input.nextElementSibling;
        if (input.value === "" && input.hasAttribute("required")) {
            errorMessage.textContent = "This field is required.";
            input.classList.add("error");
            isValid = false;
        } else if (input.name === "pinCode" && !isValidPinCode(input.value)) {
            errorMessage.textContent = "Pin Code must be exactly 6 digits.";
            input.classList.add("error");
            isValid = false;
        }
    });

    // Additional validation for required selects
    const gender = form.querySelector('select[name="gender"]');
    const bloodGroup = form.querySelector('select[name="bloodGroup"]');
    const disease = form.querySelector('select[name="disease"]');
    const donateThroughCamp = form.querySelector('select[name="donateThroughCamp"]');

    // Validate each select field
    if (gender.value === "" || gender.value === "Select gender") {
        const errorMessage = gender.nextElementSibling;
        errorMessage.textContent = "Please select an option.";
        gender.classList.add("error");
        isValid = false;
    }
    if (bloodGroup.value === "" || bloodGroup.value === "Select Blood Group") {
        const errorMessage = bloodGroup.nextElementSibling;
        errorMessage.textContent = "Please select an option.";
        bloodGroup.classList.add("error");
        isValid = false;
    }
    if (disease.value === "" || disease.value === "Select anyone") {
        const errorMessage = disease.nextElementSibling;
        errorMessage.textContent = "Please select an option.";
        disease.classList.add("error");
        isValid = false;
    }
    if (donateThroughCamp.value === "" || donateThroughCamp.value === "Select anyone") {
        const errorMessage = donateThroughCamp.nextElementSibling;
        errorMessage.textContent = "Please select an option.";
        donateThroughCamp.classList.add("error");
        isValid = false;
    }

    // Prevent form submission if any validation fails
    if (!isValid) {
        e.preventDefault();
    }
    // If valid, form will submit naturally to the backend
});

// Event listener to go back to the first section
backBtn.addEventListener("click", () => {
    form.classList.remove('secActive');
});

// Remove error messages as the user types
allInputFirst.forEach(input => {
    input.addEventListener("input", () => {
        const errorMessage = input.nextElementSibling;
        errorMessage.textContent = '';
        input.classList.remove("error");
    });
});

allInputSecond.forEach(input => {
    input.addEventListener("input", () => {
        const errorMessage = input.nextElementSibling;
        errorMessage.textContent = '';
        input.classList.remove("error");
    });
});
