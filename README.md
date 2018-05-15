*******************************************************************
******                                                       ******
******               INGRID   V_1.0.0.0                      ******
******                                                       ******
*******************************************************************

BotNet.
O bot é um software quando for executado vai abri uma conexão com o servidor, tcp melhor, por meio dessa conexão ele recebe comando para ser executado pelo terminal, e retorna o resultado da operação.

#Como rodar

Projeto criado no maven, pode ser aberto com netbeans.

Executar arquivos:
  BotMaster-servidor
  BotSlave- bots
  
Ao commitar nao e necessario incluir a pasta target do projeto( gerado pelo maven).

##Oque foi implementado

Inicialmente foi implementado apenas o servidor mult-thread responsavel por connectar os bots.
A troca de dados entre servidor e cliente esta sendo realizada via Object.

###Oque precisa ser feito??????

Criar uma classe que contenha como atributos os comandos para serem executados.
Interface do BotMaster para listar bots conectados e enviar comandos a todos ou a bots especificos....
.....
..........
