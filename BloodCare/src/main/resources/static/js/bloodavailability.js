document.getElementById('fetchAllButton').addEventListener('click', () => {
    console.log("Fetch All Records button clicked.");
    fetch('http://localhost:8085/api/blood-availability/all')
        .then(response => response.json())
        .then(data => {
            console.log("Data received from the server:", data);
            const allRecordsDiv = document.getElementById('allRecords');
            if (data.length === 0) {
                allRecordsDiv.innerHTML = "<p>No records found.</p>";
                return;
            }
            let table = "<table border='1'><thead><tr><th>Blood Group</th><th>Component Type</th><th>Units Available</th></tr></thead><tbody>";
            data.forEach(record => {
                table += `<tr>
                    <td>${record.id.bloodGroup}</td>
                    <td>${record.id.componentType}</td>
                    <td>${record.unitsAvailable}</td>
                </tr>`;
            });
            table += "</tbody></table>";
            allRecordsDiv.innerHTML = table;
        })
        .catch(error => {
            console.error("Error fetching records:", error);
            document.getElementById('allRecords').innerHTML = "<p>Error fetching records.</p>";
        });
});