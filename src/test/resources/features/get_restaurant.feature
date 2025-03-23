Feature: Buscar restaurante
  Restaurante deve ser encontrado com sucesso

  Scenario: Buscando restaurante por nome
    Given nome do restaurante passado via url corretamente
    When disparar a requisicao get restaurante
    Then devo encontrar dados de restaurante e ter status "200"