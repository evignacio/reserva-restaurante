Feature: Criar usuario
  Usuario deve ser criado com sucesso

  Scenario: Criando usuario
    Given todos os dados de usuario foram preenchidos corretamente
    When a requisicao de criar usuario for executada
    Then devo ter status "201"