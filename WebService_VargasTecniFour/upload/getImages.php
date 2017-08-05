<?php 
 
 //Importing dbdetails file 
 require_once 'Database.php';
 
 //connection to database 
 $con = mysqli_connect(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME) or die('Unable to Connect...');
 
 //sql query to fetch all images 
 $sql = "SELECT * FROM registro";
 
 //getting images 
 $result = mysqli_query($con,$sql);
 
 //response array 
 $response = array(); 
 $response['error'] = false; 
 $response['registro'] = array(); 
 
 //traversing through all the rows 
 while($row = mysqli_fetch_array($result)){
 $temp = array(); 
 $temp['id_cliente']=$row['id_cliente'];
 $temp['imagen_smartphone']=$row['imagen_smartphone'];
 $temp['nombre_cliente']=$row['nombre_cliente'];
 $temp['telefono_cliente']=$row['telefono_cliente'];
 $temp['marca_smartphone']=$row['marca_smartphone'];
 $temp['modelo_smartphone']=$row['modelo_smartphone'];
 $temp['imei1_smartphone']=$row['imei1_smartphone'];
 $temp['imei2_smartphone']=$row['imei2_smartphone'];
 $temp['observacion']=$row['observacion'];
 array_push($response['registro'],$temp);
 }
 //displaying the response 
 echo json_encode($response);
?>