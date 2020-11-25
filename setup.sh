#!/bin/bash

ENV_CHECK=$(grep -e '=$' .env | wc -l)
if [ 0 -eq $ENV_CHECK ]; then
  export $(cat .env | xargs)
  [ "$1" == "start" ] && ./mvnw spring-boot:run
else
  echo 'Missing API key. Please update .env file appropriately.'
fi
