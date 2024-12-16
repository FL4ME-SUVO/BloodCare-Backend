const form = document.querySelector("#editProfile"),
    nextBtn = form.querySelector(".nextBtn"),
    backBtn = form.querySelector(".backBtn"),
    allInputFirst = form.querySelectorAll(".first input, .first select"),
    allInputSecond = form.querySelectorAll(".second input, .second select"),
    successPopup = document.getElementById('successPopup'),
    closePopupBtn = document.getElementById('closePopup');

// Clear previous error messages
function clearErrorMessages(inputs) {
    inputs.forEach(input => {
        const errorMessage = input.nextElementSibling; // Assuming the error message is the next sibling
        errorMessage.textContent = ''; // Clear error message
        input.classList.remove("error"); // Remove error class
    });
}

// Email validation function
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Basic email pattern
    return emailRegex.test(email);
}

// Function to validate fields
function isValidName(name) {
    const nameRegex = /^[A-Za-z\s]+$/; // Only letters and spaces allowed
    return nameRegex.test(name);
}

function isValidAadhaar(aadhaar) {
    const aadhaarRegex = /^\d{12}$/; // Must be exactly 12 digits
    return aadhaarRegex.test(aadhaar);
}

function isValidMobile(mobile) {
    const mobileRegex = /^\d{10}$/; // Must be exactly 10 digits
    return mobileRegex.test(mobile);
}

function isValidAge(age) {
    return age >= 18 && age <= 65; // Age must be between 18 and 65
}

function isValidPinCode(pin) {
    const pinRegex = /^\d{6}$/; // Must be exactly 6 digits
    return pinRegex.test(pin);
}

// Validate inputs in the first part before moving to the second part
nextBtn.addEventListener("click", () => {
    let isValid = true;
    clearErrorMessages(allInputFirst); // Clear previous error messages

    allInputFirst.forEach(input => {
        const errorMessage = input.nextElementSibling; // Get the error message element
        if (input.value === "" && input.hasAttribute("required")) {
            errorMessage.textContent = "This field is required."; // Set error message
            input.classList.add("error"); // Add error class for styling
            isValid = false;
        } else if (input.name === "email") {
            if (!isValidEmail(input.value)) {
                errorMessage.textContent = "Please enter a valid email address.";
                input.classList.add("error");
                isValid = false;
            }
        } else if (input.name === "firstName" || input.name === "lastName") {
            if (!isValidName(input.value)) {
                errorMessage.textContent = "Please enter a valid name (letters only).";
                input.classList.add("error");
                isValid = false;
            }
        } else if (input.name === "aadhaar") {
            if (!isValidAadhaar(input.value)) {
                errorMessage.textContent = "Aadhaar number must be 12 digits.";
                input.classList.add("error");
                isValid = false;
            }
        } else if (input.name === "mobile") {
            if (!isValidMobile(input.value)) {
                errorMessage.textContent = "Mobile number must be 10 digits.";
                input.classList.add("error");
                isValid = false;
            }
        } else if (input.name === "age") {
            if (!isValidAge(input.value)) {
                errorMessage.textContent = "Age must be between 18 and 65.";
                input.classList.add("error");
                isValid = false;
            }
        }
    });

    if (isValid) {
        form.classList.add('secActive');
    }
});

// Validate inputs in the second part before submitting
form.querySelector(".submit").addEventListener("click", (e) => {
    e.preventDefault(); // Prevent default form submission
    let isValid = true;
    clearErrorMessages(allInputSecond); // Clear previous error messages

    allInputSecond.forEach(input => {
        const errorMessage = input.nextElementSibling; // Get the error message element
        if (input.value === "" && input.hasAttribute("required")) {
            errorMessage.textContent = "This field is required."; // Set error message
            input.classList.add("error"); // Add error class for styling 
            isValid = false;
        } else if (input.name === "pinCode") {
            if (!isValidPinCode(input.value)) {
                errorMessage.textContent = "Pin Code must be 6 digits.";
                input.classList.add("error");
                isValid = false;
            }
        }
    });

    // Additional required fields from the second part of the form
    const gender = form.querySelector('select[name="gender"]');
    const bloodGroup = form.querySelector('select[name="bloodGroup"]');
    // Validate additional required fields
    if (gender.value === "" || gender.value === "Select gender") {
        const errorMessage = gender.nextElementSibling;
        errorMessage.textContent = "Please select your option."; // Set error message
        gender.classList.add("error"); // Add error class for styling
        isValid = false;
    }

    if (bloodGroup.value === "" || bloodGroup.value === "Select Blood Group") {
        const errorMessage = bloodGroup.nextElementSibling;
        errorMessage.textContent = "Please select your option."; // Set error message
        bloodGroup.classList.add("error"); // Add error class for styling
        isValid = false;
    }
    


    if (isValid) {
        console.log("Form submitted!"); // For demonstration purposes
        successPopup.style.display = 'flex'; // Display the success popup
    }
});

// Go back to the first section
backBtn.addEventListener("click", () => form.classList.remove('secActive'));

// Remove error messages on input event
allInputFirst.forEach(input => {
    input.addEventListener("input", () => {
        const errorMessage = input.nextElementSibling;
        if (errorMessage.textContent) {
            errorMessage.textContent = ''; // Clear error message
        }
    });
});

allInputSecond.forEach(input => {
    input.addEventListener("input", () => {
        const errorMessage = input.nextElementSibling;
        if (errorMessage.textContent) {
            errorMessage.textContent = ''; // Clear error message
        }
    });
});

// Close the success popup
closePopupBtn.addEventListener('click', () => {
    successPopup.style.display = 'none'; // Hide the success popup
    window.location.href = 'user.html';
});