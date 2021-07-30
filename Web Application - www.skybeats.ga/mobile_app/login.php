<?php 
include '../db_connect.php';
	/*	$db_name = "song";
	$username = "root";
	$password = "";
	$servername = "127.0.0.1";
	$con = mysqli_connect($servername,$username,$password,$db_name);*/
	
		$email = $_POST["email"];
		$password = $_POST["password"];
		$password = md5($password);

		$sql = "SELECT * FROM users WHERE  email = '$email' AND password = '$password'";
		$result = mysqli_query($conn,$sql);
		
		if($result->num_rows > 0){
			echo "logged in successfully" ;
		}else{
  			 echo "user not found";
}
?>