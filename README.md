# task-api

<strong>URL: https://api.fluo.work/v1/</strong>
<br/>

<h1>Autenticação</h1>
<br/>
<strong>POST</strong><br/>
account/auth<br/>
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
account/me<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>

<h1>Cadastro de usuário</h1>
<br/>
<strong>POST</strong><br/>
account<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
<br/>
Body<br/>

```json 
{"name":"Fulano","email": "user@user.com", "password": "pass"}
```

<h1>Esqueci minha senha</h1>
<br/>
<strong>POST</strong><br/>
account/forgot<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
<br/>
Body<br/>
{"email": "user@user.com"}<br/>

<h1>Novo Projeto</h1>
<br/>
<strong>POST</strong><br/>
project<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>
Body<br/>
{"name": "NOME DO PROJETO"}<br/>

<h1>Lista de Projetos</h1>
<br/>
<strong>GET</strong><br/>
project<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>

<h1>Obter um Projeto</h1>
<br/>
<strong>GET</strong><br/>
project/{ID DO PROJETO}<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>

<h1>Alterar um Projeto</h1>
<br/>
<strong>PUT</strong><br/>
project<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>
Body<br/>
{"id": "ID DO PROJETO", "name": "NOME DO PROJETO", "active": true}<br/>

<h1>Nova Tarefa</h1>
<br/>
<strong>POST</strong><br/>
task<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>
Body<br/>
{"name":"nome","idProject":"id do projeto","idAccountTo":"id da conta","description":"descricao da tarefa","tags":"tags"}<br/>

<h1>Tarefas de Um Projeto</h1>
<br/>
<strong>GET</strong><br/>
project/{id do projeto}/tasks<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>

<h1>Alterar Tarefa</h1>
<br/>
<strong>PUT</strong><br/>
task<br/>
Header:<br/>
Content-Type: application-json; charset=utf-8<br/>
token: [USER TOKEN]<br/><br/>
Body<br/>
{"id": "813704983274091827", "name":"nome","idProject":"id do projeto","idAccountTo":"id da conta","description":"descricao da tarefa","tags":"tags"}<br/>

