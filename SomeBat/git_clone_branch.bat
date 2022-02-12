@echo off
chcp 65001
for  %%s in (*.jar) do ( 
   echo %%~ns 
   git clone -b %%~ns http://10.5.14.18:8089/beihuo/cfpslocal.git  ../cfps/%%~ns 
   echo;
)