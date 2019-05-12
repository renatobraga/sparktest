val textFile = sc.textFile("hdfs:///semantix/vingadores.txt")
val counts = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
counts.saveAsTextFile("hdfs:///semantix/saida")
exit
