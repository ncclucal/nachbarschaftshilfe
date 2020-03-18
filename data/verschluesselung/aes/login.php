<?php 

$showFormular = false;
$showLogin = true;
                    
if(isset($_GET['showform'])) {
    $showFormular = true;
    $showLogin = false;        
}
 
                    
//Registrierung
if(isset($_GET['register'])) {
    
    $error = false;
    $email = $_POST['mail'];
    $passwort = $_POST['pwd'];
	$passwort2 = $_POST['pwd2'];
    $name = $_POST['userid'];
  
    if(!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo 'Bitte eine gültige E-Mail-Adresse eingeben<br>';
        $error = true;
    }     
    if(strlen($passwort) == 0) {
        echo 'Bitte ein Passwort angeben<br>';
        $error = true;
    }
    if($passwort != $passwort2) {
        echo 'Die Passwörter müssen übereinstimmen<br>';
        $error = true;
    }
    
    //Überprüfe E-Mail
    if(!$error) { 
        $statement = $pdo->prepare("SELECT * FROM cloud_users WHERE email = :email");
        $result = $statement->execute(array('email' => $email));
        $user = $statement->fetch();
        
        if($user !== false) {
            echo 'Diese E-Mail-Adresse ist bereits vergeben<br>';
            $error = true;
        }    
    }
    
    //Keine Fehler
    if(!$error) {    
        $hash = password_hash($passwort, PASSWORD_DEFAULT);
        
        $statement = $pdo->prepare("INSERT INTO cloud_users (email, password, username) VALUES (:email, :password, :username)");
        $result = $statement->execute(array('email' => $email, 'password' => $hash, 'username' => $name));
        
        if($result) {        
            echo 'Du wurdest erfolgreich registriert!</a>';
            $showFormular = false;
        } else {
            echo 'Deine Anfrage kann zur Zeit nicht verarbeitet werden<br>';
        }
		
    } 
}
                    
if(isset($_GET['login'])) {
    $email = $_POST['mail'];
    $passwort = $_POST['pwd'];
	
	if(!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo 'Bitte eine gültige E-Mail-Adresse eingeben<br>';
        $error = true;
    }
    
    $statement = $pdo->prepare("SELECT cloud_users.password FROM cloud_users WHERE email = :email");
    $result = $statement->execute(array('email' => $email));
    $user = $statement->fetch();
        
    //Passwort prüfen 
	if(strlen($passwort) == 0) {
        echo 'Bitte ein Passwort angeben<br>';
        $error = true;
    }
	
    if ($user !== false && password_verify($passwort, $user['password'])) {
        $_SESSION['userid'] = $user['id'];
        header('Location: indexloggedin.php');
        //die('Login erfolgreich ! Hier geht es zum <a href="indexloggedin.php">Index</a>');
    } else {
        $errorMessage = "E-Mail oder Passwort war ungültig<br>";
    }
    
}
?>