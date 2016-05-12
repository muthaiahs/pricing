#!/usr/bin/perl -w
use Pithub;
use Data::Dumper;

use Git::PurePerl;
use IO::File;
use Path::Class;

use Cwd;
use FileHandle;
use English;
use Git::Repository;

# chdir to the script directory
my $thisScript = $0;
my $scriptDir = getcwd;

print "scriptDir  :     $scriptDir  \n"; 

my $url="https://github.com/muthaiahs/pricing.git";
my $dir="E:/pricing_git/pricing";
my $user="muthaiahs";
my $repo="pricing";

Git::Repository->run( clone => $url => $dir );
 $r = Git::Repository->new( work_tree => $dir );





exit;  


  





  

