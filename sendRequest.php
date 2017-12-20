<?php
include_once './db_functions.php';
//Create Object for DB_Functions clas
$db = new DB_Functions(); 
//Get JSON posted by Android Application
$json = $_POST["sendRequest"];
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
	$db->updateFarmerRequest($data[$i]->phonenumberfarmer, $data[$i]->request);

	$db->updateTransporterRequest($data[$i]->phonenumbertransporter, $data[$i]->request);
}
//Post JSON response back to Android Application
echo json_encode($a);
?>
