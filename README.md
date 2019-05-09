# task-api

<strong>URL: http://api.fluo.site</strong>
<br/>
<h1>Autenticação</h1>
<br/>
<strong>POST</strong><br/>
/v1/auth<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
Accept: application-json; charset=utf-8<br/>
<br/>
Body<br/>
{"email": "user@user.com", "password": "pass"}<br/>
<br/>
<br/>
<h1>Me (Dados do Usuário logado)</h1>
<br/>
<strong>GET</strong><br/>
/v1/me<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/>
