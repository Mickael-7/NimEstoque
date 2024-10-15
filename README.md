# NimEstoque
Este projeto implementa um sistema simples de gerenciamento de pedidos e estoque utilizando o padrão Observer para monitorar as mudanças de estoque, o padrão Factory para a criação de diferentes tipos de pedidos (Normal e Expresso), e o Singleton para garantir que o estoque e o repositório de pedidos sejam instâncias únicas no sistema.
# Funcionalidades
1. Cadastrar Produto: Permite ao usuário registrar um novo produto no estoque informando nome, preço e quantidade.

2. Criar Pedido: O usuário pode criar um pedido Normal ou Expresso. O sistema atualiza automaticamente o estoque e registra o pedido no repositório.

3. Visualizar Pedidos: Exibe a lista de pedidos feitos até o momento, incluindo o nome do produto, tipo de pedido e o valor total.

4. Visualizar Estoque: Mostra a quantidade e o preço unitário dos produtos atualmente em estoque.

5. Gerar Relatório de Estoque: Gera um relatório detalhado dos produtos em estoque.

6. Log de Estoque: Monitora as mudanças no estoque (adição, remoção ou atualização de quantidade) e exibe logs.
# Padrões Utilizados
  • Observer: O GerenciadorEstoque gerencia observadores (RelatorioEstoque e LogEstoque) que são notificados sempre que há alterações no estoque.

  • Factory: Utiliza fábricas para criar diferentes tipos de pedidos. PedidoNormalFactory cria pedidos normais e PedidoExpressoFactory cria pedidos expressos.

  • Singleton: As classes Estoque e PedidoRepository são implementadas como singletons para garantir uma única instância de cada uma.
# Como Executar
1. Clone o repositório e abra o projeto em seu ambiente de desenvolvimento Java.

2. Compile e execute a classe PedidoApp.

3. Siga as instruções no terminal para navegar pelas opções do sistema.
