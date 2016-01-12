#!/bin/bash

numberOfCores=$(/bin/grep -c 'model name' /proc/cpuinfo)

echo $numberOfCores
