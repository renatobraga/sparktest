#!/bin/bash
echo "Criando diretorio de leitura..."
hdfs dfs -mkdir /semantix
echo "Adicionando arquivo no hdfs..."
hdfs dfs -put vingadores.txt /semantix/vingadores.txt
echo "Executando codigo scala..."
spark-shell -i word-count.scala
echo "Resultado do processamento..."
hdfs dfs -cat /semantix/saida/part-00000
