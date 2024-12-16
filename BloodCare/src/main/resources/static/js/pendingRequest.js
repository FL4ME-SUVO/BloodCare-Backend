document.getElementById('sort').addEventListener('click', () => {
    const selectedOption = document.getElementById('sortingField').value;
    const url = selectedOption === 'donor' ? '/api/getPendingDonorRequests' : '/api/getPendingReceiverRequests';

    // Fetch data from the backend
    fetch(url)
        .then(response => response.json())
        .then(data => populateTable(data, selectedOption))
        .catch(error => console.error('Error fetching data:', error));
});

// Function to populate the table dynamically
function populateTable(data, requestType) {
    const tableBody = document.getElementById('requestTableBody');
    tableBody.innerHTML = ''; // Clear existing table rows

    data.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.firstName} ${item.lastName}</td>
            <td>${item.email}</td>
            <td>${item.mobile}</td>
            <td>${requestType === 'donor' ? item.bloodGroup : item.reason}</td>
            <td>
                <button class="btn btn-success btn-sm approve-btn" data-id="${item.id}" data-type="${requestType}">Approve</button>
                <button class="btn btn-danger btn-sm reject-btn" data-id="${item.id}" data-type="${requestType}">Reject</button>
            </td>
        `;
        tableBody.appendChild(row);
    });

    attachActionHandlers();
}

// Function to attach event handlers for Approve/Reject buttons
function attachActionHandlers() {
    document.querySelectorAll('.approve-btn').forEach(button => {
        button.addEventListener('click', () => handleAction(button.dataset.id, button.dataset.type, 'approve'));
    });

    document.querySelectorAll('.reject-btn').forEach(button => {
        button.addEventListener('click', () => handleAction(button.dataset.id, button.dataset.type, 'reject'));
    });
}

// Function to handle Approve/Reject actions
function handleAction(id, requestType, action) {
    // Determine the URL based on the request type and action
    const url = action === 'approve'
        ? (requestType === 'donor' ? '/api/approveDonorRequest' : '/api/approveReceiverRequest')
        : (requestType === 'donor' ? '/api/rejectDonorRequest' : '/api/rejectReceiverRequest');

    // Send a POST request to the appropriate URL
    fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id })
    })
    .then(response => {
        if (response.ok) {
            alert(`${action === 'approve' ? 'Approved' : 'Rejected'} successfully!`); // Corrected the template literal syntax
            document.getElementById('sort').click(); // Refresh the table
        } else {
            alert('Failed to process the request. Please try again.');
        }
    })
    .catch(error => console.error('Error handling action:', error));
}