document.getElementById('sort').addEventListener('click', () => {
    const selectedOption = document.getElementById('sortingField').value;

    // Define the API endpoint and table headers based on the selection
    let apiUrl = '';
    let tableHeaders = '';

    if (selectedOption === 'donor') {
        apiUrl = '/approvedDonors';
        tableHeaders = `
            <th>Full Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Blood Group</th>
            <th>Appointment Date</th>
        `;
    } else if (selectedOption === 'receiver') {
        apiUrl = '/approvedReceivers';
        tableHeaders = `
            <th>Full Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Blood Group</th>
            <th>Receiving Date</th>
        `;
    } else {
        alert('Please select a valid request type.');
        return; // Exit if no valid option is selected
    }

    // Update table headers dynamically
    document.getElementById('tableHeaders').innerHTML = tableHeaders;

    // Fetch data from the API
    fetch(apiUrl)
        .then(response => {
            // Check if the response is OK (status in the range 200-299)
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`); // Corrected the template literal syntax
            }
            return response.json(); // Parse JSON data from the response
        })
        .then(data => {
            const tableBody = document.getElementById('approvedRequestsTable');
    
            // Check if the data array is empty
            if (data.length === 0) {
                tableBody.innerHTML = '<tr><td colspan="5">No data available.</td></tr>';
                return;
            }
    
            // Populate table with data
            tableBody.innerHTML = data.map(item => `
                <tr>
                    <td>${item.firstName} ${item.lastName}</td>
                    <td>${item.email}</td>
                    <td>${item.mobile}</td>
                    <td>${item.bloodGroup}</td>
                    <td>${selectedOption === 'donor' ? item.appointmentDate : item.receivingDate}</td>
                </tr>
            `).join('');
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            alert('Failed to load data. Please try again later.');
        });
});