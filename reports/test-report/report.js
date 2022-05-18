$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/somosmakers.feature");
formatter.feature({
  "name": "Prueba técnica – Automatización",
  "description": "  La característica consta de dos escenarios para automatizar",
  "keyword": "Feature"
});
formatter.background({
  "name": "Definir los parametros previos",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "los siguientes parametros de entrada",
  "rows": [
    {},
    {},
    {},
    {},
    {}
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.losSiguientesParametrosDeEntrada(io.cucumber.datatable.DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "debo insertar los registros en la base datos",
  "keyword": "When "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.deboInsertarLosRegistrosEnLaBaseDatos()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Primer escenario - Calculos y cruce informacion",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "los parametros de entrada previos",
  "keyword": "Given "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.losParametrosDeEntradaPrevios()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "calcular el valor total asi [nominal * precio]",
  "keyword": "When "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.calcularElValorTotalAsiNominalPrecio()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "si el portafolio es \"OBL-RIESGO\" se debe calcular el valor total asi [(\"nominal\" * \"precio\") + 2000]",
  "keyword": "But "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.siElPortafolioEsSeDebeCalcularElValorTotalAsi(java.lang.String,java.lang.String,java.lang.String,int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "validar que el valor total calculado sea igual al campo total de la base de datos",
  "keyword": "And "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.validarQueElValorTotalCalculadoSeaIgualAlCampoTotalDeLaBaseDeDatos()"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "Definir los parametros previos",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "los siguientes parametros de entrada",
  "rows": [
    {},
    {},
    {},
    {},
    {}
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.losSiguientesParametrosDeEntrada(io.cucumber.datatable.DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "debo insertar los registros en la base datos",
  "keyword": "When "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.deboInsertarLosRegistrosEnLaBaseDatos()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Segundo escenario - Manejo de selenium",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "la siguiente url \"https://somosmakers.co/\"",
  "keyword": "Given "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.laSiguienteUrl(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "hacemos clic en el enlace de \"CONTACTO\"",
  "keyword": "When "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.hacemosClicEnElEnlaceDe(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "capturar el numero de celular de servicio al cliente",
  "keyword": "But "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.capturarElNumeroDeCelularDeServicioAlCliente()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "llenar el formulario \"dejanos un mensaje\" y en el campo mensaje adjuntar el numero de celular capturado",
  "keyword": "And "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.llenarElFormularioYEnElCampoMensajeAdjuntarElNumeroDeCelularCapturado(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "antes de hacer clic en el boton \"enviar mensaje\" tomar un pantallazo con la informacion diligenciada",
  "keyword": "And "
});
formatter.match({
  "location": "com.test.somosmakers.StepDefinitions.antesDeHacerClicEnElBotonTomarUnPantallazoConLaInformacionDiligenciada(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
});