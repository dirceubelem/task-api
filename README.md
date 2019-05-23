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
<h1>Dados do Usuário logado</h1>
<br/>
<strong>GET</strong><br/>
/v1/account/me<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>
<h1>Cadastro de usuário</h1>
<br/>
<strong>POST</strong><br/>
/v1/account<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
<br/>
Body<br/>
{"name":"Fulano","email": "user@user.com", "password": "pass"}<br/><br/>
<h1>Esqueci minha senha</h1>
<br/>
<strong>POST</strong><br/>
/v1/account/forgot<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
<br/>
Body<br/>
{"email": "user@user.com"}<br/>
<h1>Novo de Projeto</h1>
<br/>
<strong>POST</strong><br/>
/v1/project<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>
Body<br/>
{"name": "NOME DO PROJETO"}<br/>
<h1>Lista de Projetos</h1>
<br/>
<strong>GET</strong><br/>
/v1/project<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>
<h1>Obter um Projeto</h1>
<br/>
<strong>GET</strong><br/>
/v1/project/{ID DO PROJETO}<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>
<h1>Alterar um Projeto</h1>
<br/>
<strong>POST</strong><br/>
/v1/project<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>
Body<br/>
{"id": "ID DO PROJETO", "name": "NOME DO PROJETO", "active": true}<br/>
