<?php

// url/add.php?x=3&y=8

$d1 = $_GET['d1'];
$d2 = $_GET['d2'];
$d3 = $_GET['d3'];
$isPainting = $_GET['isPainting'];

if(!isset($d1) || !isset($d2)){
        echo "joints not specified";
        die;
}

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

$sql = "INSERT INTO coordinates (d1, d2, d3, isPainting)
VALUES (".$d1.", ".$d2.", ".$d3.", ".$isPainting.")";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>


