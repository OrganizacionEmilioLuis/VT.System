<?php 

	

if($_SERVER['REQUEST_METHOD']=='POST'){
 
 $imagen_smartphone = $_POST['imagen_smartphone'];
 	$nombre_cliente = $_POST['nombre_cliente'];
 		$telefono_cliente = $_POST['telefono_cliente'];
 			$marca_smartphone = $_POST['marca_smartphone'];
 			 	$modelo_smartphone = $_POST['modelo_smartphone'];
   					$imei1_smartphone = $_POST['imei1_smartphone'];
    					$imei2_smartphone = $_POST['imei2_smartphone'];
 							$observacion = $_POST['observacion'];
 
require_once 'Database.php';
 
 $sql ="SELECT id_cliente FROM registro ORDER BY id_cliente ASC";
 
 $res = mysqli_query($con,$sql);
 
 $id_cliente = 0;
 
 while($row = mysqli_fetch_array($res)){
 $id_cliente = $row['id_cliente'];
 }
 
 $path = "upload/$id_cliente.png";
 
 $actualpath = "http://192.168.137.1/WebService_VargasTecniFour/$path";
 
 $sql = "INSERT INTO registro (imagen_smartphone, nombre_cliente, telefono_cliente, marca_smartphone, modelo_smartphone, imei1_smartphone, imei2_smartphone, observacion) VALUES ('$actualpath','$nombre_cliente','$telefono_cliente','$marca_smartphone','$modelo_smartphone','$imei1_smartphone','$imei2_smartphone','$observacion')";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($imagen_smartphone));
 echo "Registrado";
 }
 
 mysqli_close($con);
 }else{
 echo "Error";
 }
	
?>