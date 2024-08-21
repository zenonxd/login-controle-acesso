<p align="center">
  <img src="https://img.shields.io/static/v1?label=SpringProfessional - Dev Superior&message=Login e Controle de Acesso&color=8257E5&labelColor=000000" alt="Testes automatizados na prática com Spring Boot" />
</p>


# Tópicos

- [Ideia geral de login e controle de acesso](#ideia-geral-de-login-e-controle-de-acesso)
- [Visão geral OAuth2](#visão-geral-oauth2)
- [Login, credenciais e JWT](#login-credenciais-e-jwt)
  - [Aplicativo](#aplicativo)
  - [Usuário](#usuário)
- [JWT](#jwt)

<hr>

- []()
<hr>


# Objetivo

Aqui, implementaremos esquema de login e controle de acesso. O usuário irá informar as suas credenciais, receberá um token
de acesso e com esse token ele vai acessar os recursos que estão protegidos no sistema.

Teremos controle por perfil de usuário. Ou seja, alguns end points serão liberados para o público, outros para clientes
e outros para administratores (que poderá fazer tudo).

## Requisitos projeto

Todas as premissas e o sumário com o que deve ser feito está no "Documento de Requesitos DSCommerce.pdf".
Como é algo específico do curso, não colocarei o link, mas você pode adquirir no site [devsuperior]().

## UML

![img.png](img.png)

## Ideia geral de login e controle de acesso

Em algum momento o usuário vai fazer o seu login, ou seja, irá informar as suas credenciais (email ou id/senha).

Depois disso, o sistema retorno para ou usuário um token de acesso.

![img_1.png](img_1.png)

Um token de acesso é um pedaço de uma string que será usado para autorizar esse usuário a acessar alguns recursos.
Isso é muito usado hoje em dia, pois atualmente o backend é separado fisicamente do frontend, então é uma boa estratégia
manter o token (um controle de acesso), sem precisar armazenar estado dentro do banco de dados (quem está logado ou não).

❗Caso não seja aprovado, como pode ser visto na imagem acima, será retornado um erro 401.
<hr>
O token de acesso sendo concedido, a nossa aplicação enviará as próximas requisições usando este token.

![img_2.png](img_2.png)

A partir dessa tentativa de conectar a um recurso específico (cliente ou adm), o backend irá analisar se o token é valido,
o perfil do usuário. Após isso, ele irá ou devolver o recurso para o usuário, ou:

- um erro 401 - quando o token informado for inválido, ou seja, dado corrompido ou tempo expirado.
- um erro 403 - usuário tenta informar o recurso, o token É VALIDO, mas o perfil do usuário não pode acessar o recurso,
(como uma área administrativa, por exemplo).

## Visão geral OAuth2

https://oauth.net/2/

Uma forma de implementar o login (controle de acesso) aos recursos da aplicação.

OAuth2 é um protocolo padrão para autorização. **Mas como funciona na prática?**

![img_3.png](img_3.png)

Na imagem acima podemos observar um cliente acessando o sistema backend informando as credenciais e recebendo um token
para login.

Depois, informarmos nosso token + URL do recurso onde recebemos de volta o mesmo.

Mas é bacana observar que o OAuth2 separa essas etapas, delegando-as para servidores específicos. Um responsável para
autorização e outro para verificar se o usuário tem direito a acessar tais recursos.

Cada coisa dessa imagem acima (app e user credentials, signature token, claims e mais), veremos nos tópicos abaixo.

## Login, credenciais e JWT

Aqui falaremos do login (informar credenciais) para pegar o token.

![img_4.png](img_4.png)

Repare na imagem que temos dois tipos de credenciais: de usuário e aplicativo.

### Aplicativo

![img_5.png](img_5.png)

Os dados (id e secret do cliente), são passados na requisição através da request header (o cabeçalho, chamado 
authorization).

Base64 é basicamente algo para "bagunçar", os dados, para nada ficar exposto na requisição.
<hr>

### Usuário

![img_6.png](img_6.png)

Na parte de usuário, precisamos passar na requisição o "grant_type", ou seja, o tipo de autenticação. Neste caso, é
password.
<hr>

Tudo isso nos dará a resposta, ou seja: **O token**!

![img_7.png](img_7.png)

O token não tem um formato específico, a gente coloca o que for melhor para a aplicação. Nós utilizaremos o formato
JWT, um formato padrão das indústrias, veja abaixo:

## JWT

[Introdução a JWT - Nélio Alves](https://www.youtube.com/watch?v=n1z9lx4ymPM)

