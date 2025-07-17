# Script PowerShell pour tester l'API Research App

# Test de l'endpoint public
Write-Host "=== Test de l'endpoint public ===" -ForegroundColor Green
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/test/all" -Method GET -ErrorAction Stop
    Write-Host "Succès: $response" -ForegroundColor Green
} catch {
    Write-Host "Erreur: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "Status: $($_.Exception.Response.StatusCode)" -ForegroundColor Red
}

Write-Host "`n=== Test d'inscription ===" -ForegroundColor Green
# Test d'inscription
$signupData = @{
    username = "testuser"
    password = "password123"
    email = "test@example.com"
    nom = "Test"
    prenom = "User"
    specialite = "Informatique"
    role = "CHERCHEUR"
} | ConvertTo-Json

$headers = @{
    "Content-Type" = "application/json"
}

try {
    $signupResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/auth/signup" -Method POST -Body $signupData -Headers $headers -ErrorAction Stop
    Write-Host "Inscription réussie: $signupResponse" -ForegroundColor Green
} catch {
    Write-Host "Erreur inscription: $($_.Exception.Message)" -ForegroundColor Red
    if ($_.Exception.Response) {
        $statusCode = $_.Exception.Response.StatusCode
        Write-Host "Status Code: $statusCode" -ForegroundColor Red
    }
}

Write-Host "`n=== Test de connexion ===" -ForegroundColor Green
# Test de connexion
$loginData = @{
    username = "testuser"
    password = "password123"
} | ConvertTo-Json

try {
    $loginResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/auth/signin" -Method POST -Body $loginData -Headers $headers -ErrorAction Stop
    Write-Host "Connexion réussie!" -ForegroundColor Green
    Write-Host "Token: $($loginResponse.accessToken)" -ForegroundColor Yellow
    
    # Test d'un endpoint protégé
    Write-Host "`n=== Test endpoint protégé ===" -ForegroundColor Green
    $authHeaders = @{
        "Authorization" = "Bearer $($loginResponse.accessToken)"
    }
    
    $protectedResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/test/chercheur" -Method GET -Headers $authHeaders -ErrorAction Stop
    Write-Host "Accès protégé réussi: $protectedResponse" -ForegroundColor Green
    
} catch {
    Write-Host "Erreur connexion: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n=== Tests terminés ===" -ForegroundColor Blue
