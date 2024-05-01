# Clínica-App

Esse repositório mantém o <em>backend</em> e <em>frontend</em> da aplicação de clínica médica.

<img src="https://github.com/maylajamile/github-images/blob/d6ed626bbf1025712ed69424f0d203408797a1b0/image12.png" alt="Imagem página de login" width="500px" >
<img src="https://github.com/maylajamile/github-images/blob/d6ed626bbf1025712ed69424f0d203408797a1b0/image13.png" alt="Imagem de página de cadastro">
<img src="https://github.com/maylajamile/github-images/blob/d6ed626bbf1025712ed69424f0d203408797a1b0/image14.png" alt="Imagem de página de listagem">

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

Para iniciar esse serviço basta usar os comandos <strong>npm install vite</strong> e <strong>npm run dev</strong> dentro da pasta.

## O que foi utilizado no frontend?

- Vite + React.js
- Armazenamento de token no localStorage
	
