Rosario, Argentina - Septiembre 2023
Lalli, Alesandro

Proyecto para Examen Tecnico "Mareo Envios" para empresa "Nacion Servicios"

Tecnologias utilizadas:
	Backend: Java 17, maven, springboot, hibernate, criteria, mapstruct, lombok
	Base de datos: MySQL8
	Documentacion: Swagger

Dificultades:
	No se realizo dockerizacion
	No se realizaron Test unitarios

Las dificultades fueron por un lado algo de falta de conocimiento sumado a falta de tiempo para poder investigar y aprender. Queda pendiente a nivel personal.

La aplicacion realizada se basa en los requerimientos enviados para el examen tecnico, consta de 5 servicios ( existe un 6to servicio pero es a modo practico para simular la API de la empresa mareo envios ). Los servicios se encuentran detallados en los swaggers.
La aplicacion consta de una API Rest que permite obtener HTTP Requests y hacer su devolucion como Responses. Se trabajo el manejo de Excepciones y validaciones de datos entrantes de las requests. Utiliza la tecnologia Springboot siguiendo el patron de diseño Service - BO - Repository ( tambien se suele utilizar la nomenclatura Controller en lugar de Service y DAO en lugar de repository ). Se utiliza mapstruct para los mapeos de entidad a DTO. Hibernate para el mapeo de entidad de base de datos a POJOs. Criteria para consultas a mySQL. Maven para control de dependencias.

Se deja script de base de datos, se requiere agregar data antes de utilizar la aplicacion.

Adicionales:
	Como se nombra anteriormente se creo el servicio MareoEnviosService para simular la aplicacion de Mareo Servicios, este es un PATCH que recibe la transicion y segun el estado, simula ejecutar una tarea con un tiempo determinado.

Para levantar la aplicacion:
	- clonar repo en ruta local
	- abrir proyecto como proyecto spring boot en IDE ( en mi caso utilizo IntelliJ )
	- descargar todas las dependencias desde maven
	- ejecutar un maven clean install
	- levantar proyecto
	- para probar las APIs pegue en postman, insomnia o cualquier plataforma que desee utilizando: http://localhost:8080/mareoenvios/api/ como base

Para tener en cuenta: como se hablo via mail se me permitio cambiar los estados de la tabla shipping a ingles