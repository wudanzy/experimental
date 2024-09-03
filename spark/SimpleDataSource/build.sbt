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
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.19"

val sparkVersion = "3.2.0"

lazy val root = (project in file("."))
  .settings(
    name := "SimpleDataSource"
  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.scalatest" %% "scalatest" % sparkVersion % "test",
  "org.apache.spark" %% "spark-core" % sparkVersion % "test" classifier "tests",
  "org.apache.spark" %% "spark-catalyst" % sparkVersion % "test" classifier "tests",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "test" classifier "tests"
)