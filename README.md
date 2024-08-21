<p align="center">
  <img src="https://img.shields.io/static/v1?label=SpringProfessional - Dev Superior&message=JPA, Consultas SQL e JPQL&color=8257E5&labelColor=000000" alt="Testes automatizados na prática com Spring Boot" />
</p>


# Tópicos


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

