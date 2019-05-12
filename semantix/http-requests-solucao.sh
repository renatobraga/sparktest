#!/bin/bash
echo "Baixando arquivos de log..."
wget ftp://ita.ee.lbl.gov/traces/NASA_access_log_Jul95.gz
wget ftp://ita.ee.lbl.gov/traces/NASA_access_log_Aug95.gz
echo "Descompactando arquivos"
gzip -d *.gz
echo "Criando diretorio para armazenar logs no hdfs..."
hdfs dfs -mkdir /semantix/log
echo "Adicionando arquivos no hdfs..."
hdfs dfs -put NASA_access_log_* /semantix/log/.
echo "Executando codigo scala..."
spark-shell -i http-requests-solucao.scala
