# Clínica-App

Esse repositório mantém o <em>backend</em> e <em>frontend</em> da aplicação de clínica médica.

<img src="https://cdn.discordapp.com/attachments/1221951787318378607/1234400694312632330/image.png?ex=663098bb&is=662f473b&hm=7ac8e170e1756b81de1800ad3a29d4f917327536a8b2075d6c2971b02136887c&" width="500px" >
<img src="https://cdn.discordapp.com/attachments/1221951787318378607/1234400918607499345/image.png?ex=663098f1&is=662f4771&hm=220c36f355d1e4082ec0f98936331e7612ecb5d77ba2a1f0348640b206fdd082&">
<img src="https://cdn.discordapp.com/attachments/1221951787318378607/1234400981509476402/image.png?ex=66309900&is=662f4780&hm=2ce469f5171fa5b3c2b3dde77a98a08efbcd252d4821b0104a05a5fefdffe125&">

## Endpoints

<table>
<caption><strong>Urls da aplicação:</strong></caption>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Descrição</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>http://localhost:8080/auth/login</td>
      <td>Autenticar o usuário</td>
    </tr>
    <tr>
      <td>http://localhost:8080/clinica/cadastrar/paciente</td>
      <td>Cadastrar um novo paciente</td>
    </tr>
    <tr>
      <td>http://localhost:8080/clinica/listar/pacientes</td>
      <td>Listar todos os pacientes</td>
    </tr>
    <tr>
      <td>http://localhost:8080/clinica/listar/enfermeiros</td>
      <td>Listar todos os enfermeiros</td>
    </tr>
  </tbody>
</table>

## Backend

Para autenticação foram criados 2 tipos de usuários: MEDICO e ENFERMEIRO. Com as seguintes credenciais:
~~~json
{
	"login" : "fulano",
	"senha" : "teste123"
}

{
	"login" : "ciclano",
	"senha" : "teste123"
}
~~~

<em>Esses usuários foram criados hard-coded no banco de dados.</em>

Com o usuário fulano (role MEDICO), é possível visualizar a lista de pacientes e enfermeiros, além de pode cadastrar um novo paciente. Já com o usuário ciclano (role ENFERMEIRO) é possível apenas ver lista de pacientes e cadastrar um novo paciente.

Para iniciar esse serviço basta executar a classe <strong>ClinicaApiApplication</strong>

## O que foi utilizado no backend?

- Spring Framework
- H2 Database
- Auntenticação com JWT e OAuth2

## FrontEnd:

É possível efetuar o login com uma das credenciais, podendo visualizar a lista de usuarios, sejam pacientes ou enfermeiros e cadastrar um paciente.

Para iniciar esse serviço basta usar o comando <strong>npm run dev</strong> dentro da pasta.

## O que foi utilizado no frontend?

- Vite + React.js
- Armazenamento de token no localStorage
	
