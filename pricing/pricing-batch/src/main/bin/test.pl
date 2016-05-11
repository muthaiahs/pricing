#!/usr/bin/perl -w
use Pithub;
use Data::Dumper;

use Git::PurePerl;
use IO::File;
use Path::Class;

use Cwd;
use FileHandle;
use English;

# chdir to the script directory
my $thisScript = $0;
my $scriptDir = getcwd;

print "scriptDir  :     $scriptDir  \n"; 

my $url="https://github.com/muthaiahs/pricing.git";
my $dir="E:/pricing_git/pricing";
my $user="muthaiahs";
my $repo="pricing";


my $p = Pithub->new;
# my $p = Pithub->new(utf8 => 0); # enable compatibility options for version 0.01029 or lower
my $result = $p->repos->get( user => 'muthaiahs', repo => 'pricing' );

# $result->content is either an arrayref or an hashref
# depending on the API call that has been made
printf "%s\n", $result->content->{html_url};     # prints https://github.com/plu/Pithub
printf "%s\n", $result->content->{clone_url};    # prints https://github.com/plu/Pithub.git

# if the result is an arrayref, you can use the result iterator
my $result = $p->repos->list( user => 'muthaiahs' );
while ( my $row = $result->next ) {
    printf "%s\n", $row->{name};
}


$p = Pithub->new(
    api_uri => 'https://github.com/muthaiahs/pricing.git'
);

print "test   :    \n";  


my $directory = 'test_git_1';
dir($directory)->rmtree;

# my $git = Git::PurePerl->init( directory => $directory );

# $git->clone( 'https://github.com/muthaiahs/pricing.git' , "pricing" );
my $git= Git::PurePerl ->clone( 'github.com', '/muthaiahs/pricing.git' )  or die "Error :  $! \n";
# $git->get_object($git->master->tree);

 
 





exit;  


  





  

