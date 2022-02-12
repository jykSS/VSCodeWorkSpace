@echo off
chcp 65001
git switch master
for  %%s in (*.jar) do ( 
   echo %%~ns 
   git add %%s
   git commit -m "提交%%~ns"
   git checkout %%~ns
   git push origin %%~ns
   git switch master
)