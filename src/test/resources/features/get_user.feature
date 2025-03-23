Feature: Buscar usuario
  Usuario deve ser encontrado com sucesso

  Scenario: Buscando usuario por email
    Given email passado via url corretamente
    When disparar a requisicao get
    Then devo encontrar dados de usuario e ter status "200"