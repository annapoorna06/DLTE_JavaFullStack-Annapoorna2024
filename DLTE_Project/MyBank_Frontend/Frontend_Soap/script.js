const loanData = [
    { id: 1, name: 'Personal Loan', description: 'A loan for personal use,education, medical expenses, travel' },
    { id: 2, name: 'Home Loan', description: 'A loan for purchasing a new home or refinancing an existing mortgage.' },
    { id: 3, name: 'Gold Loan', description: 'Unlock the power of your precious metal.' },
    { id: 4, name: 'Education Loan', description: 'A loan for financing educational expenses, such as tuition, books, and housing.' },
    { id: 5, name: 'Agriculture Loan', description: 'A loan to buying inputs such as fertilizers, seeds, insecticides ' },
    { id: 6, name: 'Business Loan', description: 'A loan for starting or expanding a business.' },
];

function displayLoanCards(loans) {
    const loanCardContainer = document.getElementById('loan-card-container');
    loanCardContainer.innerHTML = '';

    const mainContent = document.getElementById('main-content');
    mainContent.style.display = 'none'; 

    loans.forEach(loan => {
        const loanCard = document.createElement('div');
        loanCard.classList.add('loan-card', 'col-md-4');
        loanCard.textContent = loan.name;

        const loanDescription = document.createElement('div');
        loanDescription.classList.add('loan-description');
        loanDescription.textContent = loan.description;

        loanCard.appendChild(loanDescription);

        loanCard.addEventListener('click', () => {
            loanDescription.style.display = loanDescription.style.display === 'none' ? 'block' : 'none';
        });

        loanCardContainer.appendChild(loanCard);
    });
}
function filterLoans(searchTerm) {
    const filteredLoans = loanData.filter(loan =>
        loan.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    displayLoanCards(filteredLoans);
}
document.getElementById('allLoansLink').addEventListener('click', (e) => {
    e.preventDefault(); 
    displayLoanCards(loanData);
});

document.getElementById('searchBtn').addEventListener('click', () => {
    const searchTerm = document.getElementById('searchInput').value;
    filterLoans(searchTerm);
});

const loanCardContainer = document.getElementById('loan-card-container');
loanCardContainer.innerHTML = '';
const mainContent = document.getElementById('main-content');
mainContent.style.display = 'block'; 
