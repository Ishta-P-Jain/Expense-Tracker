const API_URL = "http://localhost:8080/api/expenses";

// Load expenses on page load
window.onload = loadExpenses;

function addExpense() {
    const expense = {
        amount: document.getElementById("amount").value,
        category: document.getElementById("category").value,
        date: document.getElementById("date").value,
        description: document.getElementById("description").value
    };

    fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(expense)
    })
    .then(() => {
        clearInputs();
        loadExpenses();
    });
}

function loadExpenses() {
    fetch(API_URL)
        .then(response => response.json())
        .then(data => {
            const table = document.getElementById("expenseTable");
            table.innerHTML = "";
            let total = 0;

            data.forEach(expense => {
                total += expense.amount;

                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${expense.amount}</td>
                    <td>${expense.category}</td>
                    <td>${expense.date}</td>
                    <td>${expense.description}</td>
                    <td><button onclick="deleteExpense(${expense.id})">Delete</button></td>
                `;
                table.appendChild(row);
            });

            document.getElementById("total").innerText = total;
        });
}

function deleteExpense(id) {
    fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    }).then(loadExpenses);
}

function getMonthlyTotal() {
    const year = document.getElementById("year").value;
    const month = document.getElementById("month").value;

    fetch(`${API_URL}/monthly?year=${year}&month=${month}`)
        .then(response => response.json())
        .then(total => {
            document.getElementById("monthlyTotal").innerText = total;
        });
}

function clearInputs() {
    document.getElementById("amount").value = "";
    document.getElementById("category").value = "";
    document.getElementById("date").value = "";
    document.getElementById("description").value = "";
}
