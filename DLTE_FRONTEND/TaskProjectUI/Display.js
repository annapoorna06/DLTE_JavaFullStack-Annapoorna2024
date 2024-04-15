function toggleLoanCards() {
    var loanCardsContainer = document.getElementById("loanCardsContainer");
    var showLoansBtn = document.getElementById("showLoansBtn");
    if (loanCardsContainer.classList.contains("d-none")) {
        loanCardsContainer.classList.remove("d-none");
        showLoansBtn.style.display = "none";
    } else {
        loanCardsContainer.classList.add("d-none");
        showLoansBtn.style.display = "block"; // Show the button when cards are hidden
    }
}
document.getElementById("showLoansBtn").addEventListener("click", toggleLoanCards);
