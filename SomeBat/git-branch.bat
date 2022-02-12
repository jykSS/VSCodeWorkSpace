@echo off
chcp 65001
set dir = D:\infosky\cfpslocal\
echo 选择主分支
git switch master
echo 暂存所有 
git add .
echo 提交所有jar包
git commit -m "提交所有jar包"
echo 推送到主分支
git push origin master
for  %%s in (*.jar) do ( 
   echo %%~ns 
   copy  .\%%s  ..\cfps\%%~ns\
   cd ../cfps/%%~ns
   ping -n 8 127.0.0.1>nul
   git switch %%~ns
   echo 开始添加变更：git add %%s 
   git add %%s
   echo git commit -m "%%s"
   git commit -m "%%s"
   echo 将变更情况提交到远程分支：git push origin %%~ns
   git push origin %%~ns
   cd /d D:\infosky\cfpslocal
   echo;
)
pause