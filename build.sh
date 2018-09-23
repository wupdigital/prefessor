#!/bin/bash

# Build project
./gradlew clean build sonarqube -Dsonar.login=$SONAR_API_KEY  -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=wupdigital -Dsonar.branch=$TRAVIS_BRANCH || exit 1

if [[ "$TRAVIS_REPO_SLUG" == "wupdigital/prefessor" && "$TRAVIS_PULL_REQUEST" == "false" && ("$TRAVIS_BRANCH" == "master" || "$TRAVIS_BRANCH" == support/* ) ]]; then
	# Publish the artifacts
	./gradlew deploy || exit 1
fi