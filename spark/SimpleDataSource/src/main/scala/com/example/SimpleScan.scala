// Copyright (C) 2011-2012 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.example

import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.connector.read.{Batch, InputPartition, PartitionReader, PartitionReaderFactory, Scan}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

case class RangePartition(start: Int, end: Int) extends InputPartition

class SimpleScan extends Scan with Batch {

  override def readSchema(): StructType = {
    StructType(StructField("Name", StringType, nullable = true) :: Nil)
  }

  override def toBatch: Batch = this

  override def planInputPartitions(): Array[InputPartition] = {
    // scalastyle:off
    Array(RangePartition(0,10), RangePartition(10,20), RangePartition(20,30), RangePartition(30,40))
    // scalastyle:on
  }

  override def createReaderFactory(): PartitionReaderFactory = SimplePartitionReaderFactory

}

object SimplePartitionReaderFactory extends PartitionReaderFactory {
  override def createReader(partition: InputPartition): PartitionReader[InternalRow] = {
    val RangePartition(start, end) = partition
    new SimpleReader(start, end)
  }
}