
<?php

include'db_connect.php';

    //fetch table rows from mysql db
    $sql = "SELECT * FROM uploads order by title asc";
    $result = mysqli_query($conn, $sql) or die("Error in Selecting " . mysqli_error($conn));
 header("Content-Type: JSON");
    //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
    echo json_encode($emparray);

    //close the db connection
    mysqli_close($conn);
?>