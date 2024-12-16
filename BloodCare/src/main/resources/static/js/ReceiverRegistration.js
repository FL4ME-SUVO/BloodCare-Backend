const form = document.querySelector("#receiverRegistrationForm"),
    nextBtn = form.querySelector(".nextBtn"),
    backBtn = form.querySelector(".backBtn"),
    allInputFirst = form.querySelectorAll(".first input, .first select"),
    allInputSecond = form.querySelectorAll(".second input, .second select");

// Clear previous error messages
function clearErrorMessages(inputs) {
    inputs.forEach(input => {
        const errorMessage = input.nextElementSibling;
        errorMessage.textContent = ''; // Clear error message
        input.classList.remove("error"); // Remove error class
    });
}

// Validation functions (email, name, aadhaar, mobile, age, pin code)
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
    return aadhaarRegex.test(aadhaar);
}

function isValidMobile(mobile) {
    const mobileRegex = /^\d{10}$/;
    return mobileRegex.test(mobile);
}

function isValidAge(age) {
    return age >= 18 && age <= 65;
}

function isValidPinCode(pin) {
    const pinRegex = /^\d{6}$/;
    return pinRegex.test(pin);
}

// First part validation
nextBtn.addEventListener("click", () => {
    let isValid = true;
    clearErrorMessages(allInputFirst);

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
            errorMessage.textContent = "Aadhaar number must be 12 digits.";
            input.classList.add("error");
            isValid = false;
        } else if (input.name === "mobile" && !isValidMobile(input.value)) {
            errorMessage.textContent = "Mobile number must be 10 digits.";
            input.classList.add("error");
            isValid = false;
        } else if (input.name === "age" && !isValidAge(input.value)) {
            errorMessage.textContent = "Age must be between 18 and 65.";
            input.classList.add("error");
            isValid = false;
        }
    });

    if (isValid) {
        form.classList.add('secActive');
    }
});

// Second part validation and submission
form.querySelector(".submit").addEventListener("click", (e) => {
    let isValid = true;
    clearErrorMessages(allInputSecond);

    allInputSecond.forEach(input => {
        const errorMessage = input.nextElementSibling;
        if (input.value === "" && input.hasAttribute("required")) {
            errorMessage.textContent = "This field is required.";
            input.classList.add("error");
            isValid = false;
        } else if (input.name === "pinCode" && !isValidPinCode(input.value)) {
            errorMessage.textContent = "Pin Code must be 6 digits.";
            input.classList.add("error");
            isValid = false;
        }
    });

    const gender = form.querySelector('select[name="gender"]');
    const bloodGroup = form.querySelector('select[name="bloodGroup"]');
    const desiredBloodType = form.querySelector('select[name="desiredBloodType"]');

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
    if (desiredBloodType.value === "" || desiredBloodType.value === "Select Blood Type") {
        const errorMessage = desiredBloodType.nextElementSibling;
        errorMessage.textContent = "Please select your desired blood type.";
        desiredBloodType.classList.add("error");
        isValid = false;
    }

    // Allow form submission if valid
    if (!isValid) {
        e.preventDefault(); // Prevent submission if form is invalid
    }
});

// Back button to go back to the first section
backBtn.addEventListener("click", () => form.classList.remove('secActive'));

// Remove error messages on input
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