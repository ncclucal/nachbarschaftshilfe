<?php
	include "dbinfo.php";
	
	$email = $_GET["email"];
	$password = $_GET["password"];
	
	$conn = new mysqli($dbserver, $dbuser, $dbpassword, $dbname);
	
	$pwhash = sha1($password);
	
	$sql = "select password from user where email='".$email."';";
	
	$result = mysqli_query($conn, $sql);
	$resultNum = mysqli_num_rows($result);
	
	if($resultNum == 0){
		echo "ERROR: User nicht gefunden!";
	}else if($resultNum == 1){
		
		$row = mysqli_fetch_row($result);
		
		$savedPassword = $row[0];
		
		//echo "HASH: ".$pwhash." PW: ".$savedPassword;
		if($pwhash == $savedPassword){
			echo "OK";
		}else{
			echo "ERROR: Falsches Passwort!";
		}
		
	}
	
?>
