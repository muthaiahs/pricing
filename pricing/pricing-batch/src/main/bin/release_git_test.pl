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
my $dir="E:/pricing_git/pricing";


# my $git = Git::Repository->new(work_tree => "$dir",);

# or init our own repository first
Git::Repository->run( init => $dir ,  dir => $dir ,  git_binary => 'C:/Users/tomart/AppData/Local/Programs/Git/' );
# my $r = Git::Repository->new( work_tree => $dir );
 
# or clone from a URL first
# Git::Repository->run( clone => $url, $dir, ... );
 
my $local_dir = Git::Repository->run( clone => $url => $dir )  or die "Connection Error: $DBI::errstr\n";
# my $r = Git::Repository->new( work_tree => $dir );

exit;  


  




