/*=============Approved Request Table==========================*/
    
var clients = [
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
    { "Donor Name": "Shubhajit Giri", "Disease" : "Yes", "Age": 22, "Blood Group": "O+", "Quantity": 2, "Request Date": "2025-04-09" },
   
    
];

/*===========Approved Request Table ================================*/

$("#jsGrid2").jsGrid({
    height: "500px",
    width: "100%",

   

    //controller: db,
    data: clients,

    fields: [
        { name: "Donor Name", type: "text", width: 100, validate: "required" },
        { name: "Disease", type: "text", width: 80, validate: "required" },
        { name: "Age", type: "number", width: 50 , validate: "required" },
        { name: "Blood Group", type: "text", width: 50, validate: "required" },
        { name: "Quantity", type: "number", width:100, validate: "required" },
        { name: "Request Date", type: "date", width: 100, validate: "required"  }
       
    ]
});


$("#sort").click(function() {
    var field = $("#sortingField").val();
    $("#jsGrid2").jsGrid("sort", field);
});
/*===========JsGrid sorting Table ================================*/



