<html>
<style>
form {
  text-align: center;
}
table, td {
  border:2px solid black;
  border-collapse: collapse;
  width: 25%;
  text-align: center;
  margin-left: auto; 
  margin-right: auto;
  font-weight: bold;
  font-size: medium;
  font-variant: petite-caps;
  color: darkred;
}
input, select{
  border-radius: 10px;
  padding: 13px;
  color: darkred;
  font-weight: bold;
  font-size: smaller;
  font-variant: petite-caps;
}
h3, p {
  font-variant: petite-caps;
  color: darkred;
  font-size: large;
}      
</style>
<body style="background-color: beige;">
<form action= "SearcAlgo.php" method="POST">
<label for="algorithm"><h3>Select an Algorithm:</h3></label>
<p>Multiple options can be selected at once</p>
  <select name="algo[]" id="algo" multiple> 
    <option value="linear" name="linear">LINEAR SEARCH</option>
    <option value="binary" name="binary">BINARY SEARCH</option>
    <option value="bst" name="bst">BINARY SEARCH TREE</option>
    <option value="rbt" name ="rbt">RED BLACK TREE</option>
  </select><br/>
<h3>Input Size: </h3>
<p>Can provide multiple input size separated by space</p><input type="text" name="size"><br/>
<h3>Search Element: </h3><input type="text" name="search"><br/><br/><br/>
<input type="submit" value="Search" name="submit">
<input type="submit" value="Compare All Algorithm" name="compare"><br/><br/>
</form>
<?php 
error_reporting(E_ALL);
ini_set('display_errors','On');
$input = "java -jar project2Daa.jar";
$space = " ";
$arrValue = "";

if(isset($_POST["submit"])) {
$algoValue = $_POST['algo'];
$inputSearch = $_POST['search'];
$input = $input.$space.$inputSearch.$space;
foreach($algoValue as $value){
  $arrValue = $arrValue.$value.$space;
}
$input = $input.$arrValue.$_POST['size'];
$html = "<table>";
exec($input, $output);
foreach($output as $printvalue){
  if(!empty($printvalue)){
    $spiltValue = explode(" ", $printvalue);
    $html .= "<tr>";
    foreach($spiltValue as $abc){
      if(!empty($abc)){
        $html .= "<td>". $abc ."</td>"; 
      }
    }
    $html .= "</tr>";
  }
}
$html .= "</table>";
echo $html;
echo "<p>In the above table element column shows the index of search element for Linear and binary search. But for Binary search tree and Red black tree it shows the element if found. Else it will show not found </p>";
}

if(isset($_POST["compare"])) {
$algoValue = $_POST['algo'];
$inputSearch = $_POST['search'];
$input = $input.$space.$inputSearch.$space;
foreach($algoValue as $value){
  $arrValue = $arrValue.$value.$space;
}
$input = $input.$arrValue.$_POST['size'].$space."compare";
$html = "<table>";
exec($input, $output);
foreach($output as $printvalue){
  if(!empty($printvalue)){
    $spiltValue = explode(" ", $printvalue);
    $html .= "<tr>";
    foreach($spiltValue as $abc){
      if(!empty($abc)){
        $html .= "<td>". $abc ."</td>"; 
      }
    }
    $html .= "</tr>";
  }
}
$html .= "</table>";
echo $html;
}
?>
</body>
</html>