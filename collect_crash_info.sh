#!/bin/bash

echo "================ CRASH LOGS ==========================================="
echo "--- FILES -------"
find . -name hs_err_*.log
echo "--- CONTENTS ----"
find . -name hs_err_*.log -exec cat {} \;
echo "======================================================================="
