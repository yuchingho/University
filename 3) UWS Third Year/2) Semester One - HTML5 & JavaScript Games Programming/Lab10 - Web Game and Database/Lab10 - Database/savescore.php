<?php
// Connect to the database(host, username, password)
$con = mysql_connect('localhost','root','');
if (!$con)
{
    echo "Failed to make connection.";
    exit;
}
// Select the database. Enter the name of your database (not the same as the table name)
$db = mysql_select_db('phaserdb');
if (!$db)
{
    echo "Failed to select db.";
    exit;
}

if (isset($_POST['json'])) {
        // use json_decode() transform to array
        $request_json = json_decode($_POST['json'], true);
        $theuser = $request_json['user'];
		$thescore = $request_json['thescore'];
		echo "This is a message from PHP. The user:".$theuser." have stored his score : ".$thescore;
        
	    $sql = "INSERT INTO score_tbl (userid,userscore) VALUES ('".$theuser."',$thescore)";
	    $query = mysql_query($sql);
		
    }
?>