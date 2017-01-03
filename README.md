# Deliberaciones Agora@US 16-17
[![Build Status](https://travis-ci.org/AgoraUS-G1-1617/Deliberations.svg?branch=master)](https://travis-ci.org/AgoraUS-G1-1617/Deliberations)

Deliberaciones es un subsistema de código abierto del proyecto Agora@US que introduce la funcionalidad de un foro para administrar hilos y mensajes de usuarios votantes. 
Dichos mensajes contendrán información sobre el votante que los haya realizado. Sólo los votantes válidos podrán realizar comentarios, por lo que se deberá consultar al sistema de **autenticación**.

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

Deliberaciones se integra con el subsistema [Autenticación](https://github.com/AgoraUS-G1-1617/Autentication) mediante su API para ofrecer las herramientas de foro a usuarios dados de alta.
A su vez se integra con el subsistema [Censo]()


### Instalación
Para configurar el entorno de trabajo consulta el repositorio [ConfigurationVMS](https://github.com/EGCG2/ConfigurationVMS) donde podrás encontrar el workspace a usar además de un script que autoinstala las herramientas necesarias.

Para el sistema de integración continua basado en [jenkins](https://jenkins.egc.duckdns.org) el cual ofrece construcción y despliegue se usa el repositorio [continuous-delivery-integration](https://github.com/ManuelLR/continuous-delivery-integration) donde cada equipo crea y configura sus scripts de despliegue para que automáticamente se desplieguen las aplicaciones. Para los test se usa [travis-ci](https://travis-ci.org/AgoraUS-G1-1617/Deliberations) los cuales se lanzan automáticamente tras cada commit publicado en el repositorio. Para más información consulta la siguiente tabla:

| Branch | Estado | Despliegue |
|---|:---:|:---:|
| master |  [![Build Status](https://travis-ci.org/AgoraUS-G1-1617/Deliberations.svg?branch=master)](https://travis-ci.org/AgoraUS-G1-1617/Deliberations) | __[deliberaciones.agoraus1](http://deliberaciones.agoraus1.egc.duckdns.org)__ |
| develop | [![Build Status](https://travis-ci.org/AgoraUS-G1-1617/Deliberations.svg?branch=develop)](https://travis-ci.org/AgoraUS-G1-1617/Deliberations)| __[beta.deliberaciones.agoraus1](http://beta.deliberaciones.agoraus1.egc.duckdns.org)__ |

Igualmente, mediante [travis-ci](https://travis-ci.org/AgoraUS-G1-1617/Deliberations), cuando se ponga una tag/release se adjuntará automáticamente el war para el despliegue y el sql de populación de la base de datos.

## Equipos de trabajo

### Equipo 2016/17
<img src="https://avatars3.githubusercontent.com/u/9135377?v=3&s=4000" alt="Avatar: " height="50" /> &nbsp;
[Javier García Calvo](https://github.com/jjxp)

<img src="https://avatars3.githubusercontent.com/u/12049827?v=3&s=4000" alt="Avatar: " height="50" /> &nbsp;
[Manuel Francisco López Ruiz](https://github.com/ManuelLR)

<img src="https://avatars3.githubusercontent.com/u/22616365?v=3&s=4000" alt="Avatar: " height="50" /> &nbsp;
[Bartolome Marquez Dominguez](https://github.com/barmardom)

<img src="https://avatars3.githubusercontent.com/u/8267403?v=3&s=4000" alt="Avatar: " height="50" />  &nbsp;
[Juan Ramón Rodríguez Rosado](https://github.com/juanrarodriguez18)

<img src="https://avatars3.githubusercontent.com/u/6894925?v=3&s=4000" alt="Avatar: " height="50" /> &nbsp;
[Jose Antonio Rodríguez Torres](https://github.com/josearodriguez)



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

