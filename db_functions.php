<?php

class DB_Functions {

    private $db;

    //put your code here
    // constructor
    function __construct() {
        include_once './db_connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }

    // destructor
    function __destruct() {
        
    }

    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($Id,$User) {
        // Insert user into database
        $result = mysql_query("INSERT INTO transporters VALUES('$Id','$User')");
		
        if ($result) {
			return true;
        } else {
			if( mysql_errno() == 1062) {
				// Duplicate key - Primary Key Violation
				return true;
			} else {
				// For other errors
				return false;
			}            
        }
    }

    public function insertFarmer($fn,$ln, $phone, $pass, $address, $country, $postalcode, $city,
        $transactions, $deliveries, $ratings) {
        // Insert user into database
        $result = mysql_query(
		"INSERT INTO farmers (firstname, lastname, phonenumber, pass, address
				, city, postalcode, country, transactions, deliveries
				, ratings) 
				VALUES('$fn','$ln', '$phone', '$pass'
				, '$address', '$city', '$postalcode', '$country'
				, '$transactions', '$deliveries', '$ratings') ON DUPLICATE KEY UPDATE 
				firstname = values(firstname), lastname = values(lastname)
				, pass = values(pass), address = values(address), country = values(country)
				, postalcode = values(postalcode), city = values(city)
				, transactions = values(transactions), deliveries = values(deliveries)
				, ratings = values(ratings)");
        if ($result) {
            return true;
        } else {
            if( mysql_errno() == 1062) {
                // Duplicate key - Primary Key Violation
                return true;
            } else {
                // For other errors
                return false;
            }            
        }
    }

    public function insertTransporter($fn,$ln, $availability, $address, $city, $postalcode, $country
				, $password, $phonenumber, $carmake, $capacity, $licenseplatenumber
				, $requests, $ratings, $deliveries) {
        // Insert user into database
        $result = mysql_query(
		"INSERT INTO transporters (firstname, lastname, availability, address
					, city, postalcode, country, pass, phonenumber
					, carmake, capacity, licenseplatenumber, requests
					, ratings, deliveries) 
					VALUES('$fn','$ln', '$availability', '$address', '$city'
					, '$postalcode', '$country', '$password', '$phonenumber'
					, '$carmake', '$capacity', '$licenseplatenumber'
					, '$requests', '$ratings', '$deliveries') 
					ON DUPLICATE KEY UPDATE firstname = values(firstname), 
					lastname = values(lastname), availability = values(availability)
					, address = values(address), city = values(city)
					, postalcode = values(postalcode), country = values(country)
					, pass = values(pass), carmake = values(carmake)
					, capacity = values(capacity)
					, licenseplatenumber = values(licenseplatenumber)
					, requests = values(requests), ratings = values(ratings)
					, deliveries = values(deliveries)");
        if ($result) {
            return true;
        } else {
            if( mysql_errno() == 1062) {
                // Duplicate key - Primary Key Violation
                return true;
            } else {
                // For other errors
                echo $result;
            }            
        }
    }
	
    public function getTransporter($phonenumber){
        $result = mysql_query("select * FROM transporters WHERE phonenumber = '$phonenumber'");
        return $result;
    }

    public function getFarmer($phonenumber){
        $result = mysql_query("select * FROM farmers WHERE phonenumber = '$phonenumber'");
        return $result;
    }

    public function updateFarmerRequest($phonenumber, $request){
	$farmerres = mysql_query("select * FROM farmers WHERE phonenumber = '$phonenumber'");

	while ($farmer = mysql_fetch_assoc($farmerres)){
		$deliveries = json_decode($farmer["deliveries"]);
		array_push($deliveries, json_decode($request));
	}
	$del = json_encode($deliveries);
	$result = mysql_query("UPDATE farmers SET deliveries = '$del' WHERE phonenumber = '$phonenumber'");
    	return $result;
    }

    public function updateTransporterRequest($phonenumber, $request){
	$transporterres = mysql_query("select * FROM transporters WHERE phonenumber = '$phonenumber'");
	while ($transporter = mysql_fetch_assoc($transporterres)){
                $deliveries = json_decode($transporter["deliveries"]);
                array_push($deliveries, json_decode($request));
        }
        $del = json_encode($deliveries);
        $result = mysql_query("UPDATE transporters SET requests = '$del' WHERE phonenumber = '$phonenumber'");

    	return $result;
    }
	 /**
     * Getting all users
     */
    public function getAllTransporters() {
        $result = mysql_query("select * FROM transporters");
        return $result;
    }
    
    public function getTransactionFarmer($phonenumber){
	$result = mysql_query("select * FROM Transactions WHERE phonenumberfarmer = '$phonenumber'");
   	return $result;
    }

    public function getTransactionTransporter($phonenumber){
	$result = mysql_query("select * FROM Transactions WHERE phonenumbertransporter = '$phonenumber'");
    	return $result;
    }

    public function insertTransaction($crop, $amount, $metric, $locationtype, $phonenumberfarmer
					, $phonenumbertransporter, $date, $startaddress, $startcity
					, $startcountry, $startpostalcode, $endaddress, $endcity
					, $endcountry, $endpostalcode, $status)
    {
	$result = mysql_query("INSERT INTO Transactions VALUES('$crop', '$amount', '$metric'
							, '$locationtype', '$phonenumberfarmer'
							, '$phonenumbertransporter', '$date'
							, '$startaddress', '$startcity', '$startcountry'
							, '$startpostalcode', '$endaddress', '$endcity'
							, '$endcountry', '$endpostalcode', '$status')");
	if ($result) {
            return true;
        } else {
            if( mysql_errno() == 1062) {
                // Duplicate key - Primary Key Violation
                return true;
            } else {
                // For other errors
                echo $result;
            }            
        }
	
    }
}

?>
