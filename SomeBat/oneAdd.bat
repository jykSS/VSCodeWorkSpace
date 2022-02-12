@echo off
chcp 65001   
set /p declation=输入新增的jar包名称:
   git switch -c %declation%
   git rm -rf .
   echo %declation% > %declation%.txt
   git add %declation%.txt
   git commit -m "%declation%"
   git push origin %declation% 
   git clone -b %declation% http://10.5.14.18:8089/beihuo/cfpslocal.git  ../cfps/%declation% 
   git switch master