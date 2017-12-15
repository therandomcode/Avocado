<?php
include_once './db_functions.php';
//Create Object for DB_Functions clas
$db = new DB_Functions(); 
//Get JSON posted by Android Application

$json = $_POST["getRequests"];
//Remove Slashes

if (get_magic_quotes_gpc()){
	$json = stripslashes($json);
}
//Decode JSON into an Array
$data = json_decode($json);


//Util arrays to create response JSON
$a=array();
$b=array();
//Loop through an Array and insert data read from JSON into MySQL DB

for($i=0; $i<count($data) ; $i++)
{
	//Store User into MySQL DB   
	$res = $db->getTransporter($data[0]->phonenumber);
	
	while ($row = mysql_fetch_assoc($res)){
		//echo json_encode($row);
		
		$requests = $row["requests"];
		$req = json_decode($requests, true);
		foreach ($req as $ind){ 
			$farmer = $db->getFarmer($ind["phonenumberfarmer"]);
			while ($acfarmer = mysql_fetch_assoc($farmer)){
				$ind["firstname"] = $acfarmer["firstname"];
				$ind["lastname"] = $acfarmer["lastname"];
			}
		}
		array_push($a, $ind);
	}

}
//Post JSON response back to Android Application
echo json_encode($a);
?>
