#!/usr/bin/perl -w
use Git::Repository;

use Cwd;
use FileHandle;
use English;

# chdir to the script directory
my $thisScript = $0;
my $scriptDir = getcwd;

print "scriptDir  :     $scriptDir  \n"; 

my $url="https://github.com/muthaiahs/pricing.git";
my $clone_dir="E:/pricing_git/pricing";

my $local_repo="pricing";


# my $git = Git::Repository->new(work_tree => "$dir",);

# or init our own repository first
# Git::Repository->run( init => $dir );
# my $r = Git::Repository->new( work_tree => $dir );
 
# or clone from a URL first
# Git::Repository->run( clone => $url, $dir, ... );
 
# my $local_dir = Git::Repository->run( clone => $url => $dir )  or die " Exiting:$!\n\n";
# my $r = Git::Repository->new( work_tree => $dir );


my $pricing_dir ="${scriptDir}/${local_repo}/${local_repo}";
print " pricing_dir   :       $pricing_dir\n";  
my $cmd=" clean install ";   
my $args =" -X -e -U ";
run_mvn_cmd("${pricing_dir}", "${cmd}" , "${args}");


exit;  


  
sub run_mvn_cmd() {
 
	my $dir = shift;
	my $cmd = shift;	
	my $args = shift;
	# check if dir exists 
	chomp $dir;
	chomp $cmd;
	chomp $args;
	
	print "$dir \n  $cmd \n  $args  \n";  
	
	if(! -d $dir) {
		die "\n    Invalid directory $dir  Exiting:$!   \n\n";
    }		
	
	
	my $MAVEN_HOME ="";	
		
	if ($ENV{"MAVEN_HOME"} eq "") {
		$MAVEN_HOME="E:/apache-maven-3.3.9-bin/apache-maven-3.3.9";
		print "Pricing release :  env field MAVEN_HOME is not set ,  Using path $MAVEN_HOME  \n"; 		
	} else {
		$MAVEN_HOME=$ENV{"MAVEN_HOME"};
	}
	
    my @mvn_cmd = ("${MAVEN_HOME}/bin/mvn");
    push @mvn_cmd, " ${cmd} ";
    push @mvn_cmd, " ${args} ";
    push @mvn_cmd, " -f ${dir}/pom.xml ";	
	
#	my $mvn_cmd = "${MAVEN_HOME}/bin/mvn ${cmd} ${args}";
	print "Executing command:\n@mvn_cmd\n";			
	system("@mvn_cmd");
	
}




