<?php
$sambung=mysqli_connect("ictthatworks.org","arcDBu","!Ognt807","arc_mario");

// Check connection
if (mysqli_connect_errno($sambung))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }


?>