# Deliberaciones Agora@US 16-17

Deliberaciones es un subsistema de código abierto del proyecto Agora@US que introduce la funcionalidad de un foro para administrar hilos y mensajes de usuarios votantes. 
Dichos mensajes contendrán información sobre el votante que los haya realizado. Sólo los votantes válidos podrán realizar comentarios, por lo que se deberá consultar al sistema de autenticación.


### Tecnología
Se trata de una aplicación web J2EE que hace uso de una base de datos MySQL con las herramientas Hibernate y Spring para el mapeo de objetos.

Además, se utilizan las siguientes herramientas y frameworks:
- Maven
- JSP
- JPQL
- HTML
- CSS
- JavaScript
- Bootstrap
 
 
### Modulos relacionados

Deliberaciones se integra con el subsistema [Autenticación][https://github.com/AgoraUS-G1-1617/Autentication] mediante su API para ofrecer las herramientas de foro a usuarios dados de alta.
A su vez se integra con el subsistema [Censo][]


### Instalación
Para configurar el entorno de trabajo consulta el repositorio [ConfigurationVMS][https://github.com/EGCG2/ConfigurationVMS] donde podrás encontrar el workspace a usar además de un script que autoinstala las herramientas necesarias.

Para el sistema de integración continua el cual ofrece construcción, tests y despliegue se usa el repositorio [continuous-delivery-integration][https://github.com/ManuelLR/continuous-delivery-integration] donde cada equipo crea y configura sus scripts de despliegue para que automáticamente se desplieguen las aplicaciones. Las instancias automáticas de Deliberations puedes verlas en:
 - http://beta.deliberaciones.agoraus1.egc.duckdns.org (beta)
 - http://deliberaciones.agoraus1.egc.duckdns.org (stable)

 
## Equipos de trabajo

### Equipo 2016/17
 - [BARTOLOME MARQUEZ DOMINGUEZ][https://github.com/barmardom]
 - [JAVIER GARCIA CALVO][https://github.com/jjxp]
 - [JOSE ANTONIO RODRIGUEZ TORRES][https://github.com/josearodriguez]
 - [JUAN RAMON RODRIGUEZ ROSADO][https://github.com/juanrarodriguez18]
 - [MANUEL FRANCISCO LOPEZ RUIZ][https://github.com/ManuelLR]
 
 
### Equipo 2015/16

 - Juan García Orozco
 - Juan García-Quismondo Fernández
 - Roberto Jiménez Castillo
 - Francisco José Macías García
 - Alfredo Menéndez Charlo
 - Rubén Ramírez Vera

[entrega 1]: <https://github.com/juagarfer4/Deliberations/releases/tag/Entrega1>
[entrega 2]: <https://github.com/juagarfer4/Deliberations/releases/tag/Entrega2>
[auth]: <https://github.com/AgoraUS1516/G03>



## Acceso
 * Cuenta de usuario
   - Username: deliberations
   - Password: deliberations
