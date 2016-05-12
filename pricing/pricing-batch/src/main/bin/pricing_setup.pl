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


my @sql = (
    qq{
		
		CREATE DATABASE test;	
	},
	qq{
		CREATE USER test_user IDENTIFIED BY 'test_pwd';
	
	},
	qq{
		
		grant usage on *.* to test_user\@localhost identified by 'test_user';	
	
	},
	qq{
		
		grant all privileges on test.* to test_user\@localhost;	
	
	},
    qq{
		CREATE TABLE test.price_feed (
		ID int(25) NOT NULL AUTO_INCREMENT,
	   ITEM_ID varchar(300) NOT NULL,
	   ITEM_TITLE varchar(500) NOT NULL,
	   PRICE double NOT NULL,
	   STORE varchar(300) NOT NULL,
	   ITEM_CATEGORY varchar(300) NOT NULL,
	   ITEM_SUB_CATEGORY varchar(300) DEFAULT '',
	   PRIMARY KEY (ID)
	)
	},
    qq{
		CREATE TABLE test.store_category_price_result (
		   ID int(25) NOT NULL AUTO_INCREMENT,
		   PRICE double NOT NULL,
		   STORE varchar(300) NOT NULL,
		   ITEM_CATEGORY varchar(300) NOT NULL,  
		  PRIMARY KEY (ID)
		) DEFAULT CHARSET=utf8;
		}

);

for (@sql) {
    my $sth = $connection->prepare($_);
    $sth->execute or die "SQL Error: $DBI::errstr\n";
    $sth->finish;	
}
$connection->commit or die $DBI::errstr;

$connection->disconnect  or die $DBI::errstr;

# exit the script
exit;

#--- start sub-routine ------------------------------------------------
sub ConnectToMySql {
#----------------------------------------------------------------------

my $driver = "mysql"; 
my $database = "tarun";
my $dsn = "DBI:$driver:database=$database";
my $userid = "root";
my $password = "root";

chomp ($database, $dsn, $userid, $password);

my $dbh = DBI->connect($dsn, $userid, $password ,  {AutoCommit => 0} ) or die "Connection Error: $DBI::errstr\n";

# assign the values to your connection variable
# my $connectionInfo="dbi:mysql:$db;$host";        

# make connection to database
#  my $l_connection = DBI->connect($connectionInfo,$userid,$password)  or die "Connection Error: $DBI::errstr\n";
  
# the value of this connection is returned by the sub-routine
return $dbh;

}

#--- end sub-routine --------------------------------------------------

# - - - - STOP SCRIPT - - - - (do not include this line in the script)  


