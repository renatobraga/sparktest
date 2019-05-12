// Função criada para converter string em long.
// Quando a string não for um valor numérico é retornado 0
def parseLong(a:String) : Long = 
{
  if(a.matches("^\\d+$")){
    return a.toLong
  }
  else return 0.toLong
}
// Estrutura do arquivo de Log
case class Log(host: String, time: String, request: String, status: String, bytes: String)

// Padrão utilizado na leitura do arquivo
val regex = "(^.*)( - - )(\\[.*\\]) (\".*\") (\\d+) (.+)".r

// Lê arquivos no HDFS
val inputRDD = sc.textFile("hdfs:///semantix/log/access_log_Aug95,hdfs:///semantix/log/access_log_Jul95")

// Converte para a estrutura de Log
val parsedRDD = inputRDD.map { str =>
  regex.findFirstMatchIn(str).map(m => Log(m.group(1), m.group(3), m.group(4), m.group(5), m.group(6))) match {
    case Some(l) => l
    case None => Log(null, null, null, "0", "0")
  }
}

// Contabiliza o Número​ ​de​ ​hosts​ ​únicos.
val num_host_unicos = parsedRDD.groupBy(m => m.host).count()
println("numero de hosts unicos: "+ num_host_unicos)

// Filtra registros que possuem status 404
val status_404RDD = parsedRDD.filter(_.status.toInt == 404);

// Contabiliza o​ ​total​ ​de​ ​erros​ ​404
val num_erros = status_404RDD.count()
println("Total de erros 404: "+ num_erros)

// Contabiliza numero de erros por host e ordena pela quantidade encontrada
val num_erros_host = status_404RDD.map(m => (m.host, 1)).reduceByKey(_ + _).sortBy(_._2, false)

// Imprime os​ ​5​ ​URLs​ ​que​ ​mais​ ​causaram​ ​erro​ ​404.
num_erros_host.take(5).foreach(println)

// Contabiliza a quantidade​ ​de​ ​erros​ ​404​ ​por​ ​dia.
val erros_dia = status_404RDD.map(m => (m.time.slice(1, 12), 1)).reduceByKey(_ + _)

// Imprime quantidades calculadas
erros_dia.collect.foreach(println)

// Soma o ​total​ ​de​ ​bytes​ ​retornados.
val total_bytes = parsedRDD.map(m => parseLong(m.bytes)).sum()
println("Total de bytes retornados: "+ total_bytes.toLong)

exit


