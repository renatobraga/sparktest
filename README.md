# Resolução do desafio engenheiro de dados
```diff
+ Todos os códigos desenvolvidos então disponíveis no diretório semantix
```
### Qual o objetivo do comando cache em Spark?
Armazenar um RDD na memória para poder ser reutilizado por todo o processo.
### O mesmo código implementado em Spark é normalmente mais rápido que a implementação equivalente em MapReduce. Por quê?
O mapreduce necessita gravar seus resultados parciais em disco causando um maior overhead de rede e IO. O spark tem a capacidade de armazenar os resultados intermediarios em memória (cache), reduzindo a leitura e escrita em disco, otimizando o uso do processador, normalmente sendo mais rapido que o mesmo processo implementado em mapreduce.
### Qual é a função do SparkContext?
O SparkContext configura os serviços disponíveis e disponibiliza o acesso ao ambiente distribuido do Spark. Após criamos um sparkContext podemos utiliza-lo para criar RDDs, acessar todos os serviços e executar as tarefas desejadas.
### Explique com suas palavras  o que é Resilient Distributed Datasets (RDD).
Um RDD é a abstração de um conjunto de dados do Spark, esse conjunto é distrubido pelo cluster e possui tolerancia a falhas, ou seja, o spark é capaz de reprocessar partições ausentes ou danificadas devido a falha de um ou mais nós do cluster.
### GroupByKey é menos eficiente que reduceByKey em grandes dataset. Por quê?
O reduceByKey combina o resultado de cada partição e gera apenas uma saida para cada key para enviar para o proximo worker, já o groupByKey envia cada key para seu respectivo worker (para que o worker faça a combinação total). Em grandes datasets o groupByKey vai ser menos eficiente pois ele causa maior trafego de rede e aumenta a probabilidade de sobre carregar a memória do worker que irá fazer a combinação gerando IO em disco.
### Explique o que o código Scala abaixo faz.
------------
val textFile = sc.textFile("hdfs://...") <br>
val counts = textFile.flatMap(line => line.split(" ")) <br>
                .map(word => (word, 1)) <br>
                .reduceByKey(_ + _) <br>
counts.saveAsTextFile("hdfs://...") <br>
------------
```diff
+ Script de teste do código disponível em https://github.com/renatobraga/sparktest/tree/master/semantix/scala-solucao.sh
```
O código lê um arquivo no hdfs, executa uma tarefa de contagem de palavras presente no arquivo e grava o resultado da contagem em um arquivo no hdfs.

## HTTP requests to the NASA Kennedy Space Center WWW server
------------
```diff
+ Script com a resolucao disponível em https://github.com/renatobraga/sparktest/tree/master/semantix/http-requests-solucao.sh
```
#### 1. Número de hosts únicos.
137979 hosts únicos
#### 2. O total de erros 404.
20901 erros 404
#### 3. Os 5 URLs que mais causaram erro 404.
1. hoohoo.ncsa.uiuc.edu com 251 ocorrências. <br>
2. piweba3y.prodigy.com com 157 ocorrências. <br>
3. jbiagioni.npt.nuwc.navy.mil com 132 ocorrências. <br>
4. piweba1y.prodigy.com com 114 ocorrências. <br>
5. www-d4.proxy.aol.com com 91 ocorrências. <br>

#### 4. Quantidade de erros 404 por dia.
|Dia        |Quantidade de erros 404 |
|-----------|------------------------|
|01/Jul/1995|316|
|02/Jul/1995|291|
|03/Jul/1995|474|
|04/Jul/1995|359|
|05/Jul/1995|497|
|06/Jul/1995|640|
|07/Jul/1995|570|
|08/Jul/1995|302|
|09/Jul/1995|348|
|10/Jul/1995|398|
|11/Jul/1995|471|
|12/Jul/1995|471|
|13/Jul/1995|532|
|14/Jul/1995|413|
|15/Jul/1995|254|
|16/Jul/1995|257|
|17/Jul/1995|406|
|18/Jul/1995|465|
|19/Jul/1995|639|
|20/Jul/1995|428|
|21/Jul/1995|334|
|22/Jul/1995|192|
|23/Jul/1995|233|
|24/Jul/1995|328|
|25/Jul/1995|461|
|26/Jul/1995|336|
|27/Jul/1995|336|
|28/Jul/1995|94 |
|01/Aug/1995|243|
|03/Aug/1995|304|
|04/Aug/1995|346|
|05/Aug/1995|236|
|06/Aug/1995|373|
|07/Aug/1995|537|
|08/Aug/1995|391|
|09/Aug/1995|279|
|10/Aug/1995|315|
|11/Aug/1995|263|
|12/Aug/1995|196|
|13/Aug/1995|216|
|14/Aug/1995|287|
|15/Aug/1995|327|
|16/Aug/1995|259|
|17/Aug/1995|271|
|18/Aug/1995|256|
|19/Aug/1995|209|
|20/Aug/1995|312|
|21/Aug/1995|305|
|22/Aug/1995|288|
|23/Aug/1995|345|
|24/Aug/1995|420|
|25/Aug/1995|415|
|26/Aug/1995|366|
|27/Aug/1995|370|
|28/Aug/1995|410|
|29/Aug/1995|420|
|30/Aug/1995|571|
|31/Aug/1995|526|

#### 5. O total de bytes retornados.
65.524.314.915 bytes
