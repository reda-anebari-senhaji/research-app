@echo off
cd /d "C:\Users\pc\Downloads\research-app"
echo Starting SIMPLIFIED Spring Boot application...
echo.
echo Stopping any running Java processes...
taskkill /F /IM java.exe 2>nul
echo.
echo Starting with bypass profile on PORT 8081...
java -jar target\research-app-0.0.1-SNAPSHOT.jar --spring.profiles.active=bypass --server.port=8081
pause
