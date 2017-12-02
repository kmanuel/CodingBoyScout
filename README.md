# CodingBoyScout

#About
The Boy Scout Rule can be summarized as: Leave your code better than you found it. Boy Scouts have a rule regarding camping, that they should leave the campground cleaner than they found it.

This repository contains a pre-commit hook that utilizes checkstyle to determine the amount of added/removed warnings in a commit.


## Getting Started

The commithook can be used in two different modes:

### Local Mode
Local mode saves metrics about the number of added/eliminated commit warnings locally.

### Server Mode
Server mode saves metrics about the numer of added/eliminated warnings per git user to a server via REST


### Prerequisites


The hooks requires 
    
[Checkstyle](http://checkstyle.sourceforge.net/)

[GIT](https://git-scm.com/)


### Installing


#### Setup common to Local and Server Mode
And the following two environment variables to be set

- CHECKSTYLE_JAR - location of the checkstyle.jar to use
- CHECKSTYLE_FILE -  location of the checkstyle.xml to use


In order to use the hook in your git project you have to copy the <em>pre-commit</em> file from the <em>commithook</em> directory  


into 

<em>.git/hooks/<em>

of the git project you want to use the hook with.


#### Additional Setup for Server Mode
TBD


## Built With
Spring Boot
