# CV-Server

## The Problem

The need to host an online version of a CV/Resume with editing and version control.

## The Solution

A Self-hosted server that links to a private repository with versioned CV information securely held.
This allows for personal, private ownership of cv data, and for tailored CVs presenting only relevant information for
the applied role.

## Installation

### Preparation

You will need a git repo with your personal cv data in it, the application uses the directory name as part of the URL,
so the data repo should have the following structure:

`https://github.com/[USER_NAME]/[REPO_NAME]/[FOLDER_NAME]/cv.json`

cv.json can be a copy of sample.json at the start, just to verify it is all working OK.

Make a note of the USER_NAME and REPO_NAME as these are environment variables required by the app.

You will also need a personal access token from github if you want to access a private repo with your CV data.

- Currently this is under Settings, Developer Settings, Personal Access Tokens.
- You only need to select the top tick box, "Full control of private repositories"
- Make a note/copy the key, as it will only be displayed once.

The server is containerised with a Dockerfile, and this is the easiest way to install locally or deploy:

### Build the container

#### Linux

```bash
sudo docker build -t cvserver .
```

### Run the container

#### Linux

```bash
sudo docker run -e "PERSONAL_ACCESS_TOKEN=github-personal-access-token-here" -e "USER_NAME=github-user-name" -e "REPO_NAME=cv-data-repo-here" cvserver
```

### Access the Sample CV

navigate to [`http://localhost:8080/sample`](`http://localhost:8080/sample`) if all is working a sample cv should be
displayed.

### Access your personal cv

navigate to `http://localhost:8080/cv/[FOLDER_NAME]`

## Acknowledgements

- svg favicon available [here](https://vectr.com/design/editor/ed06544c-ae0a-406f-b71b-72a20a44613f) (Massive thanks to
  the excellent [Vectr](https://vectr.com/about.html)).