# Ejemplos Servicios con Postman

Este documento se utiliza para demostrar con ejemplos las funcionalidades de los servicios del examen "Mareo Envios".

## GET CUSTOMER

Se utiliza la ruta: *http://localhost:8080/mareoenvios/api/customers/4*

***No se envia body***

Devuelve como respuesta:

```
{
    "firstName": "Homero ",
    "lastName": "Simpson",
    "address": "Avenida Siempre Viva  742",
    "city": "Springfield",
    "shippings": [
        {
            "state": "Entregado",
            "sendDate": "2010-04-05",
            "arriveDate": "2010-04-06",
            "priority": 1
        },
        {
            "state": "Entregado",
            "sendDate": "2017-01-15",
            "arriveDate": "2023-09-26",
            "priority": 2
        },
        {
            "state": "Cancelado",
            "sendDate": "2010-04-05",
            "arriveDate": null,
            "priority": 3
        }
    ]
}
```
![RESP_GET_CUSTOMER](ejemplos%20postman%20imagenes/image-2.png)


## GET SHIPPING

Se utiliza la ruta: *http://localhost:8080/mareoenvios/api/shippings/5*

***No se envia body***

Devuelve como respuesta:

```
{
    "customer": {
        "firstName": "Sofia",
        "lastName": "Gonzalez",
        "address": "Pellegrini 560 3 B",
        "city": "Rosario"
    },
    "state": "Entregado",
    "sendDate": "2022-03-01",
    "arriveDate": "2022-03-07",
    "priority": 2,
    "products": [
        {
            "weight": 30.0,
            "description": "Plato"
        }
    ]
}
```

![RESP_GET_SHIPPING](ejemplos%20postman%20imagenes/image-3.png)

## GET STATUS

Se utiliza la ruta: *http://localhost:8080/mareoenvios/api/status*

***No se envia body***

Devuelve como respuesta:

```
[
    {
        "id": 1,
        "state": "Cancelado"
    },
    {
        "id": 2,
        "state": "En camino"
    },
    {
        "id": 3,
        "state": "Entregado a correo"
    },
    {
        "id": 4,
        "state": "Entregado"
    },
    {
        "id": 5,
        "state": "Entregado"
    },
    {
        "id": 6,
        "state": "Entregado a correo"
    },
    {
        "id": 7,
        "state": "En camino"
    },
    {
        "id": 8,
        "state": "Inicial"
    },
    {
        "id": 9,
        "state": "Entregado"
    },
    {
        "id": 10,
        "state": "Entregado"
    },
    {
        "id": 11,
        "state": "Cancelado"
    }
]
```
![RESP_GET_STATUS](ejemplos%20postman%20imagenes/image-4.png)

## GET TOP SENT

Se utiliza la ruta: *http://localhost:8080/mareoenvios/api/reports/top-sent*

***No se envia body***

Devuelve como respuesta:

```
[
    {
        "weight": 60.0,
        "description": "Producto 1",
        "totalAmount": 48
    },
    {
        "weight": 10.0,
        "description": "Auriculares",
        "totalAmount": 25
    },
    {
        "weight": 12.0,
        "description": "Jueguete 2",
        "totalAmount": 12
    }
]
```
![RESP_GET_TOP-SENT](ejemplos%20postman%20imagenes/image-5.png)

## POST CONCURRENT TASK

Se utiliza la ruta: *http://localhost:8080/mareoenvios/api/concurrent-task*

Se envia por body:

```
{
    "shippings": [
        {
            "shipping-id": 1,
            "time-start-in-seg": 10,
            "next-state": true
        },
        {
            "shipping-id": 1,
            "time-start-in-seg": 5,
            "next-state": true
        },
        {
            "shipping-id": 2,
            "time-start-in-seg": 4,
            "next-state": true
        },
                {
            "shipping-id": 1,
            "time-start-in-seg": 8,
            "next-state": false
        },
                {
            "shipping-id": 2,
            "time-start-in-seg": 12,
            "next-state": true
        }
    ]
  } 
```

Devuelve como respuesta:

![RESP_POST_TASKS](ejemplos%20postman%20imagenes/image.png)

Si vamos a los logs de la aplicacion:

![LOGS_POST_TASKS](ejemplos%20postman%20imagenes/image-1.png)

---

*Nota: se realizo la prueba de ejecutar las tareas y en mitad del proceso se solicito status de los envios con GET STATUS y no interfirio en el proceso de las tareas, ya que las tareas se estan ejecutando en hilos diferentes*