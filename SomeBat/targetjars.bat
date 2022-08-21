set start=%time%
set "ccpth=E:\IDEAWorkSpace\feature_branch_lcn5.RELEASE\cfps"
for /r %ccpth% %%i in (*.jar) do (
   echo "begin xcopy %%i"
   xcopy "%%i"  "E:\IDEAWorkSpace\feature_branch_lcn5.RELEASE/target"
)

set end=%time%

set /a h1=%start:~0,2%
set /a m1=%start:~3,2%
set /a s1=%start:~6,2%
set /a h2=%end:~0,2%
set /a m2=%end:~3,2%
set /a s2=%end:~6,2%

if %h2% LSS %h1% set /a h2=%h2%+24

set /a ts1=%h1%*3600+%m1%*60+%s1%
set /a ts2=%h2%*3600+%m2%*60+%s2%
set /a ts=%ts2%-%ts1%

set /a h=%ts%/3600
set /a m=(%ts%-%h%*3600)/60
set /a s=%ts%%%60

echo %h%:%m%:%s% used
pause