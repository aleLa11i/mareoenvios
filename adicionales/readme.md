# Readme Mareo Envios

Lalli, Alesandro  
Rosario, Argentina - Septiembre 2023  

## Proyecto para Examen Tecnico "Mareo Envios" para Empresa "Nacion Servicios"

### Tecnologias utilizadas:
* Backend: Java 17, maven, springboot, hibernate, criteria, mapstruct, lombok
* Base de datos: MySQL8
* Documentacion: Swagger
* Readme: Markdown

### Dificultades:
* No se realizo dockerizacion
* No se realizaron Test unitarios

### Descripcion:  

La aplicacion realizada se basa en los requerimientos enviados para el examen tecnico, consta de 5 servicios ( existe un 6to servicio pero es a modo practico para simular la API de la empresa mareo envios ). Los servicios se encuentran detallados en los swaggers.  

La aplicacion consta de una API Rest que permite obtener HTTP Requests y hacer su devolucion como Responses. Se trabajo el manejo de Excepciones y validaciones de datos entrantes de las requests. Utiliza la tecnologia Springboot siguiendo el patron de diseño Service - BO - Repository ( tambien se suele utilizar la nomenclatura Controller en lugar de Service y DAO en lugar de repository ). Se utiliza mapstruct para los mapeos de entidad a DTO. Hibernate para el mapeo de entidad de base de datos a POJOs. Criteria para consultas a mySQL. Maven para control de dependencias.  

La aplicacion se trabajo con hilos para aceptar tareas varias y que puedan ejecutarse al mismo tiempo, y en caso de ser para el mismo id, se trabajo con la tecnologia ExecuteService para crear una cola de tareas. 

---

### Informacion importante: 

    Se dejan 2 dump de base de datos uno posee tanto los drop como los insert y el otro solo los drop table sin data, para que la aplicacion funcione correctamente es recomendable que las tablas tengan data. 

***Adicionales:
	Como se nombra anteriormente se creo el servicio MareoEnviosService para simular la aplicacion de Mareo Servicios, este es un PATCH que recibe la transicion y segun el estado, simula ejecutar una tarea con un tiempo determinado.***

### Para levantar la aplicacion:
* Clonar repo en ruta local
* Abrir proyecto como proyecto spring boot en IDE ( en mi caso utilizo IntelliJ )
* Descargar todas las dependencias desde maven
* Ejecutar mvn clean install
* Levantar proyecto
* Para probar las APIs pegue en postman, insomnia o cualquier plataforma que desee utilizando: http://localhost:8080/mareoenvios/api/ como base

---

### Dudas o consultas:

📞 +54 9 341 349-3683  
📩 alesandro.lalli@gmail.com

