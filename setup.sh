#!/bin/bash

ENV_CHECK=$(grep -e '=$' .env | wc -l)
if [ 0 -eq $ENV_CHECK ]; then
  export $(cat .env | xargs)
else
  echo 'Missing API key. Please update .env file appropriately.'
fi
