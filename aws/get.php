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

$sql = "SELECT * FROM coordinates ORDER BY id DESC LIMIT 1";

$rsp = $conn->query($sql);

if ( $rsp->num_rows > 0) {
	$row = $rsp->fetch_assoc();

	echo json_encode($row);

} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
