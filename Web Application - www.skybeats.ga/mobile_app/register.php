<?php 

include '../db_connect.php';
if(!$conn) {
    die("noconn");
  }
  $firstname = "";
  $lastname = "";
  $email = "";
  $password = "";
  $gender = "";
  $contact = "";
  $date = date('Y-m-d H:i:s');


  if(isset($_POST['firstname']) &&
    isset($_POST['lastname']) &&
    isset($_POST['email']) &&
    isset($_POST['password']) &&
    isset($_POST['gender']) &&
    isset($_POST['contact'])) {
    $firstname = mysqli_real_escape_string($conn, $_POST['firstname']);
    $lastname = mysqli_real_escape_string($conn, $_POST['lastname']);
    $email = mysqli_real_escape_string($conn, $_POST['email']);
    $gender = mysqli_real_escape_string($conn, $_POST['gender']);
    $contact = mysqli_real_escape_string($conn, $_POST['contact']);
    $password = mysqli_real_escape_string($conn, $_POST['password']);
    $password = md5($password);
  }
  else {
    die("nodata");
  }
  $user_check_query = "SELECT email FROM users WHERE email = '$email' LIMIT 1";
  $result = mysqli_query($conn, $user_check_query);
  $user = mysqli_fetch_assoc($result);
  
 // if user exists
    if ($user['email'] === $email) {
      die("emailexists");
    }

    else {

      $query_register = "INSERT INTO users (firstname,lastname,email, password,gender, contact, date_created) VALUES ('$firstname', '$lastname', '$email', '$password', '$gender', '$contact', '$date')";  
           if(mysqli_query($conn, $query_register))  
           {  
                die("success");
           } 

           else {
                die("dataerror");
           }
    }

  

?>