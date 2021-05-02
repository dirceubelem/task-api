# task-api

URL: https://api.fluo.work/v1/


## Autenticação
##### POST
account/auth
#### Header:
```
Content-Type: application-json; charset=utf-8
Accept: application-json; charset=utf-8
```

### Request
#### Body

```json 
{"email": "user@user.com", "password": "pass"}
```

#### Response
    
```json
{"id":"4sdokkc9bs56f1f4nh5s6l","name":"Dirceu Belém","email":"dirceu@fingermidia.com","password":null,"token":"6ph38tb2im9em1f4nh6441","picture":null,"active":true,"createdAt":"02/05/2021 18:54:01","expiredAt":"02/05/2021 18:59:09"}
```

## Dados do Usuário logado

##### GET
account/me

#### Header:
```
Content-Type: application-json; charset=utf-8
Accept: application-json; charset=utf-8
```

token: [USER TOKEN]

#### Response
```json
{"id":"4sdokkc9bs56f1f4nh5s6l","name":"Dirceu Belém","email":"dirceu@fingermidia.com","password":null,"token":"6ph38tb2im9em1f4nh6441","picture":null,"active":true,"createdAt":"02/05/2021 18:54:01","expiredAt":"02/05/2021 18:59:09"}
```

## Cadastro de usuário

##### POST
account

#### Header:
```
Content-Type: application-json; charset=utf-8
```

### Request
#### Body

```json 
{"name":"Fulano","email": "user@user.com", "password": "pass"}
```
### Response
```json
{"id":"4sdokkc9bs56f1f4nh5s6l"}
```

## Esqueci minha senha

##### POST
account/forgot
Header:
```
Content-Type: application-json; charset=utf-8
```

Body

```json 
{"email": "user@user.com"}
```

## Novo Projeto

##### POST
project
#### Header:
```
Content-Type: application-json; charset=utf-8
token: [USER TOKEN]
```

#### Body

```json 
{"name": "NOME DO PROJETO"}
```

## Lista de Projetos

##### GET
project
#### Header:
```
Content-Type: application-json; charset=utf-8
token: [USER TOKEN]
```

## Obter um Projeto

##### GET
project/{ID DO PROJETO}

#### Header:
```
Content-Type: application-json; charset=utf-8
token: [USER TOKEN]
```

## Alterar um Projeto

##### PUT
project
#### Header:
```
Content-Type: application-json; charset=utf-8
token: [USER TOKEN]
```
####Body

```json 
{"id": "ID DO PROJETO", "name": "NOME DO PROJETO", "active": true}
```

## Nova Tarefa

##### POST
task
#### Header:
```
Content-Type: application-json; charset=utf-8
token: [USER TOKEN]
```
#### Body

```json 
{"name":"nome","idProject":"id do projeto","idAccountTo":"id da conta","description":"descricao da tarefa","tags":"tags"}
```

## Tarefas de Um Projeto

##### GET
project/{id do projeto}/tasks

#### Header:

```
Content-Type: application-json; charset=utf-8
token: [USER TOKEN]
```
## Alterar Tarefa

##### PUT
task

#### Header:
```
Content-Type: application-json; charset=utf-8
token: [USER TOKEN]
```
#### Body

```json 
{"id": "813704983274091827", "name":"nome","idProject":"id do projeto","idAccountTo":"id da conta","description":"descricao da tarefa","tags":"tags"}
```
