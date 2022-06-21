# Todo App

Descrição: Aplicação para o gerenciamento de projetos e as tarefas envolvidas nesses projetos.
Objetivo: Resolver a questão de organização de tarefas de um ou vários projetos.

Entidades:

* Projeto;
	- Nome
	- Descrição
	- Data de Criação
	- Data de Atualização
* Tarefa;
	- Nome
	- Descrição
	- Status
	- Observações
	- Prazo
	- Data de Criação
	- Data de Atualização

Requisitos:

* Permitir criar Projetos;
* Permitir alterar Projetos;
* Permitir deletar Projetos;

* Permitir criar Tarefas;
* Permitir alterar Tarefas;
* Permitir deletar Tarefas;

Regras de negócio:

* O sistema não contará com um sistema de Login;
* Não haverá o conceito de usuário;
* Toda tarefa deve pertencer a um Projeto;

Tecnologias utilizadas:

* Java
* MySql

Progresso do desenvolvimento:

* Criação do banco de dados; (OK)
* Criação da classe modelo; (OK)
* Criação da conexão com o banco de dados; (OK)
* Criação da interface gráfica; (OK)
	- Criação da tela inicial; (OK)
		- Criação da célula de prazo; (OK)
		- Criação da célula de editar/excluir; (OK)
	- Criação da tela de cadastro do projeto; (OK)
	- Criação da tela de cadastro de tarefas; (OK)
* Implementação de eventos; (OK)
	-  Validação; (OK)
* Configurações finais; (OK)
* Teste de aplicação; (OK)

Próximos desafios:

* Implementar o conceito de Tags para que se possa atribuir Tags à Tarefa;
* Implementar o conceito de Conta e Usuário. Para que mais usuários possam utilizar a aplicação;
* Construção de uma tela de Login (feita após a criação dos conceitos de conta e usuário). Para que usuários possam informar Login e senha;
* Corrigir o campo "Excluir" que deleta a tarefa ao clicar em qualquer lugar da célula, e não do ícone de exclusão;
* Permitir que o usuário altere as informações de uma Tarefa já criada;

## <i>Tela Final com Projetos e Tarefas</i>
![image](https://user-images.githubusercontent.com/86898523/174441702-e84daac5-22d5-4910-8e0a-bf76a736b1fa.png)

## <i>Tela Inicial sem Tarefas</i>
![image](https://user-images.githubusercontent.com/86898523/174443221-9f7c717c-cba8-4ac4-8554-0eb1ef1cc839.png)

## <i>Tela de Adicionar Projetos</i>
![image](https://user-images.githubusercontent.com/86898523/173421105-8aa17be9-7304-49fb-b75c-5f39747f93d7.png)

## <i>Tela de Adicionar Tarefas</i>
![image](https://user-images.githubusercontent.com/86898523/173421646-9f11c3f7-9a69-446c-b9f4-0b1c823655f7.png)
