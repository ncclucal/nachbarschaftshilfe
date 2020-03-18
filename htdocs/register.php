<?php
	include "dbinfo.php";
	
	$email = $_GET["email"];
	$password = $_GET["password"];
	
	$conn = new mysqli($dbserver, $dbuser, $dbpassword, $dbname);
	
	$pwhash = sha1($password);
	
	$sql = "select * from user where email='".$email."';";
	
	$result = mysqli_query($conn, $sql);
	$resultNum = mysqli_num_rows($result);
	
	if($resultNum != 0){
		echo "ERROR: User existiert schon!";
	}else{
		$sql = "insert into user (email, password) values ('".$email."','".$pwhash."');";
		
		mysqli_query($conn, $sql);
		echo "Registriert!";
	}
	
?>
