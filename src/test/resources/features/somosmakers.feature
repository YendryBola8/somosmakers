Feature: Prueba técnica – Automatización
  La característica consta de dos escenarios para automatizar

  Background: Definir los parametros previos
    Given los siguientes parametros de entrada
    | fecha      | portafolio | nominal | precio | total     |
    | 15/05/2020 | OBL-MODER  | 123000  | 23.65  | 2908950   |
    | 15/05/2020 | OBL-MODER  | 10000   | 25.00  | 250000    |
    | 15/05/2020 | OBL-RIESGO | 1276987 | -10.20 | -13023267 |
    | 15/05/2020 | OBL-RIESGO | 123000  | 1.29   | 160670    |
    When debo insertar los registros en la base datos

  Scenario: Primer escenario - Calculos y cruce informacion
  Given los parametros de entrada previos
  When calcular el valor total asi [nominal * precio]
  But si el portafolio es "OBL-RIESGO" se debe calcular el valor total asi [("nominal" * "precio") + 2000]
  And validar que el valor total calculado sea igual al campo total de la base de datos

  Scenario: Segundo escenario - Manejo de selenium
    Given la siguiente url "https://somosmakers.co/"
    When hacemos clic en el enlace de "CONTACTO"
    But capturar el numero de celular de servicio al cliente
    And llenar el formulario "dejanos un mensaje" y en el campo mensaje adjuntar el numero de celular capturado
    And antes de hacer clic en el boton "enviar mensaje" tomar un pantallazo con la informacion diligenciada