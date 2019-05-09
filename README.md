# task-api

<strong>URL: http://api.fluo.site</strong>

<h1>Autenticação</h1>

<strong>POST</strong>
/v1/auth
Header
Content-Type: application-json; charset=utf-8
Accept: application-json; charset=utf-8

Body
{"email": "user@user.com", "password": "pass"}


<h1>Me (Dados do Usuário logado)</h1>

GET
Header
Content-Type: application-json; charset=utf-8
token: [USER TOKEN]
/v1/me
