# ASIGNACIONES

-TODO añadir descripcion de la app

## Componentes y estructura

-TODO kotlin, docker, liquibase,

### Spring data

-TODO

### Liquibase

-TODO

## Desarrollo local

-TODO

## Despliegue

-TODO

## How-tos

-TODO

### Primeros pasos para crear el ui (react.s o next.js)

El frontend sera un proyecto react administrado via gradle en la carpeta ui

Instrucciones para iniciar el package.json e inportar librerias:

En linea de comandos en el directorio raiz del proyecto ejecutar:

_para instalar node (local, sandboxed)_

     gradlew.bat :npmSetup

_para inicializar el package.json_

     gradlew.bat :npmInit

_para instalar react y react dom_

    gradlew.bat :installReactAndReactDom

_para instalar next_

    gradlew.bat :installNextD

### Como ejecutar la aplicacion (para desarrollar o testar localmente localmente)

Para ejecutar la aplicacion es necesario disponer de acceso a un servidor postgres o mysql

La manera mas sencilla es ejecutar postgres mediante docker compose

Para instalar Docker en windows https://docs.docker.com/desktop/install/windows-install/

Para instalar Docker en mac https://docs.docker.com/desktop/install/mac-install/

Para instalar Docker en linux https://docs.docker.com/desktop/install/linux-install/

Las siguientes instrucciones son para entorno windows:

Una vez instalado abre una ventana del terminal, navega hasta la carpeta docker y dentro de esa carpeta ejecuta el comando

    docker compose up -d

esto iniciara el servidor postgres

entonces vuelve a la carpeta raiz

     cd ..

entonces iniciar laapplicacion ejecuta

     gradlew.bat :bootRun

previamente asegurate que la variable JAVA_HOME apunta a la carpeta donde se instalo java (se necesita java 17 o superior)

La definicion de la api se puede descargar de http://localhost:8080/api-docs en formato JSON y de http://localhost:8080/api-docs.yaml en formato YAML

La api se puede invocar mediate swagger-ui en http://localhost:8080/swagger-ui/index.html






### REFERENCE LINKS

http://exploringjs.com/

https://reactjs.org/docs/introducing-jsx.html

