Feature: carito de compras
  usuario realizara compras

  Scenario: Escenario 1 Agregar y Comprar articulos de carrito
    Given el usuario inicia sesión
    When el usuario selecciona el artículo
    Then se finaliza compra

  Scenario: Escenario 2 el usuario quita artículo del carrito
    Given el usuario regresa a página principal
    When el usuario selecciona el articulo a eliminar
    Then los articulos cambian de estado

  Scenario: Escenario 3 Inicio de sesión con usuario bloqueado
    Given se ingresan datos de usuario bloqueado
    When se selecciona login
    Then el sistema muestra mensaje de error


