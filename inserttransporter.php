<?php
include_once './db_functions.php';
//Create Object for DB_Functions clas
$db = new DB_Functions(); 
//Get JSON posted by Android Application
$json = $_POST["insertTransporter"];
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
	$res = $db->insertTransporter($data[$i]->firstname,$data[$i]->lastname,
							$data[$i]->availability, $data[$i]->address,
							$data[$i]->city, $data[$i]->postalcode,
							$data[$i]->country, $data[$i]->password,
							$data[$i]->phonenumber, $data[$i]->carmake,
							$data[$i]->capacity, $data[$i]->licenseplatenumber,
							$data[$i]->requests, $data[$i]->ratings,
							$data[$i]->deliveries);
	//Based on inserttion, create JSON response
	if($res){
		$b["id"] = $data[$i]->password;
		$b["status"] = 'yes';
		array_push($a,$b);
	}else{
		$b["id"] = $data[$i]->lastname;
		$b["status"] = 'no';
		array_push($a,$b);
	}
}
//Post JSON response back to Android Application
echo json_encode($a);
?>
