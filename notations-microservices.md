**MICROSERVICES EXAMPLE**
*INDEPENDÊNCIAS ENTRE OS SERVIÇOS*
```mermaid
graph TD;
    Cliente-->Frontend-->Gateway;
    Gateway-->MS1-->MS1BD;
    Gateway-->MS2-->MS2BD;
    Gateway-->MS3-->MS2BD;
    MS3-->MS4;
    Gateway-->MS4;
```