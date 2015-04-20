<?php

$servername = "localhost";
$username = "root";
$password = "@res77";
$dbname = "ares";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM coordinates ORDER BY id DESC";

$rsp = $conn->query($sql);

if ( $rsp->num_rows > 0) {
    	echo "<pre>";
	print_r($rsp);
	echo "</pre>";
	
	echo "Filtered by most recent first:<br>";
	while($row = $rsp->fetch_assoc()) {
		echo "<pre>";
        	echo json_encode($row);
		echo "</pre>";
    	}

} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>

