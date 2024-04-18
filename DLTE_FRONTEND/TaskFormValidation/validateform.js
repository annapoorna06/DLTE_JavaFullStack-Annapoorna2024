function validateForm() {
    let valid = true;

    let accountNumber = document.getElementById("accountNumber").value;
    if (!/^\d{12}$/.test(accountNumber)) {
        document.getElementById("accountNumberError").innerText = "Account Number must be 12 digits";
        valid = false;
    } else {
        document.getElementById("accountNumberError").innerText = "";
    }

    let accountHolder = document.getElementById("accountHolder").value;
    if (!/^[a-zA-Z\s]*$/.test(accountHolder)) {
        document.getElementById("accountHolderError").innerText = "Account Holder must contain only letters";
        valid = false;
    } else {
        document.getElementById("accountHolderError").innerText = "";
    }

    let contactNumber = document.getElementById("contactNumber").value;
    if (!/^\d{10}$/.test(contactNumber)) {
        document.getElementById("contactNumberError").innerText = "Contact Number must be 10 digits";
        valid = false;
    } else {
        document.getElementById("contactNumberError").innerText = "";
    }

    let email = document.getElementById("email").value;
    if (!/^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\.[A-Za-z]+$/.test(email)) {
        document.getElementById("emailError").innerText = "Invalid Email format";
        valid = false;
    } else {
        document.getElementById("emailError").innerText = "";
    }


    let chequeBookType = document.getElementById("chequeBookType").value;
    if (chequeBookType === "") {
        document.getElementById("chequeBookTypeError").innerText = "Cheque Book Type is required";
        valid = false;
    } else {
        document.getElementById("chequeBookTypeError").innerText = "";
    }


    let accountType = document.querySelector('input[name="accountType"]:checked');
    if (!accountType) {
        document.getElementById("accountTypeError").innerText = "Account Type is required";
        valid = false;
    } else {
        document.getElementById("accountTypeError").innerText = "";
    }

   
    let dateOfApply = document.getElementById("dateOfApply").value;
    let currentDate = new Date().toISOString().split('T')[0];
    if (dateOfApply === "" || dateOfApply < currentDate) {
        document.getElementById("dateOfApplyError").innerText = "Invalid Date of Apply";
        valid = false;
    } else {
        document.getElementById("dateOfApplyError").innerText = "";
    }


    let address = document.getElementById("address").value;
    if (address === "") {
        document.getElementById("addressError").innerText = "Address is required";
        valid = false;
    } else {
        document.getElementById("addressError").innerText = "";
    }

    let signature = document.getElementById("signature").value;
    if (signature === "") {
        document.getElementById("signatureError").innerText = "Signature is required";
        valid = false;
    } else {
        document.getElementById("signatureError").innerText = "";
    }

    let termsAndConditions = document.getElementById("termsAndConditions").checked;
    if (!termsAndConditions) {
        document.getElementById("termsAndConditionsError").innerText = "You must agree to the Terms and Conditions";
        valid = false;
    } else {
        document.getElementById("termsAndConditionsError").innerText = "";
    }

    return valid;
}