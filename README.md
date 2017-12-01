# CodingBoyScout

The Boy Scout Rule can be summarized as: Leave your code better than you found it. Boy Scouts have a rule regarding camping, that they should leave the campground cleaner than they found it.

This repository contains a pre-commit hook that utilizes checkstyle to determine the amount of added/removed warnings in a commit.

The pre-commit hook works in the following two modes:
local mode: number of added/removed warnings are stored in a local file
server mode: commits and the number of added/removed warnings per git user are stored on a server via REST and the provided Spring Boot application.
