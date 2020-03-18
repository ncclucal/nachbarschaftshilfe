<?php 
session_start();
require 'aes/password.php';
$pdo = new PDO('mysql:host=localhost;dbname=nachbarschaftshilfe', 'root', ''); //eintragung

include 'aes/login.php';
?>

<!DOCTYPE html>
<html lang="de">
	<head>
		<meta charset="utf-8">

    <title>Login</title>
	</head>
	<body>

            <div class="login-page">
                <div class="form">
										
						<form class="register-form" method="POST" action="?register=1">
						  <input class="username" type="text" placeholder="Username" name="userid"/>
						  <input class="pw" type="password" placeholder="Passwort" name="pwd"/>
						  <input class="pw" type="password" placeholder="Passwort wiederholen" name="pwd2"/>
						  <input class="mail" type="text" placeholder="E-Mail" name="mail"/>
						  <button type="submit">Account erstellen</button>
						</form>
					
					
						<form class="login-form" method="POST" action="?login=1">
						  <input class="mail" type="text" name="mail" placeholder="E-Mail"/>
						  <input class="pw" type="password" name="pwd" placeholder="Passwort"/>
						  <button type="submit">Anmelden</button>
							
							
						</form>

				</div>
          </div>


	</body>
</html>