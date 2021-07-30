<?php
include'../db_connect.php';
  if(!$conn) {
    die("noconn");
  }
  $user_username = "";
  $user_password = "";
  if(isset($_POST['user_username']) && !empty($_POST['user_username']) && isset($_POST['user_password']) && !empty($_POST['user_password'])) {
    $user_username = mysqli_real_escape_string($conn, $_POST['user_username']);
    $user_password = mysqli_real_escape_string($conn, $_POST['user_password']);
    $user_password = md5($user_password);
  }
  elseif(isset($_GET['user_username']) && !empty($_GET['user_username']) && isset($_GET['user_password']) && !empty($_GET['user_password'])) {
    $user_username = mysqli_real_escape_string($conn, $_GET['user_username']);
    $user_password = mysqli_real_escape_string($conn, $_GET['user_password']);
    $user_password = md5($user_password);
  } else {
    die("nodata");
  }
  $query_login = $conn->Query("SELECT email, password FROM users WHERE email = '".$user_username."' LIMIT 1");
  if(!$query_login) {
    die("dataerror");
  }
  elseif($query_login->num_rows != 1) {
    die("usernotfound");
  } else {
    $query_result = $query_login->Fetch_assoc();
    if($user_password != $query_result['password']) {
      die("userwrongpassword");
    }
    else {
      die("success");
    }
  }
?>