<?php
include_once './db_functions.php';
//Create Object for DB_Functions clas
$db = new DB_Functions(); 
//Get JSON posted by Android Application

$json = $_POST["getTransporters"];
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
	$res = $db->getAllTransporters();
	

	while ($row = mysql_fetch_assoc($res)){
		
		$availability = json_decode($row["availability"]);
		
		for ($j=0; $j<count($availability); $j++){
			if (strcmp($availability[$j]->date, $data[$i]->date) == 0)
			{
				if ((strcmp($availability[$j]->time, "ALLDAY") == 0) 
					|| (strcmp($availability[$j]->time, $data[$i]->time) == 0
					|| (strcmp("ALLDAY", $data[$i]->time)) == 0))
				{
					array_push($a, $row);
					break;
				}
			}
		}
	}

}
//Post JSON response back to Android Application
echo json_encode($a);
?>
