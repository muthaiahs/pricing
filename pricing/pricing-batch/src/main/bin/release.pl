#!/usr/bin/perl -w
use DBI;

use Cwd;
use FileHandle;
use English;

# chdir to the script directory
my $thisScript = $0;
my $scriptDir = getcwd;

print "scriptDir  :     $scriptDir  \n";  

# invoke the ConnectToMySQL sub-routine to make the database connection
$connection = ConnectToMySql();  


my $input = " SELECT table_schema,table_name 
	FROM information_schema.tables
	WHERE table_schema = 'test' 
    AND table_name IN ( 'price_feed' , 'store_category_price_result' )  	
	";
$sth_input = $connection->prepare($input);  
$sth_input->execute or die "SQL Error: $DBI::errstr\n";

while (@row = $sth_input->fetchrow_array)
{ 
  
 my $input_1 = " TRUNCATE  $row[0].$row[1]  ";   
 $sth_input_1 = $connection->prepare($input_1);
 $sth_input_1->execute or die "SQL Error: $DBI::errstr\n";
 $sth_input_1->finish;	
}

$connection->commit or die $DBI::errstr;

$connection->disconnect  or die $DBI::errstr;


#### check 
	
my $pid = fork();
if(!$pid) {
print "$pid \n";

}



	
	

# exit the script
exit;



sub ConnectToMySql {


my $driver = "mysql"; 
my $database = "tarun";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "root";

chomp ($database, $dsn, $userid, $password);

my $dbh = DBI->connect($dsn, $userid, $password ,  {AutoCommit => 0} ) or die "Connection Error: $DBI::errstr\n";

return $dbh;

}



